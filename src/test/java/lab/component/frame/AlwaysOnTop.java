package lab.component.frame;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 窗体总是在顶层
 */
public class AlwaysOnTop {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			Container p = SwingTestUtil.init(new FlowLayout());
			JCheckBox check = new JCheckBox("Always On Top", true);
			check.addActionListener(e -> {
				JCheckBox c = (JCheckBox) e.getSource();
				Container w = c.getTopLevelAncestor(); //找到frame
				if (w instanceof Window) {
					((Window) w).setAlwaysOnTop(c.isSelected());
				}
			});

			SwingTestUtil.getFrame().setAlwaysOnTop(true);
			p.add(check);
			SwingTestUtil.test();
		});
	}
}
