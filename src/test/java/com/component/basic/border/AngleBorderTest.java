package com.component.basic.border;

import com.component.basic.color.ColorUtil;
import com.component.common.SwingPosition;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

import static com.component.common.SwingPosition.BOTTOM_RIGHT;

public class AngleBorderTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();

			JLabel label = new JLabel("这是文本");
			label.setPreferredSize(new Dimension(200, 200));
			label.setOpaque(true);
			label.setBackground(ColorUtil.SUCCESS.brighter());

			AngleBorder border = new AngleBorder(BOTTOM_RIGHT, ColorUtil.SUCCESS, 20, 3, new Point(0, 0));

			JComponent c = new JComponent() {
				{
					setLayout(null);
					Dimension s = label.getPreferredSize();
					Insets insets = border.getBorderInsets(this);

					// 用于展示，这里写死
					// label.setBounds(insets.left, insets.top, s.width, s.height);
					label.setBounds(20, 20, s.width, s.height);
					add(label);

					// s.width += insets.left + insets.right;
					// s.height += insets.top + insets.bottom;
					s.width += 40;
					s.height += 40;
					setPreferredSize(s);
					setBorder(border);
				}
			};

			ThreadLocalRandom random = ThreadLocalRandom.current();
			JButton b = new JButton("改变方位");
			b.addActionListener(e -> {
				int i = random.nextInt(0, 12);
				System.out.println("方向为 " + i);
				c.setBorder(new AngleBorder(SwingPosition.values()[i],
						ColorUtil.SUCCESS, 20, 3, new Point(0, 0)));
			});
			SwingTestUtil.test(c, b);
		});
	}
}
