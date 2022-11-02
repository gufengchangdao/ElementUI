package com.component.navigation.navmenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Vector;

/**
 * 多级导航列表，作为多级导航的弹窗列表
 */
public class NavList<E> extends JList<E> implements MouseMotionListener, MouseListener {
	/** 父节点 */
	private NavList<E> last;
	/** 子节点，键为索引，值为列表 */
	private Map<Integer, NavList<E>> next;
	/** 绘制列表的容器面板，一般这个面板作为玻璃窗格或放入层次面板中 */
	private JPanel container;
	/** 当前展开子节点的单元格索引值，没有子节点的话鼠标悬停该值也不会改变 */
	private int showIndex = -1;

	public NavList(ListModel<E> dataModel) {
		super(dataModel);
		init();
	}

	public NavList(E[] listData) {
		super(listData);
		init();
	}

	public NavList(Vector<? extends E> listData) {
		super(listData);
		init();
	}

	/**
	 * @param dataModel 数据模型
	 * @param last      父节点
	 * @param index     对应父列表单元格的索引值
	 * @param next      子列表，对列表单元格的映射
	 */
	public NavList(ListModel<E> dataModel, NavList<E> last, int index, Map<Integer, NavList<E>> next) {
		super(dataModel);
		setLast(last, index);
		setNext(next);

		init();
	}

	public NavList(E[] listData, NavList<E> last, int index, Map<Integer, NavList<E>> next) {
		super(listData);
		setLast(last, index);
		setNext(next);
		init();
	}

	public NavList(E[] listData, Map<Integer, NavList<E>> next) {
		super(listData);
		setNext(next);
		init();
	}

	public NavList(Vector<? extends E> listData, NavList<E> last, int index, Map<Integer, NavList<E>> next) {
		super(listData);
		setLast(last, index);
		setNext(next);
		init();
	}

	public NavList(Vector<? extends E> listData, Map<Integer, NavList<E>> next) {
		super(listData);
		setNext(next);
		init();
	}

	public NavList(NavList<E> last, int index, Map<Integer, NavList<E>> next) {
		setLast(last, index);
		setNext(next);
		init();
	}

	private void init() {
		setVisibleRowCount(Integer.MAX_VALUE);
		setLayoutOrientation(VERTICAL);

		addMouseMotionListener(this);
		addMouseListener(this);
	}

	public NavList<E> getLast() {
		return last;
	}

	public Map<Integer, NavList<E>> getNext() {
		return next;
	}

	/**
	 * 设置该导航列表的指向，但是该方法不会设置 last的next属性
	 */
	public void setLast(NavList<E> last) {
		this.last = last;
	}

	/**
	 * 设置该导航列表的指向，同时也会设置 last 的next属性
	 *
	 * @param last  上一个导航列表
	 * @param index 该列表对应上一个导航列表单元格索引值
	 */
	public void setLast(NavList<E> last, int index) {
		this.last = last;
		Map<Integer, NavList<E>> child = last.getNext();
		if (child == null) {
			child = new HashMap<>();
			last.setNext(child);
		}
		child.put(index, this);
	}

