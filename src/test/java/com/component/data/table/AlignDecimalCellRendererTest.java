package com.component.data.table;// -*- mode:java; encoding:utf-8 -*-
// vim:set fileencoding=utf-8:
// @homepage@

import com.component.data.table.renderer.AlignDecimalCellRenderer;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public final class AlignDecimalCellRendererTest extends JPanel {
	private AlignDecimalCellRendererTest() {
		super(new BorderLayout());
		String[] columnNames = {"String", "Double", "ALIGN_DECIMAL"};
		Object[][] data = {
				{"aaa", 1.4142, 1.4142}, {"bbb", 98.765, 98.765},
				{"CCC", 1.73, 1.73}, {"DDD", 0d, 0d}
		};
		TableModel model = new DefaultTableModel(data, columnNames) {
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
		};
		JTable table = new JTable(model);
		// 对最后一列应用该渲染器
		table.getColumnModel().getColumn(2).setCellRenderer(new AlignDecimalCellRenderer());
		table.setAutoCreateRowSorter(true);
		table.setRowSelectionAllowed(true);
		table.setFillsViewportHeight(true);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setFocusable(false);
		add(new JScrollPane(table));
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.init(new FlowLayout()).add(new AlignDecimalCellRendererTest());
			SwingTestUtil.test();
		});
	}
}

