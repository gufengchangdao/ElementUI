package com.component.form.area;

import javax.swing.*;
import javax.swing.text.Caret;
import javax.swing.text.DefaultCaret;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.Objects;

/**
 * 插入符所在行高亮的 JTextArea
 */
public class HighlightCursorTextArea extends JTextArea {
	private static final Color LINE_COLOR = new Color(0xFA_FA_DC);
	private final Rectangle rect = new Rectangle();

	@Override
	public void updateUI() {
		super.updateUI();
		setOpaque(false);
		Caret caret = new DefaultCaret() {
			@Override
			protected synchronized void damage(Rectangle r) {
				if (Objects.nonNull(r)) {
					JTextComponent c = getComponent();
					x = 0;
					y = r.y;
					width = c.getSize().width;
					height = r.height;
					c.repaint();
				}
			}
		};
		// caret.setBlinkRate(getCaret().getBlinkRate());
		caret.setBlinkRate(UIManager.getInt("TextArea.caretBlinkRate"));
		setCaret(caret);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Caret c = getCaret();
		if (c instanceof DefaultCaret) {
			Graphics2D g2 = (Graphics2D) g.create();
			DefaultCaret caret = (DefaultCaret) c;
			Rectangle r = SwingUtilities.calculateInnerArea(this, rect);
			r.y = caret.y;
			r.height = caret.height;
			g2.setPaint(LINE_COLOR);
			g2.fill(r);
			g2.dispose();
		}
		super.paintComponent(g);
	}
}
