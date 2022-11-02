package com.component.notice.loading;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

public class LoadingLabel3Test {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new FlowLayout(FlowLayout.CENTER));

			LoadingLabel3 label = new LoadingLabel3();
			label.startAnimation();
			p.add(label);
			SwingTestUtil.test();
		});
	}
}
