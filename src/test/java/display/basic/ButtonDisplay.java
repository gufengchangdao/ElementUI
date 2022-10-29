package display.basic;

import com.component.basic.button.ButtonFactory;
import com.component.basic.button.IconButton;
import com.component.basic.color.ColorUtil;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.svg.icon.fill.CircleWavyCheckSvg;
import com.component.svg.icon.fill.SwordSvg;
import com.component.svg.icon.regular.CrosshairSvg;
import com.component.svg.icon.regular.QuestionSvg;
import com.component.svg.icon.regular.XCircleSvg;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import java.awt.*;

public class ButtonDisplay {
	public static void main(String[] args) {
		Container p = SwingTestUtil.init(new MigLayout("wrap 6", "", ""));
		p.add(ButtonFactory.createDefaultButton("默认按钮", ColorUtil.PRIMARY_TEXT, Color.WHITE, ColorUtil.SECONDARY_TEXT));
		p.add(ButtonFactory.createDefaultButton("主要按钮", ColorUtil.PRIMARY));
		p.add(ButtonFactory.createDefaultButton("成功按钮", ColorUtil.SUCCESS));
		p.add(ButtonFactory.createDefaultButton("信息按钮", ColorUtil.INFO));
		p.add(ButtonFactory.createDefaultButton("警告按钮", ColorUtil.WARNING));
		p.add(ButtonFactory.createDefaultButton("危险按钮", ColorUtil.DANGER));

		p.add(ButtonFactory.createPlainButton("默认按钮", ColorUtil.PRIMARY_TEXT, Color.WHITE, ColorUtil.SECONDARY_TEXT));
		p.add(ButtonFactory.createPlainButton("主要按钮", ColorUtil.PRIMARY));
		p.add(ButtonFactory.createPlainButton("成功按钮", ColorUtil.SUCCESS));
		p.add(ButtonFactory.createPlainButton("信息按钮", ColorUtil.INFO));
		p.add(ButtonFactory.createPlainButton("警告按钮", ColorUtil.WARNING));
		p.add(ButtonFactory.createPlainButton("危险按钮", ColorUtil.DANGER));

		p.add(ButtonFactory.createRoundButton("默认按钮", ColorUtil.PRIMARY_TEXT, Color.WHITE, null));
		p.add(ButtonFactory.createRoundButton("主要按钮", ColorUtil.PRIMARY));
		p.add(ButtonFactory.createRoundButton("成功按钮", ColorUtil.SUCCESS));
		p.add(ButtonFactory.createRoundButton("信息按钮", ColorUtil.INFO));
		p.add(ButtonFactory.createRoundButton("警告按钮", ColorUtil.WARNING));
		p.add(ButtonFactory.createRoundButton("危险按钮", ColorUtil.DANGER));

		RadianceIcon icon = XCircleSvg.of(32, 32);
		icon.setColorFilter(color -> ColorUtil.WARNING);
		p.add(new IconButton(icon, color -> ColorUtil.PRIMARY));
		p.add(new IconButton(CircleWavyCheckSvg.of(32, 32), color -> ColorUtil.PRIMARY));
		p.add(new IconButton(CrosshairSvg.of(32, 32), color -> ColorUtil.PRIMARY));
		p.add(new IconButton(QuestionSvg.of(32, 32), color -> ColorUtil.PRIMARY));
		p.add(new IconButton(SwordSvg.of(32, 32), color -> ColorUtil.PRIMARY));
		p.add(ButtonFactory.createIconButton(null, XCircleSvg.of(32, 32).toImage(1), null));

		p.add(ButtonFactory.createIconRoundButton(SwordSvg.of(32, 32), ColorUtil.PRIMARY, true));
		p.add(ButtonFactory.createIconRoundButton(SwordSvg.of(32, 32), ColorUtil.PRIMARY, false));

		SwingTestUtil.test();
	}
}
