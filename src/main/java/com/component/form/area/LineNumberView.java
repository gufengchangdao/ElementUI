package com.component.form.area;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Objects;

// https://community.oracle.com/thread/1479759 Advice for editor gutter implementation...
// Original author: Alan Moore
// Modified by: TERAI Atsuhiro

/**
 * 行递增组件，用于滚动窗格设置行标题视口使用，例如
 *
 * <pre>
 * JScrollPane scroll = new JScrollPane(textArea);
 * scroll.setRowHeaderView(new LineNumberView(textArea));
 * </pre>
 */
public class LineNumberView extends JComponent {
	private static final int MARGIN = 5;
	private final JTextArea textArea;
	private final FontMetrics fontMetrics;
	// private final int topInset;
	private final int fontAscent;
	private final int fontHeight;
	private final int fontDescent;
	private final int fontLeading;

	public LineNumberView(JTextArea textArea) {
		super();
		this.textArea = textArea;
		Font font = textArea.getFont();
		fontMetrics = getFontMetrics(font);
		fontHeight = fontMetrics.getHeight();
		fontAscent = fontMetrics.getAscent();
		fontDescent = fontMetrics.getDescent();
		fontLeading = fontMetrics.getLeading();
		// topInset = textArea.getInsets().top;

		textArea.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				repaint();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				repaint();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				/* not needed */
			}
		});
		textArea.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				revalidate();
				repaint();
			}
		});
		Insets i = textArea.getInsets();
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 0, 0, 1, Color.GRAY),
				BorderFactory.createEmptyBorder(i.top, MARGIN, i.bottom, MARGIN - 1)));
		setOpaque(true);
		setBackground(Color.WHITE);
		setFont(font);
	}

	private int getComponentWidth() {
		// Document doc = textArea.getDocument();
		// Element root = doc.getDefaultRootElement();
		// int lineCount = root.getElementIndex(doc.getLength());
		int lineCount = textArea.getLineCount();
		int maxDigits = Math.max(3, Objects.toString(lineCount).length());
		Insets i = getInsets();
		return maxDigits * fontMetrics.stringWidth("0") + i.left + i.right;
		// return 48;
	}

	private int getLineAtPoint(int y) {
		Element root = textArea.getDocument().getDefaultRootElement();
		int pos = textArea.viewToModel(new Point(0, y));
		// Java 9: int pos = textArea.viewToModel2D(new Point(0, y));
		return root.getElementIndex(pos);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(getComponentWidth(), textArea.getHeight());
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(getBackground());
		Rectangle clip = g2.getClipBounds();
		g2.fillRect(clip.x, clip.y, clip.width, clip.height);

		g2.setColor(getForeground());
		int base = clip.y;
		int start = getLineAtPoint(base);
		int end = getLineAtPoint(base + clip.height);
		int y = start * fontHeight;
		int rmg = getInsets().right;
		for (int i = start; i <= end; i++) {
			String text = Objects.toString(i + 1);
			int x = getComponentWidth() - rmg - fontMetrics.stringWidth(text);
			y += fontAscent;
			g2.drawString(text, x, y);
			y += fontDescent + fontLeading;
		}
		g2.dispose();
	}
}