	/**
	 * 为导航项添加下一级导航，同时也会设置 next 中导航项的 last
	 *
	 * @param next 下一级导航列表
	 */
	public void setNext(Map<Integer, NavList<E>> next) {
		this.next = next;
		for (NavList<E> c : next.values()) {
			c.setLast(this);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	private int hoverIndex;

	@Override
	public void mouseMoved(MouseEvent e) {
		hoverIndex = locationToIndex(e.getPoint());
		if (hoverIndex == -1) return; //刚出去会有短暂一刻为 -1

		Map<Integer, NavList<E>> map = getNext();

		// 所有节点都没有没有子结点
		if (map == null) return;

		NavList<E> child = map.get(hoverIndex);

		// 鼠标悬停到其他节点上
		if (hoverIndex != showIndex && showIndex != -1) {
			NavList<E> oldList = map.get(showIndex);
			removeAllList(container, oldList);
			showIndex = -1;
			return;
		}

		// 该节点没有子节点
		if (child == null) return;

		Rectangle bounds = getCellBounds(hoverIndex, hoverIndex);
		Dimension childSize = child.getPreferredSize();

		child.setBounds(getX() + bounds.width + 2, getY() + bounds.y, childSize.width, childSize.height);
		container.add(child);
		container.repaint(child.getBounds());
		showIndex = hoverIndex;
	}

	public JPanel getContainer() {
		return container;
	}

	public void setContainer(JPanel container) {
		this.container = container;
	}

	/**
	 * 寻找给定节点的根节点
	 *
	 * @param node 给定节点
	 * @param <E>  数据类型
	 * @return 根节点
	 */
	public static <E> NavList<E> findRoot(NavList<E> node) {
		NavList<E> root = node;
		while (root.getLast() != null)
			root = root.getLast();
		return root;
	}

	/**
	 * 从容器中移除给定节点及其子节点并对容器重绘，因为调用该方法不必再调用 repaint()
	 *
	 * @param container 节点所在容器
	 * @param root      要移除节点树根节点
	 */
	public static void removeAllList(JPanel container, NavList<?> root) {
		container.remove(root);
		container.repaint(root.getBounds());
		if (root.getNext() == null) return;
		for (NavList<?> c : root.getNext().values()) {
			removeAllList(container, c);
		}
	}

	/**
	 * 移除所给节点及其子节点选项
	 */
	public static void removeChildListSelected(NavList<?> root) {
		root.setSelectedValue(null, false);
		if (root.next == null) return;
		for (NavList<?> v : root.next.values()) {
			removeChildListSelected(v);
		}
	}

	/**
	 * 判断当前鼠标是否悬停在该节点或其子节点上
	 *
	 * @param root 节点树根节点
	 * @return 如果鼠标悬停在节点或其子节点则返回 true
	 */
	public static boolean isHoverList(NavList<?> root) {
		if (root.getHoverIndex() != -1) return true;
		if (root.next == null) return false;
		for (NavList<?> c : root.next.values()) {
			if (isHoverList(c)) return true;
		}
		return false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// 移除其他节点
		// removeAllList(container, findRoot(this));
		// 移除兄弟节点的子节点选择、移除父节点的其他子节点选择
		// removeOtherListSelected(last, this);

		// 获取根节点和选择路径
		NavList<E> root = this;
		LinkedList<ListSelect<E>> path = new LinkedList<>();
		path.addFirst(new ListSelect<>(root, root.getSelectedIndex()));
		while (root.last != null) {
			root = root.last;
			path.addFirst(new ListSelect<>(root, getIndex(root)));
		}

		// 遍历所有节点
		LinkedList<NavList<E>> q = new LinkedList<>();
		q.add(root);
		int layer = 0;
		while (q.size() != 0) { //层次遍历
			int len = q.size();
			for (int i = 0; i < len; i++) {
				// 处理该节点
				NavList<E> l = q.removeFirst();
				// 从容器中移除
				container.remove(l);
				container.repaint(l.getBounds());
				// 设置所选索引
				// 未选中
				if (layer + 1 > path.size()) l.setSelectedValue(null, false);

				if (l.next != null)
					q.addAll(l.next.values());
			}
			layer++;
		}
	}

	private static Integer getIndex(NavList<?> list) {
		if (list.last == null) return null;
		for (Map.Entry<Integer, ? extends NavList<?>> entry : list.last.next.entrySet()) {
			if (entry.getValue() == list) return entry.getKey();
		}
		//不应该会出现这种问题
		throw new RuntimeException("传入list的 last 中不包含该list");
	}

	private static class ListSelect<E> {
		NavList<E> parent;
		Integer selected;

		public ListSelect(NavList<E> parent, Integer selected) {
			this.parent = parent;
			this.selected = selected;
		}

		@Override
		public String toString() {
			return "ListSelect{" +
					"parent=" + parent +
					", selected=" + selected +
					'}';
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		ListModel<E> model = getModel();
		for (int i = 0, len = model.getSize(); i < len; i++) {
			builder.append(model.getElementAt(i)).append(", ");
		}
		return builder.toString();
	}

	public int getHoverIndex() {
		return hoverIndex;
	}

	public void setHoverIndex(int hoverIndex) {
		this.hoverIndex = hoverIndex;
	}
}
