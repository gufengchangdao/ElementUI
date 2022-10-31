package com.component.votice;

import com.component.others.votice.AuditoryCuesFactory;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

public class AuditoryCuesFactoryTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			// 这个外观打开对话框默认有提示音
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
			         UnsupportedLookAndFeelException ex) {
				ex.printStackTrace();
				Toolkit.getDefaultToolkit().beep();
			}

			Container p = SwingTestUtil.getFrame().getContentPane();
			JButton b1 = new JButton("默认提示音");
			b1.addActionListener(e -> {
				AuditoryCuesFactory.resetAuditoryCues();
				JOptionPane.showMessageDialog(p, "默认提示音");
			});
			p.add(b1);

			JButton b2 = new JButton("自定义提示音");
			b2.addActionListener(e -> {
				AuditoryCuesFactory.closeAuditoryCues();
				AuditoryCuesFactory.playAudio(b2,
						AuditoryCuesFactoryTest.class.getResourceAsStream("notice2.wav"),
						() -> JOptionPane.showConfirmDialog(p, "自定义提示音"));
			});
			p.add(b2);

			SwingTestUtil.test();
		});
	}
}
