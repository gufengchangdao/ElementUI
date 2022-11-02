package com.component.data.badge;

import com.component.common.template.C2PLPGroup;
import com.component.data.label.badge.BadgeLabel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用层次面板实现的标记
 * <p>
 * 支持操作：标记增删改查
 */
public class BadgePanelGroup extends C2PLPGroup {
	/** 标记左边界距组件右边界重叠长度。默认为 6 */
	private int gap = 6;
	/** 要添加标记的组件列表 */
	private List<JComponent> components;
	/** 标记列表，索引值和标记都对应 components 中的组件 */
	private List<BadgeLabel> labels;

	/**
	 * @param panel      需要添加标记的组件所在面板
	 * @param components 需要添加标记的组件列表，需要组件已经添加到面板中
	 * @param labels     对应 components 中组件的标记列表
	 * @param gap        标记左边界距组件右边界重叠长度。如果为null则使用默认值 6
	 */
	public BadgePanelGroup(JPanel panel, List<JComponent> components, List<BadgeLabel> labels,
	                       Integer gap) {
		super(panel);
		if (gap != null) this.gap = gap;
		this.components = new ArrayList<>(components);
		this.labels = new ArrayList<>(labels);
		init();
	}

	/**
	 * 添加标记组件并设置大小，该操作需要获取需添加标记组件的坐标，因为得在调用 pack() 后调用
	 */
	public void setAllBadgeLocation() {
		JLayeredPane layeredPane = getLayeredPane();
		for (int i = 0; i < components.size(); i++) {
			JComponent c = components.get(i);
			BadgeLabel l = labels.get(i);
			Dimension ls = l.getPreferredSize();
			Dimension cs = c.getPreferredSize();
			l.setBounds(c.getX() + cs.width - this.gap,
					c.getY() - ls.height / 2, ls.width, ls.height);
			layeredPane.add(l, (Integer) (i + 1));
		}
	}

	/**
	 * 为面板中组件添加标记
	 *
	 * @param c 面板中已有组件
	 * @param l 标记
	 */
	public void addBadge(JComponent c, BadgeLabel l) {
		components.add(c);
		labels.add(l);
		JLayeredPane layeredPane = getLayeredPane();
		Dimension ls = l.getPreferredSize();
		Dimension cs = c.getPreferredSize();
		Point cl = c.getLocation();
		l.setBounds(new Rectangle(cl.x + cs.width - this.gap,
				cl.y - ls.height / 2, ls.width, ls.height));
		layeredPane.add(l, (Integer) components.size()); //让新添加的标记覆盖在已存在标记上面
	}

	/**
	 * 为面板中组件移除标记
	 *
	 * @param c 面板中添加了标记的组件
	 * @return 移除的标记。可用此对象的位置信息进行重绘
	 */
	public BadgeLabel removeBadge(JComponent c) {
		int i = components.indexOf(c);
		if (i == -1) return null;
		components.remove(i);
		BadgeLabel delLabel = labels.get(i);
		labels.remove(i);
		getLayeredPane().remove(delLabel);
		return delLabel;
	}

	/**
	 * 移除所有标记
	 */
	public void removeAllBadge() {
		getLayeredPane().removeAll();
		components.clear();
		labels.clear();
	}

	/**
	 * 根据面板中组件获取其对应的标记
	 *
	 * @param component 面板中已经添加了标记的组件
	 * @return 该组件对应的标记，如果该组件没有添加标记则返回 null
	 */
	public BadgeLabel getLabel(JComponent component) {
		int i = components.indexOf(component);
		if (i == -1) return null;
		return labels.get(i);
	}

	/**
	 * 重绘给定标记组件所在区域，在新增标记或移除标记后调用该方法来使用容器对对应区域进行重绘。
	 * 新增或移除标记后不应该调用层次面板的repaint()方法，因为你不知道标记是否有部分溢出在层次面板的外面，
	 * 这时候需要调用容器的repaint()
	 *
	 * @param container 不会被标记溢出的容器对象。如果嫌麻烦可以直接传内容面板。像frame.getContentPane()
	 * @param label     被添加或移除的标记对象
	 */
	public void repaint(Component container, JComponent label) {
		Dimension size = label.getPreferredSize();
		Point point = SwingUtilities.convertPoint(getLayeredPane(),
				label.getX(), label.getY(), container);

		container.repaint(point.x, point.y, size.width, size.height);
	}
}