package com.component.others.popup;

import javax.swing.*;
import java.awt.*;

/**
 * 提示文字专用菜单项
 */
public class TipTextPopupMenu extends JPopupMenu {
	private String tipText;

	public TipTextPopupMenu(String tipText) {
		this.tipText = tipText;
		setBorderPainted(false);
		setBackground(Color.WHITE);
		add(new MyMenuItem(tipText));
	}

	public void setTipText(String tipText) {
		this.tipText = tipText;
	}

	public String getTipText() {
		return tipText;
	}


	private static class MyMenuItem extends JMenuItem {
		public MyMenuItem(String text) {
			super(text);
			setBackground(Color.WHITE);
		}
	}
}
