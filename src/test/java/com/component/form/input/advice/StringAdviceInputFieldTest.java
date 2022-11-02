package com.component.form.input.advice;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class StringAdviceInputFieldTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout("wrap 1"));
			StringAdviceInputField field = new StringAdviceInputField(new String[]{
					"霜雪千年", "阿波卡利斯", "绝世武神", "一刀两断", "一言九鼎"
			});
			p.add(field, "w ::80%");
			JButton b = new JButton("获取");
			b.addActionListener(e -> {
				Optional.ofNullable(field.getSelectedItem()).ifPresent(o -> {
					System.out.println(o.getClass());
					System.out.println(o);
				});
			});
			p.add(b);
			SwingTestUtil.test();
		});
	}
}
