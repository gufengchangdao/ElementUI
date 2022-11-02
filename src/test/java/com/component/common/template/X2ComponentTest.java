package com.component.common.template;

import com.component.basic.button.IconButton;
import com.component.basic.color.ColorUtil;
import com.component.data.tag.TagFactory;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.svg.icon.fill.CheckCircleSvg;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class X2ComponentTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			RadianceIcon icon = CheckCircleSvg.of(16, 16);
			icon.setColorFilter(color -> ColorUtil.SUCCESS);
			JLabel label = new JLabel("成功提示的文案", icon, JLabel.LEFT);
			label.setForeground(ColorUtil.SUCCESS);

			IconButton button = TagFactory.createCloseButton(ColorUtil.PLACEHOLDER_TEXT);

			X2Component<JLabel, IconButton> c = new X2Component<>(label, button, X2Component.GrowStyle.LEFT_GROW, new Insets(8, 16, 8, 16));
			Color bg = ColorUtil.blend(ColorUtil.SUCCESS, Color.WHITE, .8f);
			c.setBorderColor(bg);
			c.setBackground(bg);
			c.init(20);
			c.repaint();

			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					c.setPreferredSize(new Dimension(400, 300));
					c.setSize(400, 300);
					// 组件重新布局
					// c.validate();
					// 调用容器的validate()是因为组件变长后可能会溢出窗口
					SwingTestUtil.getFrame().getContentPane().validate();
				}
			});

			SwingTestUtil.test(c);
		});
	}
}
