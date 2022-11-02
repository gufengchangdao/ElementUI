package com.component.data.label;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

public class DropCapLabelTest {
	// private static final String TEXT = String.join(" ",
	// 		"This lesson provides an introduction to",
	// 		"Graphical User Interface (GUI) programming with Swing and the NetBeans IDE.",
	// 		"As you learned in the \"Hello World!\" lesson, the NetBeans IDE is a free,",
	// 		"open-source, cross-platform integrated development environment with built-in",
	// 		"support for the Java programming language."
	// );
	public static final String TEXT = "他如今已经抵达了旅途的尽头——他所要完成的、所要见证的、所要救赎的……它们已经在虚数之树中生根发芽，" +
			"只等待着那迷路的信使，将最后的消息在一切都结束前送达。那一刻，不会太早，也不会太晚，它会成为跨越死亡的镇魂曲，" +
			"它会成为奇迹降临的赞美诗。世界将在那一刻只为了一个人而转动……让那被强加的罪孽烟消云散，让那被终结的意志继续向前。";

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new FlowLayout());
			JLabel label = new DropCapLabel(TEXT);
			label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			label.setPreferredSize(new Dimension(320, 240));
			label.setBorder(BorderFactory.createLineBorder(new Color(0x64_64_C8_C8, true), 10));
			p.add(label);
			SwingTestUtil.test();
		});
	}
}
