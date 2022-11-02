package com.component.data.badge;

import com.component.basic.color.ColorUtil;
import com.component.data.label.badge.BadgeLabel;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class BadgePanelGroupTest {
	public static void main(String[] args) {
		// 在事件队列中执行是为了保持test()和setAllBadgeLocation()是在同一线程下执行
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();

			JPanel panel = new JPanel();
			JButton b1 = new JButton("添加");
			b1.setPreferredSize(new Dimension(150, 25));
			JButton b2 = new JButton("移除");
			JButton b3 = new JButton("修改");
			panel.add(b1);
			panel.add(b2);
			panel.add(b3);
			// panel.getLayout().layoutContainer(panel);
			// System.out.println(SwingUtilities.convertPoint(panel, 0, 0, b1));

			BadgePanelGroup badgePanelGroup = new BadgePanelGroup(panel,
					Arrays.asList(b2, b3),
					Arrays.asList(new BadgeLabel(null, ColorUtil.PRIMARY, Color.WHITE),
							new BadgeLabel("56", ColorUtil.SUCCESS, Color.WHITE)),
					12
			);
			badgePanelGroup.getPanel().setBorder(BorderFactory.createLineBorder(Color.RED));
			b1.addActionListener(e -> {
				BadgeLabel newLabel = new BadgeLabel("新添加超级长的标签", ColorUtil.PRIMARY, Color.WHITE);
				badgePanelGroup.addBadge(b1, newLabel);

				// 如果不重绘分层面板的父组件，新添加的标签会被分层面板裁剪，重绘后则不会
				// 这里指定绘制区域，如果嫌麻烦，可以直接调用repaint()
				// 1. 使用封装的方法，这个最简便
				badgePanelGroup.repaint(SwingTestUtil.getFrame().getContentPane(), newLabel);

				// 2. 把层次面板中的坐标转为容器中的坐标，手动调用
				// Point point = SwingUtilities.convertPoint(badgePanelGroup.getLayeredPane(),
				// 		newLabel.getX(), newLabel.getY(),
				// 		SwingTestUtils.getFrame().getContentPane());
				// Dimension size = newLabel.getPreferredSize();
				// SwingTestUtils.getFrame().getContentPane().repaint(point.x, point.y, size.width,
				// 		size.height);
				// 3. 重绘所有，很不推荐
				// SwingTestUtils.getFrame().getContentPane().repaint();
			});
			b2.addActionListener(e -> {
				BadgeLabel delLabel = badgePanelGroup.removeBadge(b2);
				// 原有标记可能就绘制在层次面板外面，这里不能只调用层次面板的repaint()
				if (delLabel != null)
					badgePanelGroup.repaint(SwingTestUtil.getFrame().getContentPane(), delLabel);
			});
			b3.addActionListener(e -> {
				BadgeLabel label = badgePanelGroup.getLabel(b3);
				label.setText("99");
				badgePanelGroup.repaint(SwingTestUtil.getFrame().getContentPane(), label);
			});

			SwingTestUtil.test(badgePanelGroup.getLayeredPane());
			//注意，只有在pack()之后才能获取到组件所在容器中的位置
			badgePanelGroup.setAllBadgeLocation();
		});
	}
}
