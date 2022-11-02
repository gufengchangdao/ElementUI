package lab.component.popup;// -*- mode:java; encoding:utf-8 -*-
// vim:set fileencoding=utf-8:
// @homepage@

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 根据鼠标位置调整右键菜单出现的位置
 */
public final class AdjustPopupLocation extends JPanel {
	private AdjustPopupLocation() {
		super(new BorderLayout());

		JCheckBox check = new JCheckBox("Adjust JPopupMenu location", true);
		check.setFocusPainted(false);

		JPopupMenu popup = new JPopupMenu() {
			@Override
			public void show(Component c, int x, int y) {
				if (check.isSelected()) {
					Point p = new Point(x, y);
					Rectangle r = c.getBounds();
					Dimension d = getPreferredSize();
					if (p.x + d.width > r.width) {
						p.x -= d.width;
					}
					if (p.y + d.height > r.height) {
						p.y -= d.height;
					}
					super.show(c, Math.max(p.x, 0), Math.max(p.y, 0));
				} else {
					super.show(c, x, y);
				}
			}
		};
		popup.add("JMenuItem: 11111");
		popup.add("JMenuItem: 222");
		popup.add("JMenuItem: 3");
		// setComponentPopupMenu(popup);

		JLabel label = new JLabel("JLabel: 1234567890");
		label.setOpaque(true);
		// label.setInheritsPopupMenu(true);
		// check.setInheritsPopupMenu(true);
		label.setComponentPopupMenu(popup);

		add(check, BorderLayout.NORTH);
		add(label);
		setBorder(BorderFactory.createLineBorder(Color.RED, 10));
		setPreferredSize(new Dimension(320, 240));
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.init(new FlowLayout()).add(new AdjustPopupLocation());
			SwingTestUtil.test();
		});
	}
}
