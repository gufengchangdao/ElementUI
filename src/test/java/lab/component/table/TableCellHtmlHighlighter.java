package lab.component.table;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * table的行过滤和高亮
 */
public class TableCellHtmlHighlighter extends JPanel {
	private static final Color WARNING_COLOR = new Color(0xFF_C8_C8);
	private final JTextField field = new JTextField("ab+");
	private final HighlightTableCellRenderer renderer = new HighlightTableCellRenderer();
	private final transient TableRowSorter<? extends TableModel> sorter;

	private TableCellHtmlHighlighter() {
		super(new BorderLayout(5, 5));
		String[] columnNames = {"A", "B"};
		Object[][] data = {
				{"aaa", "bb aa cc"}, {"bbb", "def"},
				{"ccc bbb aaa bbb aae abe", "xxx"}, {"ddd aaa bbb bbb", "cc bb aa"},
				{"cc cc bb bb aaa bb bb e", "xxx"}, {"ddd aaa b bb bb", "cc bb aa"}};
		TableModel model = new DefaultTableModel(data, columnNames) {
			@Override
			public Class<?> getColumnClass(int column) {
				return String.class;
			}
		};
		sorter = new TableRowSorter<>(model);

		JTable table = new JTable(model);
		table.setFillsViewportHeight(true);
		table.setRowSorter(sorter);
		table.setDefaultRenderer(String.class, renderer);

		field.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				fireDocumentChangeEvent();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				fireDocumentChangeEvent();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				/* not needed */
			}
		});
		fireDocumentChangeEvent();

		JPanel sp = new JPanel(new BorderLayout(5, 5));
		sp.add(new JLabel("regex pattern:"), BorderLayout.WEST);
		sp.add(field);
		sp.add(Box.createVerticalStrut(2), BorderLayout.SOUTH);
		sp.setBorder(BorderFactory.createTitledBorder("Search"));

		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		add(sp, BorderLayout.NORTH);
		add(new JScrollPane(table));
		setPreferredSize(new Dimension(320, 240));
	}

	public void fireDocumentChangeEvent() {
		field.setBackground(Color.WHITE);
		String pattern = field.getText().trim();
		if (pattern.isEmpty()) {
			sorter.setRowFilter(null);
			renderer.updatePattern("");
		} else if (renderer.updatePattern(pattern)) {
			try {
				sorter.setRowFilter(RowFilter.regexFilter(pattern));
			} catch (PatternSyntaxException ex) {
				field.setBackground(WARNING_COLOR);
			}
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout("fill"));
			p.add(new TableCellHtmlHighlighter(), "growx, growy");
			SwingTestUtil.test();
		});
	}
}

class HighlightTableCellRenderer extends DefaultTableCellRenderer {
	private String pattern = "";
	private String prev;

	public boolean updatePattern(String str) {
		if (Objects.equals(str, pattern)) {
			return false;
		} else {
			prev = pattern;
			pattern = str;
			return true;
		}
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		String txt = Objects.toString(value, "");
		if (!pattern.isEmpty() && !Objects.equals(pattern, prev)) {
			Matcher matcher = Pattern.compile(pattern).matcher(txt);
			int pos = 0;
			StringBuilder buf = new StringBuilder("<html>");
			while (matcher.find(pos) && !matcher.group().isEmpty()) {
				int start = matcher.start();
				int end = matcher.end();
				String span = "%s<span style='color:#000000; background-color:#FFFF00'>%s</span>";
				buf.append(String.format(span, txt.substring(pos, start), txt.substring(start, end)));
				pos = end;
			}
			buf.append(txt.substring(pos));
			txt = buf.toString();
		}
		return super.getTableCellRendererComponent(table, txt, isSelected, hasFocus, row, column);
	}
}
