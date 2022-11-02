package lab.other.date;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.plaf.LayerUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.geom.Path2D;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.*;

/**
 * 圆角表头的日历选择表格
 */
public class RoundedCornerTableHeader extends JPanel {
	private final JLabel monthLabel = new JLabel("", SwingConstants.CENTER);
	private final JTable monthTable = new JTable() {
		private void updateRowsHeight(JViewport vport) {
			int height = vport.getExtentSize().height;
			int rowCount = getModel().getRowCount();
			int defaultRowHeight = height / rowCount;
			int remainder = height % rowCount;
			for (int i = 0; i < rowCount; i++) {
				int a = Math.min(1, Math.max(0, remainder--));
				setRowHeight(i, defaultRowHeight + a);
			}
		}

		@Override
		public void doLayout() {
			super.doLayout();
			Class<JViewport> clz = JViewport.class;
			Optional.ofNullable(SwingUtilities.getAncestorOfClass(clz, this))
					.filter(clz::isInstance).map(clz::cast)
					.ifPresent(this::updateRowsHeight);
		}
	};
	private final List<Color> monthThemeColor = Arrays.asList(
			new Color(0xD5_0B_17), new Color(0x02_6C_B6), new Color(0xED_87_AD),
			new Color(0xCE_30_6A), new Color(0x48_B0_37), new Color(0xA4_62_A2),
			new Color(0x00_BD_E7), new Color(0xEB_5E_31), new Color(0xC8_01_82),
			new Color(0x8F_19_19), new Color(0x6A_31_8F), new Color(0x00_7A_70));
	private LocalDate currentLocalDate;

	private RoundedCornerTableHeader() {
		super(new BorderLayout());
		monthLabel.setOpaque(false);
		monthLabel.setFont(monthLabel.getFont().deriveFont(Font.BOLD));

		monthTable.setFont(monthTable.getFont().deriveFont(Font.BOLD));
		monthTable.setDefaultRenderer(LocalDate.class, new CalendarTableRenderer());
		monthTable.setFillsViewportHeight(true);
		monthTable.setBackground(Color.WHITE);
		monthTable.setShowVerticalLines(false);
		monthTable.setIntercellSpacing(new Dimension(0, 1));

		JTableHeader header = monthTable.getTableHeader();
		header.setForeground(Color.WHITE);
		header.setOpaque(false);
		header.setDefaultRenderer(new RoundedHeaderRenderer());
		header.setResizingAllowed(false);
		header.setReorderingAllowed(false);

		updateMonthView(LocalDate.of(2021, 6, 21));

		JButton prev = new JButton("<");
		prev.addActionListener(e -> updateMonthView(getCurrentLocalDate().minusMonths(1)));

		JButton next = new JButton(">");
		next.addActionListener(e -> updateMonthView(getCurrentLocalDate().plusMonths(1)));

		JPanel p = new JPanel(new BorderLayout());
		p.setOpaque(false);
		p.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		p.add(monthLabel);
		p.add(prev, BorderLayout.WEST);
		p.add(next, BorderLayout.EAST);

		JScrollPane scroll = new JScrollPane(monthTable);
		scroll.setColumnHeader(new JViewport() {
			@Override
			public Dimension getPreferredSize() {
				Dimension d = super.getPreferredSize();
				d.height = 24;
				return d;
			}
		});
		scroll.setBorder(BorderFactory.createEmptyBorder());
		scroll.setViewportBorder(BorderFactory.createEmptyBorder());
		scroll.getViewport().setBackground(Color.WHITE);

		add(p, BorderLayout.NORTH);
		add(scroll);
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setPreferredSize(new Dimension(320, 240));
	}

	public LocalDate getCurrentLocalDate() {
		return currentLocalDate;
	}

	public void updateMonthView(LocalDate localDate) {
		currentLocalDate = localDate;
		Color color = monthThemeColor.get(localDate.getMonthValue() - 1);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy / MM");
		monthLabel.setText(localDate.format(fmt.withLocale(Locale.getDefault())));
		monthLabel.setForeground(color);
		monthTable.setModel(new CalendarViewTableModel(localDate));
		monthTable.getTableHeader().setBackground(color);
	}

	private class CalendarTableRenderer extends DefaultTableCellRenderer {
		private final JPanel panel = new JPanel(new BorderLayout());

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
			Component c = super.getTableCellRendererComponent(table, value, false, false, row, column);
			if (value instanceof LocalDate && c instanceof JLabel) {
				LocalDate d = (LocalDate) value;
				JLabel l = (JLabel) c;
				l.setText(Objects.toString(d.getDayOfMonth()));
				l.setVerticalAlignment(SwingConstants.TOP);
				l.setHorizontalAlignment(SwingConstants.CENTER);
				updateCellWeekColor(d, c);

				LocalDate nextWeekDay = d.plusDays(7);
				boolean isLastRow = row == table.getModel().getRowCount() - 1;
				if (isLastRow && isDiagonallySplitCell(nextWeekDay)) {
					JLabel sub = new JLabel(Objects.toString(nextWeekDay.getDayOfMonth()));
					sub.setFont(table.getFont());
					sub.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
					sub.setOpaque(false);
					sub.setVerticalAlignment(SwingConstants.BOTTOM);
					sub.setHorizontalAlignment(SwingConstants.RIGHT);

					panel.removeAll();
					panel.setOpaque(false);
					panel.setForeground(getDayOfWeekColor(d.getDayOfWeek()));
					panel.add(sub, BorderLayout.SOUTH);
					panel.add(c, BorderLayout.NORTH);
					panel.setBorder(l.getBorder());
					l.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
					l.setHorizontalAlignment(SwingConstants.LEFT);

					updateCellWeekColor(d, sub);
					return new JLayer<>(panel, new DiagonallySplitCellLayerUI());
				}
			}
			return c;
		}

