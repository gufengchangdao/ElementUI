package display.notice;

import com.component.basic.color.ColorUtil;
import com.component.common.template.X2Component;
import com.component.notice.alert.AlertComponent;
import com.component.svg.icon.fill.CheckCircleSvg;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import java.awt.*;

import static com.component.notice.alert.AlertFactory.*;

public class AlertDisplay {
	public static void main(String[] args) {
		Container p = SwingTestUtil.init(new MigLayout("wrap 2", "grow"));

		p.add(createSuccessAlert("成功提示的文案", true, true, X2Component.GrowStyle.LEFT_GROW));
		p.add(createInfoAlert("消息提示的文案", true, true, X2Component.GrowStyle.LEFT_GROW));
		p.add(createWarningAlert("警告提示的文案", true, true, X2Component.GrowStyle.LEFT_GROW));
		p.add(createDangerAlert("错误提示的文案", true, true, X2Component.GrowStyle.LEFT_GROW));
		p.add(new AlertComponent(CheckCircleSvg.of(16, 16),
				"<html><b>带辅助性文字介绍</b><p>这是一句绕口令：黑灰化肥会挥发发灰黑化肥挥发；" +
						"灰黑化肥会挥发发黑灰化肥发挥。 黑灰化肥会挥发发灰黑化肥黑灰挥发化为灰……</p></html>",
				ColorUtil.SUCCESS,
				true,
				X2Component.GrowStyle.LEFT_GROW,
				new Insets(8, 16, 8, 16)), "span");

		SwingTestUtil.test();
	}
}
