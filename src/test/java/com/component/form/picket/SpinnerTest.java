/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Based on SpinnerDemo, this application listens for
 * changes in a spinner's values.  Specifically, we listen
 * for when the third spinner changes and then change that
 * spinner's text color accordingly.
 * Other files required:
 *   SpringUtilities.java
 *   CyclingSpinnerListModel.java
 */

package com.component.form.picket;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期选择器
 */
public class SpinnerTest extends JPanel implements ChangeListener {
	protected Calendar calendar;
	protected JSpinner dateSpinner;

	protected Color SPRING_COLOR = new Color(0, 204, 51);
	protected Color SUMMER_COLOR = Color.RED;
	protected Color FALL_COLOR = new Color(255, 153, 0);
	protected Color WINTER_COLOR = Color.CYAN;

	public SpinnerTest(boolean cycleMonths) {
		// super(new SpringLayout());
		super(new MigLayout("wrap 2", "fill"));

		String[] labels = {"Month: ", "Year: ", "Another Date: "};
		calendar = Calendar.getInstance();
		JFormattedTextField ftf;

		// 添加月份选择器
		// 获取月份名
		String[] monthStrings = getMonthStrings();
		SpinnerListModel monthModel;
		if (cycleMonths) { //使用循环模型
			monthModel = new CyclingSpinnerListModel(monthStrings);
		} else { //使用标准模型
			monthModel = new SpinnerListModel(monthStrings);
		}
		// 创建月份选择器
		JSpinner spinner = addLabeledSpinner(this, labels[0], monthModel);
		//获取选择器的输入框组件
		ftf = getTextField(spinner);
		if (ftf != null) {
			ftf.setColumns(8);
			ftf.setHorizontalAlignment(JTextField.RIGHT);
		}


		//添加年选择器
		int currentYear = calendar.get(Calendar.YEAR);
		SpinnerModel yearModel = new SpinnerNumberModel(currentYear,
				currentYear - 100,
				currentYear + 100,
				1);
		//如果月份模型是可循环的，将其与年份模型相关联
		if (monthModel instanceof CyclingSpinnerListModel) {
			((CyclingSpinnerListModel) monthModel).setLinkedModel(yearModel);
		}
		spinner = addLabeledSpinner(this, labels[1], yearModel);
		//去除数字编辑器中的千分位分割符
		spinner.setEditor(new JSpinner.NumberEditor(spinner, "#"));

		//添加第三个选择器
		Date initDate = calendar.getTime();
		calendar.add(Calendar.YEAR, -100);
		Date earliestDate = calendar.getTime();
		calendar.add(Calendar.YEAR, 200);
		Date latestDate = calendar.getTime();
		SpinnerDateModel dateModel = new SpinnerDateModel(initDate,
				earliestDate,
				latestDate,
				Calendar.YEAR);//ignored for user input
		dateSpinner = spinner = addLabeledSpinner(this, labels[2], dateModel);
		// 设置日期的格式
		spinner.setEditor(new JSpinner.DateEditor(spinner, "MM/yyyy"));
		// 调整选择器的输入框格式
		ftf = getTextField(spinner);
		if (ftf != null) {
			ftf.setHorizontalAlignment(JTextField.RIGHT);
			ftf.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 3));
		}
		// 设置初始字体色
		setSeasonalColor(dateModel.getDate());
		//监听日期的改变，改变字体色
		dateSpinner.addChangeListener(this);

		// SpringUtilities.makeCompactGrid(this,
		// 		numPairs, 2,
		// 		10, 10,
		// 		6, 10);
	}

	/**
	 * Return the formatted text field used by the editor, or
	 * null if the editor doesn't descend from JSpinner.DefaultEditor.
	 */
	public JFormattedTextField getTextField(JSpinner spinner) {
		JComponent editor = spinner.getEditor();
		if (editor instanceof JSpinner.DefaultEditor) {
			return ((JSpinner.DefaultEditor) editor).getTextField();
		} else {
			System.err.println("Unexpected editor type: "
					+ spinner.getEditor().getClass()
					+ " isn't a descendant of DefaultEditor");
			return null;
		}
	}

	/**
	 * Required by the ChangeListener interface. Listens for
	 * changes in the date spinner and does something silly in
	 * response.
	 */
	public void stateChanged(ChangeEvent e) {
		SpinnerModel dateModel = dateSpinner.getModel();
		if (dateModel instanceof SpinnerDateModel) {
			setSeasonalColor(((SpinnerDateModel) dateModel).getDate());
		}
	}

	/**
	 * 根据传入时间设置输入框字体色
	 */
	protected void setSeasonalColor(Date date) {
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH);
		JFormattedTextField ftf = getTextField(dateSpinner);
		if (ftf == null) return;

		//Set the color to match northern hemisphere seasonal conventions.
		switch (month) {
			case 2:
			case 3:
			case 4:
				ftf.setForeground(SPRING_COLOR);
				break;

			case 5:
			case 6:
			case 7:
				ftf.setForeground(SUMMER_COLOR);
				break;

			case 8:
			case 9:
			case 10:
				ftf.setForeground(FALL_COLOR);
				break;

			default:
				ftf.setForeground(WINTER_COLOR);
		}
	}

	/**
	 * DateFormatSymbols returns an extra, empty value at the
	 * end of the array of months.  Remove it.
	 */
	static protected String[] getMonthStrings() {
		String[] months = new java.text.DateFormatSymbols().getMonths();
		int lastIndex = months.length - 1;

		if (months[lastIndex] == null || months[lastIndex].length() <= 0) { //last item empty
			String[] monthStrings = new String[lastIndex];
			System.arraycopy(months, 0, monthStrings, 0, lastIndex);
			return monthStrings;
		} else { //last item not empty
			return months;
		}
	}

	static protected JSpinner addLabeledSpinner(Container c,
	                                            String label,
	                                            SpinnerModel model) {
		JLabel l = new JLabel(label, SwingConstants.RIGHT);
		c.add(l);

		JSpinner spinner = new JSpinner(model);
		l.setLabelFor(spinner);
		c.add(spinner);

		return spinner;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			SwingTestUtil.init(new FlowLayout()).add(new SpinnerTest(true));
			UIManager.put("swing.boldMetal", Boolean.FALSE);
			SwingTestUtil.test();
		});
	}
}
