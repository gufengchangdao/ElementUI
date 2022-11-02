package com.component.data.progress;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class LineRoundedProgressTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();

			LineRoundedProgress pgbar = new LineRoundedProgress();
			pgbar.setStringPainted(true);

			new Thread(() -> {
				try {
					for (int i = 0; i < 101; i++) {
						pgbar.setValue(i);
						TimeUnit.MILLISECONDS.sleep(30);
					}
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}).start();

			JButton button = new JButton("获取");
			button.addActionListener(e -> {
				System.out.println(pgbar.getPercentComplete()); //0到1的值
				System.out.println(pgbar.getString()); //百分数
				System.out.println(pgbar.getValue()); //真实值

			});
			SwingTestUtil.test(pgbar, button);
		});
	}
}
