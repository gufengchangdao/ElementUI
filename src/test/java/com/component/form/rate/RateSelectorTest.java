package com.component.form.rate;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class RateSelectorTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			RateSelector rateSelector = new RateSelector();
			// rateSelector.setIcon(FaceSmileFillSvg.of(16,16));
			// rateSelector.setIcon(ThumbsUpSvg.of(16, 16));
			// 设置图标颜色渐变
			rateSelector.setTurnColor(true);
			// 设置初始
			rateSelector.setSelectedCount(2);
			// 设置右侧提示文本，文本宽度依据最长字符串而定
			rateSelector.setTipList(Arrays.asList("0分", "1分", "2分", "3分", "4分", "超级满分结果"));
			// rateSelector.setEnabled(false);
			JButton button = new JButton("获取");
			button.addActionListener(e -> {
				System.out.println(rateSelector.getSelectedCount());
			});
			SwingTestUtil.test(rateSelector, button);
		});

	}
}
