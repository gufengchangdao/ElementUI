package com.component.basic.border;

import com.component.basic.color.ColorUtil;
import com.component.common.component.BaseInputField;
import com.component.util.SwingTestUtil;

import java.awt.*;

public class RoundBorderTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			BaseInputField field = new BaseInputField(20);
			field.setPlaceholder("请输入关键词");
			field.setBorder(new RoundBorder(ColorUtil.SECONDARY_TEXT, 10));
			SwingTestUtil.test(field);
		});
	}
}
