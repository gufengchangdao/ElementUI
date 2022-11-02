package com.component.navigation.navmenu;

import com.component.basic.color.ColorUtil;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * 导航测试。
 * <p>
 */
public class NavListTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			// 原有面板
			JPanel contentPanel = new JPanel();
			Dimension panelSize = new Dimension(400, 400);
			contentPanel.setPreferredSize(panelSize);
			contentPanel.setBackground(ColorUtil.SUCCESS);
			JButton b = new JButton("触发导航");
			JButton b2 = new JButton("获取选择");
			contentPanel.add(b);
			contentPanel.add(b2);

			// 上层面板，用于绘制子导航
			JPanel glassPanel = new JPanel(null);
			glassPanel.setBounds(0, 0, panelSize.width, panelSize.height);
			glassPanel.setOpaque(false);
			contentPanel.setBounds(0, 0, panelSize.width, panelSize.height);

			JLayeredPane layeredPane = new JLayeredPane();
			layeredPane.setPreferredSize(panelSize);
			layeredPane.add(contentPanel, (Integer) 0);
			layeredPane.add(glassPanel, (Integer) 1);

			// 构造导航项
			NavList<String> child1 = new NavList<>(new String[]{"21", "22", "23"});
			NavList<String> child11 = new NavList<>(new String[]{"221", "222", "223"});
			HashMap<Integer, NavList<String>> m = new HashMap<>();
			m.put(1, child11);
			child1.setNext(m);

			NavList<String> child2 = new NavList<>(new String[]{"31", "32", "33"});

			HashMap<Integer, NavList<String>> map = new HashMap<>();
			map.put(1, child1);
			map.put(2, child2);
			NavList<String> root = new NavList<>(new String[]{"1", "2", "3"}, map);
			// 创建导航
			NavBuilder<String> navBuilder = new NavBuilder<>(glassPanel, b, root);

			b2.addActionListener(e -> {

			});

			SwingTestUtil.test(layeredPane);
		});
	}
}
