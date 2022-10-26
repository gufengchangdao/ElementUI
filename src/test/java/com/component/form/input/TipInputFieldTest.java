package com.component.form.input;

import com.component.util.SwingTestUtil;

import java.awt.*;

public class TipInputFieldTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() ->{
			SwingTestUtil.loadSkin();

			// TipInputField field = new TipInputField(20, "请输入账号");
			TipInputField field = new TipInputField("");
			field.setPlaceholder("请输入密码");
			SwingTestUtil.test(field);
		});
	}
}
