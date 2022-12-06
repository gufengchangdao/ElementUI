package lab.other.animatedtray;

import com.component.util.SwingTestUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * 系统托盘
 * <p>
 * 注意，如果使用菜单，就需要把项目编码改为GBK，否则会乱码
 */
public class AnimatedTrayIcon extends JFrame {
	private final JDialog dialog = new JDialog();
	private final Timer animator = new Timer(100, null);
	private int idx;

	public AnimatedTrayIcon() {
		dialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		dialog.setSize(new Dimension(200, 100));
		dialog.setLocationRelativeTo(null);
		dialog.setTitle("TEST: JDialog");

		// 创建系统托盘
		try {
			TrayIcon icon = makeTrayIcon();
			SystemTray.getSystemTray().add(icon);
		} catch (AWTException | IOException ex) {
			throw new RuntimeException(ex);
		}

		setSize(400, 400);
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	private TrayIcon makeTrayIcon() throws IOException {
		Image[] images = new Image[4];
		images[0] = ImageIO.read(getClass().getResourceAsStream("16x16.png"));
		images[1] = ImageIO.read(getClass().getResourceAsStream("16x16l.png"));
		images[2] = images[0];
		images[3] = ImageIO.read(getClass().getResourceAsStream("16x16r.png"));

		MenuItem item1 = new MenuItem("open Frame");
		item1.addActionListener(e -> setVisible(true));

		MenuItem item2 = new MenuItem("open dialog");
		item2.addActionListener(e -> dialog.setVisible(true));

		MenuItem item3 = new MenuItem("start animator");
		item3.addActionListener(e -> animator.start());

		MenuItem item4 = new MenuItem("end animator");
		item4.addActionListener(e -> {
			animator.stop();
			SystemTray tray = SystemTray.getSystemTray();
			// 回到第一张图片上
			Stream
					.of(tray.getTrayIcons())
					.forEach(i -> i.setImage(images[0]));
		});

		MenuItem item5 = new MenuItem("exit");
		item5.addActionListener(e -> {
			animator.stop();
			SystemTray tray = SystemTray.getSystemTray();
			Stream.of(tray.getTrayIcons()).forEach(tray::remove);
			Stream.of(Frame.getFrames()).forEach(Frame::dispose);
		});

		PopupMenu popup = new PopupMenu();
		popup.add(item1);
		popup.add(item2);
		popup.addSeparator();
		popup.add(item3);
		popup.add(item4);
		popup.addSeparator();
		popup.add(item5);

		TrayIcon icon = new TrayIcon(images[0], "这是系统托盘", popup);
		animator.addActionListener(e -> {
			icon.setImage(images[idx]);
			idx = (idx + 1) % images.length;
		});
		return icon;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			new AnimatedTrayIcon().setVisible(true);
		});
	}

	/**
	 * 获取默认的系统托盘图标
	 */
	private static Image makeDefaultTrayImage() {
		Icon icon = UIManager.getIcon("InternalFrame.icon");
		int w = icon.getIconWidth();
		int h = icon.getIconHeight();
		BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = bi.createGraphics();
		icon.paintIcon(null, g2, 0, 0);
		g2.dispose();
		return bi;
	}
}
