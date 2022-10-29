package display.other;

import com.component.basic.color.ColorUtil;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.svg.icon.regular.SwordSvg;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class SvgDisplay {
	public static void main(String[] args) {
		Container p = SwingTestUtil.init(new MigLayout("wrap 2", "grow"));

		RadianceIcon icon = SwordSvg.of((int) SwordSvg.getOrigWidth(), (int) SwordSvg.getOrigHeight());
		p.add(new JLabel("指定初始大小"), "right");
		p.add(new JLabel(icon));

		icon = SwordSvg.of(108, 108);
		p.add(new JLabel("按比例改变大小"), "right");
		p.add(new JLabel(icon));

		icon = SwordSvg.of(108, 108);
		icon.setColorFilter(color -> ColorUtil.PRIMARY);
		p.add(new JLabel("改变颜色"), "right");
		p.add(new JLabel(icon));

		SwingTestUtil.test();
	}
}
