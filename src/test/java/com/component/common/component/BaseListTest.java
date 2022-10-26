package com.component.common.component;

import com.component.util.SwingTestUtil;

import java.awt.*;

import static javax.swing.JList.HORIZONTAL_WRAP;

public class BaseListTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() ->{
			SwingTestUtil.loadSkin();

			String[] s = new String[13];
			for (int i = 0; i < s.length; i++) {
				s[i] = "御坂" + i + "号";
			}

			BaseList<String> list = new BaseList<>(s);
			list.setVisibleRowCount(5);
			// list.setLayoutOrientation(VERTICAL);
			list.setLayoutOrientation(HORIZONTAL_WRAP);
			SwingTestUtil.test(list);
		});
	}
}
