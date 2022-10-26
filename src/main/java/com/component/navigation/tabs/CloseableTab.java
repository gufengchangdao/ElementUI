package com.component.navigation.tabs;

import com.component.basic.button.IconButton;
import com.component.basic.color.ColorUtil;
import com.component.common.template.X2Component;
import com.component.data.tag.TagFactory;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 含有关闭按钮的tab组件
 */
public class CloseableTab extends X2Component<JLabel, IconButton> implements MouseListener {
	private final JTabbedPane pane;

	public CloseableTab(JTabbedPane pane) {
		if (pane == null) {
			throw new NullPointerException("TabbedPane is null");
		}
		this.pane = pane;


		JLabel label = new JLabel() {
			public String getText() {
				int i = pane.indexOfTabComponent(CloseableTab.this);
				if (i != -1) return pane.getTitleAt(i);
				return null;
			}
		};
		IconButton closeButton = TagFactory.createCloseButton(ColorUtil.INFO);
		closeButton.addMouseListener(this);
		setProperty(label, closeButton, null, null);
		init(5);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int i = pane.indexOfTabComponent(this);
		if (i != -1) {
			pane.remove(i);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}