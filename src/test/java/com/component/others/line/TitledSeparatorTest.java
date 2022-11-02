package com.component.others.line;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class TitledSeparatorTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout("wrap 1", "grow"));

			p.add(new TitledSeparator("TitledBorder", 2, TitledBorder.DEFAULT_POSITION), "growx");
			p.add(new JCheckBox("JCheckBox 0"));
			p.add(new JCheckBox("JCheckBox 1"));
			p.add(Box.createVerticalStrut(10));

			Color color = new Color(0x64_B4_C8);
			p.add(new TitledSeparator("TitledBorder ABOVE TOP", color, 2, TitledBorder.ABOVE_TOP), "growx");
			p.add(new JCheckBox("JCheckBox 2"));
			p.add(new JCheckBox("JCheckBox 3"));
			p.add(Box.createVerticalStrut(10));

			p.add(new JSeparator(), "growx");
			p.add(new JCheckBox("JCheckBox 4"));
			p.add(new JCheckBox("JCheckBox 5"));
			// box.add(Box.createVerticalStrut(8));

			SwingTestUtil.test();
		});
	}
}