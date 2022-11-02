package com.component.navigation.tabs.hoverclosetab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * 悬停显示关闭按钮的 TabbedPane
 */
public class HoverCloseButtonTabbedPane extends JTabbedPane {
	// private final Insets tabInsets = UIManager.getInsets("TabbedPane.tabInsets");
	private transient MouseMotionListener hoverHandler;

	public HoverCloseButtonTabbedPane() {
		super(TOP, SCROLL_TAB_LAYOUT);
	}

	public HoverCloseButtonTabbedPane(int tabPlacement) {
		super(tabPlacement, SCROLL_TAB_LAYOUT);
	}

	// protected HoverCloseButtonTabbedPane(int tabPlacement, int tabLayoutPolicy) {
	//   super(tabPlacement, SCROLL_TAB_LAYOUT);
	// }

	@Override
	public void updateUI() {
		removeMouseMotionListener(hoverHandler);
		super.updateUI();
		// setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		hoverHandler = new MouseAdapter() {
			private int prev = -1;

			@Override
			public void mouseMoved(MouseEvent e) {
				JTabbedPane source = (JTabbedPane) e.getComponent();
				int focused = source.indexAtLocation(e.getX(), e.getY());
				if (focused == prev) {
					return;
				}
				for (int i = 0; i < source.getTabCount(); i++) {
					Component c = source.getTabComponentAt(i);
					if (c instanceof TabPanel) {
						((TabPanel) c).setButtonVisible(i == focused);
					}
				}
				prev = focused;
			}
		};
		addMouseMotionListener(hoverHandler);
	}

	@Override
	public void addTab(String title, Component content) {
		super.addTab(title, content);
		setTabComponentAt(getTabCount() - 1, new TabPanel(this, title, content));
	}

	/**
	 * 指定鼠标悬停时显示的关闭图标
	 */
	public void addTab(String title, Component content, Icon closeIcon) {
		super.addTab(title, content);
		setTabComponentAt(getTabCount() - 1, new TabPanel(this, title, content, closeIcon));
	}
}

