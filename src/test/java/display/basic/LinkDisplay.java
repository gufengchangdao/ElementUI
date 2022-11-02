package display.basic;

import com.component.basic.color.ColorUtil;
import com.component.basic.link.LinkButton;
import com.component.svg.icon.regular.SwordSvg;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import java.awt.*;

public class LinkDisplay {
	public static void main(String[] args) {
		Container p = SwingTestUtil.init(new MigLayout("wrap 6", "", ""));

		p.add(new LinkButton("默认链接", null, ColorUtil.PRIMARY));
		p.add(new LinkButton("主要链接", ColorUtil.PRIMARY, null));
		p.add(new LinkButton("成功链接", ColorUtil.SUCCESS, null));
		p.add(new LinkButton("警告链接", ColorUtil.WARNING, null));
		p.add(new LinkButton("危险链接", ColorUtil.DANGER, null));
		p.add(new LinkButton("信息链接", ColorUtil.INFO, null));

		LinkButton b1 = new LinkButton("默认链接", null, ColorUtil.PRIMARY);
		LinkButton b2 = new LinkButton("默认链接", null, null, ColorUtil.PRIMARY, null, false);

		p.add(b1);
		p.add(b2, "wrap");
		System.out.println(p.getLayout());

		LinkButton b3 = new LinkButton("默认链接", null, ColorUtil.PRIMARY);
		b3.setEnabled(false);
		p.add(b3);

		p.add(new LinkButton("宝剑", SwordSvg.of(16, 16), null, ColorUtil.PRIMARY));

		SwingTestUtil.test();
	}
}
