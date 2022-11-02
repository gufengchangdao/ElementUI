package com.component.data.pagination;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

public class PaginationListTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();

			// page.add("长文本测试");
			PaginationList list = new PaginationList(10);
			// 绘制背景
			list.getCellRenderer().setBackgroundPainted(true);

			JButton button1 = new JButton("增加5个");
			button1.addActionListener(e -> {
				// int oldCount = list.getModel().getSize();
				// list.getModel().addData(5);
				// list.firePropertyChange("visibleRowCount", oldCount, list.getModel().getSize());

				list.getModel().addData(5);
				list.updateUI();
			});
			JButton button2 = new JButton("减少5个");
			button2.addActionListener(e -> {
				list.getModel().removeData(5);
				// list.updateUI();
			});
			SwingTestUtil.test(list, button1, button2);
		});
	}
}
