package com.component.data.table;

import com.component.basic.color.ColorUtil;
import com.component.data.table.renderer.ETableCellRenderer;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ETableTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();

			String[][] s1 = {{"张三", "12346", "12"}, {"李四", "234561", "18"}, {"王五", "34561", "22"},
					{"王五", "34561", "22"}, {"王五", "34561", "22"}, {"王五", "34561", "22"},
					{"王五", "34561", "22"}};
			String[] s2 = {"姓名", "学号", "年龄"};
			DefaultTableModel tableModel = new DefaultTableModel(s1, s2);

			ETable table = new ETable(tableModel);
			ETableCellRenderer renderer = table.getCellRenderer();
			renderer.setContainBorder(true);
			renderer.setContainStripe(true);

			renderer.addState(1, ColorUtil.PRIMARY);
			renderer.addState(2, ColorUtil.DANGER);
			renderer.addState(4, ColorUtil.WARNING);

			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBorder(null);
			SwingTestUtil.test(scrollPane);
		});
	}
}
