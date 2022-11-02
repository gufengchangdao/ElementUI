package com.component.others.line;

import com.component.basic.color.ColorUtil;
import com.component.util.SwingTestUtil;

import java.awt.*;

public class LineLabelTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			LineLabel lineLabel = new LineLabel(3);
			lineLabel.setForeground(ColorUtil.PRIMARY);
			lineLabel.setPreferredSize(new Dimension(200, 200));
			SwingTestUtil.test(lineLabel);
		});
	}
}
