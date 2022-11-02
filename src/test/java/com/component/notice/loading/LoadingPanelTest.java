package com.component.notice.loading;

import com.component.basic.color.ColorUtil;
import com.component.common.template.X2Component;
import com.component.data.result.ResultFactory;
import com.component.notice.alert.AlertFactory;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoadingPanelTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.setDefaultTimingSource();
			SwingTestUtil.loadSkin();

			// LoadingPanel<LoadingLabel> panel = new LoadingPanel<>(
			// 		new LoadingLabel(CrosshairSvg.of(48, 48), 700));
			LoadingPanel<LoadingLabel2> panel = new LoadingPanel<>(
					new LoadingLabel2(ColorUtil.PRIMARY, 3));
			panel.setPreferredSize(new Dimension(400, 400));
			panel.setBorder(BorderFactory.createLineBorder(Color.RED));
			JFrame frame = SwingTestUtil.getFrame();
			frame.setGlassPane(panel);

			JButton b = new JButton("开启加载");
			b.addActionListener(e -> {
				//获取图像得在该面板加入面板之前，不然会把加载动画也印上去
				panel.flushBackground();
				frame.getGlassPane().setVisible(true);
			});
			panel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					frame.getGlassPane().setVisible(false);
				}
			});
			SwingTestUtil.test(ResultFactory.createSuccessResult("成功文本", new JButton("返回")),
					AlertFactory.createSuccessAlert("这是一条警告", true, true, X2Component.GrowStyle.LEFT_GROW),
					new JTextField("输入框"),
					b);
		});
	}
}
