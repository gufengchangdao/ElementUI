package com.component.others.popconfirm;

import com.component.basic.button.ButtonFactory;
import com.component.basic.color.ColorUtil;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.svg.icon.fill.QuestionSvg;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

public class PopConfirmTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			SwingTestUtil.setDefaultTimingSource();

			JButton del = ButtonFactory.createPlainButton("删除", ColorUtil.PRIMARY);

			RadianceIcon icon = QuestionSvg.of(16, 16);
			icon.setColorFilter(color -> ColorUtil.WARNING);
			PopConfirm p = new PopConfirm(del, new JLabel("这是一段内容确定删除吗？", icon, SwingConstants.LEFT));
			SwingTestUtil.test(del);
		});
	}
}
