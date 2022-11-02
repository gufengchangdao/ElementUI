package display.data;

import com.component.basic.color.ColorUtil;
import com.component.data.label.LabelFactory;
import com.component.data.label.LineThroughLabel;
import com.component.data.label.StrokeLabel;
import com.component.data.label.badge.BadgeLabel;
import com.component.font.FontUtil;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import java.awt.*;

public class LabelDisplay {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			Container p = SwingTestUtil.init(new MigLayout("wrap 4", "grow"));

			p.add(new BadgeLabel("测试标签", ColorUtil.SUCCESS, Color.WHITE));
			p.add(new BadgeLabel("测试标签", ColorUtil.WARNING, Color.WHITE));
			p.add(new BadgeLabel("测试标签", ColorUtil.DANGER, Color.WHITE));
			p.add(new BadgeLabel("测试标签", ColorUtil.INFO, Color.WHITE), "wrap");

			p.add(LabelFactory.createLabel("书籍未拆封", FontUtil.SMALL_FONT, ColorUtil.SUCCESS));
			p.add(LabelFactory.createLabel("书籍未拆封", FontUtil.SMALL_FONT, ColorUtil.WARNING));
			p.add(LabelFactory.createLabel("书籍未拆封", FontUtil.SMALL_FONT, ColorUtil.DANGER));
			p.add(LabelFactory.createLabel("书籍未拆封", FontUtil.SMALL_FONT, ColorUtil.INFO), "wrap");

			p.add(new LineThroughLabel("测试标签删除线"), "wrap");

			p.add(new StrokeLabel("测试标签描边2", FontUtil.DEFAULT_FONT.deriveFont(36f),
					ColorUtil.COMMON_TEXT, 1, ColorUtil.SUCCESS), "span 2, right");
			p.add(new StrokeLabel("测试标签描边2", FontUtil.DEFAULT_FONT.deriveFont(36f),
					ColorUtil.COMMON_TEXT, 2, ColorUtil.WARNING), "span 2");
			p.add(new StrokeLabel("测试标签描边2", FontUtil.DEFAULT_FONT.deriveFont(36f),
					ColorUtil.COMMON_TEXT, 3, ColorUtil.DANGER), "span 2, right");
			p.add(new StrokeLabel("测试标签描边2", FontUtil.DEFAULT_FONT.deriveFont(36f),
					ColorUtil.COMMON_TEXT, 4, ColorUtil.INFO), "span 2");
			SwingTestUtil.test();
		});
	}
}
