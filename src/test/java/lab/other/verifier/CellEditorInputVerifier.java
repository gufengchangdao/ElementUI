package lab.other.verifier;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;
import javax.swing.text.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Objects;

/**
 * 演示验证输入的几种方式
 * <p>
 * 主要有三种表现
 * <ul>
 *     <li>不合法的能输入，但是无法选中其他单元格，点其他地方有提示音，失去焦点时清空输入</li>
 *     <li>不合法的能输入，但是无法选中其他单元格，点其他地方无提示音，失去焦点时清空输入</li>
 *     <li>不合法的不能输入，且有提示音</li>
 * </ul>
 */
public class CellEditorInputVerifier extends JPanel {
	private CellEditorInputVerifier() {
		super(new BorderLayout());
		JTextField field1 = new JTextField();
		initBorderAndAlignment(field1);
		Document doc1 = field1.getDocument();
		if (doc1 instanceof AbstractDocument) {
			((AbstractDocument) doc1).setDocumentFilter(new IntegerDocumentFilter());
		}

		JTextField field2 = new JTextField();
		initBorderAndAlignment(field2);
		field2.setInputVerifier(new IntegerInputVerifier());

		JFormattedTextField field3 = new JFormattedTextField();
		initBorderAndAlignment(field3);
		field3.setFormatterFactory(new NumberFormatterFactory());

		String[] columnNames = {"Default", "DocumentFilter", "InputVerifier", "JFormattedTextField"};
		TableModel model = new DefaultTableModel(columnNames, 10) {
			@Override
			public Class<?> getColumnClass(int column) {
				return Integer.class;
			}
		};
		JTable table = new JTable(model) {
			@Override
			public Component prepareEditor(TableCellEditor editor, int row, int column) {
				Component c = super.prepareEditor(editor, row, column);
				if (c instanceof JComponent) {
					((JComponent) c).setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
				}
				return c;
			}
		};
		table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(field1));
		table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(field2) {
			@Override
			public boolean stopCellEditing() {
				JComponent editor = (JComponent) getComponent();
				boolean isEditValid = editor.getInputVerifier().verify(editor);
				editor.setBorder(isEditValid ? BorderFactory.createEmptyBorder(1, 1, 1, 1)
						: BorderFactory.createLineBorder(Color.RED));
				return isEditValid && super.stopCellEditing();
			}
		});
		table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(field3) {
			@Override
			public boolean stopCellEditing() {
				JFormattedTextField editor = (JFormattedTextField) getComponent();
				boolean isEditValid = editor.isEditValid();
				editor.setBorder(isEditValid ? BorderFactory.createEmptyBorder(1, 1, 1, 1)
						: BorderFactory.createLineBorder(Color.RED));
				return isEditValid && super.stopCellEditing();
			}
		});

		add(new JScrollPane(table));
		setPreferredSize(new Dimension(320, 240));
	}

	private static void initBorderAndAlignment(JTextField textField) {
		textField.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout("", "grow"));
			p.add(new CellEditorInputVerifier(), "growx");
			SwingTestUtil.test();
		});
	}
}


class IntegerInputVerifier extends InputVerifier {
	@Override
	public boolean verify(JComponent c) {
		boolean verified = false;
		if (c instanceof JTextComponent) {
			String txt = ((JTextComponent) c).getText();
			if (txt.isEmpty()) {
				return true;
			}
			try {
				int iv = Integer.parseInt(txt);
				verified = iv >= 0;
			} catch (NumberFormatException ex) {
				// 提示音
				UIManager.getLookAndFeel().provideErrorFeedback(c);
			}
		}
		return verified;
	}
}

class IntegerDocumentFilter extends DocumentFilter {
	@Override
	public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
		if (Objects.nonNull(text)) {
			replace(fb, offset, 0, text, attr);
		}
	}

	@Override
	public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
		replace(fb, offset, length, "", null);
	}

	@Override
	public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
		Document doc = fb.getDocument();
		int currentLength = doc.getLength();
		String currentContent = doc.getText(0, currentLength);
		String before = currentContent.substring(0, offset);
		String after = currentContent.substring(length + offset, currentLength);
		String newValue = before + Objects.toString(text, "") + after;
		checkInput(newValue, offset);
		fb.replace(offset, length, text, attrs);
	}

	// 检查新值是否合法，不合法时抛出异常，即不允许修改
	private static void checkInput(String proposedValue, int offset) throws BadLocationException {
		if (!proposedValue.isEmpty()) {
			try {
				Integer.parseInt(proposedValue);
			} catch (NumberFormatException ex) {
				throw (BadLocationException) new BadLocationException(proposedValue, offset).initCause(ex);
			}
		}
	}
}

// How to Use Formatted Text Fields (The Java™ Tutorials > ... > Using Swing Components)
// https://docs.oracle.com/javase/tutorial/uiswing/components/formattedtextfield.html
class NumberFormatterFactory extends DefaultFormatterFactory {
	private static final NumberFormatter FORMATTER = new NumberFormatter();

	static {
		FORMATTER.setValueClass(Integer.class);
		((NumberFormat) FORMATTER.getFormat()).setGroupingUsed(false);
	}

	protected NumberFormatterFactory() {
		super(FORMATTER, FORMATTER, FORMATTER);
	}
}
