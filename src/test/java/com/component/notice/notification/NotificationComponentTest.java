package com.component.notice.notification;

import com.component.basic.color.ColorUtil;
import com.component.util.SwingTestUtil;
import org.jdesktop.swingx.border.DropShadowBorder;

import javax.swing.*;
import java.awt.*;

public class NotificationComponentTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();

			JLabel label = new JLabel("这是一条成功的提示消息");

			NotificationComponent c = new NotificationComponent("提示", label, true);
			// 绘制阴影
			c.setBorder(new DropShadowBorder(ColorUtil.INFO, 7, 0.2f, 12,
					true, true, true, true));

			SwingTestUtil.test(c);
		});
	}
}
