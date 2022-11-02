package com.component.data.table;

import javax.swing.*;
import javax.swing.table.TableModel;

/**
 * 将表格左侧指定列数固定
 */
public class FixedColumnTablePanel extends JScrollPane {
	private final JTable table;
	private final int fixedRange;
	private JTable fixedTable;

	/**
	 * @param table      表格
	 * @param fixedRange 从左侧开始，要固定的列数
	 */
	public FixedColumnTablePanel(JTable table, int fixedRange) {
		super(table);
		this.table = table;
		this.fixedRange = fixedRange;
		init();
	}

	private void init() {
		if (fixedRange > table.getColumnCount())
			throw new IllegalArgumentException("固定列数不能超过表格总列数");

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		TableModel model = table.getModel();
		fixedTable = new JTable(model);
		fixedTable.setSelectionModel(table.getSelectionModel());
		fixedTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		for (int i = model.getColumnCount() - 1; i >= 0; i--) {
			if (i < fixedRange) {
				table.removeColumn(table.getColumnModel().getColumn(i));
				fixedTable.getColumnModel().getColumn(i).setResizable(false);
			} else {
				fixedTable.removeColumn(fixedTable.getColumnModel().getColumn(i));
			}
		}

		fixedTable.setPreferredScrollableViewportSize(fixedTable.getPreferredSize());
		setRowHeaderView(fixedTable);
		setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, fixedTable.getTableHeader());

		// 固定列y值也变化
		getRowHeader().addChangeListener(e -> {
			JViewport viewport = (JViewport) e.getSource();
			getVerticalScrollBar().setValue(viewport.getViewPosition().y);
		});
	}

	public int getFixedRange() {
		return fixedRange;
	}
}
