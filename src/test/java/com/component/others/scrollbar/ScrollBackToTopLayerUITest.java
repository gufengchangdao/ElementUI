package com.component.others.scrollbar;

import com.component.form.area.LineNumberView;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;

public class ScrollBackToTopLayerUITest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new BorderLayout());

			JTextArea textArea = new JTextArea();
			textArea.setText(String.join("\n", Collections.nCopies(2000, "1111111111111")));
			JScrollPane scroll1 = new JScrollPane(textArea);
			scroll1.setRowHeaderView(new LineNumberView(textArea));
			textArea.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0));
			textArea.setEditable(false);

			JTable table = new JTable(500, 3);
			JScrollPane scroll2 = new JScrollPane(table);
			SwingUtilities.invokeLater(() -> table.scrollRectToVisible(table.getCellRect(500, 0, true)));

			JTabbedPane tabbedPane = new JTabbedPane();
			tabbedPane.addTab("JTextArea", new JLayer<>(scroll1, new ScrollBackToTopLayerUI()));
			tabbedPane.addTab("JTable", new JLayer<>(scroll2, new ScrollBackToTopLayerUI()));
			p.add(tabbedPane);

			SwingTestUtil.test();
		});
	}
}