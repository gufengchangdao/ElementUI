package com.component.data.table.render;

import com.component.data.table.renderer.TextAreaCellRenderer;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class TextAreaCellRendererTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout("wrap 1", "grow"));
			SwingTestUtil.setSize(400, 400);
			String[] columnNames = {"Default", "AutoWrap"};
			Object[][] data = {
					{"123456789012345678901234567890", "123456789012345678901234567890"},
					{"1111", "22222222222222222222222222222222222222222222222222222222"},
					{"3333333", "----------------------------------------------0"},
					{"4444444444444444444", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>|"},
			};
			JTable table = new JTable(data, columnNames);
			table.setEnabled(false);
			table.setShowGrid(false);
			TextAreaCellRenderer renderer = new TextAreaCellRenderer();

			// table.getColumnModel().getColumn(0).setCellRenderer(renderer);
			table.getColumnModel().getColumn(1).setCellRenderer(renderer);

			// 虽然放入滚动窗格，但这里禁止滚动条出现
			JScrollPane scroll = new JScrollPane(table);
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			p.add(new JLabel("拖动窗口大小看内容如何变化"));
			p.add(scroll, "growx");

			SwingTestUtil.test();
		});
	}
}
