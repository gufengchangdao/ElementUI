package com.component.data.badge;

import com.component.basic.color.ColorUtil;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

public class BadgePopupFactoryTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();

			JButton b = new JButton("给按钮添加标签");
			BadgePopupFactory factory = new BadgePopupFactory();
			AtomicReference<LabelPopup> popup = new AtomicReference<>();
			b.addActionListener(e -> {
				// popup.set(factory.openPopup(b, null, ColorUtil.DANGER, Color.WHITE, null));
				popup.set(factory.openPopup(b, "给按钮添加标签", ColorUtil.DANGER, Color.WHITE, null));
				popup.get().show();
			});

			JButton b2 = new JButton("关闭");
			b2.addActionListener(e -> {
				popup.get().hide();
			});
			JButton b3 = new JButton("修改");
			b3.addActionListener(e -> {
				// popup.get().setText(Calendar.getInstance().get(Calendar.SECOND) + "");
				popup.get().setText("超级长的标签");
				popup.get().repaint();
			});
			SwingTestUtil.test(b, b2, b3);
		});
	}
}
