package com.component.common.component;

import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.svg.icon.fill.SwordSvg;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class BaseInputFieldTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout());
			BaseInputField field = new BaseInputField(10, "请输入密码");
			p.add(field);

			JButton b = new JButton("添加");
			b.addActionListener(e -> {
				RadianceIcon icon = SwordSvg.of(16, 16);
				field.setLeftIcon(icon);
				field.setRightIcon(icon);
				field.getParent().revalidate();
			});
			p.add(b);
			SwingTestUtil.test();
		});
	}
}
