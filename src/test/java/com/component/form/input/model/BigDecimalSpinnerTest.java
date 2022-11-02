package com.component.form.input.model;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class BigDecimalSpinnerTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout("wrap 1"));
			p.add(new JLabel("SpinnerNumberModel(double, ...)"));
			p.add(new JSpinner(new SpinnerNumberModel(2.01, 2.00, 3.02, .01)));
			p.add(new JSpinner(new SpinnerNumberModel(29.7, 29.6, 30.2, .1)));

			p.add(new JLabel("BigDecimalSpinnerModel"));
			p.add(new JSpinner(new BigDecimalSpinnerModel(2.01, 2.00, 3.02, .01)));
			p.add(new JSpinner(new BigDecimalSpinnerModel(29.7, 29.6, 30.2, .1)));


			p.add(new JLabel("精度测试"));
			double d = 29.7 - 29.6 - .1;
			String s1 = String.format("%f-%f-%f>=0:%b%n", 29.7, 29.6, .1, d >= .0);
			String s2 = String.format("abs(%f-%f-%f)<1.0e-14:%b%n", 29.7, 29.6, .1, Math.abs(d) < 1.0e-14);
			String s3 = String.format("abs(%f-%f-%f)<1.0e-15:%b%n", 29.7, 29.6, .1, Math.abs(d) < 1.0e-15);
			p.add(new JScrollPane(new JTextArea(s1 + s2 + s3)));

			SwingTestUtil.test();
		});
	}
}
