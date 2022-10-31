package com.component.util;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

/**
 * 可编辑组件的工具类
 */
public class TextEditorUtil {
	/**
	 * 在给定组件插入符(就是一闪一闪的那个光标)后插入文本
	 *
	 * @param editor 要插入内容的组件
	 * @param str    插入的内容
	 */
	public static void appendAtCaret(JTextComponent editor, String str) {
		try {
			Document doc = editor.getDocument();
			doc.insertString(editor.getCaretPosition(), str, null);
		} catch (BadLocationException ex) {
			// should never happen
			RuntimeException wrap = new StringIndexOutOfBoundsException(ex.offsetRequested());
			wrap.initCause(ex);
			throw wrap;
		}
	}

	/**
	 * 在给定组件最后追加文本，并设置插入符在最后
	 *
	 * @param editor 要追加内容的组件
	 * @param str    追加的内容
	 */
	public static void appendAtEnd(JTextComponent editor, String str) {
		try {
			Document doc = editor.getDocument();
			doc.insertString(doc.getLength(), str, null);
			editor.setCaretPosition(doc.getLength());
		} catch (BadLocationException ex) {
			RuntimeException wrap = new StringIndexOutOfBoundsException(ex.offsetRequested());
			wrap.initCause(ex);
			throw wrap;
		}
	}
}
