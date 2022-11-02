package lab.component.border;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 选中绘制特殊边框的按钮
 */
public class RadioButtonSelectedBorder extends JPanel {
	private RadioButtonSelectedBorder() {
		super();
		JRadioButton r1 = makeRadioButton("red", new ColorIcon(Color.RED));
		r1.setSelectedIcon(new SelectedIcon(r1.getIcon(), Color.GREEN));

		JRadioButton r2 = makeRadioButton("green", new ColorIcon(Color.GREEN));
		r2.setSelectedIcon(new SelectedIcon(r2.getIcon(), Color.BLUE));

		JRadioButton r3 = makeRadioButton("blue", new ColorIcon(Color.BLUE));
		r3.setSelectedIcon(new SelectedIcon(r3.getIcon(), Color.RED));

		ButtonGroup bg1 = new ButtonGroup();
		Stream.of(r1, r2, r3).forEach(r -> {
			bg1.add(r);
			add(r);
		});

		JRadioButton r4 = makeRadioButton("test1.jpg", makeIcon("test1.jpg"));
		r4.setSelectedIcon(new SelectedIcon(r4.getIcon(), Color.GREEN));

		JRadioButton r5 = makeRadioButton("test2.jpg", makeIcon("test2.jpg"));
		r5.setSelectedIcon(new SelectedIcon(r5.getIcon(), Color.BLUE));

		JRadioButton r6 = makeRadioButton("test3.jpg", makeIcon("test3.jpg"));
		r6.setSelectedIcon(new SelectedIcon(r6.getIcon(), Color.RED));

		ButtonGroup bg2 = new ButtonGroup();
		Stream.of(r4, r5, r6).forEach(r -> {
			bg2.add(r);
			add(r);
		});
	}

	private static JRadioButton makeRadioButton(String text, Icon icon) {
		JRadioButton radio = new JRadioButton(text, icon);
		radio.setVerticalAlignment(SwingConstants.BOTTOM);
		radio.setVerticalTextPosition(SwingConstants.BOTTOM);
		radio.setHorizontalAlignment(SwingConstants.CENTER);
		radio.setHorizontalTextPosition(SwingConstants.CENTER);
		return radio;
	}

	private static Icon makeIcon(String path) {
		try {
			return Optional.ofNullable(ImageIO.read(RadioButtonSelectedBorder.class.getResourceAsStream(path)))
					.map(u -> (Icon) new ImageIcon(u)).orElseGet(MissingIcon::new);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout());
			p.add(new RadioButtonSelectedBorder());
			SwingTestUtil.test();
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
		g2.dispose();
	}

	@Override
	public int getIconWidth() {
		return 64;
	}

	@Override
	public int getIconHeight() {
		return 48;
	}
}

class SelectedIcon implements Icon {
	private final Icon icon;
	private final Color color;

	protected SelectedIcon(Icon icon, Color color) {
		this.icon = icon;
		this.color = color;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.translate(x, y);
		icon.paintIcon(c, g2, 0, 0);
		Path2D triangle = new Path2D.Double();
		triangle.moveTo(getIconWidth(), getIconHeight() / 2d);
		triangle.lineTo(getIconWidth(), getIconHeight());
		triangle.lineTo(getIconWidth() - getIconHeight() / 2d, getIconHeight());
		triangle.closePath();

		g2.setPaint(color);
		g2.fill(triangle);
		g2.setStroke(new BasicStroke(3f));
		g2.drawRect(0, 0, getIconWidth(), getIconHeight());
		g2.setPaint(Color.WHITE);
		Font f = g2.getFont();
		g2.drawString("✔", getIconWidth() - f.getSize(), getIconHeight() - 3);
		g2.dispose();
	}

	@Override
	public int getIconWidth() {
		return icon.getIconWidth();
	}

	@Override
	public int getIconHeight() {
		return icon.getIconHeight();
	}
}

class MissingIcon implements Icon {
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g.create();

		int w = getIconWidth();
		int h = getIconHeight();
		int gap = w / 5;

		g2.setColor(Color.WHITE);
		g2.fillRect(x, y, w, h);

		g2.setColor(Color.RED);
		g2.setStroke(new BasicStroke(w / 8f));
		g2.drawLine(x + gap, y + gap, x + w - gap, y + h - gap);
		g2.drawLine(x + gap, y + h - gap, x + w - gap, y + gap);

		g2.dispose();
	}

	@Override
	public int getIconWidth() {
		return 92;
	}

	@Override
	public int getIconHeight() {
		return 69;
	}
}
