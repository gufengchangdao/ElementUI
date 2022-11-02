package com.component.notice.message;

import com.component.basic.color.ColorUtil;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.svg.icon.fill.SwordSvg;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class SystemMessageFactoryTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout());

			MenuItem exitItem = new MenuItem("EXIT");
			exitItem.addActionListener(e -> {
				Container c = p.getTopLevelAncestor();
				if (c instanceof Window) {
					((Window) c).dispose();
				}
				//其实会自动删除的
				// SystemTray tray = SystemTray.getSystemTray();
				// for (TrayIcon icon : tray.getTrayIcons()) {
				// 	tray.remove(icon);
				// }
			});
			PopupMenu popup = new PopupMenu();
			popup.add(exitItem);

			// 托盘图标
			RadianceIcon ic = SwordSvg.of(16, 16);
			ic.setColorFilter(color -> ColorUtil.PRIMARY);
			TrayIcon icon = new TrayIcon(ic.toImage(1), "这是系统托盘", popup);
			try {
				// 托盘图标添加到系统托盘中
				SystemTray.getSystemTray().add(icon);
			} catch (AWTException ex) {
				throw new IllegalStateException(ex);
			}

			JComboBox<TrayIcon.MessageType> messageType = new JComboBox<>(TrayIcon.MessageType.values());

			JButton messageButton = new JButton("弹出消息");
			messageButton.addActionListener(e -> {
				SystemMessageFactory.openMessage("标题", "正文",
						messageType.getItemAt(messageType.getSelectedIndex()));
			});
			p.add(messageType);
			p.add(messageButton);

			SwingTestUtil.test();
		});
	}
}
