package com.component.form.select;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CascaderSelectorTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();

			HashMap<String, Map<String, ?>> childNode2 = new HashMap<>();
			childNode2.put("大苹果", null);
			childNode2.put("中苹果", null);
			childNode2.put("小苹果", null);

			Map<String, Map<String, ?>> childNode1 = new HashMap<>();
			childNode1.put("甜苹果", childNode2);
			childNode1.put("酸苹果", null);
			childNode1.put("青苹果", null);

			HashMap<String, Map<String, ?>> map = new HashMap<>();
			map.put("苹果", childNode1);
			map.put("香蕉", null);
			map.put("梨", null);

			CascaderSelector main = new CascaderSelector(15, "，", map);
			ItemSelector itemSelector = main.getItemSelector();

			JButton button = new JButton("获取");
			button.addActionListener(e -> {
				System.out.println(main.getText());
			});


			JButton addButton = new JButton("添加");
			addButton.addActionListener(e -> itemSelector.addChild("西瓜"));
			JButton delButton = new JButton("删除");
			delButton.addActionListener(e -> {
				itemSelector.removeChild("西瓜");
				itemSelector.removeChild("苹果");
				itemSelector.removeChild("香蕉");
			});
			SwingTestUtil.test(main, button, addButton, delButton);
		});
	}
}
