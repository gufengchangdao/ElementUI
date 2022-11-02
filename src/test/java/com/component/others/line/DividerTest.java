package com.component.others.line;

import com.component.util.SwingTestUtil;
import org.jdesktop.swingx.JXLabel;

import javax.swing.*;
import java.awt.*;

public class DividerTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();

			Divider c1 = new Divider(new JLabel("少年包青天", SwingConstants.LEFT), 0.25f, false, 300);

			JXLabel label = new JXLabel("MY TEXT");
			label.setTextRotation(3 * Math.PI / 2);
			// label.setTextRotation(Math.PI/2);
			Divider c2 = new Divider(label, 0.25f, false, 300);


			SwingTestUtil.test(c2);
		});
	}
}
