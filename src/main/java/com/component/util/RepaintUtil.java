package com.component.util;

import javax.accessibility.Accessible;
import javax.swing.*;
import javax.swing.plaf.basic.ComboPopup;

public class RepaintUtil {
	/**
	 * 重绘 JComboBox 指定行，如果该行已选中，则按钮也会被重绘
	 *
	 * @param combo JComboBox
	 * @param row   要重绘的行索引
	 */
	public static void repaint(JComboBox<?> combo, int row) {
		// 如果已选中就重绘按钮
		if (combo.getSelectedIndex() == row) {
			combo.repaint();
		}
		// 如果下拉列表已展开就重绘列表指定行
		Accessible a = combo.getAccessibleContext().getAccessibleChild(0);
		if (a instanceof ComboPopup) {
			JList<?> list = ((ComboPopup) a).getList();
			if (list.isShowing()) {
				list.repaint(list.getCellBounds(row, row));
			}
		}
	}

	/**
	 * 重绘表格指定单元格
	 *
	 * @param table 表格
	 * @param row   表格模型中所在行
	 * @param col   表格模型中所在列
	 */
	public static void repaint(JTable table, int row, int col) {
		int vr = table.convertRowIndexToView(row); // JDK 1.6.0
		int vc = table.convertColumnIndexToView(col);
		table.repaint(table.getCellRect(vr, vc, false));
	}
}
