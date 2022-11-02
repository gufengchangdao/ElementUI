package com.component.notice.message;

import java.awt.*;

/**
 * 系统提示的简便写法
 * <p>
 * 注意要弹出消息，必须先托盘图标到系统托盘，例如：
 *
 * <pre>
 * TrayIcon icon = new TrayIcon(img, "这是托盘", popup);
 * SystemTray.getSystemTray().add(icon);
 * openErrorMessage("标题", "错误消息");
 * </pre>
 */
public class SystemMessageFactory {
	public static void openErrorMessage(String caption, String text) {
		openMessage(caption, text, TrayIcon.MessageType.ERROR);
	}

	public static void openWarningMessage(String caption, String text) {
		openMessage(caption, text, TrayIcon.MessageType.WARNING);
	}

	public static void openPlainMessage(String caption, String text) {
		openMessage(caption, text, TrayIcon.MessageType.NONE);
	}

	public static void openInfoMessage(String caption, String text) {
		openMessage(caption, text, TrayIcon.MessageType.INFO);
	}

	public static void openMessage(String caption, String text, TrayIcon.MessageType type) {
		TrayIcon[] icons = SystemTray.getSystemTray().getTrayIcons();
		if (icons.length > 0) {
			icons[0].displayMessage(caption, text, type);
		}
	}
}
