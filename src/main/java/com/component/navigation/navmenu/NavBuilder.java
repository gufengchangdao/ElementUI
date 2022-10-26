package com.component.navigation.navmenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

/**
 * 控制导航的弹窗
 */
public class NavBuilder<E> implements MouseListener {
	/** 有多级导航的导航项 */
	private JComponent c;
	/** 根节点 */
	private NavList<E> rootList;
	/** 子列表弹出方式 */
	private int popupMethod = MouseEvent.MOUSE_CLICKED;
	/** 绘制列表的容器面板，一般这个面板作为玻璃窗格或放入层次面板中 */
	private JPanel container;

	/** 鼠标是否悬停在组件 c 上 */
	private boolean isEnter;
	public static final Rectangle DEFAULT_RECTANGLE = new Rectangle(0, 2, 0, 0);
	/**
	 * 相对位置和单元格大小
	 * 以组件c左下角为坐标原点，一级列表的相对坐标，默认是组件c正下方 2 像素位置，即(x=0,y=2)
	 * 默认是预设大小，可以指定宽度而不知道高度(不指定就填 0)
	 */
	private Rectangle rectangle = DEFAULT_RECTANGLE;

	/**
	 * @see #NavBuilder(JPanel, JComponent, NavList, int)
	 */
	public NavBuilder(JPanel container, JComponent c, NavList<E> rootList) {
		this.container = container;
		this.c = c;
		this.rootList = rootList;
		init();
	}

	/**
	 * @param container   绘制列表的容器面板
	 * @param c           要添加导航列表的组件
	 * @param rootList    导航数据
	 * @param popupMethod 导航列表弹出的事件，可选 MouseEvent.MOUSE_CLICKED，MouseEvent.MOUSE_ENTERED
	 */
	public NavBuilder(JPanel container, JComponent c, NavList<E> rootList, int popupMethod) {
		this.container = container;
		this.c = c;
		this.rootList = rootList;
		this.popupMethod = popupMethod;
		init();
	}

	/**
	 * @param container   绘制列表的容器面板
	 * @param c           要添加导航列表的组件
	 * @param rootList    导航数据
	 * @param popupMethod 导航列表弹出的事件，可选 MouseEvent.MOUSE_CLICKED，MouseEvent.MOUSE_ENTERED
	 * @param rectangle   相对位置和大小
	 */
	public NavBuilder(JPanel container, JComponent c, NavList<E> rootList, int popupMethod, Rectangle rectangle) {
		this.container = container;
		this.c = c;
		this.rootList = rootList;
		this.popupMethod = popupMethod;
		this.rectangle = rectangle;
		init();
	}

	private void init() {
		// 弹窗方式
		c.addMouseListener(this);

		if (rootList != null)
			initList(rootList);
	}

	/** 递归方式设置导航列表的容器对象 */
	private void initList(NavList<E> list) {
		// 填充container属性
		list.setContainer(container);
		// 设置大小
		if (rectangle.width > 0) list.setFixedCellWidth(rectangle.width);
		if (rectangle.height > 0) list.setFixedCellHeight(rectangle.height);

		if (list.getNext() == null) return;
		for (NavList<E> c : list.getNext().values()) {
			initList(c);
		}
	}

	/**
	 * 展示一级导航
	 */
	public void showList() {
		Dimension s = c.getPreferredSize();
		Point l;
		// 玻璃窗格下坐标原点是标题栏左上角了，需要对坐标进行转换
		JRootPane rootPane = c.getRootPane();
		if (rootPane == null) return; //窗口还没显示
		if (rootPane.getGlassPane() == container) {
			l = SwingUtilities.convertPoint(c.getParent(), c.getLocation(), rootPane);
		} else {
			l = c.getLocation();
		}

		int x = l.x + rectangle.x;
		int y = l.y + s.height + rectangle.y;

		Dimension size = rootList.getPreferredSize();
		rootList.setBounds(x, y, size.width, size.height);
		container.add(rootList);
		container.repaint(rootList.getBounds());
	}

	/**
	 * 隐藏所有导航
	 */
	private void hideList() {
		NavList.removeAllList(container, rootList);
		container.repaint(rootList.getBounds());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (popupMethod != MouseEvent.MOUSE_CLICKED) return;
		if (rootList.getParent() != null) hideList();
		else showList();
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (popupMethod != MouseEvent.MOUSE_ENTERED) return;
		if (!isEnter) {
			isEnter = true;
			showList();
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		isEnter = false;
		// 延迟关闭
		Timer timer = new Timer(1000, ex -> {
			if (NavList.isHoverList(rootList)) return;
			hideList();
		});
		timer.setRepeats(false);
		timer.start();
	}

	public E getSelectedValue() {
		return getSelectedValue(0);
	}

	public E getSelectedValue(int layer) {
		LinkedList<NavList<E>> queue = new LinkedList<>();
		queue.add(rootList);

		while (layer-- > 0) {
			for (int i = 0, len = queue.size(); i < len; i++) {
				NavList<E> node = queue.removeFirst();
				if (node.getNext() != null)
					queue.addAll(node.getNext().values());
			}
		}
		if (queue.size() == 0)
			throw new IllegalArgumentException("layer超出导航层级最大数");
		for (NavList<E> l : queue) {
			E v = l.getSelectedValue();
			if (v != null) return v;
		}
		return null; //没有选择任何一个单元格
	}

	public JComponent getC() {
		return c;
	}

	public NavList<E> getRootList() {
		return rootList;
	}

	public int getPopupMethod() {
		return popupMethod;
	}

	public JPanel getContainer() {
		return container;
	}

	public boolean isEnter() {
		return isEnter;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
}
