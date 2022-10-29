package display.other;

import com.component.basic.button.ButtonFactory;
import com.component.basic.color.ColorUtil;
import com.component.others.popconfirm.PopConfirm;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.svg.icon.fill.QuestionSvg;
import com.component.svg.icon.regular.NotePencilSvg;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class PopConfirmDisplay {
	public static void main(String[] args) {
		Container p = SwingTestUtil.init(new MigLayout("wrap 2", "grow, center"));

		JButton update = ButtonFactory.createPlainButton("修改", ColorUtil.WARNING);
		RadianceIcon icon = NotePencilSvg.of(16, 16);
		icon.setColorFilter(color -> ColorUtil.WARNING);
		PopConfirm popConfirm = new PopConfirm(update, new JLabel("这是一段内容确定修改吗？", icon, SwingConstants.LEFT));
		p.add(update);

		JButton del = ButtonFactory.createPlainButton("删除", ColorUtil.PRIMARY);
		icon = QuestionSvg.of(16, 16);
		icon.setColorFilter(color -> ColorUtil.WARNING);
		PopConfirm popConfirm2 = new PopConfirm(del, new JLabel("这是一段内容确定删除吗？", icon, SwingConstants.LEFT));
		p.add(del);

		SwingTestUtil.test();
	}
}
