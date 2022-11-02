package com.component.navigation.navmenu;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class NavPanelTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();

			JTabbedPane tabbedPane = new JTabbedPane();
			tabbedPane.setPreferredSize(new Dimension(500, 400));
			tabbedPane.addTab("御坂1号", new JLabel("御坂1号"));
			tabbedPane.addTab("御坂2号", new JLabel("御坂2号"));
			tabbedPane.addTab("御坂3号", new JLabel("御坂3号"));
			tabbedPane.addTab("御坂4号", new JLabel("御坂4号"));
			tabbedPane.addTab("御坂5号", new JLabel("御坂5号"));
			List<JLabel> tab = Arrays.asList(new JLabel("御坂1号"), new JLabel("御坂2号"), new JLabel("御坂3号"),
					new JLabel("御坂4号"), new JLabel("御坂5号"));
			for (int i = 0; i < tab.size(); i++) {
				tabbedPane.setTabComponentAt(i, tab.get(i));
			}

			NavList<String> list111 = new NavList<>(new String[]{"1121", "1122", "1123"});
			HashMap<Integer, NavList<String>> m1 = new HashMap<>();
			m1.put(1, list111);
			NavList<String> list11 = new NavList<>(new String[]{"111", "112", "113"}, m1);
			NavList<String> list21 = new NavList<>(new String[]{"131", "132", "133"});
			HashMap<Integer, NavList<String>> m2 = new HashMap<>();
			m2.put(0, list11);
			m2.put(2, list21);
			NavList<String> list1 = new NavList<>(new String[]{"11", "12", "13"}, m2);
			NavList<String> list2 = new NavList<>(new String[]{"21", "22", "23"});
			NavList<String> list3 = new NavList<>(new String[]{"31", "32", "33"});
			NavList<String> list4 = new NavList<>(new String[]{"41", "42", "43"});
			NavList<String> list5 = new NavList<>(new String[]{"51", "52", "53"});

			Dimension tabSize = tabbedPane.getPreferredSize();
			// 我不知道如何获取tab大小
			Rectangle rec = new Rectangle(-14, 14, tabSize.width / tabbedPane.getTabCount(), 0);
			NavPanel<String> navPanel = new NavPanel<>(tab, Arrays.asList(list1, list2, list3, list4, list5),
					MouseEvent.MOUSE_ENTERED, rec);

			JPanel contentPanel = new JPanel();
			contentPanel.add(tabbedPane);
			Dimension size = new Dimension(500, 400);

			JLayeredPane layeredPane = new JLayeredPane();
			layeredPane.setPreferredSize(size);
			contentPanel.setBounds(0, 0, size.width, size.height);
			navPanel.setBounds(0, 0, size.width, size.height);
			layeredPane.add(contentPanel, (Integer) 0);
			layeredPane.add(navPanel, (Integer) 1);

			JButton b = new JButton("获取");
			b.addActionListener(e -> {
				NavBuilder<String> builder = navPanel.getBuilder(tab.get(0));
				// NavList<String> rootList = builder.getRootList();
				// System.out.println(rootList.getSelectedValue());
				System.out.println(builder.getSelectedValue(0));
			});

			SwingTestUtil.setSize(600, 500);
			SwingTestUtil.test(layeredPane, b);
		});
	}
}
