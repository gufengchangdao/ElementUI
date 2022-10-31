package lab.other.frame;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * 模拟桌面底部任务栏
 */
public class DesktopIconTaskBar extends JPanel {
	private DesktopIconTaskBar() {
		super(new BorderLayout());
		JDesktopPane desktop = new JDesktopPane();
		desktop.add(createFrame(2));
		desktop.add(createFrame(1));
		desktop.add(createFrame(0));

		JToggleButton button = new JToggleButton("InternalFrame.useTaskBar");
		button.addActionListener(e -> {
			Object c = e.getSource();
			if (c instanceof AbstractButton) {
				AbstractButton b = (AbstractButton) c;
				UIManager.put("InternalFrame.useTaskBar", b.isSelected());
				SwingUtilities.updateComponentTreeUI(getRootPane());
			}
		});

		add(desktop);
		add(button, BorderLayout.NORTH);
		setPreferredSize(new Dimension(320, 240));
	}

	private JInternalFrame createFrame(int i) {
		JInternalFrame f = new JInternalFrame("title: " + i, true, true, true, true);
		f.setSize(160, 120);
		f.setLocation(10 + 20 * i, 10 + 20 * i);
		EventQueue.invokeLater(() -> f.setVisible(true));
		return f;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout());
			p.add(new DesktopIconTaskBar());
			SwingTestUtil.test();
		});
	}
}

