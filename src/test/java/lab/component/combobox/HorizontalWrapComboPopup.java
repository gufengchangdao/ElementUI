package lab.component.combobox;

import javax.accessibility.Accessible;
import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;

/**
 * 自定义JComboBox下拉列表样式
 */
public class HorizontalWrapComboPopup extends JPanel {
	private HorizontalWrapComboPopup() {
		super(new BorderLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridheight = 1;
		c.gridwidth = 1;

		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.0;
		c.insets = new Insets(5, 5, 5, 0);
		c.anchor = GridBagConstraints.WEST;

		JPanel p = new JPanel(new GridBagLayout());
		p.add(new JLabel("设置下拉列表的大小"), c);

		c.gridx = 1;
		c.weightx = 1.0;
		p.add(makeComboBox1(makeModel(), new ColorIcon(Color.DARK_GRAY), 3), c);

		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.0;
		c.insets = new Insets(5, 5, 5, 0);
		c.anchor = GridBagConstraints.WEST;
		p.add(new JLabel("使用监听器实现分别设置输入框和下拉列表的大小"), c);

		c.gridx = 1;
		c.weightx = 1.0;
		p.add(makeComboBox2(makeModel(), new ColorIcon(Color.DARK_GRAY), 3), c);

		add(p, BorderLayout.NORTH);
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setPreferredSize(new Dimension(500, 250));
	}

	public static JComboBox<Icon> makeComboBox1(ComboBoxModel<Icon> model, Icon proto, int rowCount) {
		return new JComboBox(model) {
			@Override
			public Dimension getPreferredSize() {
				Insets i = getInsets();
				int w = proto.getIconWidth();
				int h = proto.getIconHeight();
				int totalCount = getItemCount();
				int columnCount = totalCount / rowCount + (totalCount % rowCount == 0 ? 0 : 1);
				return new Dimension(w * columnCount + i.left + i.right, h + i.top + i.bottom);
			}

			@Override
			public void updateUI() {
				super.updateUI();
				setMaximumRowCount(rowCount);
				setPrototypeDisplayValue(proto);

				Accessible o = getAccessibleContext().getAccessibleChild(0);
				if (o instanceof ComboPopup) {
					JList<?> list = ((ComboPopup) o).getList();
					list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
					list.setVisibleRowCount(rowCount);
					list.setFixedCellWidth(proto.getIconWidth());
					list.setFixedCellHeight(proto.getIconHeight());
				}
			}
		};
	}

	public static JComboBox<Icon> makeComboBox2(ComboBoxModel<Icon> model, Icon proto, int rowCount) {
		return new JComboBox<Icon>(model) {
			private PopupMenuListener listener;

			@Override
			public Dimension getPreferredSize() {
				Insets i = getInsets();
				int w = proto.getIconWidth();
				int h = proto.getIconHeight();
				int buttonWidth = 20; // 这里指定输入框的宽度，可以为设置下拉列表的宽度相分开
				return new Dimension(buttonWidth + w + i.left + i.right, h + i.top + i.bottom);
			}

			@Override
			public void updateUI() {
				setRenderer(null);
				removePopupMenuListener(listener);
				super.updateUI();
				setMaximumRowCount(rowCount);
				setPrototypeDisplayValue(proto);
				ListCellRenderer<? super Icon> r = getRenderer();
				setRenderer((list, value, index, isSelected, cellHasFocus) -> {
					Component c = r.getListCellRendererComponent(
							list, value, index, isSelected, cellHasFocus);
					if (c instanceof JLabel) {
						// value是一个icon对象，因此设置渲染器正确展示icon
						JLabel l = (JLabel) c;
						l.setIcon(value);
						l.setBorder(BorderFactory.createEmptyBorder());
					}
					return c;
				});

				Accessible o = getAccessibleContext().getAccessibleChild(0);
				if (o instanceof ComboPopup) {
					JList<?> list = ((ComboPopup) o).getList();
					list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
					list.setVisibleRowCount(rowCount);
					list.setFixedCellWidth(proto.getIconWidth());
					list.setFixedCellHeight(proto.getIconHeight());
				}

				listener = new PopupMenuListener() {
					private boolean adjusting;

					@Override
					public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
						JComboBox<?> combo = (JComboBox<?>) e.getSource();

						Insets i = combo.getInsets();
						int totalCount = getItemCount();
						int columnCount = totalCount / rowCount + (totalCount % rowCount == 0 ? 0 : 1);
						int popupWidth = proto.getIconWidth() * columnCount + i.left + i.right;

						Dimension size = combo.getSize();
						if (size.width >= popupWidth) {
							return;
						}
						if (!adjusting) { //防止多次调用，点击一下会调用两次
							adjusting = true;
							combo.setSize(popupWidth, size.height);
							combo.showPopup();
						}
						adjusting = false;
					}

					@Override
					public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
						/* not needed */
					}

					@Override
					public void popupMenuCanceled(PopupMenuEvent e) {
						/* not needed */
					}
				};
				addPopupMenuListener(listener);
			}
		};
	}

	private static ComboBoxModel<Icon> makeModel() {
		DefaultComboBoxModel<Icon> model = new DefaultComboBoxModel<>();
		model.addElement(new ColorIcon(Color.RED));
		model.addElement(new ColorIcon(Color.GREEN));
		model.addElement(new ColorIcon(Color.BLUE));
		model.addElement(new ColorIcon(Color.ORANGE));
		model.addElement(new ColorIcon(Color.CYAN));
		model.addElement(new ColorIcon(Color.PINK));
		model.addElement(new ColorIcon(Color.YELLOW));
		model.addElement(new ColorIcon(Color.MAGENTA));
		model.addElement(new ColorIcon(Color.GRAY));
		return model;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(HorizontalWrapComboPopup::createAndShowGui);
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
		frame.getContentPane().add(new HorizontalWrapComboPopup());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
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
		g2.fillRect(1, 1, getIconWidth() - 2, getIconHeight() - 2);
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
