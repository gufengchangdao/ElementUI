package com.component.data.table.editor;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * 表格单元格的日期编辑器
 */
public class DateSpinnerCellEditor extends AbstractCellEditor implements TableCellEditor {
	private final JSpinner spinner = new JSpinner(new SpinnerDateModel());

	/**
	 * 默认格式 yyyy/MM/dd
	 */
	public DateSpinnerCellEditor() {
		this("yyyy/MM/dd");
	}

	/**
	 * @param dateFormatPattern 日期的格式
	 */
	public DateSpinnerCellEditor(String dateFormatPattern) {
		super();
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, dateFormatPattern);
		spinner.setEditor(editor);
		spinner.setBorder(BorderFactory.createEmptyBorder());
		setArrowButtonEnabled(false);

		editor.getTextField().setHorizontalAlignment(SwingConstants.LEFT);
		editor.getTextField().addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				setArrowButtonEnabled(false);
			}

			@Override
			public void focusGained(FocusEvent e) {
				// System.out.println("getTextField");
				setArrowButtonEnabled(true);
				EventQueue.invokeLater(() -> {
					JTextField field = (JTextField) e.getComponent();
					field.setCaretPosition(8);
					field.setSelectionStart(8);
					field.setSelectionEnd(10);
				});
			}
		});
	}

	protected final void setArrowButtonEnabled(boolean flag) {
		for (Component c : spinner.getComponents()) {
			if (c instanceof JButton) {
				c.setEnabled(flag);
			}
		}
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		spinner.setValue(value);
		return spinner;
	}

	@Override
	public Object getCellEditorValue() {
		return spinner.getValue();
	}
}

