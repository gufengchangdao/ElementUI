package com.component.form.input.spinner;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Calendar;

public class TimePickerTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			Calendar c1 = Calendar.getInstance();
			c1.set(Calendar.HOUR_OF_DAY, 8);
			Calendar c2 = Calendar.getInstance();
			c2.set(Calendar.HOUR_OF_DAY, 12);
			Calendar c3 = Calendar.getInstance();
			c3.set(Calendar.HOUR_OF_DAY, 20);
			TimePicker field = new TimePicker(10, Arrays.asList(c1, c2, c3));

			// 输入框失去焦点时检验输入是否合法，因为可以提供格式化器Formatter，具体格式不知，这一步只能手动添加了
			field.setCheckInputFunction(s -> {
				int i = s.indexOf(":");
				try {
					if (i == -1) {
						int hour = Integer.parseInt(s.trim());
						if (hour < 0 || hour > 24) return "";
						return String.format("%02d:00", hour);
					} else {
						int hour = Integer.parseInt(s.substring(0, i).trim());
						if (hour < 0 || hour > 24) return "";
						int minute = Integer.parseInt(s.substring(i + 1).trim());
						return String.format("%02d:%02d", hour, minute);
					}
				} catch (NumberFormatException | IndexOutOfBoundsException e) {
					return "";
				}
			});

			JButton button = new JButton("获取");
			button.addActionListener(e -> {
				System.out.println(field.getText());
				System.out.println(field.getSelectedItem());
			});
			SwingTestUtil.test(field, button);
		});
	}
}
