package display.other;

import com.component.basic.color.ColorUtil;
import com.component.others.line.Divider;
import com.component.others.line.LineLabel;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXLabel;

import javax.swing.*;
import java.awt.*;

public class LineDisplay {
	public static void main(String[] args) {
		Container p = SwingTestUtil.init(new MigLayout("", "grow, center"));

		Divider c1 = new Divider(new JLabel("少年包青天", SwingConstants.LEFT), 0.25f, false, 300);

		JXLabel label = new JXLabel("MY TEXT");
		label.setTextRotation(3 * Math.PI / 2);
		// label.setTextRotation(Math.PI/2);
		Divider c2 = new Divider(label, 0.25f, false, 300);
		p.add(c1, "span 1 2");
		p.add(c2, "span 1 2");

		LineLabel lineLabel2 = new LineLabel(5, LineLabel.VERTICAL);
		lineLabel2.setForeground(ColorUtil.DANGER);
		lineLabel2.setPreferredSize(new Dimension(200, 200));
		p.add(lineLabel2);

		LineLabel lineLabel = new LineLabel(3);
		lineLabel.setForeground(ColorUtil.PRIMARY);
		lineLabel.setPreferredSize(new Dimension(200, 200));
		p.add(lineLabel);

		SwingTestUtil.test();
	}
}
