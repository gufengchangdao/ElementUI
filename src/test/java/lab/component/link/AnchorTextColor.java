package lab.component.link;
// vim:set fileencoding=utf-8:
// @homepage@


import com.component.util.SwingTestUtil;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.awt.*;

/**
 * 链接文本
 *
 * <ul>
 *     <li>使用JLabel生成的链接文本，不会响应事件</li>
 *     <li>使用JEditorPane生成的链接文本，可以选中，鼠标悬停会变成手形</li>
 * </ul>
 */
public final class AnchorTextColor extends JPanel {
	private static final String SITE = "https://ateraimemo.com/";
	private static final String HREF = String.format("<html><a href='%s'>%s</a>", SITE, SITE);

	private AnchorTextColor() {
		super(new GridLayout(3, 1));
		add(makeUrlPanel("Default", HREF));

		// 设置全局样式
		HTMLEditorKit kit = new HTMLEditorKit();
		StyleSheet styleSheet = kit.getStyleSheet();
		styleSheet.addRule("a{color:#FF0000;}");
		add(makeUrlPanel("styleSheet.addRule(\"a{color:#FF0000;}\")", HREF));

		String html = String.format("<html><a style='color:#00FF00' href='%s'>%s</a>", SITE, SITE);
		add(makeUrlPanel("<a style='color:#00FF00'...", html));

		setPreferredSize(new Dimension(320, 240));
	}

	private static Component makeUrlPanel(String title, String href) {
		JPanel p = new JPanel(new GridBagLayout());
		p.setBorder(BorderFactory.createTitledBorder(title));

		JEditorPane editor = new JEditorPane("text/html", href);
		editor.setOpaque(false);
		editor.setEditable(false);
		editor.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 0);

		c.gridx = 0;
		c.anchor = GridBagConstraints.LINE_END;
		p.add(new JLabel("JLabel:"), c);
		p.add(new JLabel("JEditorPane:"), c);

		c.gridx = 1;
		c.weightx = 1d;
		c.anchor = GridBagConstraints.LINE_START;
		p.add(new JLabel(href), c);
		p.add(editor, c);

		return p;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			Container p = SwingTestUtil.init(new FlowLayout());
			p.add(new AnchorTextColor());
			SwingTestUtil.test();
		});
	}
}