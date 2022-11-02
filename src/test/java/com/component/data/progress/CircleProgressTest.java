package com.component.data.progress;

import com.component.svg.icon.regular.CircleWavyCheckSvg;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class CircleProgressTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();

			CircleProgress progress = new CircleProgress();

			new Thread(() -> {
				try {
					for (int i = 0; i < 101; i++) {
						progress.setValue(i);
						TimeUnit.MILLISECONDS.sleep(30);
					}
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}).start();

			JButton button = new JButton("添加");
			button.addActionListener(e -> {
				// 进度
				// progress.setFont(new Font("宋体",Font.BOLD,50));
				// progress.setStringPainted(true);

				// label
				// progress.setLabel(new JLabel("轮换转盘"));

				// 图标
				progress.setLabel(new JLabel(CircleWavyCheckSvg.of(32, 32)));

				progress.setLineWidth(20);

				progress.repaint();
			});

			SwingTestUtil.test(progress, button);
		});
	}
}
