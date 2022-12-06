package lab.other;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.EnumSet;
import java.util.Optional;

/**
 * 使面板四边可拉伸
 */
public class ResizableComponents extends JPanel {
	private ResizableComponents() {
		super(new BorderLayout());
		Point pt = new Point();
		JPopupMenu popup = new JPopupMenu() {
			@Override
			public void show(Component c, int x, int y) {
				pt.setLocation(x, y);
				super.show(c, x, y);
			}
		};
		popup.add("table").addActionListener(e -> createTable());
		popup.add("tree").addActionListener(e -> createTree());

		JLayeredPane layeredPane = new JLayeredPane() {
			@Override
			public boolean isOptimizedDrawingEnabled() {
				return false;
			}
		};
		layeredPane.setComponentPopupMenu(popup);
		// ??? for 1.5.0
		// layeredPane.addMouseListener(new MouseAdapter() {
		//   /* Dummy listener */
		// });
		add(layeredPane);

		JButton addTable = new JButton("add table");
		addTable.addActionListener(e -> {
			pt.setLocation(pt.x + 20, pt.y + 20);
			Component c = createTable();
			Dimension d = c.getPreferredSize();
			c.setBounds(pt.x, pt.y, d.width, d.height);
			layeredPane.add(c);
			layeredPane.moveToFront(c);
		});

		JButton addTree = new JButton("add tree");
		addTree.addActionListener(e -> {
			pt.setLocation(pt.x + 20, pt.y + 20);
			Component c = createTree();
			Dimension d = c.getPreferredSize();
			c.setBounds(pt.x, pt.y, d.width, d.height);
			layeredPane.add(c);
			layeredPane.moveToFront(c);
		});

		JToolBar toolBar = new JToolBar("Resizable Components");
		toolBar.add(addTable);
		toolBar.addSeparator();
		toolBar.add(addTree);
		add(toolBar, BorderLayout.NORTH);
		setPreferredSize(new Dimension(320, 240));
	}

	private static Component createTree() {
		JTree tree = new JTree();
		tree.setVisibleRowCount(8);
		Container c = new JResizer(new BorderLayout());
		c.add(new JScrollPane(tree));
		return c;
	}

	private static Component createTable() {
		JTable table = new JTable(12, 3);
		table.setPreferredScrollableViewportSize(new Dimension(160, 160));
		Container c = new JResizer(new BorderLayout());
		c.add(new JScrollPane(table));
		return c;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(ResizableComponents::createAndShowGui);
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
		frame.getContentPane().add(new ResizableComponents());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}

class JResizer extends JPanel { // implements Serializable {
	private transient MouseInputListener resizeListener;

	protected JResizer(LayoutManager layout) {
		super(layout);
	}

	@Override
	public void updateUI() {
		removeMouseListener(resizeListener);
		removeMouseMotionListener(resizeListener);
		super.updateUI();
		resizeListener = new ResizeMouseListener();
		addMouseListener(resizeListener);
		addMouseMotionListener(resizeListener);
		setBorder(new DefaultResizableBorder());
	}
	// private void writeObject(ObjectOutputStream out) throws IOException {}
	// private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {}
	// private void readObjectNoData() throws ObjectStreamException {}
	// private void readObject() {
	//   this.resizeListener = new ResizeMouseListener();
	// }
	// private Object readResolve() {
	//   this.resizeListener = new ResizeMouseListener();
	//   return this;
	// }

	@Override
	public void setBorder(Border border) {
		removeMouseListener(resizeListener);
		removeMouseMotionListener(resizeListener);
		if (border instanceof ResizableBorder) {
			addMouseListener(resizeListener);
			addMouseMotionListener(resizeListener);
		}
		super.setBorder(border);
	}
}

// Resizable Components - Santhosh Kumar's Weblog
// https://github.com/santhosh-tekuri/MyBlog/tree/master/ResizableBorder
interface ResizableBorder extends Border {
	Cursor getResizeCursor(MouseEvent e);
}

class DefaultResizableBorder implements ResizableBorder, SwingConstants {
	private static final int SIZE = 6;

