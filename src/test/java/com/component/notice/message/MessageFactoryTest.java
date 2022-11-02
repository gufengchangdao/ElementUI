package com.component.notice.message;

import com.component.animator.popup.PopupAnimatorTask;
import com.component.common.template.X2Component;
import com.component.notice.alert.AlertComponent;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

public class MessageFactoryTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			SwingTestUtil.setDefaultTimingSource();

			JLayeredPane panel = new JLayeredPane();
			SwingTestUtil.getFrame().setGlassPane(panel);

			MessageFactory messageFactory = new MessageFactory(panel);

			JButton button = new JButton("弹出");
			button.addActionListener(e -> {
				PopupAnimatorTask<AlertComponent> task = messageFactory.openSuccessMessage("你成功了！", true, X2Component.GrowStyle.LEFT_GROW,
						10, true, true);
				messageFactory.openInfoMessage("你成功了！", true, X2Component.GrowStyle.LEFT_GROW,
						50, true, true);
				messageFactory.openDangerMessage("你成功了！", true, X2Component.GrowStyle.LEFT_GROW,
						90, true, true);
				messageFactory.openWarningMessage("你成功了！", true, X2Component.GrowStyle.LEFT_GROW,
						130, true, false);
				panel.setVisible(true);
			});
			SwingTestUtil.test(button);
		});
	}
}
