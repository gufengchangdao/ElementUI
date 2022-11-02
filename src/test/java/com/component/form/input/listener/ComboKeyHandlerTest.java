package com.component.form.input.listener;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class ComboKeyHandlerTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout());
			String[] array = {
					"1111", "1111222", "111122233", "111122233444",
					"12345", "67890", "55551", "555512"
			};
			JComboBox<String> combo = new JComboBox<>(array);

			// combo.setEditable(true);
			// combo.setSelectedIndex(-1);
			// JTextField field = (JTextField) combo.getEditor().getEditorComponent();
			// field.setText("");
			// field.addKeyListener(new ComboKeyHandler(combo));

			ComboKeyHandler.addAdviceListener(combo);

			p.add(combo);
			SwingTestUtil.test();
		});
	}
}
