package lab.animator;

import com.component.util.GraphicsUtil;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 标题淡进淡出动画的画框(原生的)
 */
public class EaseInOut extends JPanel {
	private EaseInOut() {
		super();
		Icon icon = null;
		try {
			BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/img/beauty.jpg"));
			image = GraphicsUtil.createThumbnail(image, 400);
			icon = new ImageIcon(image);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		String txt = "Mini-size 86Key Japanese Keyboard\n  Model No: DE-SK-86BK\n  SERIAL NO: 0000";
		add(new ImageCaptionLabel(txt, icon));
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout());
			p.add(new EaseInOut());
			SwingTestUtil.test();
		});
	}
}

class ImageCaptionLabel extends JLabel {
	private final JTextArea textArea = new JTextArea() {
		private transient MouseListener listener;

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setPaint(getBackground());
			g2.fillRect(0, 0, getWidth(), getHeight());
			g2.dispose();
			super.paintComponent(g);
		}

		@Override
		public void updateUI() {
			removeMouseListener(listener);
			super.updateUI();
			setFont(getFont().deriveFont(11f));
			setOpaque(false);
			setEditable(false);
			// setFocusable(false);
			setBackground(new Color(0x0, true));
			setForeground(Color.WHITE);
			setBorder(BorderFactory.createEmptyBorder(2, 4, 4, 4));
			listener = new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					dispatchMouseEvent(e);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					dispatchMouseEvent(e);
				}
			};
			addMouseListener(listener);
		}
		// @Override public boolean contains(int x, int y) {
		//   return false;
		// }
	};
	private final transient LabelHandler handler = new LabelHandler(textArea);

	protected void dispatchMouseEvent(MouseEvent e) {
		Component src = e.getComponent();
		// this: target Component;
		this.dispatchEvent(SwingUtilities.convertMouseEvent(src, e, this));
	}

	@Override
	public void updateUI() {
		super.updateUI();
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(new Color(0xDE_DE_DE)),
				BorderFactory.createLineBorder(Color.WHITE, 4)));
		setLayout(new OverlayLayout(this) {
			@Override
			public void layoutContainer(Container parent) {
				// Insets insets = parent.getInsets();
				int ncomponents = parent.getComponentCount();
				if (ncomponents == 0) {
					return;
				}
				int width = parent.getWidth(); // - insets.left - insets.right;
				int height = parent.getHeight(); // - insets.left - insets.right;
				int x = 0; // insets.left; int y = insets.top;
				int tah = handler.getTextAreaHeight();
				// for (int i = 0; i < ncomponents; i++) {
				Component c = parent.getComponent(0); // = textArea;
				c.setBounds(x, height - tah, width, c.getPreferredSize().height);
				// }
			}
		});
	}

	protected ImageCaptionLabel(String caption, Icon icon) {
		super();
		setIcon(icon);
		textArea.setText(caption);
		add(textArea);
		addMouseListener(handler);
		addHierarchyListener(handler);
	}
}

class LabelHandler extends MouseAdapter implements HierarchyListener {
	private final Timer animator = new Timer(5, e -> updateTextAreaLocation());
	private final Component textArea;
	private int areaHeight;
	private int count;
	private int direction;

	protected LabelHandler(Component textArea) {
		super();
		this.textArea = textArea;
	}

	private void updateTextAreaLocation() {
		double height = textArea.getPreferredSize().getHeight();
		double a = AnimationUtil.easeInOut(count / height);
		count += direction;
		areaHeight = (int) (.5 + a * height);
		textArea.setBackground(new Color(0f, 0f, 0f, (float) (.6 * a)));
		if (direction > 0) { // show
			if (areaHeight >= textArea.getPreferredSize().height) {
				areaHeight = textArea.getPreferredSize().height;
				animator.stop();
			}
		} else { // hide
			if (areaHeight <= 0) {
				areaHeight = 0;
				animator.stop();
			}
		}
		Container p = SwingUtilities.getUnwrappedParent(textArea);
		p.revalidate();
		p.repaint();
	}

	public int getTextAreaHeight() {
		return areaHeight;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (animator.isRunning() || areaHeight == textArea.getPreferredSize().height) {
			return;
		}
		this.direction = 1;
		animator.start();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (animator.isRunning()) {
			return;
		}
		Component c = e.getComponent();
		if (c.contains(e.getPoint()) && areaHeight == textArea.getPreferredSize().height) {
			return;
		}
		this.direction = -1;
		animator.start();
	}

	@Override
	public void hierarchyChanged(HierarchyEvent e) {
		boolean b = (e.getChangeFlags() & HierarchyEvent.DISPLAYABILITY_CHANGED) != 0;
		if (b && !e.getComponent().isDisplayable()) {
			animator.stop();
		}
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
		return 240;
	}

	@Override
	public int getIconHeight() {
		return 160;
	}
}

