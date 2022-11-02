package com.component.form.input.filter;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import java.util.Objects;

/**
 * 整数文档过滤器，只允许输入为整数
 */
public class IntegerDocumentFilter extends DocumentFilter {
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
