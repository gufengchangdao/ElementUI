package com.component.form.select.removable;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class RemoveButtonComboBoxTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout("wrap 1"));

			ComboBoxModel<String> m = new DefaultComboBoxModel<>(
					new String[]{"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "bbb", "ccc"});
			JComboBox<String> comboBox = new RemoveButtonComboBox<>(m);
			p.add(comboBox, "w :300:");

			JComboBox<String> comboBox2 = new RemoveButtonComboBox<>(m);
			comboBox2.setEditable(true);
			p.add(comboBox2, "w :300:");

			SwingTestUtil.test();
		});
	}
}