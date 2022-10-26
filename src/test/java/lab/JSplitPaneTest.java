package lab;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 分割窗格，可以便捷的移动分割符来决定两侧组件所占大小
 */
public class JSplitPaneTest {
	public static void main(String[] args) {
		SwingTestUtil.loadSkin();

		JLabel left = new JLabel("左侧面板");
		JLabel right = new JLabel("右侧面板");
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
		splitPane.setOneTouchExpandable(true);
		splitPane.setContinuousLayout(true);
		splitPane.setResizeWeight(0.5);
		splitPane.setDividerLocation(150);
		splitPane.setPreferredSize(new Dimension(400, 200));

		SwingTestUtil.test(splitPane);
	}
}
