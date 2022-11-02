package com.component.others.card;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CardPanelTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			ArrayList<JButton> buttons = new ArrayList<>();
			buttons.add(new JButton("操作按钮"));
			buttons.add(new JButton("不操作按钮"));
			CardPanel<String> c = new CardPanel<>(
					"卡片名称",
					buttons,
					new JList<>(new String[]{"列表内容 1", "列表内容 2", "列表内容 3", "列表内容 4", "列表内容 5"}),
					true
			);
			c.setPreferredSize(new Dimension(400, 200));
			SwingTestUtil.test(c);
		});
	}
}
