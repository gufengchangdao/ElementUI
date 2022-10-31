package com.component.data.table.renderer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * 数表格头部右侧添加按钮，点击弹出菜单
 */
public class TableHeaderPopupRenderer extends JButton implements TableCellRenderer {
	protected static final int BUTTON_WIDTH = 16;
	protected static final Color BUTTON_BGC = new Color(0x64_C8_C8_C8, true);
	protected final JPopupMenu pop;
	protected int rolloverIndex = -1;
	protected final transient MouseInputListener handler = new MouseInputAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			JTableHeader header = (JTableHeader) e.getComponent();
			JTable table = header.getTable();
			TableColumnModel columnModel = table.getColumnModel();
			int vci = columnModel.getColumnIndexAtX(e.getX());
			Rectangle r = header.getHeaderRect(vci);
			Container c = (Container) getTableCellRendererComponent(table, "", true, true, -1, vci);
			r.translate(r.width - BUTTON_WIDTH, 0);
			r.setSize(BUTTON_WIDTH, r.height);
			Point pt = e.getPoint();
			if (c.getComponentCount() > 0 && r.contains(pt)) {
				pop.show(header, r.x, r.height);
				JButton b = (JButton) c.getComponent(0);
				b.doClick();
				e.consume();
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			rolloverIndex = -1;
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			JTableHeader header = (JTableHeader) e.getComponent();
			JTable table = header.getTable();
			TableColumnModel columnModel = table.getColumnModel();
			int vci = columnModel.getColumnIndexAtX(e.getX());
			rolloverIndex = table.convertColumnIndexToModel(vci);
		}
	};

	public TableHeaderPopupRenderer(JTableHeader header, JPopupMenu pop) {
		super();
		this.pop = pop;
		header.addMouseListener(handler);
		header.addMouseMotionListener(handler);
	}

	@Override
	public void updateUI() {
		super.updateUI();
		// setOpaque(false);
		// setFont(header.getFont());
		setBorder(BorderFactory.createEmptyBorder());
		setContentAreaFilled(false);
		EventQueue.invokeLater(() -> SwingUtilities.updateComponentTreeUI(pop));
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		TableCellRenderer r = table.getTableHeader().getDefaultRenderer();
		Component c = r.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (c instanceof JLabel) {
			JLabel l = (JLabel) c;
			setIcon(new MenuArrowIcon());
			l.removeAll();
			int mci = table.convertColumnIndexToModel(column);
			if (rolloverIndex == mci) {
				int w = table.getColumnModel().getColumn(mci).getWidth();
				int h = table.getTableHeader().getHeight();
				// Icon icon = new MenuArrowIcon();
				Border outside = l.getBorder();
				Border inside = BorderFactory.createEmptyBorder(0, 0, 0, BUTTON_WIDTH);
				Border b = BorderFactory.createCompoundBorder(outside, inside);
				l.setBorder(b);
				l.add(this);
				// Insets i = b.getBorderInsets(l);
				// setBounds(w - i.right, 0, BUTTON_WIDTH, h - 2);
				setBounds(w - BUTTON_WIDTH, 0, BUTTON_WIDTH, h - 2);
				setBackground(BUTTON_BGC);
				setOpaque(true);
				setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.GRAY));
			}
		}
		return c;
	}
}

class MenuArrowIcon implements Icon {
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setPaint(Color.BLACK);
		g2.translate(x, y);
		g2.drawLine(2, 3, 6, 3);
		g2.drawLine(3, 4, 5, 4);
		g2.drawLine(4, 5, 4, 5);
		g2.dispose();
	}

	@Override
	public int getIconWidth() {
		return 10;
	}

	@Override
	public int getIconHeight() {
		return 10;
	}
}
