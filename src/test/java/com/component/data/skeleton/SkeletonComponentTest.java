package com.component.data.skeleton;

import com.component.util.SwingTestUtil;

import java.awt.*;

public class SkeletonComponentTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			SkeletonComponent c = new SkeletonComponent(new Dimension(100, 30));
			SwingTestUtil.test(c);
		});
	}
}
