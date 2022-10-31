package com.component.data.table.renderer;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 文本换行渲染器，允许单元格内容换行，并且支持多列共用一个渲染器
 */
public class TextAreaCellRenderer implements TableCellRenderer {
	private final JTextArea renderer = new JTextArea();
	/** 表格行最大高度， 元素是每行高度情况，List<Integer> 是该行每列高度 */
	private final List<List<Integer>> rowAndCellHeights = new ArrayList<>();

	public TextAreaCellRenderer() {
		super();
		renderer.setLineWrap(true);
		renderer.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		renderer.setName("Table.cellRenderer");
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		renderer.setFont(table.getFont());
		renderer.setText(Objects.toString(value, ""));
		adjustRowHeight(table, row, column);
		return renderer;
	}

	/**
	 * 计算给定行的新首选高度，并在表上设置该高度。
	 */
	private void adjustRowHeight(JTable table, int row, int column) {
		// 设置Area的宽度和高度，由于x和y不等于0，这一步不能省
		renderer.setBounds(table.getCellRect(row, column, false));
		// 拿到换行后的高度
		int preferredHeight = renderer.getPreferredSize().height;
		while (rowAndCellHeights.size() <= row) { //该行数据还未记录
			rowAndCellHeights.add(new ArrayList<>(column));
		}
		List<Integer> list = rowAndCellHeights.get(row);
		// 未初始化的列高度初始化为0
		while (list.size() <= column) list.add(0);
		list.set(column, preferredHeight);
		// 这一列最大高度
		int max = list.stream().max(Integer::compare).orElse(0);
		// 调整这一列高度
		if (table.getRowHeight(row) != max) {
			table.setRowHeight(row, max);
		}
	}
}
