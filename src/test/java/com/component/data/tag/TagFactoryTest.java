package com.component.data.tag;

import com.component.basic.color.ColorUtil;
import com.component.util.SwingTestUtil;

import java.awt.*;

import static com.component.data.tag.TagFactory.createDefaultTag;
import static com.component.data.tag.TagFactory.createIconTag;

public class TagFactoryTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			ETag tag1 = createDefaultTag("标签一", ColorUtil.PRIMARY);
			ETag tag2 = createIconTag("Tag 组件提供除了默认值以外的三种尺寸", ColorUtil.WARNING);
			SwingTestUtil.test(tag1, tag2);
		});
	}
}
