package com.component.data.table;

import com.component.common.component.DefaultTableModel2;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.stream.IntStream;

public class FixedColumnTablePanelTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout());

			String ES = "";
			Object[][] data = {
					{1, 11, "A", ES, ES, ES, ES, ES},
					{2, 22, ES, "B", ES, ES, ES, ES},
					{3, 33, ES, ES, "C", ES, ES, ES},
					{4, 1, ES, ES, ES, "D", ES, ES},
					{5, 55, ES, ES, ES, ES, "E", ES},
					{6, 66, ES, ES, ES, ES, ES, "F"}
			};
			String[] columnNames = {"fixed 1", "fixed 2", "A", "B", "C", "D", "E", "F"};
			DefaultTableModel model = new DefaultTableModel2(data, columnNames);

			JTable table = new JTable(model);
			FixedColumnTablePanel panel = new FixedColumnTablePanel(table, 2);

			JButton addButton = new JButton("add");
			addButton.addActionListener(e -> {
				IntStream.range(0, 100)
						.mapToObj(i -> new Object[]{i, i + 1, "A" + i, "B" + i})
						.forEach(model::addRow);
			});
			p.add(panel, "wrap");
			p.add(addButton);
			SwingTestUtil.test();
		});
	}
}