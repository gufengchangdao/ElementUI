package com.component.notice.alert;

import com.component.basic.color.ColorUtil;
import com.component.common.template.X2Component;
import com.component.svg.icon.fill.CheckCircleSvg;
import com.component.util.SwingTestUtil;

import java.awt.*;

public class AlertComponentTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			SwingTestUtil.test(new AlertComponent(CheckCircleSvg.of(16, 16),
					"成功提示的文案",
					// """
					// 		<html>
					// 		<b>带辅助性文字介绍</b>
					// 		<p>这是一句绕口令：黑灰化肥会挥发发灰黑化肥挥发；灰黑化肥会挥发发黑灰化肥发挥。 黑灰化肥会挥发发灰黑化肥黑灰挥发化为灰……</p>
					// 		</html>""",
					ColorUtil.SUCCESS,
					true,
					X2Component.GrowStyle.LEFT_GROW,
					new Insets(8, 16, 8, 16)));
		});
	}
}
