package com.component.basic.button;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

public class RoundButtonTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			RoundButton button1 = new RoundButton("测试按钮",
					// new ImageIcon("D:\\MyDefault\\desktop\\SchoolBooksTrade\\SBT\\src\\main\\resources\\images\\choiceness.png"),
					null,
					Color.GRAY, 10);
			JButton button2 = new JButton("测试按钮");
			button2.setBackground(Color.GRAY);
			SwingTestUtil.test(button1, button2);
		});
	}
}
