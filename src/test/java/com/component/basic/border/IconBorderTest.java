package com.component.basic.border;

import com.component.common.component.BaseInputField;
import com.component.svg.icon.fill.StarSvg;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

public class IconBorderTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			BaseInputField test = new BaseInputField(15, "你好");
			IconBorder border = new IconBorder(test.getBorder(), StarSvg.of(16, 16), StarSvg.of(16, 16));
			test.setBorder(border);
			JButton button = new JButton("改变大小");
			Container contentPane = SwingTestUtil.getFrame().getContentPane();
			button.addActionListener(e -> {
				test.setPreferredSize(new Dimension(200, 50));
				test.setSize(200, 50);
				border.adjustIconSize(test);
				contentPane.validate();
				contentPane.repaint();
			});
			SwingTestUtil.test(test, button);
		});
	}
}
