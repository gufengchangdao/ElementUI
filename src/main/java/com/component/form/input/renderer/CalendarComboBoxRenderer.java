package com.component.form.input.renderer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.function.Function;

public class CalendarComboBoxRenderer extends JLabel implements ListCellRenderer<Calendar>, Serializable, Function<Calendar, String> {
	/** 默认格式化器是例如 13:51 这样的结构 */
	private Function<Calendar, String> formatter = this;
	/**
	 * 一个空的边界。此字段可能不会被使用。要改变这个渲染器使用的边框，直接使用setBorder方法设置它
	 */
	protected static Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);
	private static final Border SAFE_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

	/**
	 * 构造一个BasicComboBoxRenderer的新实例。
	 */
	public CalendarComboBoxRenderer() {
		super();
		setOpaque(true);
		setBorder(getNoFocusBorder());
	}

	public CalendarComboBoxRenderer(Function<Calendar, String> formatter) {
		super();
		this.formatter = formatter;
		setOpaque(true);
		setBorder(getNoFocusBorder());
	}

	@SuppressWarnings("removal")
	private static Border getNoFocusBorder() {
		if (System.getSecurityManager() != null) {
			return SAFE_NO_FOCUS_BORDER;
		} else {
			return noFocusBorder;
		}
	}

	public Dimension getPreferredSize() {
		Dimension size;

		if (this.getText() == null || this.getText().isEmpty()) {
			setText(" ");
			size = super.getPreferredSize();
			setText("");
		} else {
			size = super.getPreferredSize();
		}

		return size;
	}

	public Component getListCellRendererComponent(JList<? extends Calendar> list,
	                                              Calendar value,
	                                              int index,
	                                              boolean isSelected,
	                                              boolean cellHasFocus) {
		// if (isSelected) {
		// 	setBackground(UIManager.getColor("ComboBox.selectionBackground"));
		// 	setForeground(UIManager.getColor("ComboBox.selectionForeground"));
		// } else {
		// 	setBackground(UIManager.getColor("ComboBox.background"));
		// 	setForeground(UIManager.getColor("ComboBox.foreground"));
		// }

		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}

		setFont(list.getFont());

		setText((value == null) ? "" : formatter.apply(value));
		return this;
	}

	@Override
	public String apply(Calendar calendar) {
		return String.format("%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
	}

	/**
	 * A subclass of BasicComboBoxRenderer that implements UIResource.
	 * BasicComboBoxRenderer doesn't implement UIResource
	 * directly so that applications can safely override the
	 * cellRenderer property with BasicListCellRenderer subclasses.
	 * <p>
	 * <strong>Warning:</strong>
	 * Serialized objects of this class will not be compatible with
	 * future Swing releases. The current serialization support is
	 * appropriate for short term storage or RMI between applications running
	 * the same version of Swing.  As of 1.4, support for long term storage
	 * of all JavaBeans
	 * has been added to the <code>java.beans</code> package.
	 * Please see {@link java.beans.XMLEncoder}.
	 */
	@SuppressWarnings("serial") // Same-version serialization only
	public static class UIResource extends BasicComboBoxRenderer implements javax.swing.plaf.UIResource {
		/**
		 * Constructs a {@code UIResource}.
		 */
		public UIResource() {
		}
	}


}
