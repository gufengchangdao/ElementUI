package com.component.data.pagination;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

public class IconPaginationListTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();

			IconPaginationList list = new IconPaginationList(21);
			// 绘制背景
			list.getList().getCellRenderer().setBackgroundPainted(true);

			JButton button1 = new JButton("增加5个");
			button1.addActionListener(e -> {
				// int oldCount = list.getModel().getSize();
				// list.getModel().addData(5);
				// list.firePropertyChange("visibleRowCount", oldCount, list.getModel().getSize());

				list.getModel().addData(5);
				list.getList().updateUI();

			});
			JButton button2 = new JButton("减少5个");
			button2.addActionListener(e -> {
				list.getModel().removeData(5);
				list.getList().updateUI();
			});
			JButton button3 = new JButton("获取");
			button3.addActionListener(e -> {
				System.out.println("列表 " + list.getList().getSelectedIndex());
				System.out.println("模型 " + list.getModel().getSelectedIndex());
				System.out.println("渲染 " + list.getCellRenderer().getSelectedIndex());
				System.out.println(list.getList().getSelectedValue());
			});
			SwingTestUtil.test(list, button1, button2, button3);
		});
	}
}
