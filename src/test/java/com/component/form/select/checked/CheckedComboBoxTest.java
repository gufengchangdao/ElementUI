package com.component.form.select.checked;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;


public class CheckedComboBoxTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout("wrap 1"));
			p.add(new JLabel("Default:"));
			p.add(new JComboBox<>(makeModel()));
			p.add(Box.createVerticalStrut(20));
			p.add(new JLabel("CheckedComboBox:"));
			p.add(new CheckedComboBox<>(makeModel()));
			p.add(Box.createVerticalStrut(20));
			p.add(new JLabel("CheckedComboBox(Windows):"));
			p.add(new WindowsCheckedComboBox<>(makeModel()));

			SwingTestUtil.test();
		});
	}

	private static ComboBoxModel<CheckableItem> makeModel() {
		CheckableItem[] m = {
				new CheckableItem("aaa", false),
				new CheckableItem("bb", true),
				new CheckableItem("111", false),
				new CheckableItem("33333", true),
				new CheckableItem("2222", true),
				new CheckableItem("c", false)
		};
		return new DefaultComboBoxModel<>(m);
	}
}
