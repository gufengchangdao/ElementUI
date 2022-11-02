package com.component.basic.border;

import com.component.basic.color.ColorUtil;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class RippleBorderTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout());
			p.setBackground(ColorUtil.BORDER_LEVEL1);

			Box box = Box.createVerticalBox();
			box.add(makeLabel("00000000000"));
			box.add(makeLabel("111111111111111111111111111"));
			box.add(makeLabel("1235436873434325"));
			box.add(makeLabel("22222222"));
			box.add(makeLabel("3333333333333333333333333333333333333333333"));
			box.add(makeLabel("1235436873434325"));
			box.add(Box.createVerticalGlue());
			box.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			p.add(box);

			SwingTestUtil.test();
		});
	}

	private static JLabel makeLabel(String str) {
		JLabel label = new JLabel(str);
		label.setBorder(new RippleBorder(label, 10, ColorUtil.PRIMARY));
		return label;
	}
}