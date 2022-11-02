package com.component.form.input.spinner;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * swing自带选择器
 */
public class JSpinnerTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();

			// 默认时间、日期
			// SpinnerDateModel model = new SpinnerDateModel();

			// 列表
			SpinnerListModel model1 = new SpinnerListModel(Arrays.asList(1, 2, 3, 4, 5));

			// 连续数字
			// Calendar calendar = Calendar.getInstance();
			// int currentYear = calendar.get(Calendar.YEAR);
			// SpinnerModel yearModel = new SpinnerNumberModel(currentYear, //initial value
			// 		currentYear - 100, //min
			// 		currentYear + 100, //max
			// 		1);

			JSpinner spinner = new JSpinner(model1);

			// 格式化日期
			// spinner.setEditor(new JSpinner.DateEditor(spinner, "MM/yyyy"));

			SwingTestUtil.test(spinner);
		});
	}
}
