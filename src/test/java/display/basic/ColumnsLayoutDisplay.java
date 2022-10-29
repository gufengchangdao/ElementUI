package display.basic;

import com.component.basic.color.ColorUtil;
import com.component.basic.layout.ColumnsLayout;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

public class ColumnsLayoutDisplay {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.init(new FlowLayout());
			ColumnsLayout layout = new ColumnsLayout(600, 5, 5);
			JPanel panel = new JPanel(layout);
			panel.add(new JButton(" "), (Integer) 24);
			panel.add(new JButton(" "), (Integer) 12);
			panel.add(new JButton(" "), (Integer) 12);
			panel.add(new JButton(" "), (Integer) 8);
			panel.add(new JButton(" "), (Integer) 8);
			panel.add(new JButton(" "), (Integer) 8);
			panel.add(new JButton(" "), (Integer) 6);
			panel.add(new JButton(" "), (Integer) 6);
			panel.add(new JButton(" "), (Integer) 6);
			panel.add(new JButton(" "), (Integer) 6);
			panel.add(new JButton(" "), (Integer) 4);
			panel.add(new JButton(" "), (Integer) 4);
			panel.add(new JButton(" "), (Integer) 4);
			panel.add(new JButton(" "), (Integer) 4);
			panel.add(new JButton(" "), (Integer) 4);
			panel.add(new JButton(" "), (Integer) 4);
			panel.setBorder(BorderFactory.createLineBorder(ColorUtil.INFO));
			SwingTestUtil.test(panel);
		});
	}
}