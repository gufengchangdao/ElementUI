package com.component.data.tag;

import com.component.basic.button.IconButton;
import com.component.basic.color.ColorUtil;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.svg.icon.regular.XCircleSvg;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

import static java.awt.BorderLayout.NORTH;

public class ETagTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();

			RadianceIcon icon1 = XCircleSvg.of(16, 16);
			RadianceIcon icon2 = com.component.svg.icon.fill.XCircleSvg.of(16, 16);
			RadianceIcon.ColorFilter filter1 = color -> ColorUtil.PRIMARY;
			icon1.setColorFilter(filter1);
			icon2.setColorFilter(filter1);
			RadianceIcon.ColorFilter filter2 = color -> ColorUtil.blend(ColorUtil.PRIMARY, Color.WHITE, 0.3f);
			IconButton closeButton = new IconButton(icon1, icon2, filter2);

			ETag tag = new ETag("标签一",
					ColorUtil.PRIMARY, ColorUtil.blend(ColorUtil.PRIMARY, Color.WHITE, 0.8f),
					closeButton, BorderLayout.EAST);
			ETag tag2 = new ETag("标签一",
					ColorUtil.PRIMARY, ColorUtil.changeAlpha(ColorUtil.blend(ColorUtil.PRIMARY, Color.WHITE, 0.6f), .5f),
					null, NORTH);

			JButton button = new JButton("修改");
			button.addActionListener(e -> {
				tag.setFont(tag.getFont().deriveFont(30f));
				tag2.setFg(Color.RED);
				SwingTestUtil.getFrame().getContentPane().validate();
			});
			SwingTestUtil.test(tag, tag2, button);
		});
	}
}
