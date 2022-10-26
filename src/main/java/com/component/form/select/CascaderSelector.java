package com.component.form.select;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

/***
 * 级联选择器
 */
public class CascaderSelector extends JTextField {
	private ItemSelector itemSelector;

	public CascaderSelector(int columns, Map<String, Map<String, ?>> data) {
		this(columns, " ", data);
	}

	/**
	 * 创建级联选择器
	 *
	 * @param columns   输入框列数，见
	 * @param separator 选项之间的分割符
	 * @param data      选项数据，类型为Map<String,Map>，叶子节点值为null
	 * @see JTextField#JTextField(int columns)
	 */
	public CascaderSelector(int columns, String separator, Map<String, Map<String, ?>> data) {
		super(columns);

		if (data == null || data.size() == 0)
			throw new IllegalArgumentException("data不能为null并且必须存在数据");

		itemSelector = new ItemSelector(data, this, this, ItemSelector.Position.BOTTOM, separator);

		setEditable(false);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				itemSelector.showSelector();
			}
		});

	}

	public ItemSelector getItemSelector() {
		return itemSelector;
	}

	public void setItemSelector(ItemSelector itemSelector) {
		this.itemSelector = itemSelector;
	}
}

