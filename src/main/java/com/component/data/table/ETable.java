package com.component.data.table;

import com.component.common.component.BaseTable;
import com.component.data.table.renderer.ETableCellRenderer;
import com.component.data.table.renderer.TextFieldCellEditor;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.util.Vector;

/**
 * 表格组件
 * <p>
 * 支持的操作有
 * <ul>
 *     <li>JXTable的所有功能</li>
 *     <li>渲染相关：斑马线、边框类型</li>
 * </ul>
 */
public class ETable extends BaseTable {
	private ETableCellRenderer cellRenderer = new ETableCellRenderer();
	private TextFieldCellEditor cellEditor = new TextFieldCellEditor(new JTextField());

	public ETable() {
		init();
	}

	public ETable(TableModel dm) {
		super(dm);
		init();
	}

	public ETable(TableModel dm, TableColumnModel cm) {
		super(dm, cm);
		init();
	}

	public ETable(TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
		super(dm, cm, sm);
		init();
	}

	public ETable(int numRows, int numColumns) {
		super(numRows, numColumns);
		init();
	}

	public ETable(Vector<?> rowData, Vector<?> columnNames) {
		// super(rowData, columnNames);
		init();
	}

	public ETable(Object[][] rowData, Object[] columnNames) {
		super(rowData, columnNames);
		init();
	}

	private void init() {
		getColumns()
				.forEach(tableColumn -> {
					tableColumn.setCellRenderer(cellRenderer);
					tableColumn.setCellEditor(cellEditor);
				});

		// DefaultTableColumnModel model = new DefaultTableColumnModel();
		// model.addColumn(new TableColumn());
		// JXTableHeader header = new JXTableHeader(model);
		// setTableHeader(header);
	}

	public ETableCellRenderer getCellRenderer() {
		return cellRenderer;
	}

	@Override
	public TextFieldCellEditor getCellEditor() {
		return cellEditor;
	}
}

