package com.component.form.input.model;

import javax.swing.*;
import java.io.Serializable;
import java.util.*;

public class InputComboBoxModel<E> extends AbstractListModel<E> implements MutableComboBoxModel<E>, Serializable {
	List<E> objects;
	E selectedObject;

	/**
	 * 构造一个空的InputComboBoxModel对象。
	 */
	public InputComboBoxModel() {
		objects = new Vector<>();
	}

	/**
	 * 构造一个用对象数组初始化的InputComboBoxModel对象。
	 *
	 * @param items 对象的数组
	 */
	public InputComboBoxModel(final E[] items) {
		objects = new LinkedList<>();

		Collections.addAll(objects, items);

		// 默认选择第一个
		if (getSize() > 0) {
			selectedObject = getElementAt(0);
		}
	}

	/**
	 * 构造一个用向量初始化的InputComboBoxModel对象。
	 *
	 * @param v 一个 E 对象
	 */
	public InputComboBoxModel(List<E> v) {
		objects = v;

		if (getSize() > 0) {
			selectedObject = getElementAt(0);
		}
	}

	// implements javax.swing.ComboBoxModel

	/**
	 * 设置所选项目的值。所选项可能为空
	 *
	 * @param anObject 组合框值或null表示没有选择。
	 * @throws ClassCastException 如果 anObject 不是 E 表示的类型会抛异常
	 */
	public void setSelectedItem(Object anObject) {
		if ((selectedObject != null && !selectedObject.equals(anObject)) || selectedObject == null && anObject != null) {
			selectedObject = (E) anObject;
			fireContentsChanged(this, -1, -1);
		}
	}

	// implements javax.swing.ComboBoxModel
	public E getSelectedItem() {
		return selectedObject;
	}

	// implements javax.swing.ListModel
	public int getSize() {
		return objects.size();
	}

	// implements javax.swing.ListModel
	public E getElementAt(int index) {
		if (index >= 0 && index < objects.size())
			return objects.get(index);
		else
			return null;
	}

	/**
	 * 返回指定对象在列表中的索引位置
	 *
	 * @param anObject 要返回索引的对象
	 * @return 表示索引位置的int，其中0是第一个位置
	 */
	public int getIndexOf(E anObject) {
		return objects.indexOf(anObject);
	}

	// implements javax.swing.MutableComboBoxModel
	public void addElement(E anObject) {
		objects.add(anObject);
		fireIntervalAdded(this, objects.size() - 1, objects.size() - 1);
		if (objects.size() == 1 && selectedObject == null && anObject != null) {
			setSelectedItem(anObject);
		}
	}

	// implements javax.swing.MutableComboBoxModel
	public void insertElementAt(E anObject, int index) {
		objects.add(index, anObject);
		fireIntervalAdded(this, index, index);
	}

	// implements javax.swing.MutableComboBoxModel
	public void removeElementAt(int index) {
		if (getElementAt(index) == selectedObject) {
			if (index == 0) {
				setSelectedItem(getSize() == 1 ? null : getElementAt(index + 1));
			} else {
				setSelectedItem(getElementAt(index - 1));
			}
		}

		objects.remove(index);

		fireIntervalRemoved(this, index, index);
	}

	// implements javax.swing.MutableComboBoxModel
	public void removeElement(Object anObject) {
		int index = objects.indexOf(anObject);
		if (index != -1) {
			removeElementAt(index);
		}
	}

	/**
	 * 清空列表
	 */
	public void removeAllElements() {
		if (objects.size() > 0) {
			int firstIndex = 0;
			int lastIndex = objects.size() - 1;
			objects.clear();
			selectedObject = null;
			fireIntervalRemoved(this, firstIndex, lastIndex);
		} else {
			selectedObject = null;
		}
	}

	/**
	 * 添加集合中出现的所有元素
	 *
	 * @param c 包含要添加的元素的集合
	 * @throws NullPointerException 如果{@code c}为空
	 */
	public void addAll(Collection<? extends E> c) {
		if (c.isEmpty()) {
			return;
		}

		int startIndex = getSize();

		objects.addAll(c);
		fireIntervalAdded(this, startIndex, getSize() - 1);
	}

	/**
	 * 从指定的索引开始，添加集合中存在的所有元素。
	 *
	 * @param index 从指定集合c插入第一个元素的索引
	 * @param c     包含要添加的元素的集合
	 * @throws ArrayIndexOutOfBoundsException 如果索引不在当前持有的元素数量的范围内
	 * @throws NullPointerException           如果c为空
	 */
	public void addAll(int index, Collection<? extends E> c) {
		if (index < 0 || index > getSize()) {
			throw new ArrayIndexOutOfBoundsException("index out of range: " +
					index);
		}

		if (c.isEmpty()) {
			return;
		}

		objects.addAll(index, c);
		fireIntervalAdded(this, index, index + c.size() - 1);
	}
}
