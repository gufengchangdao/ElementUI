package com.component.navigation.tabs.ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.awt.geom.GeneralPath;

/**
 * 梯形Tab
 */
public class IsoscelesTrapezoidTabbedPaneUI extends BasicTabbedPaneUI {
	private static final int ADJ2 = 3;
	private final Color selectedTabColor = UIManager.getColor("TabbedPane.highlight");
	private static final Color TAB_BGC = UIManager.getColor("TabbedPane.background");
	private static final Color TAB_BORDER = UIManager.getColor("TabbedPane.highlight");

	@Override
	protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
		int tabCount = tabPane.getTabCount();

		Rectangle iconRect = new Rectangle();
		Rectangle textRect = new Rectangle();
		Rectangle clipRect = g.getClipBounds();

		// copied from BasicTabbedPaneUI#paintTabArea(...)
		for (int i = runCount - 1; i >= 0; i--) {
			int start = tabRuns[i];
			int next = tabRuns[(i == runCount - 1) ? 0 : i + 1];
			// int end = next != 0 ? next - 1 : tabCount - 1;
			int end = next == 0 ? tabCount - 1 : next - 1;
			// for (int j = start; j <= end; j++) {
			// https://stackoverflow.com/questions/41566659/tabs-rendering-order-in-custom-jtabbedpane
			for (int j = end; j >= start; j--) {
				if (j != selectedIndex && rects[j].intersects(clipRect)) {
					paintTab(g, tabPlacement, rects, j, iconRect, textRect);
				}
			}
		}
		if (selectedIndex >= 0 && rects[selectedIndex].intersects(clipRect)) {
			paintTab(g, tabPlacement, rects, selectedIndex, iconRect, textRect);
		}
	}

	@Override
	protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
		// Do nothing
	}

	@Override
	protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) {
		// Do nothing
	}

	@Override
	protected void paintContentBorderTopEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int y, int w, int h) {
		super.paintContentBorderTopEdge(g, tabPlacement, selectedIndex, x, y, w, h);
		Rectangle selRect = getTabBounds(selectedIndex, calcRect);
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setColor(selectedTabColor);
		g2.drawLine(selRect.x - ADJ2 + 1, y, selRect.x + selRect.width + ADJ2 - 1, y);
		g2.dispose();
	}

	@Override
	protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Rectangle clipRect = g2.getClipBounds();
		clipRect.grow(ADJ2 + 1, 0);
		g2.setClip(clipRect);

		float textShiftOffset = isSelected ? 0f : 1f;
		GeneralPath trapezoid = new GeneralPath();
		trapezoid.moveTo((float) (x - ADJ2), (float) (y + h));
		trapezoid.lineTo((float) (x + ADJ2), y + textShiftOffset);
		trapezoid.lineTo((float) (x + w - ADJ2), y + textShiftOffset);
		trapezoid.lineTo((float) (x + w + ADJ2), (float) (y + h));
		// trapezoid.closePath();

		// TEST: g2.setColor(isSelected ? tabPane.getBackground() : tabBackgroundColor);
		g2.setColor(isSelected ? selectedTabColor : TAB_BGC);
		g2.fill(trapezoid);

		g2.setColor(TAB_BORDER);
		g2.draw(trapezoid);

		g2.dispose();
	}
}
