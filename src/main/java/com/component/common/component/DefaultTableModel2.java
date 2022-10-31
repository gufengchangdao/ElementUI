package com.component.common.component;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

/**
 * 基础表格模型
 *
 * <ul>
 *     <li>可识别icon、boolean</li>
 *     <li>默认不可编辑</li>
 * </ul>
 */
public class DefaultTableModel2 extends DefaultTableModel {
	/** 所有单元格是否可以编辑 */
	private boolean cellEditable = false;

	public DefaultTableModel2() {
	}

	public DefaultTableModel2(int rowCount, int columnCount) {
		super(rowCount, columnCount);
	}

	public DefaultTableModel2(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	public DefaultTableModel2(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	public DefaultTableModel2(Vector data, Vector columnNames) {
		super(data, columnNames);
	}

	public DefaultTableModel2(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
	}

	@Override
	public Class<?> getColumnClass(int column) {
		return getValueAt(0, column).getClass();
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return cellEditable;
	}

	public boolean isCellEditable() {
		return cellEditable;
	}

	public void setCellEditable(boolean cellEditable) {
		this.cellEditable = cellEditable;
	}
}
