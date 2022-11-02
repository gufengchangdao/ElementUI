package lab.component.inputfield;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 演示格式输入框的使用
 */
public class JFormattedTextFieldTest extends JPanel {
	private JFormattedTextFieldTest() {
		super(new BorderLayout());
		Box box = Box.createVerticalBox();
		box.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

		String mask = "###-####";
		JFormattedTextField field0 = new JFormattedTextField();
		createFormatter(mask)
				.ifPresent(f -> field0.setFormatterFactory(new DefaultFormatterFactory(f)));
		box.add(makeTitledPanel("new MaskFormatter(\"###-####\")", field0));
		box.add(Box.createVerticalStrut(15));

		JFormattedTextField field1 = new JFormattedTextField();
		createFormatter(mask).ifPresent(formatter -> {
			formatter.setPlaceholderCharacter('_');
			field1.setFormatterFactory(new DefaultFormatterFactory(formatter));
		});

		JFormattedTextField field2 = new JFormattedTextField();
		createFormatter(mask).ifPresent(formatter -> {
			formatter.setPlaceholderCharacter('_');
			formatter.setPlaceholder("000-0000");
			field2.setFormatterFactory(new DefaultFormatterFactory(formatter));
		});
		box.add(makeTitledPanel("MaskFormatter#setPlaceholderCharacter('_')", field1));
		box.add(Box.createVerticalStrut(15));

		Font font = new Font(Font.MONOSPACED, Font.PLAIN, 18);
		Insets insets = new Insets(1, 1 + 18 / 2, 1, 1);
		Stream.of(field0, field1, field2).forEach(tf -> {
			tf.setFont(font);
			tf.setColumns(mask.length() + 1); //设置的宽一点，防止输入框宽度不够
			tf.setMargin(insets);
		});
		box.add(makeTitledPanel("MaskFormatter#setPlaceholder(\"000-0000\")", field2));
		box.add(Box.createVerticalGlue());

		add(box, BorderLayout.NORTH);
		setPreferredSize(new Dimension(320, 240));
	}

	private static Optional<MaskFormatter> createFormatter(String s) {
		try {
			return Optional.of(new MaskFormatter(s));
		} catch (ParseException ex) {
			return Optional.empty();
		}
	}

	private static Component makeTitledPanel(String title, Component c) {
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createTitledBorder(title));
		p.add(c);
		return p;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout());
			p.add(new JFormattedTextFieldTest());
			SwingTestUtil.test();
		});
	}
}
