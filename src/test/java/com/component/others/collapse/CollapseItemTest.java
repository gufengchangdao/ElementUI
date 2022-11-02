package com.component.others.collapse;

import com.component.util.SwingTestUtil;

import java.awt.*;

public class CollapseItemTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			SwingTestUtil.setDefaultTimingSource();
			SwingTestUtil.setSize(600, 400);

			SwingTestUtil.test(new CollapseItem("一致性 Consistency",
					"与现实生活一致：与现实生活的流程、逻辑保持一致，遵循用户习惯的语言和概念；\n" +
							"在界面中一致：所有的元素和结构需保持一致，比如：设计样式、图标和文本、元素的位置等。"));
		});
	}
}
