package com.component.data.empty;

import com.component.basic.button.ButtonFactory;
import com.component.basic.color.ColorUtil;
import com.component.svg.empty.EmptyImgGraySvg;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

public class EmptyComponentTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			// SwingTestUtils.test(new EmptyPanel(null, "暂无数据",
			// 		ButtonFactory.createDefaultButton("按钮", ColorUtil.PRIMARY)));
			SwingTestUtil.test(new EmptyComponent(new JLabel(EmptyImgGraySvg.of(200, 200)),
					"暂无数据",
					ButtonFactory.createDefaultButton("按钮", ColorUtil.PRIMARY)));
		});
	}
}
