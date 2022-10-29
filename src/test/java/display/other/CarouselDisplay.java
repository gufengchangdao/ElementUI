package display.other;

import com.component.others.carousel.HorizontalCarouselPanel;
import com.component.util.ImageUtil;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class CarouselDisplay {
	public static void main(String[] args) throws IOException {
		Container p = SwingTestUtil.init(new MigLayout("", "grow, center"));

		ArrayList<JLabel> list = new ArrayList<>();
		for (int i = 1; i <= 4; i++) {
			BufferedImage image = ImageIO.read(CarouselDisplay.class.getResourceAsStream("/" + i + ".jpg"));
			System.out.println(image.getWidth());
			image = ImageUtil.createThumbnailFast(image, 500, 400);
			JLabel label = new JLabel(new ImageIcon(image));
			list.add(label);
		}

		HorizontalCarouselPanel<JLabel> c = new HorizontalCarouselPanel<>(list, System.out::println);
		c.setBorder(BorderFactory.createLineBorder(Color.RED));
		p.add(c);

		SwingTestUtil.test();
	}
}
