package lab.component.editorpane;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

/**
 * 获取表单提交数据
 */
public class FormSubmitEvent extends JPanel {
	private FormSubmitEvent() {
		super(new GridLayout(2, 1, 5, 5));

		JTextArea logger = new JTextArea();
		logger.setEditable(false);

		JEditorPane editor = new JEditorPane();
		HTMLEditorKit kit = new HTMLEditorKit();
		kit.setAutoFormSubmission(false);
		editor.setEditorKit(kit);
		editor.setEditable(false);

		String form = "<form action='#'><input type='text' name='word' value='12345' /></form>";
		editor.setText("<html><h1>Form test</h1>" + form);
		editor.addHyperlinkListener(e -> {
			if (e instanceof javax.swing.text.html.FormSubmitEvent) {
				String data = ((javax.swing.text.html.FormSubmitEvent) e).getData();
				logger.append(data + "\n");

				String charset = Charset.defaultCharset().toString();
				logger.append("default charset: " + charset + "\n");

				try {
					String txt = URLDecoder.decode(data, charset);
					logger.append(txt + "\n");
				} catch (UnsupportedEncodingException ex) {
					ex.printStackTrace();
					logger.append(ex.getMessage() + "\n");
				}
			}
		});

		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		add(new JScrollPane(editor));
		add(new JScrollPane(logger));
		setPreferredSize(new Dimension(320, 240));
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout());
			p.add(new FormSubmitEvent());
			SwingTestUtil.test();
		});
	}
}
