package com.component.common.component;

import com.component.util.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * 圆角组件，用于绘制圆角的组件。自定义组件继承该类实现自己的圆角组件，或者直接传入已有组件为组件添加圆角
 * <p>
 * 建议：
 * <ul>
 *     <li>建议传入静态的组件，如果是JTextField、JButton这样不断重绘的组件会使裁剪无法生效的</li>
 *     <li>建议不要阻止边框的绘制，clip 具有锯齿，可以通过边框来覆盖边缘的锯齿</li>
 * </ul>
 */
public class RoundComponent extends BaseComponent {
	private int arcSize;
	private JComponent component;
	private boolean isPaintedBorder = true;
	private Color borderColor;
	private Color background;

	public RoundComponent() {
		this(null, 0, null);
	}

	public RoundComponent(int arcSize) {
		this(null, arcSize, null);
	}

	public RoundComponent(Color borderColor, int arcSize) {
		this(borderColor, arcSize, null);
	}

	public RoundComponent(JComponent component) {
		this(null, 0, component);
	}

	/**
	 * @param borderColor 边框颜色
	 * @param arcSize     圆角大小，如果需要圆角最大，请设置为 -1
	 * @param component   应用圆角的组件，如果为 null 则不进行任何操作
	 */
	public RoundComponent(Color borderColor, int arcSize, JComponent component) {
		this.borderColor = borderColor;
		this.arcSize = arcSize;
		this.component = component;
		init();
	}

	private void init() throws RuntimeException {
		if (this.component != null) {
			// 如果是InputField这样会不断重绘的组件应该设置与边框的间距，否则会使裁剪无效
			// 设置流布局可以解决这个问题，但是双重边框看起来挺怪的
			// setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
			setLayout(new GridLayout(1, 1));
			add(component);
		}
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		int w = getWidth();
		int h = getHeight();
		if (arcSize > 0 || arcSize == -1)
			g2.setClip(new RoundRectangle2D.Float(1, 1, w - 2, h - 2,
					arcSize == -1 ? h : arcSize, arcSize == -1 ? h : arcSize));

		// 背景
		if (background != null) {
			g2.setColor(background);
			g2.fillRect(0, 0, w, h);
		}

		super.paint(g2);

		if (isPaintedBorder && (arcSize > 0 || arcSize == -1)) {
			// 绘制边框的时候取消掉裁剪，防止边框也被裁剪了
			Object[] oldRender = UIUtil.setRenderingHints(g2);
			g2.setClip(null);
			g2.setColor(borderColor);
			g2.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2,
					arcSize == -1 ? h : arcSize, arcSize == -1 ? h : arcSize);
			UIUtil.resetRenderingHints(g2, oldRender);
		}
	}

	@Override
	public void setPreferredSize(Dimension preferredSize) {
		super.setPreferredSize(preferredSize);
	}

	@Override
	public Dimension getPreferredSize() {
		// Dimension size = super.getPreferredSize();
		// size.width -= 2;
		// size.height -= 2;
		// return size; //这个值会使传入的标签字显示不完全
		return super.getPreferredSize();
	}

	public int getArcSize() {
		return arcSize;
	}

	public JComponent getComponent() {
		return component;
	}

	public boolean isPaintedBorder() {
		return isPaintedBorder;
	}

	public void setPaintedBorder(boolean paintedBorder) {
		isPaintedBorder = paintedBorder;
	}

	@Override
	public Color getBackground() {
		return background;
	}

	@Override
	public void setBackground(Color background) {
		this.background = background;
	}

	public void setArcSize(int arcSize) {
		this.arcSize = arcSize;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}
}
