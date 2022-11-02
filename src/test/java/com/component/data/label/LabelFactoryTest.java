package com.component.data.label;

import com.component.font.FontUtil;
import com.component.util.SwingTestUtil;

import java.awt.*;

public class LabelFactoryTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			SwingTestUtil.test(LabelFactory.createLabel("书籍未拆封", FontUtil.SMALL_FONT, Color.RED));
		});
	}
}
