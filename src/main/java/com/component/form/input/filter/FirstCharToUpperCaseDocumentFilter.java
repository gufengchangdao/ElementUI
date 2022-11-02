package com.component.form.input.filter;

import javax.swing.text.*;
import java.util.Locale;
import java.util.Objects;

/**
 * 首字母大写
 */
public class FirstCharToUpperCaseDocumentFilter extends DocumentFilter {
	private final JTextComponent textField;

	protected FirstCharToUpperCaseDocumentFilter(JTextComponent textField) {
		super();
		this.textField = textField;
	}

	@Override
	public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
		Document doc = fb.getDocument();
		if (offset == 0 && doc.getLength() - length > 0) {
			fb.replace(length, 1, doc.getText(length, 1).toUpperCase(Locale.ENGLISH), null);
			textField.setCaretPosition(0);
		}
		fb.remove(offset, length);
	}

	@Override
	public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
		String str = text;
		if (offset == 0 && Objects.nonNull(text) && !text.isEmpty()) {
			str = text.substring(0, 1).toUpperCase(Locale.ENGLISH) + text.substring(1);
		}
		fb.replace(offset, length, str, attrs);
	}
}
