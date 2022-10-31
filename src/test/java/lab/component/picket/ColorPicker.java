package lab.component.picket;

import com.component.util.GraphicsUtil;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 从图片中获取RGB值
 */
public class ColorPicker extends JPanel {
	private final Rectangle viewRect = new Rectangle();
	private final Rectangle iconRect = new Rectangle();
	private final Rectangle textRect = new Rectangle();

	private ColorPicker() throws IOException {
		super(new BorderLayout());
		JTextField field = new JTextField("#FFFFFF");
		field.setEditable(false);
		field.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
		field.setColumns(8);
		JLabel sample = new JLabel(new ColorIcon(Color.WHITE));

		JPanel box = new JPanel();
		box.add(sample);
		box.add(field);

		BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/img/beauty.jpg"));
		BufferedImage image2 = GraphicsUtil.createThumbnail(image, 400);

		JLabel label = new JLabel(new ImageIcon(image2));
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				updateViewRect(label);
				Point pt = e.getPoint();
				if (iconRect.contains(pt)) {
					int argb = image2.getRGB(pt.x - iconRect.x, pt.y - iconRect.y);
					field.setText(String.format("#%06X", argb & 0x00_FF_FF_FF));
					sample.setIcon(new ColorIcon(new Color(argb, true)));
				}
			}
		});
		add(label);
		add(box, BorderLayout.SOUTH);
		setPreferredSize(new Dimension(320, 240));
	}

	public void updateViewRect(JLabel c) {
		iconRect.setBounds(0, 0, 0, 0);
		textRect.setBounds(0, 0, 0, 0);
		SwingUtilities.calculateInnerArea(c, viewRect);
		SwingUtilities.layoutCompoundLabel(
				c,
				c.getFontMetrics(c.getFont()),
				c.getText(),
				c.getIcon(),
				c.getVerticalAlignment(),
				c.getHorizontalAlignment(),
				c.getVerticalTextPosition(),
				c.getHorizontalTextPosition(),
				viewRect,
				iconRect,
				textRect,
				c.getIconTextGap());
	}

	private static BufferedImage makeMissingImage() {
		Icon missingIcon = UIManager.getIcon("OptionPane.errorIcon");
		int w = missingIcon.getIconWidth();
		int h = missingIcon.getIconHeight();
		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = bi.createGraphics();
		missingIcon.paintIcon(null, g2, 0, 0);
		g2.dispose();
		return bi;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				JPanel p = SwingTestUtil.init(new MigLayout());
				p.add(new ColorPicker());
				SwingTestUtil.test();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
	}
}

class ColorIcon implements Icon {
	private final Color color;

	protected ColorIcon(Color color) {
		this.color = color;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.translate(x, y);
		g2.setPaint(color);
		g2.fillRect(0, 0, getIconWidth(), getIconHeight());
		g2.setPaint(Color.BLACK);
		g2.drawRect(0, 0, getIconWidth(), getIconHeight());
		g2.dispose();
	}

	@Override
	public int getIconWidth() {
		return 32;
	}

	@Override
	public int getIconHeight() {
		return 32;
	}
}
