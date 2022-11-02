package com.component.others.carousel;

import com.component.common.component.NoMouseListenerComponentFactory;
import com.component.util.SwingTestUtil;

import java.awt.*;
import java.util.Arrays;

public class SlideshowPanelTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.setDefaultTimingSource();
			SwingTestUtil.loadSkin();
			SwingTestUtil.setSize(900, 200);
			// SwingTestUtils.test(new SlideshowPanel(850, 180, List.of(
			// 		new JLabel("你好，这里是测试频道1你好，这里是测试频道1你好，这里是测试频道1你好，这里是测试频道1你好，这里是测试频道1你好，这里是测试频道1你好，这里是测试频道1你好，这里是测试频道1",SwingConstants.LEFT),
			// 		new JLabel("你好，这里是测试频道2"), new JLabel("你好，这里是测试频道3")),
			// 		integer -> System.out.println("点击了第 " + (integer + 1) + "个label")));
			SwingTestUtil.test(new SlideshowPanel(850, 180, 1500L, Arrays.asList(
					NoMouseListenerComponentFactory.getTextArea("测试文档1"),
					NoMouseListenerComponentFactory.getTextArea("测试文档2"),
					NoMouseListenerComponentFactory.getTextArea("测试文档3"),
					NoMouseListenerComponentFactory.getTextArea("测试文档4")
			),
					integer -> System.out.println("点击了第 " + (integer + 1) + "个label")));
		});
	}
}
