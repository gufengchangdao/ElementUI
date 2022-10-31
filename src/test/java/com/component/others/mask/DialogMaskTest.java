package com.component.others.mask;

import com.component.basic.color.ColorUtil;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class DialogMaskTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout("", "grow"));

			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(300, 300));
			panel.setBackground(ColorUtil.BORDER_LEVEL1);
			JButton b = new JButton("打开遮罩");
			panel.add(b);
			p.add(panel, "center");

			b.addActionListener(e -> {
				// 遮罩面板
				JPanel m = new JPanel(new BorderLayout());
				m.add(new JLabel("这是遮罩面板"));
				m.setBackground(ColorUtil.changeAlpha(ColorUtil.PRIMARY, .5f));
				DialogMask mask = new DialogMask(panel, m);
				// 定时关闭用Timer或SwingWorker实现
				new Timer(4000, e1 -> mask.closeMask()).start();
				// new SwingWorker<String, Void>() {
				// 	@Override
				// 	protected String doInBackground() throws Exception {
				// 		Thread.sleep(4000);
				// 		return "Done";
				// 	}
				//
				// 	@Override
				// 	protected void done() {
				// 		mask.closeMask();
				// 	}
				// }.execute();
				mask.openMask();
			});

			SwingTestUtil.test();
		});
	}
}
