package com.component.other.tooltip;

import com.component.basic.color.ColorUtil;
import com.component.common.SwingPosition;
import com.component.others.tooltip.TooltipLabel;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

public class TooltipLabelTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() ->{
			SwingTestUtil.loadSkin();
			SwingTestUtil.setDefaultTimingSource();

			JButton b = new JButton("按钮");
			TooltipLabel tooltipLabel = new TooltipLabel(b, "这是提示文本", SwingPosition.TOP_LEFT, ColorUtil.SUCCESS);

			b.addActionListener(e -> {
				tooltipLabel.setText("这是新的提示文本");
			});
			SwingTestUtil.test(b);
		});
	}
}
