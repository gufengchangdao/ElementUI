package com.component.notice.alert;

import com.component.util.GraphicsUtil;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class LightboxGlassPaneTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				BufferedImage img = ImageIO.read(LightboxGlassPaneTest.class.getResourceAsStream("/img/beauty.jpg"));
				img = GraphicsUtil.createThumbnail(img, 400);

				JPanel p = SwingTestUtil.init(new MigLayout());
				p.getRootPane().setGlassPane(new LightboxGlassPane(img));
				p.getRootPane().getGlassPane().setVisible(false);

				JButton button = new JButton("Open");
				button.addActionListener(e -> p.getRootPane().getGlassPane().setVisible(true));
				p.add(button);

				SwingTestUtil.test();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}
}