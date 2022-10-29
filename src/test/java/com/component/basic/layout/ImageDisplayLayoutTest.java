package com.component.basic.layout;

import com.component.util.SwingTestUtil;
import org.jdesktop.swingx.util.GraphicsUtilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageDisplayLayoutTest {
	public static void main(String[] args) throws IOException {

		EventQueue.invokeLater(() -> {
			try {
				BufferedImage image1 = ImageIO.read(ImageDisplayLayoutTest.class.getResourceAsStream("1.jpg"));
				BufferedImage image2 = ImageIO.read(ImageDisplayLayoutTest.class.getResourceAsStream("2.jpg"));
				BufferedImage image3 = ImageIO.read(ImageDisplayLayoutTest.class.getResourceAsStream("3.jpg"));
				BufferedImage image4 = ImageIO.read(ImageDisplayLayoutTest.class.getResourceAsStream("4.jpg"));

				image1 = GraphicsUtilities.createThumbnailFast(image1, 800, 400);
				image2 = GraphicsUtilities.createThumbnailFast(image2, 400, 400);
				image3 = GraphicsUtilities.createThumbnailFast(image3, 200, 200);
				image4 = GraphicsUtilities.createThumbnailFast(image4, 200, 200);

				SwingTestUtil.loadSkin();
				SwingTestUtil.setSize(850, 800);

				JPanel panel = new JPanel(new ImageDisplayLayout());
				panel.setPreferredSize(new Dimension(800 + 5 * 3, 800));
				panel.setBorder(BorderFactory.createLineBorder(Color.RED));
				panel.add(new JLabel(new ImageIcon(image1)));
				panel.add(new JLabel(new ImageIcon(image2)));
				panel.add(new JLabel(new ImageIcon(image3)));
				panel.add(new JLabel(new ImageIcon(image4)));
				SwingTestUtil.test(panel);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}
}
