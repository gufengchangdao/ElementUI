package display.data;

import com.component.basic.color.ColorUtil;
import com.component.data.badge.BadgePanelGroup;
import com.component.data.label.badge.BadgeLabel;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class badgeDisplay {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.init(new MigLayout("wrap 1", "grow, center"));

			JPanel panel = new JPanel(new MigLayout("wrap 1"));
			JButton b1 = new JButton("添加");
			b1.setPreferredSize(new Dimension(150, 25));
			JButton b2 = new JButton("移除");
			JButton b3 = new JButton("修改");
			panel.add(b1);
			panel.add(b2);
			panel.add(b3);
			// panel.getLayout().layoutContainer(panel);
			// System.out.println(SwingUtilities.convertPoint(panel, 0, 0, b1));

			BadgePanelGroup badgePanelGroup = new BadgePanelGroup(panel,
					Arrays.asList(b2, b3),
					Arrays.asList(new BadgeLabel(null, ColorUtil.PRIMARY, Color.WHITE),
							new BadgeLabel("56", ColorUtil.SUCCESS, Color.WHITE)),
					12
			);

			BadgeLabel newLabel = new BadgeLabel("新添加超级长的标签", ColorUtil.PRIMARY, Color.WHITE);
			badgePanelGroup.addBadge(b1, newLabel);
			badgePanelGroup.repaint(SwingTestUtil.getFrame().getContentPane(), newLabel);

			SwingTestUtil.test(badgePanelGroup.getLayeredPane());
			//注意，只有在pack()之后才能获取到组件所在容器中的位置
			badgePanelGroup.setAllBadgeLocation();
		});
	}
}
