package display.notice;

import com.component.animator.popup.PopupAnimatorTask;
import com.component.common.template.X2Component;
import com.component.notice.alert.AlertComponent;
import com.component.notice.message.MessageFactory;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class MessageDisplay {
	public static void main(String[] args) {
		Container p = SwingTestUtil.init(new MigLayout("", "grow,center"));

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
		p.add(button);

		SwingTestUtil.test();
	}
}
