package com.component.navigation.breadcrumb;

import com.component.svg.icon.regular.ArrowFatRightSvg;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BreadcrumbTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			ArrayList<String> list = new ArrayList<>();
			list.add("首页");
			list.add("活动管理");
			list.add("活动列表");
			list.add("活动详情");
			Breadcrumb breadcrumb = new Breadcrumb(
					list, 2,
					// "/"
					ArrowFatRightSvg.of(16, 16)
			);
			JButton b1 = new JButton("添加");
			b1.addActionListener(e -> {
				breadcrumb.addItem("新页面");
				SwingTestUtil.getFrame().getContentPane().validate();
			});
			JButton b2 = new JButton("移除新页面");
			b2.addActionListener(e -> {
				breadcrumb.removeItem("新页面");
				SwingTestUtil.getFrame().getContentPane().validate();
			});
			JButton b3 = new JButton("移除第一个标签");
			b3.addActionListener(e -> {
				breadcrumb.removeItemAt(0);
				SwingTestUtil.getFrame().getContentPane().validate();
			});
			JButton b4 = new JButton("移除最后一个标签");
			b4.addActionListener(e -> {
				breadcrumb.removeLastItem();
				SwingTestUtil.getFrame().getContentPane().validate();
			});
			SwingTestUtil.test(breadcrumb, b1, b2, b3, b4);
		});
	}
}
