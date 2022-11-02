package com.component.basic.button;

import com.component.basic.color.ColorUtil;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

import static com.component.basic.button.ButtonFactory.createDefaultButton;

public class ButtonFactoryTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();

			JButton button = createDefaultButton("点击我", ColorUtil.PRIMARY);
			button.setEnabled(false);
			SwingTestUtil.test(button);
			// SwingTestUtils.test(createIconButton(200, 50, "登录", ResourcesUtil.getImage(ResourcesUtil.Image.LOGO), SwingConstants.LEFT));
			SwingTestUtil.test(createDefaultButton("你好的", ColorUtil.PRIMARY));
			// SwingTestUtils.test(createPlainButton("你好的", ColorUtil.PRIMARY));
			// SwingTestUtils.test(createRoundButton("进程已结束", ColorUtil.PRIMARY));
			// SwingTestUtils.test(createIconRoundButton(
			// 		new ImageIcon(ResourcesUtil.getImage(ResourcesUtil.Image.LOGO)), ColorUtil.PRIMARY, false));
		});
	}
}
