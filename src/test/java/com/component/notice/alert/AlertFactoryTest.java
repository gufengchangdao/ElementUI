package com.component.notice.alert;

import com.component.common.template.X2Component;
import com.component.util.SwingTestUtil;

import java.awt.*;

import static com.component.notice.alert.AlertFactory.*;

public class AlertFactoryTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();

			SwingTestUtil.test(
					createSuccessAlert("成功提示的文案", true, true, X2Component.GrowStyle.LEFT_GROW),
					createInfoAlert("消息提示的文案", true, true, X2Component.GrowStyle.LEFT_GROW),
					createWarningAlert("警告提示的文案", true, true, X2Component.GrowStyle.LEFT_GROW),
					createDangerAlert("错误提示的文案", true, true, X2Component.GrowStyle.LEFT_GROW)
			);
		});
	}
}
