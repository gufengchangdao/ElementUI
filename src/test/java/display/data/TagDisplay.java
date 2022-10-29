package display.data;

import com.component.basic.color.ColorUtil;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import java.awt.*;

import static com.component.data.tag.TagFactory.createDefaultTag;
import static com.component.data.tag.TagFactory.createIconTag;

public class TagDisplay {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			Container p = SwingTestUtil.init(new MigLayout("wrap 2", "grow"));

			p.add(createDefaultTag("标签一", ColorUtil.PRIMARY), "right");
			p.add(createIconTag("Tag 组件提供除了默认值以外的三种尺寸", ColorUtil.PRIMARY));
			p.add(createDefaultTag("标签二", ColorUtil.WARNING), "right");
			p.add(createIconTag("Tag 组件提供除了默认值以外的三种尺寸", ColorUtil.WARNING));
			p.add(createDefaultTag("标签三", ColorUtil.SUCCESS), "right");
			p.add(createIconTag("Tag 组件提供除了默认值以外的三种尺寸", ColorUtil.SUCCESS));
			p.add(createDefaultTag("标签四", ColorUtil.DANGER), "right");
			p.add(createIconTag("Tag 组件提供除了默认值以外的三种尺寸", ColorUtil.DANGER));

			SwingTestUtil.test();
		});
	}
}
