package com.component.basic.link;

import com.component.basic.color.ColorUtil;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class LinkButtonTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout("wrap 1"));
			String text = "B站";
			String url = "https://www.bilibili.com/";

			p.add(new JLabel("LinkButton"));
			LinkButton link = new LinkButton(text, ColorUtil.PRIMARY, null);
			link.setUrl(url);
			p.add(link, "center");

			p.add(new JLabel("LinkText"));
			LinkText link2 = new LinkText(text, url);
			p.add(link2);

			SwingTestUtil.test();
		});
	}
}
