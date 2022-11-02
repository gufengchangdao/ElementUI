package com.component.font;

import com.component.util.SwingTestUtil;

import javax.swing.*;

public class FontTest {
	public static void main(String[] args) {
		SwingTestUtil.loadSkin();
		FontUtil.registerGlobalFont(FontUtil.getFont("/font/ZhanKuXiaoLOGOTi-2.otf"));

		JTextArea c2 = new JTextArea("如果概括奥托·阿波卡利斯的一生，我想，用「卡莲·卡斯兰娜」再精确不过了\n" +
				"他的确称不上是一个好人，但他绝对是天命迄今为止最为伟大的主教，也是一个真正的男人");

		SwingTestUtil.test(c2);
	}
}