	private enum Locations {
		NORTH(Cursor.N_RESIZE_CURSOR) {
			@Override
			Point getPoint(Rectangle r) {
				return new Point(r.x + r.width / 2 - SIZE / 2, r.y);
			}
		},
		SOUTH(Cursor.S_RESIZE_CURSOR) {
			@Override
			Point getPoint(Rectangle r) {
				return new Point(r.x + r.width / 2 - SIZE / 2, r.y + r.height - SIZE);
			}
		},
		WEST(Cursor.W_RESIZE_CURSOR) {
			@Override
			Point getPoint(Rectangle r) {
				return new Point(r.x, r.y + r.height / 2 - SIZE / 2);
			}
		},
		EAST(Cursor.E_RESIZE_CURSOR) {
			@Override
			public Point getPoint(Rectangle r) {
				return new Point(r.x + r.width - SIZE, r.y + r.height / 2 - SIZE / 2);
			}
		},
		NORTH_WEST(Cursor.NW_RESIZE_CURSOR) {
			@Override
			Point getPoint(Rectangle r) {
				return new Point(r.x, r.y);
			}
		},
		NORTH_EAST(Cursor.NE_RESIZE_CURSOR) {
			@Override
			Point getPoint(Rectangle r) {
				return new Point(r.x + r.width - SIZE, r.y);
			}
		},
		SOUTH_WEST(Cursor.SW_RESIZE_CURSOR) {
			@Override
			Point getPoint(Rectangle r) {
				return new Point(r.x, r.y + r.height - SIZE);
			}
		},
		SOUTH_EAST(Cursor.SE_RESIZE_CURSOR) {
			@Override
			Point getPoint(Rectangle r) {
				return new Point(r.x + r.width - SIZE, r.y + r.height - SIZE);
			}
		};

		private final int cursor;

		Locations(int cursor) {
			this.cursor = cursor;
		}

		abstract Point getPoint(Rectangle r);

		public Cursor getCursor() {
			return Cursor.getPredefinedCursor(cursor);
		}
	}

	@Override
	public Insets getBorderInsets(Component component) {
		return new Insets(SIZE, SIZE, SIZE, SIZE);
	}

	@Override
	public boolean isBorderOpaque() {
		return false;
	}

	@Override
	public void paintBorder(Component component, Graphics g, int x, int y, int w, int h) {
		g.setColor(Color.black);
		g.drawRect(x + SIZE / 2, y + SIZE / 2, w - SIZE, h - SIZE);
		Rectangle rect = new Rectangle(SIZE, SIZE);
		Rectangle r = new Rectangle(x, y, w, h);
		for (Locations loc : Locations.values()) {
			rect.setLocation(loc.getPoint(r));
			g.setColor(Color.WHITE);
			g.fillRect(rect.x, rect.y, rect.width - 1, rect.height - 1);
			g.setColor(Color.BLACK);
			g.drawRect(rect.x, rect.y, rect.width - 1, rect.height - 1);
		}
	}

	@Override
	public Cursor getResizeCursor(MouseEvent e) {
		Component c = e.getComponent();
		int w = c.getWidth();
		int h = c.getHeight();
		Point pt = e.getPoint();

		Rectangle bounds = new Rectangle(w, h);
		if (!bounds.contains(pt)) {
			return Cursor.getDefaultCursor();
		}

		Rectangle actualBounds = new Rectangle(SIZE, SIZE, w - 2 * SIZE, h - 2 * SIZE);
		if (actualBounds.contains(pt)) {
			return Cursor.getDefaultCursor();
		}
		Rectangle rect = new Rectangle(SIZE, SIZE);
		Rectangle r = new Rectangle(0, 0, w, h);
		for (Locations loc : Locations.values()) {
			rect.setLocation(loc.getPoint(r));
			if (rect.contains(pt)) {
				return loc.getCursor();
			}
		}
		return Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
	}
}

class ResizeMouseListener extends MouseInputAdapter {
	private static final Dimension MIN = new Dimension(50, 50);
	private static final Dimension MAX = new Dimension(500, 500);
	private final Point startPos = new Point();
	private final Rectangle startRect = new Rectangle();
	private Cursor cursor;

