package com.component.others.collapse;

import com.component.common.component.BaseComponent;
import org.jdesktop.swingx.VerticalLayout;

import java.util.List;

/**
 * 折叠面板
 * <p>
 * 通过折叠面板收纳内容区域
 */
public class CollapsePanel extends BaseComponent {
	private List<CollapseItem> items;
	// private

	public CollapsePanel(List<CollapseItem> items) {
		this.items = items;
		init();
	}

	private void init() {
		setLayout(new VerticalLayout(5));
		for (CollapseItem item : items) {
			add(item);
		}
	}
}

