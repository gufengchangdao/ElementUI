package lab.other.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

/**
 * 更改Frame的形状
 */
public class WindowShape extends JPanel {
	private static final FontRenderContext FRC = new FontRenderContext(null, true, true);
	private static final Font FONT = new Font(Font.SERIF, Font.PLAIN, 300);
	private transient JFrame frame;

	private WindowShape() {
		super();
		JTextField textField = new JTextField("★", 20);
		JLabel label = new JLabel("", SwingConstants.CENTER);

		JToggleButton button = new JToggleButton("show");
		button.addActionListener(e -> {
			AbstractButton btn = (AbstractButton) e.getSource();
			if (Objects.isNull(frame)) {
				frame = new JFrame();
				frame.setUndecorated(true);
				frame.setAlwaysOnTop(true);
				frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
				frame.getContentPane().add(label);
				frame.getContentPane().setBackground(Color.GREEN);
				frame.pack();
			}
			if (btn.isSelected()) {
				String str = textField.getText().trim();
				// label.setText(str);
				TextLayout tl = new TextLayout(str, FONT, FRC);
				Rectangle2D b = tl.getBounds();
				Shape shape = tl.getOutline(AffineTransform.getTranslateInstance(-b.getX(), -b.getY()));

				frame.setBounds(shape.getBounds());
				// frame.setSize(shape.getBounds().width, shape.getBounds().height);
				// 关键所在
				frame.setShape(shape); // JDK 1.7.0
				frame.setLocationRelativeTo(btn.getRootPane());
				frame.setVisible(true);
			} else {
				frame.setVisible(false);
			}
		});

		add(textField);
		add(button);
		DragWindowListener dwl = new DragWindowListener();
		label.addMouseListener(dwl);
		label.addMouseMotionListener(dwl);
		setPreferredSize(new Dimension(320, 240));
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(WindowShape::createAndShowGui);
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
		frame.getContentPane().add(new WindowShape());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	static class DragWindowListener extends MouseAdapter {
		private final Point startPt = new Point();

		@Override
		public void mousePressed(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				startPt.setLocation(e.getPoint());
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			Component c = SwingUtilities.getRoot(e.getComponent());
			if (c instanceof Window && SwingUtilities.isLeftMouseButton(e)) {
				Point pt = c.getLocation();
				c.setLocation(pt.x - startPt.x + e.getX(), pt.y - startPt.y + e.getY());
			}
		}
	}
}


