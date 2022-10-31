package com.component.common.component;

import org.jdesktop.swingx.JXTable;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.Vector;

/**
 * 基础表格
 *
 * <ul>
 *     <li>解决选项单元格选中后背景变化不一致问题</li>
 * </ul>
 */
public class BaseTable extends JXTable {
	public BaseTable() {
	}

	public BaseTable(TableModel dm) {
		super(dm);
	}

	public BaseTable(TableModel dm, TableColumnModel cm) {
		super(dm, cm);
	}

	public BaseTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
		super(dm, cm, sm);
	}

	public BaseTable(int numRows, int numColumns) {
		super(numRows, numColumns);
	}

	public BaseTable(Vector rowData, Vector columnNames) {
		super(rowData, columnNames);
	}

	public BaseTable(Object[][] rowData, Object[] columnNames) {
		super(rowData, columnNames);
	}

	// 选项单元格选中时，单元格的背景色变化会慢与所在行其他单元格，这里进行设置
	@Override
	public Component prepareEditor(TableCellEditor editor, int row, int column) {
		Component c = super.prepareEditor(editor, row, column);
		if (c instanceof JCheckBox) {
			JCheckBox b = (JCheckBox) c;
			b.setBackground(getSelectionBackground());
			b.setBorderPainted(true);
		}
		return c;
	}
}
