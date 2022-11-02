package lab.other.draw;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

/**
 * 操控像素数组实现图片过渡
 */
public class RandomDissolveTest extends JPanel {
	private RandomDissolveTest() {
		super(new BorderLayout());
		BufferedImage i1 = makeImage("example/test.png");
		BufferedImage i2 = makeImage("example/test.jpg");

		RandomDissolve randomDissolve = new RandomDissolve(i1, i2);
		JButton button = new JButton("change");
		button.addActionListener(e -> randomDissolve.animationStart());

		add(randomDissolve);
		add(button, BorderLayout.NORTH);
		setPreferredSize(new Dimension(320, 240));
	}

	private BufferedImage makeImage(String path) {
		URL url = Thread.currentThread().getContextClassLoader().getResource(path);
		Icon icon = null;
		try {
			icon = new ImageIcon(ImageIO.read(new File("src/main/java/RandomDissolve/src/java/" + path)));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		int w = icon.getIconWidth();
		int h = icon.getIconHeight();
		BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = img.createGraphics();
		icon.paintIcon(this, g2, 0, 0);
		g2.dispose();
		return img;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(RandomDissolveTest::createAndShowGui);
	}

	private static void createAndShowGui() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
		         UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
			Toolkit.getDefaultToolkit().beep();
		}
		JFrame frame = new JFrame("@title@");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().add(new RandomDissolveTest());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}

class RandomDissolve extends JComponent implements ActionListener {
	private static final int STAGES = 16;
	private final Random rnd = new Random();
	private final Timer animator;
	private final transient BufferedImage image1;
	private final transient BufferedImage image2;
	private transient BufferedImage buf;
	private boolean mode = true;
	private int currentStage;
	private int[] src;
	private int[] dst;
	private int[] step;

	protected RandomDissolve(BufferedImage i1, BufferedImage i2) {
		super();
		this.image1 = i1;
		this.image2 = i2;
		this.buf = copyImage(mode ? image2 : image1);
		animator = new Timer(10, this);
	}

	private boolean nextStage() {
		if (currentStage > 0) {
			currentStage = currentStage - 1;
			for (int i = 0; i < step.length; i++) {
				if (step[i] == currentStage) {
					src[i] = dst[i];
				}
			}
			return true;
		} else {
			return false;
		}
	}

	private static BufferedImage copyImage(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = result.createGraphics();
		g2.drawRenderedImage(image, null);
		g2.dispose();
		return result;
	}

	private static int[] getData(BufferedImage image) {
		WritableRaster wr = image.getRaster();
		DataBufferInt dbi = (DataBufferInt) wr.getDataBuffer();
		return dbi.getData();
		// return ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	}

	public void animationStart() {
		currentStage = STAGES;
		buf = copyImage(mode ? image2 : image1);
		src = getData(buf);
		dst = getData(copyImage(mode ? image1 : image2));
		step = new int[src.length];
		mode ^= true;
		for (int i = 0; i < step.length; i++) {
			step[i] = rnd.nextInt(currentStage);
		}
		animator.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setPaint(getBackground());
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.drawImage(buf, 0, 0, buf.getWidth(), buf.getHeight(), this);
		g2.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (nextStage()) {
			repaint();
		} else {
			animator.stop();
		}
	}
}
