package com.component.util;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/////////////////////////////////////////////////////////
//  Bare Bones Browser Launch                          //
//  Version 1.5                                        //
//  December 10, 2005                                  //
//  Supports: Mac OS X, GNU/Linux, Unix, Windows XP    //
//  Example Usage:                                     //
//   String url = "https://www.centerkey.com/";        //
//   BareBonesBrowserLaunch.openUrl(url);              //
//  Public Domain Software -- Free to Use as You Like  //
/////////////////////////////////////////////////////////

/**
 * 浏览器启动器
 */
public class BrowserLauncherUtil {
	private static final String ERR_MSG = "Error attempting to launch web browser";

	private BrowserLauncherUtil() {
		/* Singleton */
	}

	public static void openUrl(String url) {
		String osName = System.getProperty("os.name");
		try {
			if (osName.startsWith("Mac OS")) {
				macOpenUrl(url);
			} else if (osName.startsWith("Windows")) {
				windowsOpenUrl(url);
			} else { // assume Unix or Linux
				linuxOpenUrl(url);
			}
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		} catch (IOException | ClassNotFoundException | IllegalAccessException | InvocationTargetException |
		         NoSuchMethodException ex) {
			Toolkit.getDefaultToolkit().beep();
			String msg = ERR_MSG + ":\n" + ex.getLocalizedMessage();
			JOptionPane.showMessageDialog(null, msg, "title", JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void macOpenUrl(String url) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Class<?> fileMgr = Class.forName("com.apple.eio.FileManager");
		// Method openURL = fileMgr.getDeclaredMethod("openURL", new Class[] {String.class});
		Method openUrl = fileMgr.getDeclaredMethod("openURL", String.class);
		openUrl.invoke(null, url);
	}

	private static void windowsOpenUrl(String url) throws IOException {
		Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler " + url);
	}

	private static void linuxOpenUrl(String url) throws InterruptedException, IOException {
		String[] browsers = {"firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape"};
		String browser = null;
		for (int count = 0; count < browsers.length && Objects.isNull(browser); count++) {
			String[] cmd = {"which", browsers[count]};
			if (Runtime.getRuntime().exec(cmd).waitFor() == 0) {
				browser = browsers[count];
			}
		}
		if (Objects.nonNull(browser)) {
			Runtime.getRuntime().exec(new String[]{browser, url});
		} else {
			throw new UnsupportedOperationException("Could not find web browser");
		}
	}
}
