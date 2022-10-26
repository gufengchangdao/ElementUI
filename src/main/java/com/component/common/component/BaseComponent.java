package com.component.common.component;

import com.component.common.BoxSize;
import com.component.util.UIUtil;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.abs;

/**
 * 自定义组件的抽象实现，提供一个规范建议自定义组件都实现该类
 * <p>
 * <p>
 * 该类支持的操作有
 * <ul>
 *     <li>支持设置边框值</li>
 *     <li>支持设置不透明度，默认不透明度为 1</li>
 *     <li>圆角背景绘制，需要组件为非不透明并且设置了背景色</li>
 * </ul>
 */
public class BaseComponent extends JComponent {
	/** 内边距，如果没有调用 {@link #setInsets(Insets)} ，则调用父类的insets，否则就获取该对象 */
	private Insets insets;
	/** 圆角 */
	private int radius = 0;
	private float opacity = 1f;
	private BufferedImage image;
	private BoxSize boxSize = BoxSize.BORDER_SIZE;
	// 只要保证子组件都是非不透明的，就可以使其重绘的时候使容器也进行重绘
	// static {
	// 	RepaintManager.setCurrentManager(new RepaintManager() {
	// 		// 需要重写重绘管理器方法，在子组件重绘时递归找到 TranslucentComponent 后使其也重绘
	// 		@Override
	// 		public void addDirtyRegion(JComponent c, int x, int y, int w, int h) {
	// 			Component cc = c, parent = c;
	// 			while (parent != null) {
	// 				if (parent instanceof TranslucentPanel p) {
	// 					super.addDirtyRegion(p, cc.getX(), cc.getY(), cc.getWidth(), cc.getHeight());
	// 				}
	// 				cc = parent;
	// 				parent = parent.getParent();
	// 			}
	// 			super.addDirtyRegion(c, x, y, w, h);
	// 		}
	// 	});
	// }

	public BaseComponent() {
	}

	public BaseComponent(int radius) {
		this.radius = radius;
	}

	public BaseComponent(Insets insets, int radius) {
		this.insets = insets;
		this.radius = radius;
	}

	public BaseComponent(Insets insets, int radius, BoxSize boxSize) {
		this.insets = insets;
		this.radius = radius;
		this.boxSize = boxSize;
	}

	/**
	 * 可以用于将单个组件半透明
	 *
	 * @param c       要半透明的组件
	 * @param opacity 不透明度
	 */
	public BaseComponent(JComponent c, float opacity) {
		this.opacity = opacity;
		setLayout(new GridLayout(1, 1));
		add(c);
	}

	public void setOpacity(float opacity) {
		if (isOpaque() && abs(opacity - 1) > Float.MIN_VALUE) setOpaque(false);
		this.opacity = Math.max(0.0f, Math.min(1.0f, opacity));
		repaint(); //方便使用动画的属性设置器
	}

	public float getOpacity() {
		return opacity;
	}

	private void updateImage() {
		int w = Math.min(1, getWidth());
		int h = Math.min(1, getHeight());
		if (image == null || image.getWidth() != w || image.getHeight() != h) {
			image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		}
		Graphics2D g = image.createGraphics();
		g.setColor(new Color(0, 0, 0, 0));
		g.setComposite(AlphaComposite.SrcOver);
		g.fillRect(0, 0, w, h);
		g.dispose();
	}

	@Override
	protected void paintComponent(Graphics gr) {

		Graphics2D g2 = (Graphics2D) gr;
		// 背景
		if (getBackground() != null && isOpaque()) {
			Object[] oldRender = UIUtil.setRenderingHints(gr);
			g2.setColor(getBackground());

			if (boxSize == BoxSize.BORDER_SIZE) {
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
			} else if (boxSize == BoxSize.PADDING_SIZE) {
				Border border = getBorder();
				if (border != null) {
					Insets borderInsets = border.getBorderInsets(this);
					g2.fillRoundRect(borderInsets.left, borderInsets.top,
							getWidth() - borderInsets.left - borderInsets.right,
							getHeight() - borderInsets.top - borderInsets.bottom, radius, radius);
				}
			} else if (boxSize == BoxSize.CONTENT_SIZE) {
				Insets i = getInsets();
				g2.fillRoundRect(i.left, i.top,
						getWidth() - i.left - i.right,
						getHeight() - i.top - i.bottom, radius, radius);
			}
			// g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
			UIUtil.resetRenderingHints(g2, oldRender);
		}

		// 不透明度
		if (abs(opacity - 1) < Float.MIN_VALUE) {
			super.paintComponent(g2);
		} else {
			updateImage();
			Graphics2D imageGraphics = image.createGraphics();
			super.paintComponent(imageGraphics);
			imageGraphics.dispose();

			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
			g2.drawImage(image, 0, 0, null);
		}
	}

	/**
	 * 获取内边距，如果没有调用 {@link #setInsets(Insets)} ，则调用父类的insets，否则就获取设置的内边距
	 */
	@Override
	public Insets getInsets() {
		if (insets == null) return super.getInsets();
		return insets;
	}

	public void setInsets(Insets insets) {
		this.insets = insets;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public BoxSize getBoxSize() {
		return boxSize;
	}

	public void setBoxSize(BoxSize boxSize) {
		this.boxSize = boxSize;
	}
}
