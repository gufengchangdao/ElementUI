package com.component.others.card;

import com.component.basic.color.ColorUtil;
import com.component.common.BoxSize;
import com.component.common.component.BaseComponent;
import org.jdesktop.swingx.border.DropShadowBorder;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * 卡片
 * <p>
 * 将信息聚合在卡片容器中展示。
 */
public class CardPanel<E> extends BaseComponent {
	/** 卡片标题 */
	private JLabel title;
	/** 标题右边的按钮组 */
	private List<JButton> buttons;
	/** 列表内容 */
	private JList<E> list;

	public CardPanel(String title, List<JButton> buttons, JList<E> list) {
		this(title, buttons, list, true);
	}

	public CardPanel(JLabel title, List<JButton> buttons, JList<E> list) {
		this(title, buttons, list, true);
	}

	/**
	 * @param title           卡片标题
	 * @param buttons         标题右边的按钮组
	 * @param list            列表内容
	 * @param isContainShadow 卡片是否包含阴影，内容是用边框来实现阴影效果的
	 */
	public CardPanel(String title, List<JButton> buttons, JList<E> list, boolean isContainShadow) {
		this(createTitleLabel(title), buttons, list, isContainShadow);
	}

	/**
	 * @param title           卡片标题，可自定义标签
	 * @param buttons         标题右边的按钮组
	 * @param list            列表内容
	 * @param isContainShadow 卡片是否包含阴影，内容是用边框来实现阴影效果的
	 */
	public CardPanel(JLabel title, List<JButton> buttons, JList<E> list, boolean isContainShadow) {
		this.title = title;
		this.buttons = buttons;
		this.list = list;

		init(isContainShadow);
	}

	public static JLabel createTitleLabel(String title) {
		JLabel label = new JLabel(title);
		label.setFont(UIManager.getFont("Label.font").deriveFont(18f));
		return label;
	}

	private void init(boolean isPaintShadow) {
		setLayout(new BorderLayout(0, 10));

		JPanel top = new JPanel();
		top.setOpaque(false);
		top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));

		JPanel topPanel = new JPanel();
		topPanel.setOpaque(false);
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		topPanel.add(title);
		topPanel.add(Box.createHorizontalGlue());
		for (JButton b : buttons) {
			topPanel.add(b);
		}
		top.add(topPanel);
		top.add(Box.createVerticalStrut(10));
		top.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ColorUtil.BORDER_LEVEL1));

		list.setOpaque(false);
		list.setBackground(null);

		add(top, BorderLayout.NORTH);
		add(list);

		setInsets(new Insets(10, 10, 10, 10));
		setOpaque(true);
		setBackground(Color.WHITE);

		// 阴影
		if (isPaintShadow) {
			setBoxSize(BoxSize.PADDING_SIZE);
			setBorder(new DropShadowBorder(ColorUtil.INFO, 7, 0.2f, 12,
					true, true, true, true));
		}
	}

	public JLabel getTitle() {
		return title;
	}

	public List<JButton> getButtons() {
		return buttons;
	}

	public JList<E> getList() {
		return list;
	}
}
