package com.component.common.component;

import javax.swing.*;

/**
 * 创建不拦截鼠标事件的组件
 */
public class NoMouseListenerComponentFactory {
	public static JTextArea getTextArea(String s) {
		JTextArea area = new JTextArea(s) {
			@Override
			public boolean contains(int x, int y) {
				return false;
			}
		};
		area.setEditable(false);
		return area;
	}
}
