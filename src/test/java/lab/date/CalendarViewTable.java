package lab.date;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Objects;

/**
 * 日历表格
 */
public final class CalendarViewTable extends JPanel {
	public final LocalDate realLocalDate = LocalDate.now(ZoneId.systemDefault());
	private final JLabel dateLabel = new JLabel(realLocalDate.toString(), SwingConstants.CENTER);
	private final JLabel monthLabel = new JLabel("", SwingConstants.CENTER);
	private final JTable monthTable = new JTable();
	private LocalDate currentLocalDate;

	public LocalDate getCurrentLocalDate() {
		return currentLocalDate;
	}

	private CalendarViewTable() {
		super(new BorderLayout());

		monthTable.setDefaultRenderer(LocalDate.class, new CalendarTableRenderer());
		monthTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		monthTable.setCellSelectionEnabled(true);
		monthTable.setRowHeight(20);
		monthTable.setFillsViewportHeight(true);

		JTableHeader header = monthTable.getTableHeader();
		header.setResizingAllowed(false);
		header.setReorderingAllowed(false);
		((JLabel) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

		ListSelectionListener selectionListener = e -> {
			if (!e.getValueIsAdjusting()) {
				int row = monthTable.getSelectedRow();
				int column = monthTable.getSelectedColumn();
				LocalDate ld = (LocalDate) monthTable.getValueAt(row, column);
				dateLabel.setText(ld.toString());
			}
		};
		monthTable.getSelectionModel().addListSelectionListener(selectionListener);
		monthTable.getColumnModel().getSelectionModel().addListSelectionListener(selectionListener);

		updateMonthView(realLocalDate);

		JButton prev = new JButton("<");
		prev.addActionListener(e -> updateMonthView(getCurrentLocalDate().minusMonths(1)));

		JButton next = new JButton(">");
		next.addActionListener(e -> updateMonthView(getCurrentLocalDate().plusMonths(1)));

		JPanel p = new JPanel(new BorderLayout());
		p.add(monthLabel);
		p.add(prev, BorderLayout.WEST);
		p.add(next, BorderLayout.EAST);

		add(p, BorderLayout.NORTH);
		add(new JScrollPane(monthTable));
		add(dateLabel, BorderLayout.SOUTH);
		setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		setPreferredSize(new Dimension(320, 240));
	}

	public void updateMonthView(LocalDate localDate) {
		currentLocalDate = localDate;
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy / MM");
		monthLabel.setText(localDate.format(fmt.withLocale(Locale.getDefault())));
		monthTable.setModel(new CalendarViewTableModel(localDate));
	}

	private class CalendarTableRenderer extends DefaultTableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
			Component c = super.getTableCellRendererComponent(
					table, value, selected, focused, row, column);
			if (c instanceof JLabel && value instanceof LocalDate) {
				JLabel l = (JLabel) c;
				l.setHorizontalAlignment(SwingConstants.CENTER);
				LocalDate d = (LocalDate) value;
				l.setText(Objects.toString(d.getDayOfMonth()));
				if (YearMonth.from(d).equals(YearMonth.from(getCurrentLocalDate()))) {
					l.setForeground(Color.BLACK);
				} else {
					l.setForeground(Color.GRAY);
				}
				if (d.isEqual(realLocalDate)) {
					l.setBackground(new Color(0xDC_FF_DC));
				} else {
					l.setBackground(getDayOfWeekColor(d.getDayOfWeek()));
				}
			}
			return c;
		}

		private Color getDayOfWeekColor(DayOfWeek dow) {
			switch (dow) {
				case SUNDAY:
					return new Color(0xFF_DC_DC);
				case SATURDAY:
					return new Color(0xDC_DC_FF);
				default:
					return Color.WHITE;
			}
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout("", "grow"));
			p.add(new CalendarViewTable(), "growx");
			SwingTestUtil.test();
		});
	}
}

class CalendarViewTableModel extends DefaultTableModel {
	private final LocalDate startDate;
	private final WeekFields weekFields = WeekFields.of(Locale.getDefault());

	protected CalendarViewTableModel(LocalDate date) {
		super();
		// LocalDate firstDayOfMonth = date.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate firstDayOfMonth = YearMonth.from(date).atDay(1);
		// int v = firstDayOfMonth.get(WeekFields.SUNDAY_START.dayOfWeek()) - 1;
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
		return 6;
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
