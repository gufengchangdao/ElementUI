package com.component.navigation.navmenu;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 导航面板，控制着多个多级导航的绘制
 */
public class NavPanel<E> extends JPanel {
	// private List<NavBuilder<E>> list = new ArrayList<>();
	private Map<JComponent, NavBuilder<E>> map = new HashMap<>();
	private int popupMethod;
	private Rectangle rectangle;

	public NavPanel(List<? extends JComponent> components, List<NavList<E>> rootLists, int popupMethod) {
		this(components, rootLists, popupMethod, NavBuilder.DEFAULT_RECTANGLE);
	}

	/**
	 * @param components  组件列表
	 * @param rootLists   与组件对应的导航数据列表，要求元素个数与组件列表保持一致
	 * @param popupMethod 导航列表弹出的事件，可选 MouseEvent.MOUSE_CLICKED，MouseEvent.MOUSE_ENTERED
	 * @param rectangle   相对位置和单元格大小
	 */
	public NavPanel(List<? extends JComponent> components, List<NavList<E>> rootLists, int popupMethod, Rectangle rectangle) {
		super(null);
		setOpaque(false);
		this.popupMethod = popupMethod;

		if (rootLists != null) {
			Iterator<NavList<E>> lit = rootLists.iterator();
			for (JComponent c : components) {
				map.put(c, new NavBuilder<>(this, c, lit.next(), popupMethod, rectangle));
			}
		} else {
			for (JComponent c : components) {
				map.put(c, new NavBuilder<>(this, c, null, popupMethod, rectangle));
			}
		}
	}

	/**
	 * 为给与组件添加导航
	 *
	 * @param c        要添加导航的组件
	 * @param rootList 导航数据
	 */
	public void addNav(JComponent c, NavList<E> rootList) {
		map.put(c, new NavBuilder<>(this, c, rootList, popupMethod));
	}

	public NavBuilder<E> getBuilder(JComponent c) {
		return map.get(c);
	}

	/**
	 * 移除所给组件的导航
	 *
	 * @param c 要移除导航的组件
	 */
	public void removeNav(JComponent c) {
		Iterator<NavBuilder<E>> it = map.values().iterator();
		while (it.hasNext()) {
			NavBuilder<E> builder = it.next();
			if (c == builder.getC()) {
				NavList.removeAllList(this, builder.getRootList());
				it.remove();
				return;
			}
		}
	}
}
