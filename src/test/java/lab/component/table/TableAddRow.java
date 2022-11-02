package lab.component.table;
// -*- mode:java; encoding:utf-8 -*-
// vim:set fileencoding=utf-8:
// @homepage@

import com.component.util.SwingTestUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;

/**
 * 表格添加行和减少行
 */
public final class TableAddRow extends JPanel {
	private TableAddRow() {
		super(new BorderLayout());

		RowDataModel model = new RowDataModel();
		// 重写是为了表格的斑马条纹
		JTable table = new JTable(model) {
			private final Color evenColor = new Color(0xFA_FA_FA);

			@Override
			public Component prepareRenderer(TableCellRenderer tcr, int row, int column) {
				Component c = super.prepareRenderer(tcr, row, column);
				if (isRowSelected(row)) {
					c.setForeground(getSelectionForeground());
					c.setBackground(getSelectionBackground());
				} else {
					c.setForeground(getForeground());
					c.setBackground(row % 2 == 0 ? evenColor : getBackground());
				}
				return c;
			}
		};

		// 禁止修改第一列的宽度
		TableColumn col = table.getColumnModel().getColumn(0);
		col.setMinWidth(60);
		col.setMaxWidth(60);
		col.setResizable(false);

		model.addRowData(new RowData("Name 1", "comment..."));
		model.addRowData(new RowData("Name 2", "Test"));
		model.addRowData(new RowData("Name d", "ee"));
		model.addRowData(new RowData("Name c", "Test cc"));
		model.addRowData(new RowData("Name b", "Test bb"));
		model.addRowData(new RowData("Name a", "ff"));
		model.addRowData(new RowData("Name 0", "Test aa"));

		table.setAutoCreateRowSorter(true);
		table.setFillsViewportHeight(true);
		table.setComponentPopupMenu(new TablePopupMenu());
		add(new JScrollPane(table));
		setPreferredSize(new Dimension(320, 240));
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.init(new FlowLayout()).add(new TableAddRow());
			SwingTestUtil.test();
		});
	}
}

class RowDataModel extends DefaultTableModel {
	// 列属性描述
	private static final ColumnContext[] COLUMN_ARRAY = {
			new ColumnContext("No.", Integer.class, false),
			new ColumnContext("Name", String.class, true),
			new ColumnContext("Comment", String.class, true)
	};
	private int number;

	public void addRowData(RowData t) {
		Object[] obj = {number, t.getName(), t.getComment()};
		super.addRow(obj);
		number++;
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return COLUMN_ARRAY[col].isEditable;
	}

	@Override
	public Class<?> getColumnClass(int column) {
		return COLUMN_ARRAY[column].columnClass;
	}

	@Override
	public int getColumnCount() {
		return COLUMN_ARRAY.length;
	}

	@Override
	public String getColumnName(int column) {
		return COLUMN_ARRAY[column].columnName;
	}

	private static class ColumnContext {
		public final String columnName;
		public final Class<?> columnClass;
		public final boolean isEditable;

		protected ColumnContext(String columnName, Class<?> columnClass, boolean isEditable) {
			this.columnName = columnName;
			this.columnClass = columnClass;
			this.isEditable = isEditable;
		}
	}
}

class RowData {
	private final String name;
	private final String comment;

	protected RowData(String name, String comment) {
		this.name = name;
		this.comment = comment;
	}

	public String getName() {
		return name;
	}

	public String getComment() {
		return comment;
	}
}

class TablePopupMenu extends JPopupMenu {
	private final JMenuItem delete;

	protected TablePopupMenu() {
		super();
		add("add").addActionListener(e -> {
			JTable table = (JTable) getInvoker();
			RowDataModel model = (RowDataModel) table.getModel();
			model.addRowData(new RowData("New row", ""));
			Rectangle r = table.getCellRect(model.getRowCount() - 1, 0, true);
			table.scrollRectToVisible(r);
		});
		addSeparator();
		delete = add("delete");
		delete.addActionListener(e -> {
			JTable table = (JTable) getInvoker();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			int[] selection = table.getSelectedRows();
			for (int i = selection.length - 1; i >= 0; i--) {
				model.removeRow(table.convertRowIndexToModel(selection[i]));
			}
		});
	}

	@Override
	public void show(Component c, int x, int y) {
		if (c instanceof JTable) {
			// 至少选择了一行才允许执行删除操作
			delete.setEnabled(((JTable) c).getSelectedRowCount() > 0);
			super.show(c, x, y);
		}
	}
}
