package com.component.data.table.renderer;

import javax.swing.*;
import java.awt.*;

public class TextFieldCellEditor extends DefaultCellEditor {
	private JTextField textField;

	public TextFieldCellEditor(JTextField textField) {
		super(textField);
		this.textField = textField;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
	                                             boolean isSelected,
	                                             int row, int column) {
		textField.setText(value.toString());
		return textField;
	}
}
