package lab.component.text;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 搜索结果高亮
 */
public class DrawsLayeredHighlights {
	private static final Color WARNING_COLOR = new Color(0xFF_C8_C8);
	private static final String TEXT = String.join("\n",
			"Trail: Creating a GUI with JFC/Swing",
			"Lesson: Learning Swing by Example",
			"This lesson explains the concepts you need to",
			" use Swing components in building a user interface.",
			" First we examine the simplest Swing application you can write.",
			" Then we present several progressively complicated examples of creating",
			" user interfaces using components in the javax.swing package.",
			" We cover several Swing components, such as buttons, labels, and text areas.",
			" The handling of events is also discussed,",
			" as are layout management and accessibility.",
			" This lesson ends with a set of questions and exercises",
			" so you can test yourself on what you've learned.",
			"https://docs.oracle.com/javase/tutorial/uiswing/learn/index.html");

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout("wrap 1", "grow", "grow"));

			JTextField field = new JTextField("Swing");
			JTextArea textArea = new JTextArea(TEXT);

			// DefaultHighlighter dh = (DefaultHighlighter) textArea.getHighlighter();
			// dh.setDrawsLayeredHighlights(true);

			field.getDocument().addDocumentListener(new DocumentListener() {
				@Override
				public void insertUpdate(DocumentEvent e) {
					fireDocumentChangeEvent(field, textArea);
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					fireDocumentChangeEvent(field, textArea);
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					/* not needed */
				}
			});
			fireDocumentChangeEvent(field, textArea);

			// 让文本域获初始化后取到焦点
			EventQueue.invokeLater(textArea::requestFocusInWindow);

			p.add(field, "growx");
			p.add(new JScrollPane(textArea), "growx, growy");
			SwingTestUtil.test();
		});

	}

	public static void fireDocumentChangeEvent(JTextField field, JTextArea textArea) {
		field.setBackground(Color.WHITE);
		String pattern = field.getText().trim();
		Highlighter highlighter = textArea.getHighlighter();
		highlighter.removeAllHighlights();
		if (pattern.isEmpty()) {
			return;
		}
		Document doc = textArea.getDocument();
		try {
			String text = doc.getText(0, doc.getLength());
			Matcher matcher = Pattern.compile(pattern).matcher(text);
			Highlighter.HighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
			int pos = 0;
			while (matcher.find(pos) && !matcher.group().isEmpty()) {
				int start = matcher.start();
				int end = matcher.end();
				highlighter.addHighlight(start, end, highlightPainter);
				pos = end;
			}
		} catch (BadLocationException | PatternSyntaxException ex) {
			UIManager.getLookAndFeel().provideErrorFeedback(field);
			field.setBackground(WARNING_COLOR);
		}
		textArea.repaint();
	}
}
