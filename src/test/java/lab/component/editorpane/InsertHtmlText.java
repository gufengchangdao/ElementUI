package lab.component.editorpane;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 插入HTML数据
 */
public class InsertHtmlText extends JPanel {
	private static final String HTML_TEXT = "<html><body>head<table id='log' border='1'></table>tail</body></html>";
	private static final String ROW_TEXT = "<tr bgColor='%s'><td>%s</td><td>%s</td></tr>";

	private InsertHtmlText() {
		super(new BorderLayout());

		HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
		JEditorPane editor = new JEditorPane();
		editor.setEditorKit(htmlEditorKit);
		editor.setText(HTML_TEXT);
		editor.setEditable(false);

		JButton insertAfterStart = new JButton("insertAfterStart");
		insertAfterStart.addActionListener(e -> {
			HTMLDocument doc = (HTMLDocument) editor.getDocument();
			Element element = doc.getElement("log");
			LocalDateTime date = LocalDateTime.now(ZoneId.systemDefault());
			String tag = String.format(ROW_TEXT, "#AEEEEE", "insertAfterStart", date);
			try {
				doc.insertAfterStart(element, tag);
			} catch (BadLocationException | IOException ex) {
				UIManager.getLookAndFeel().provideErrorFeedback(editor);
			}
		});

		JButton insertBeforeEnd = new JButton("insertBeforeEnd");
		insertBeforeEnd.addActionListener(e -> {
			HTMLDocument doc = (HTMLDocument) editor.getDocument();
			Element element = doc.getElement("log");
			LocalDateTime date = LocalDateTime.now(ZoneId.systemDefault());
			String tag = String.format(ROW_TEXT, "#FFFFFF", "insertBeforeEnd", date);
			try {
				doc.insertBeforeEnd(element, tag);
			} catch (BadLocationException | IOException ex) {
				UIManager.getLookAndFeel().provideErrorFeedback(editor);
			}
		});

		Box box = Box.createHorizontalBox();
		box.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		box.add(Box.createHorizontalGlue());
		box.add(insertAfterStart);
		box.add(Box.createHorizontalStrut(5));
		box.add(insertBeforeEnd);
		add(new JScrollPane(editor));
		add(box, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout("fill"));
			p.add(new InsertHtmlText(), "growx, growy");
			SwingTestUtil.test();
		});
	}

}
