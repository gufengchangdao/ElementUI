package com.component.others.collapse.expandable;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 折叠面板
 */
public class ExpandablePanel extends JPanel implements ExpansionListener {
	// 三个面板放入BorderLayout布局中，每次点击都将要展开的面板放入 centerBox
	// 这样的好处是设置面板大小时影响的主要是中间展开的面板
	Box northBox = Box.createVerticalBox();
	Box centerBox = Box.createVerticalBox();
	Box southBox = Box.createVerticalBox();
	List<? extends ExpansionPanel> panelList;

	/**
	 * @param titles 折叠面板的标题组件
	 * @param panels 折叠面板的内容组件，内容组件会放入 JScrollPane 中，因此panel不必再创建 JScrollPane
	 */
	public ExpandablePanel(List<Component> titles, List<Component> panels) {
		super(new BorderLayout());
		Iterator<Component> it = panels.iterator();
		this.panelList = titles.stream()
				.map(component -> new ExpansionPanel(component, it.next()))
				.collect(Collectors.toList());
		init();
	}

	public ExpandablePanel(List<? extends ExpansionPanel> panelList) {
		super(new BorderLayout());
		this.panelList = panelList;

		init();
	}

	private void init() {
		panelList.forEach(exp -> {
			northBox.add(exp);
			exp.addExpansionListener(this);
		});

		add(northBox, BorderLayout.NORTH);
		add(centerBox);
		add(southBox, BorderLayout.SOUTH);
	}

	@Override
	public void expansionStateChanged(ExpansionEvent e) {
		setVisible(false); //起到重新布局和重绘的作用
		Component source = (Component) e.getSource();
		centerBox.removeAll();
		northBox.removeAll();
		southBox.removeAll();
		boolean insertSouth = false;
		for (ExpansionPanel exp : panelList) {
			// 点击的按钮展开面板，其他面板折叠
			if (source.equals(exp) && exp.isExpanded()) {
				centerBox.add(exp);
				insertSouth = true;
				continue;
			}
			exp.setExpanded(false);
			if (insertSouth) {
				southBox.add(exp);
			} else {
				northBox.add(exp);
			}
		}
		setVisible(true);
	}
}

