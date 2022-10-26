package display.notice;

import com.component.basic.color.ColorUtil;
import com.component.notice.notification.NotificationComponent;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.svg.icon.fill.XCircleSvg;
import com.component.util.SwingTestUtil;
import display.util.SwingDisplayUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

import static com.component.notice.notification.NotificationFactory.openNotification;

public class NotificationDisplay {
	public static void main(String[] args) {
		Container p = SwingDisplayUtil.init(new MigLayout("", "grow, center"));

		JComponent glassPane = (JComponent) SwingTestUtil.getFrame().getGlassPane();
		glassPane.setLayout(null);

		JButton button = new JButton("弹出");
		button.addActionListener(e -> {
			// Popup popup = getPopup(SwingTestUtil.getFrame().getContentPane(), "提示",
			// 		new JLabel("这是一条成功的提示消息"), 4500, true, true,
			// 		SwingConstants.NORTH_EAST, 10);
			// popup.show();
			glassPane.setVisible(true);
			RadianceIcon icon = XCircleSvg.of(16, 16);
			icon.setColorFilter(color -> ColorUtil.SUCCESS);
			NotificationComponent c = openNotification(glassPane, "提示", icon, new JLabel("这是一条成功的提示消息"),
					true, true, SwingConstants.NORTH_EAST, 10, 4500);
		});
		p.add(button);

		SwingDisplayUtil.test();
	}
}
