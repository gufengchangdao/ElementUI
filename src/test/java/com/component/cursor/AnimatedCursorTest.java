package com.component.cursor;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class AnimatedCursorTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new FlowLayout());

			JButton b = new JButton("悬停");
			b.setPreferredSize(new Dimension(200, 200));
			// 读取图片，这里使用Toolkit.createImage()来读取图片
			// 也可以使用ImageIO读取图片，但是如果是jdk版本低的话图片会有红色背景(jdk8会有)
			Toolkit tk = b.getToolkit();
			new AnimatedCursor(b, Arrays.asList(
					tk.createImage("src/test/resources/lab/cursor/00.png"),
					tk.createImage("src/test/resources/lab/cursor/01.png"),
					tk.createImage("src/test/resources/lab/cursor/02.png")));
			p.add(b);
			SwingTestUtil.test();
		});
	}
}
