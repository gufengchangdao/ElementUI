package lab.component.menu;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * 含有按钮的顶部菜单
 */
public class ButtonsInMenuItem {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout());

			JMenu menu = new JMenu("File");
			menu.add("111111111");
			menu.addSeparator();

			JPanel panel = getButtons();
			JMenuItem item = new JMenuItem("Edit") {
				@Override
				public Dimension getPreferredSize() {
					// 如果不设置大小，组件会覆盖住文字
					Dimension d = super.getPreferredSize();
					d.width += panel.getPreferredSize().width;
					d.height = Math.max(panel.getPreferredSize().height, d.height);
					return d;
				}
			};
			// 布局,靠右水平居中
			GridBagConstraints c = new GridBagConstraints();
			item.setLayout(new GridBagLayout());
			c.anchor = GridBagConstraints.LINE_END;
			c.weightx = 1d;

			c.fill = GridBagConstraints.HORIZONTAL;
			item.add(Box.createHorizontalGlue(), c);
			c.fill = GridBagConstraints.NONE;
			item.add(panel, c);

			menu.add(item);
			menu.addSeparator();
			menu.add("22222");
			JMenuBar menuBar = new JMenuBar();
			menuBar.add(menu);
			SwingTestUtil.getFrame().setJMenuBar(menuBar);

			SwingTestUtil.test();
		});
	}

	public static JPanel getButtons() {
		// 点击以后关闭菜单
		ActionListener listener = e -> {
			Component a = (Component) e.getSource();
			Container c = SwingUtilities.getAncestorOfClass(JPopupMenu.class, a);
			if (c instanceof JPopupMenu) {
				c.setVisible(false);
			}
		};

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton b1 = new JButton("复制");
		JButton b2 = new JButton("粘贴");

		b1.addActionListener(listener);
		b2.addActionListener(listener);
		panel.add(b1);
		panel.add(b2);
		panel.setOpaque(false);
		return panel;
	}
}
