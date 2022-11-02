package lab.component.text;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Collections;
import java.util.Objects;

/**
 * 跳转到指定行
 */
public class GotoLine extends JPanel {
	private static final int MIN = 1;
	private static final int MAX = 2000;

	private GotoLine() {
		super(new BorderLayout());
		SpinnerNumberModel model = new SpinnerNumberModel(100, MIN, MAX, 1);
		JTextArea textArea = new JTextArea(String.join("\n", Collections.nCopies(MAX, "11111111111")));
		textArea.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0));
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setRowHeaderView(new LineNumberView(textArea));

		JButton button = new JButton("Goto Line");
		button.addActionListener(e -> {
			Document doc = textArea.getDocument();
			Element root = doc.getDefaultRootElement();
			// int i = Math.max(MIN, Math.min(root.getElementCount(), model.getNumber().intValue()));
			int i = model.getNumber().intValue();
			try {
				// 指定行的一段文本
				Element elem = root.getElement(i - 1);

				// 找到指定行位置，不过这一行在滚动窗格的最后一行，需要设置size让其显示在第一行
				Rectangle rect = textArea.modelToView(elem.getStartOffset());
				// Java 9: Rectangle rect = textArea.modelToView2D(elem.getStartOffset()).getBounds();
				// 设置可见区域为滚动窗格的高度，宽度随意取值
				Rectangle vr = scroll.getViewport().getViewRect();
				rect.setSize(10, vr.height);

				// 移动导航位置
				textArea.scrollRectToVisible(rect);

				// 设置光标位置并获取焦点
				textArea.setCaretPosition(elem.getStartOffset());
				textArea.requestFocus();
			} catch (BadLocationException ex) {
				UIManager.getLookAndFeel().provideErrorFeedback(textArea);
			}
		});
		// frame.getRootPane().setDefaultButton(button);
		EventQueue.invokeLater(() -> getRootPane().setDefaultButton(button));

		JPanel p = new JPanel(new BorderLayout());
		p.add(new JSpinner(model));
		p.add(button, BorderLayout.EAST);
		add(p, BorderLayout.NORTH);
		add(scroll);
		setPreferredSize(new Dimension(320, 240));
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout());
			p.add(new GotoLine());
			SwingTestUtil.test();
		});
	}
}

class LineNumberView extends JComponent {
	private static final int MARGIN = 5;
	private final JTextArea textArea;
	private final FontMetrics fontMetrics;
	// private final int topInset;
	private final int fontAscent;
	private final int fontHeight;
	private final int fontDescent;
	private final int fontLeading;

	protected LineNumberView(JTextArea textArea) {
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
		g.setColor(getBackground());
		Rectangle clip = g.getClipBounds();
		g.fillRect(clip.x, clip.y, clip.width, clip.height);

		g.setColor(getForeground());
		int base = clip.y;
		int start = getLineAtPoint(base);
		int end = getLineAtPoint(base + clip.height);
		int y = start * fontHeight;
		int rmg = getInsets().right;
		for (int i = start; i <= end; i++) {
			String text = Objects.toString(i + 1);
			int x = getComponentWidth() - rmg - fontMetrics.stringWidth(text);
			y += fontAscent;
			g.drawString(text, x, y);
			y += fontDescent + fontLeading;
		}
	}
}
