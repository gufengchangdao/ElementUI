package com.component.data.table.render;

import com.component.common.component.DefaultTableModel2;
import com.component.data.table.renderer.TableHeaderPopupRenderer;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class TableHeaderPopupRendererTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout("", "grow"));

			String[] columnNames = {"Boolean", "Integer", "String"};
			Object[][] data = {
					{true, 1, "BBB"}, {false, 12, "AAA"}, {true, 2, "DDD"}, {false, 5, "CCC"},
					{true, 3, "EEE"}, {false, 6, "GGG"}, {true, 4, "FFF"}, {false, 7, "HHH"}
			};
			DefaultTableModel2 model = new DefaultTableModel2(data, columnNames);
			model.setCellEditable(true);

			JTable table = new JTable(model) {
				// 复选框单元格选中时，单元格的背景色变化会慢与所在行其他单元格，这里进行设置
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
			};

			JPopupMenu pop = new JPopupMenu();
			pop.add("000");
			pop.add("11111");
			pop.add("2222222");

			TableHeaderPopupRenderer r = new TableHeaderPopupRenderer(table.getTableHeader(), pop);
			table.getColumnModel().getColumn(0).setHeaderRenderer(r);
			table.getColumnModel().getColumn(1).setHeaderRenderer(r);
			table.getColumnModel().getColumn(2).setHeaderRenderer(r);
			p.add(new JScrollPane(table), "center");

			SwingTestUtil.test();
		});
	}
}
