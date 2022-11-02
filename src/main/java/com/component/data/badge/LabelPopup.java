package com.component.data.badge;

import com.component.data.label.badge.BadgeLabel;

import javax.swing.*;
import java.awt.*;

/**
 * 标签与弹窗的组合，标签与弹窗可以是一对多关系(当修改标签文本时)
 */
public class LabelPopup {
	private Popup popup;
	private final BadgeLabel label;
	private Component owner;
	private int x;
	private int y;
	private boolean isShow;

	public LabelPopup(Component owner, BadgeLabel contents, int x, int y) {
		popup = new BPopup(owner, contents, x, y);
		this.owner = owner;
		this.x = x;
		this.y = y;
		this.label = contents;
	}

	public void show() {
		popup.show();
		isShow = true;
	}

	public void hide() {
		popup.hide();
		popup = null;
		isShow = false;
	}

	public String getText() {
		return label.getText();
	}

	public void setText(String text) {
		label.setText(text);
		if (popup != null) popup.hide();
		popup = new BPopup(owner, label, x, y);
		if (isShow) popup.show();
	}

	public BadgeLabel getLabel() {
		return label;
	}

	public void repaint() {
		label.repaint();
	}

	// 不继承就用不了这个类
	public static class BPopup extends Popup {
		public BPopup(Component owner, BadgeLabel contents, int x, int y) {
			super(owner, contents, x, y);
		}
	}
}