	@Override
	public void mouseMoved(MouseEvent e) {
		JComponent c = (JComponent) e.getComponent();
		ResizableBorder border = (ResizableBorder) c.getBorder();
		c.setCursor(border.getResizeCursor(e));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		e.getComponent().setCursor(Cursor.getDefaultCursor());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JComponent c = (JComponent) e.getComponent();
		ResizableBorder border = (ResizableBorder) c.getBorder();
		cursor = border.getResizeCursor(e);
		startPos.setLocation(SwingUtilities.convertPoint(c, e.getX(), e.getY(), null));
		startRect.setBounds(c.getBounds());
		Container parent = SwingUtilities.getAncestorOfClass(JLayeredPane.class, c);
		if (parent instanceof JLayeredPane) {
			((JLayeredPane) parent).moveToFront(c);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		startRect.setSize(0, 0);
	}

	// @see %JAVA_HOME%/src/javax/swing/plaf/basic/BasicInternalFrameUI.java
	@Override
	public void mouseDragged(MouseEvent e) {
		if (startRect.isEmpty()) {
			return;
		}
		Component c = e.getComponent();
		Point p = SwingUtilities.convertPoint(c, e.getX(), e.getY(), null);
		int deltaX = startPos.x - p.x;
		int deltaY = startPos.y - p.y;
		Container parent = SwingUtilities.getUnwrappedParent(c);
		int cursorType = Optional.ofNullable(cursor).map(Cursor::getType).orElse(Cursor.DEFAULT_CURSOR);
		Directions.getByCursorType(cursorType).ifPresent(dir -> {
			Point delta = getLimitedDelta(cursorType, parent.getBounds(), deltaX, deltaY);
			c.setBounds(dir.getBounds(startRect, delta));
		});
		parent.revalidate();
	}

	private int getDeltaX(int dx) {
		int left = Math.min(MAX.width - startRect.width, startRect.x);
		return Math.max(Math.min(dx, left), MIN.width - startRect.width);
		// int deltaX = dx;
		// if (deltaX < MIN.width - startingBounds.width) {
		//   deltaX = MIN.width - startingBounds.width;
		// } else if (deltaX > MAX.width - startingBounds.width) {
		//   deltaX = MAX.width - startingBounds.width;
		// }
		// if (startingBounds.x < deltaX) {
		//   deltaX = startingBounds.x;
		// }
		// return deltaX;
	}

	private int getDeltaX(int dx, Rectangle pr) {
		int right = Math.max(startRect.width - MAX.width, startRect.x + startRect.width - pr.width);
		return Math.min(Math.max(dx, right), startRect.width - MIN.width);
		// int deltaX = dx;
		// if (startingBounds.width - MIN.width < deltaX) {
		//   deltaX = startingBounds.width - MIN.width;
		// } else if (startingBounds.width - MAX.width > deltaX) {
		//   deltaX = startingBounds.width - MAX.width;
		// }
		// if (startingBounds.x + startingBounds.width - pr.width > deltaX) {
		//   deltaX = startingBounds.x + startingBounds.width - pr.width;
		// }
		// return deltaX;
	}

	private int getDeltaY(int dy) {
		int top = Math.min(MAX.height - startRect.height, startRect.y);
		return Math.max(Math.min(dy, top), MIN.height - startRect.height);
		// int deltaY = dy;
		// if (deltaY < MIN.height - startingBounds.height) {
		//   deltaY = MIN.height - startingBounds.height;
		// } else if (deltaY > MAX.height - startingBounds.height) {
		//   deltaY = MAX.height - startingBounds.height;
		// }
		// if (deltaY < startingBounds.y) {
		//   deltaY = startingBounds.y;
		// }
		// return deltaY;
	}

	private int getDeltaY(int dy, Rectangle pr) {
		int maxHeight = startRect.height - MAX.height;
		int bottom = Math.max(maxHeight, startRect.y + startRect.height - pr.height);
		return Math.min(Math.max(dy, bottom), startRect.height - MIN.height);
		// int deltaY = dy;
		// if (startingBounds.height - MIN.height < deltaY) {
		//   deltaY = startingBounds.height - MIN.height;
		// } else if (startingBounds.height - MAX.height > deltaY) {
		//   deltaY = startingBounds.height - MAX.height;
		// }
		// if (startingBounds.y + startingBounds.height - deltaY > pr.height) {
		//   deltaY = startingBounds.y + startingBounds.height - pr.height;
		// }
		// return deltaY;
	}

	private Point getLimitedDelta(int cursorType, Rectangle pr, int deltaX, int deltaY) {
		switch (cursorType) {
			case Cursor.N_RESIZE_CURSOR:
				return new Point(0, getDeltaY(deltaY));
			case Cursor.S_RESIZE_CURSOR:
				return new Point(0, getDeltaY(deltaY, pr));
			case Cursor.W_RESIZE_CURSOR:
				return new Point(getDeltaX(deltaX), 0);
			case Cursor.E_RESIZE_CURSOR:
				return new Point(getDeltaX(deltaX, pr), 0);
			case Cursor.NW_RESIZE_CURSOR:
				return new Point(getDeltaX(deltaX), getDeltaY(deltaY));
			case Cursor.SW_RESIZE_CURSOR:
				return new Point(getDeltaX(deltaX), getDeltaY(deltaY, pr));
			case Cursor.NE_RESIZE_CURSOR:
				return new Point(getDeltaX(deltaX, pr), getDeltaY(deltaY));
			case Cursor.SE_RESIZE_CURSOR:
				return new Point(getDeltaX(deltaX, pr), getDeltaY(deltaY, pr));
			default:
				return new Point(deltaX, deltaY);
		}
	}
}

enum Directions {
	NORTH(Cursor.N_RESIZE_CURSOR) {
		@Override
		Rectangle getBounds(Rectangle r, Point d) {
			return new Rectangle(r.x, r.y - d.y, r.width, r.height + d.y);
		}
	},
	SOUTH(Cursor.S_RESIZE_CURSOR) {
		@Override
		Rectangle getBounds(Rectangle r, Point d) {
			return new Rectangle(r.x, r.y, r.width, r.height - d.y);
		}
	},
	WEST(Cursor.W_RESIZE_CURSOR) {
		@Override
		Rectangle getBounds(Rectangle r, Point d) {
			return new Rectangle(r.x - d.x, r.y, r.width + d.x, r.height);
		}
	},
	EAST(Cursor.E_RESIZE_CURSOR) {
		@Override
		Rectangle getBounds(Rectangle r, Point d) {
			return new Rectangle(r.x, r.y, r.width - d.x, r.height);
		}
	},
	NORTH_WEST(Cursor.NW_RESIZE_CURSOR) {
		@Override
		Rectangle getBounds(Rectangle r, Point d) {
			return new Rectangle(r.x - d.x, r.y - d.y, r.width + d.x, r.height + d.y);
		}
	},
	NORTH_EAST(Cursor.NE_RESIZE_CURSOR) {
		@Override
		Rectangle getBounds(Rectangle r, Point d) {
			return new Rectangle(r.x, r.y - d.y, r.width - d.x, r.height + d.y);
		}
	},
	SOUTH_WEST(Cursor.SW_RESIZE_CURSOR) {
		@Override
		Rectangle getBounds(Rectangle r, Point d) {
			return new Rectangle(r.x, r.y, r.width, r.height);
		}
	},
	SOUTH_EAST(Cursor.SE_RESIZE_CURSOR) {
		@Override
		Rectangle getBounds(Rectangle r, Point d) {
			return new Rectangle(r.x, r.y, r.width - d.x, r.height - d.y);
		}
	},
	MOVE(Cursor.MOVE_CURSOR) {
		@Override
		Rectangle getBounds(Rectangle r, Point d) {
			return new Rectangle(r.x - d.x, r.y - d.y, r.width, r.height);
		}
	};

	private final int cursor;

	Directions(int cursor) {
		this.cursor = cursor;
	}

	abstract Rectangle getBounds(Rectangle rect, Point delta);

	public static Optional<Directions> getByCursorType(int cursor) {
		return EnumSet.allOf(Directions.class)
				.stream()
				.filter(d -> d.cursor == cursor)
				.findFirst();
	}
}