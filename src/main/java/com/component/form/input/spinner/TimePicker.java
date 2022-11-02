package com.component.form.input.spinner;

import com.component.basic.border.IconBorder;
import com.component.form.input.advice.InputAdviceInputField;
import com.component.form.input.renderer.CalendarComboBoxRenderer;
import com.component.svg.icon.regular.ClockSvg;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.List;
import java.util.function.Function;

/**
 * 时间选择器，用于选择或输入时间
 */
public class TimePicker extends InputAdviceInputField<Calendar> {
	private Function<Calendar, String> formatter;

	/**
	 * 带输入建议的输入框
	 *
	 * @param columns 用于计算首选宽度的列数;如果columns被设置为零，首选宽度将是组件实现的自然结果
	 * @param data    建议列表
	 */
	public TimePicker(int columns, List<Calendar> data) {
		super(columns, data);
		init();
	}

	public TimePicker(int columns, List<Calendar> data, Function<Calendar, String> formatter) {
		super(columns, data);
		this.formatter = formatter;
		init();
	}

	private void init() {
		JTextField textField = getTextField();
		Insets insets = textField.getInsets();
		int size = textField.getPreferredSize().height - insets.top - insets.bottom;
		// 设置时钟图标
		getTextField().setBorder(new IconBorder(ClockSvg.of(size, size), true));

		setFunction();
		// 设置单元格的渲染
		if (formatter != null) getComboBox().setRenderer(new CalendarComboBoxRenderer(formatter));
		else getComboBox().setRenderer(new CalendarComboBoxRenderer());
	}

	private void setFunction() {
		// 点击选项时将Calendar对象转为字符串写入输入框
		setParseTextFunction(this::calendarFormatter);
		// 输入框输入时判断是否有相近的选项
		setContainsFunction((calendar, s) -> calendarFormatter(calendar).contains(s));
	}

	public String calendarFormatter(Calendar val) {
		if (val == null) return "空";
		if (formatter != null)
			return formatter.apply(val);
		return String.format("%02d:%02d", val.get(Calendar.HOUR_OF_DAY), val.get(Calendar.MINUTE));
	}
}
