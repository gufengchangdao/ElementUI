package com.component.form.area;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class LineNumberViewTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout());
			JTextArea textArea = new JTextArea();
			JScrollPane scroll = new JScrollPane(textArea);
			scroll.setRowHeaderView(new LineNumberView(textArea));
			textArea.setText("aaa aaa aaa\nbbb bbb bbb bbb bbb\n\n\n\n\nccc ccc ccc ccc");
			p.add(scroll);

			SwingTestUtil.test();
		});
	}
}