		private boolean isDiagonallySplitCell(LocalDate nextWeekDay) {
			return YearMonth.from(nextWeekDay).equals(YearMonth.from(getCurrentLocalDate()));
		}

		private void updateCellWeekColor(LocalDate d, Component fgc) {
			if (YearMonth.from(d).equals(YearMonth.from(getCurrentLocalDate()))) {
				fgc.setForeground(getDayOfWeekColor(d.getDayOfWeek()));
			} else {
				fgc.setForeground(Color.GRAY);
			}
		}

		private Color getDayOfWeekColor(DayOfWeek dow) {
			switch (dow) {
				case SUNDAY:
					return new Color(0xD9_0B_0D);
				case SATURDAY:
					return new Color(0x10_4A_90);
				default:
					return Color.BLACK;
			}
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout("fill"));
			p.add(new RoundedCornerTableHeader(), "growx, growy");
			SwingTestUtil.test();
		});
	}

	static class CalendarViewTableModel extends DefaultTableModel {
		private final LocalDate startDate;
		private final WeekFields weekFields = WeekFields.of(Locale.getDefault());

		protected CalendarViewTableModel(LocalDate date) {
			super();
			LocalDate firstDayOfMonth = YearMonth.from(date).atDay(1);
			int v = firstDayOfMonth.get(weekFields.dayOfWeek()) - 1;
			startDate = firstDayOfMonth.minusDays(v);
		}

		@Override
		public Class<?> getColumnClass(int column) {
			return LocalDate.class;
		}

		@Override
		public String getColumnName(int column) {
			return weekFields.getFirstDayOfWeek().plus(column)
					.getDisplayName(TextStyle.SHORT_STANDALONE, Locale.getDefault());
		}

		@Override
		public int getRowCount() {
			return 5;
		}

		@Override
		public int getColumnCount() {
			return 7;
		}

		@Override
		public Object getValueAt(int row, int column) {
			return startDate.plusDays((long) row * getColumnCount() + column);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}

	class DiagonallySplitCellLayerUI extends LayerUI<JPanel> {
		@Override
		public void paint(Graphics g, JComponent c) {
			super.paint(g, c);
			if (c instanceof JLayer) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				// g2.setPaint(UIManager.getColor("Table.gridColor"));
				g2.setPaint(((JLayer<?>) c).getView().getForeground());
				g2.drawLine(c.getWidth() - 4, 4, 4, c.getHeight() - 4);
				g2.dispose();
			}
		}
	}
}

class RoundedHeaderRenderer extends DefaultTableCellRenderer {
	private final JLabel firstLabel = new JLabel() {
		@Override
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setPaint(getBackground());
			float r = 8f;
			float x = 0f;
			float y = 0f;
			float w = getWidth();
			float h = getHeight();
			Path2D p = new Path2D.Float();
			p.moveTo(x, y + r);
			p.quadTo(x, y, x + r, y);
			p.lineTo(x + w, y);
			p.lineTo(x + w, y + h);
			p.lineTo(x + r, y + h);
			p.quadTo(x, y + h, x, y + h - r);
			p.closePath();
			g2.fill(p);
			g2.dispose();
			super.paintComponent(g);
		}
	};
	private final JLabel lastLabel = new JLabel() {
		@Override
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setPaint(getBackground());
			float r = 8f;
			float x = 0f;
			float y = 0f;
			float w = getWidth();
			float h = getHeight();
			Path2D p = new Path2D.Float();
			p.moveTo(x, y);
			p.lineTo(x + w - r, y);
			p.quadTo(x + w, y, x + w, y + r);
			p.lineTo(x + w, y + h - r);
			p.quadTo(x + w, y + h, x + w - r, y + h);
			p.lineTo(x, y + h);
			p.closePath();
			g2.fill(p);
			g2.dispose();
			super.paintComponent(g);
		}
	};

	protected RoundedHeaderRenderer() {
		super();
		firstLabel.setOpaque(false);
		lastLabel.setOpaque(false);
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		firstLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		lastLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component c = super.getTableCellRendererComponent(
				table, value, isSelected, hasFocus, row, column);
		if (column == 0) {
			c = firstLabel;
		} else if (column == table.getColumnCount() - 1) {
			c = lastLabel;
		}
		c.setFont(table.getFont());
		c.setForeground(table.getTableHeader().getForeground());
		c.setBackground(table.getTableHeader().getBackground());
		if (c instanceof JLabel) {
			JLabel l = (JLabel) c;
			l.setText(value.toString());
			l.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return c;
	}
}


