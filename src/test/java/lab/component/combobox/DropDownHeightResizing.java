package lab.component.combobox;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.stream.Stream;

/**
 * 可调节下拉列表高度的 JComboBox
 */
public class DropDownHeightResizing extends JPanel {
	private DropDownHeightResizing() {
		super(new FlowLayout(FlowLayout.LEADING));
		Font[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
		DefaultListModel<String> m1 = new DefaultListModel<>();
		Stream.of(fonts).map(Font::getFontName).forEach(m1::addElement);
		JList<String> list = new JList<>(m1);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JPopupMenu popup = new JPopupMenu();
		popup.setBorder(BorderFactory.createEmptyBorder());
		popup.setPopupSize(240, 120);

		JComboBox<String> combo = makeComboBox(fonts, list, popup);
		list.addListSelectionListener(e -> combo.setSelectedIndex(list.getSelectedIndex()));
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() - 1 > 0) {
					combo.setSelectedIndex(list.getSelectedIndex());
					popup.setVisible(false);
				}
			}
		});
		combo.addItemListener(e -> {
			int idx = combo.getSelectedIndex();
			list.setSelectedIndex(idx);
			list.scrollRectToVisible(list.getCellBounds(idx, idx));
		});

		// 更换原有下拉列表
		JScrollPane scroll = new JScrollPane(list);
		scroll.setBorder(BorderFactory.createEmptyBorder());
		scroll.setViewportBorder(BorderFactory.createEmptyBorder());
		popup.add(makeResizePanel(scroll, popup));
		add(combo);
	}

	private JComboBox<String> makeComboBox(Font[] fonts, JList<String> list, JPopupMenu popup) {
		DefaultComboBoxModel<String> m2 = new DefaultComboBoxModel<>();
		Stream.of(fonts).map(Font::getFontName).forEach(m2::addElement);
		JComboBox<String> combo = new JComboBox<String>(m2) {
			private transient PopupMenuListener handler;

			@Override
			public void updateUI() {
				removePopupMenuListener(handler);
				super.updateUI();
				handler = new PopupMenuListener() {
					@Override
					public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
						JComboBox<?> c = (JComboBox<?>) e.getSource();
						list.setSelectedIndex(c.getSelectedIndex());
						EventQueue.invokeLater(() -> popup.show(c, 0, c.getHeight()));
					}

					@Override
					public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
						// rect.setSize(window.getSize());
					}

					@Override
					public void popupMenuCanceled(PopupMenuEvent e) {
						// rect.setSize(window.getSize());
					}
				};
				addPopupMenuListener(handler);
			}

			@Override
			public Dimension getPreferredSize() {
				Dimension d = super.getPreferredSize();
				d.width = Math.min(d.width, 240);
				return d;
			}
		};
		combo.setMaximumRowCount(1);
		return combo;
	}

	private static JPanel makeResizePanel(JScrollPane scroll, JPopupMenu popup) {
		JLabel bottom = new JLabel("", new DotIcon(), SwingConstants.CENTER);
		MouseInputListener rwl = new ResizeWindowListener(popup);
		bottom.addMouseListener(rwl);
		bottom.addMouseMotionListener(rwl);
		bottom.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
		bottom.setOpaque(true);
		bottom.setBackground(new Color(0xE0_E0_E0));
		bottom.setFocusable(false);

		JPanel resizePanel = new JPanel(new BorderLayout());
		resizePanel.add(scroll);
		resizePanel.add(bottom, BorderLayout.SOUTH);
		resizePanel.add(Box.createHorizontalStrut(240), BorderLayout.NORTH);
		resizePanel.setBorder(BorderFactory.createLineBorder(new Color(0x64_64_64)));
		return resizePanel;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout());
			p.add(new DropDownHeightResizing());
			SwingTestUtil.test();
		});
	}
}

class ResizeWindowListener extends MouseInputAdapter {
	private final Rectangle rect = new Rectangle();
	private final JPopupMenu popup;
	private final Point startPt = new Point();
	private final Dimension startDim = new Dimension();

	protected ResizeWindowListener(JPopupMenu popup) {
		super();
		this.popup = popup;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		rect.setSize(popup.getSize());
		startDim.setSize(popup.getSize());
		startPt.setLocation(e.getComponent().getLocationOnScreen());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		rect.height = startDim.height + e.getLocationOnScreen().y - startPt.y;
		popup.setPreferredSize(rect.getSize());
		Window w = SwingUtilities.getWindowAncestor(popup);
		if (w != null && w.getType() == Window.Type.POPUP) {
			// Popup$HeavyWeightWindow
			w.setSize(rect.width, rect.height);
		} else {
			// Popup$LightWeightWindow
			popup.pack();
		}
		// Container p = popup.getTopLevelAncestor();
		// if (p instanceof JWindow && ((Window) p).getType() == Window.Type.POPUP) {
		//   p.setSize(rect.width, rect.height);
		// } else {
		//   popup.pack();
		// }
	}
}

class DotIcon implements Icon {
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.translate(x, y);
		g2.setPaint(Color.GRAY);
		int dots = 4;
		int gap = 4;
		int start = getIconWidth() / 2 - (dots - 1) * 2;
		int h = getIconHeight() / 2;
		for (int i = 0; i < dots; i++) {
			g2.fillRect(start + gap * i, h, 2, 2);
		}
		g2.dispose();
	}

	@Override
	public int getIconWidth() {
		return 32;
	}

	@Override
	public int getIconHeight() {
		return 5;
	}
}
