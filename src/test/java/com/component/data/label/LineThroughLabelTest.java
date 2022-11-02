package com.component.data.label;

import com.component.util.SwingTestUtil;

import java.awt.*;

public class LineThroughLabelTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			SwingTestUtil.test(new LineThroughLabel("测试标签删除线"));
		});
	}
}
