package display.data;

import com.component.basic.button.ButtonFactory;
import com.component.basic.color.ColorUtil;
import com.component.data.empty.EmptyComponent;
import com.component.svg.empty.EmptyImgGraySvg;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class EmptyDisplay {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			Container p = SwingTestUtil.init(new MigLayout());
			p.setBackground(ColorUtil.PRIMARY_TEXT);
			p.add(new EmptyComponent(null, "暂无数据",
					ButtonFactory.createDefaultButton("按钮", ColorUtil.PRIMARY)));
			p.add(new EmptyComponent(new JLabel(EmptyImgGraySvg.of(200, 200)),
					"暂无数据",
					ButtonFactory.createDefaultButton("按钮", ColorUtil.PRIMARY)));

			SwingTestUtil.test();
		});
	}
}
