package com.component.data.table.editor;

import com.component.common.component.DefaultTableModel2;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class DateSpinnerCellEditorTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout());
			String[] columnNames = {"Integer", "String", "Date"};
			Object[][] data = {
					{-1, "AAA", new Date()}, {2, "BBB", new Date()},
					{-9, "EEE", new Date()}, {1, "", new Date()},
					{10, "CCC", new Date()}, {7, "FFF", new Date()},
			};
			DefaultTableModel2 model = new DefaultTableModel2(data, columnNames);
			model.setCellEditable(true);
			JTable table = new JTable(model) {
				@Override
				public void updateUI() {
					super.updateUI();
					setSurrendersFocusOnKeystroke(true);

					setDefaultEditor(Date.class, new DateSpinnerCellEditor("yyyy-MM-dd"));
				}
			};
			p.add(new JScrollPane(table));

			SwingTestUtil.test();
		});
	}
}
