package com.component.others.collapse;

import com.component.basic.color.ColorUtil;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.svg.icon.regular.CaretDownSvg;
import com.component.svg.icon.regular.CaretUpSvg;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static java.lang.Math.max;

/**
 * 手风琴子面板
 */
public class AccordionContentPanel extends JPanel implements MouseListener {
	/** 标题 */
	private final String title;
	/** 标题的label */
	private final JLabel label;
	/** 可被收起的内容面板 */
	private final JPanel panel;
	/** 图标类型，是否为填充图标 */
	private boolean iconFill;
	public static final RadianceIcon rIcon1 = CaretDownSvg.of(16, 16);
	public static final RadianceIcon rIcon2 = CaretUpSvg.of(16, 16);
	public static final RadianceIcon fIcon1 = com.component.svg.icon.fill.CaretDownSvg.of(16, 16);
	public static final RadianceIcon fIcon2 = com.component.svg.icon.fill.CaretUpSvg.of(16, 16);
	/** 默认图标色 */
	public static final Color ICON_COLOR = ColorUtil.PRIMARY;

	static {
		// 设置图标默认颜色
		RadianceIcon.ColorFilter filter = color -> ICON_COLOR;
		rIcon1.setColorFilter(filter);
		rIcon2.setColorFilter(filter);
		fIcon1.setColorFilter(filter);
		fIcon2.setColorFilter(filter);
	}

	protected AccordionContentPanel(String title, JPanel panel, Color titleBG) {
		this(title, panel, titleBG, titleBG, ICON_COLOR, true);
	}

	protected AccordionContentPanel(String title, JPanel panel, Color titleBeginBG, Color titleEndBG) {
		this(title, panel, titleBeginBG, titleEndBG, ICON_COLOR, true);
	}

	/**
	 * @param title        标题
	 * @param panel        可被收起的内容面板
	 * @param titleBeginBG 标题背景渐变色开始颜色
	 * @param titleEndBG   标题背景渐变色结束颜色
	 * @param iconColor    图标颜色
	 * @param iconFill     图标类型，是否为填充图标
	 */
	protected AccordionContentPanel(String title, JPanel panel, Color titleBeginBG, Color titleEndBG,
	                                Color iconColor, boolean iconFill) {
		super(new BorderLayout());
		this.title = title;
		this.panel = panel;
		this.iconFill = iconFill;

		if (iconColor != null) {
			RadianceIcon.ColorFilter filter = color -> iconColor;
			// 设置图标颜色
			rIcon1.setColorFilter(filter);
			rIcon2.setColorFilter(filter);
			fIcon1.setColorFilter(filter);
			fIcon2.setColorFilter(filter);
		}

		label = new JLabel(title, iconFill ? fIcon1 : rIcon1, JLabel.LEFT) {
			@Override
			protected void paintComponent(Graphics g) {
				// 绘制渐变背景
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setPaint(new GradientPaint(50f, 0f, titleBeginBG, getWidth(), getHeight(), titleEndBG));
				g2.fillRect(0, 0, getWidth(), getHeight());
				g2.dispose();
				super.paintComponent(g);
			}
		};
		label.addMouseListener(this);
		label.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 2));
		add(label, BorderLayout.NORTH);

		add(panel);
	}

	/**
	 * 创建默认亮色的JPanel，可直接在此面板上布局、添加组件
	 */
	public static JPanel getDefaultPanel() {
		JPanel p = new JPanel();
		p.setVisible(false);
		p.setOpaque(true);
		p.setBackground(new Color(0xF0_F0_FF));
		Border outBorder = BorderFactory.createMatteBorder(0, 2, 2, 2, Color.WHITE);
		Border inBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		Border border = BorderFactory.createCompoundBorder(outBorder, inBorder);
		p.setBorder(border);
		return p;
	}

	@Override
	public Dimension getPreferredSize() {
		Dimension d = label.getPreferredSize();
		if (panel.isVisible()) {
			Dimension s = panel.getPreferredSize();
			d.width = max(d.width, s.width);
			d.height += s.height;
		}
		return d;
	}

	@Override
	public Dimension getMaximumSize() {
		Dimension d = getPreferredSize();
		d.width = Short.MAX_VALUE;
		return d;
	}

	public String getTitle() {
		return title;
	}

	public JLabel getLabel() {
		return label;
	}

	public JPanel getPanel() {
		return panel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		panel.setVisible(!panel.isVisible());
		label.setIcon(panel.isVisible() ? (iconFill ? fIcon2 : rIcon2) : (iconFill ? fIcon1 : rIcon1));
		revalidate();
		panel.scrollRectToVisible(panel.getBounds());
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
