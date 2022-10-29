package display.other;

import com.component.others.collapse.CollapseItem;
import com.component.others.collapse.CollapsePanel;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.util.ArrayList;

public class CollapseDisplay {
	public static void main(String[] args) {
		Container p = SwingTestUtil.init(new MigLayout("wrap 1", "grow, center"));

		ArrayList<CollapseItem> list = new ArrayList<>();
		list.add(new CollapseItem("一致性 Consistency",
				"与现实生活一致：与现实生活的流程、逻辑保持一致，遵循用户习惯的语言和概念；\n" +
						"在界面中一致：所有的元素和结构需保持一致，比如：设计样式、图标和文本、元素的位置等。", true));
		list.add(new CollapseItem("反馈 Feedback",
				"控制反馈：通过界面样式和交互动效让用户可以清晰的感知自己的操作；\n" +
						"页面反馈：操作后，通过页面元素的变化清晰地展现当前状态。"));
		list.add(new CollapseItem("可控 Controllability",
				"用户决策：根据场景可给予用户操作建议或安全提示，但不能代替用户进行决策；\n" +
						"结果可控：用户可以自由的进行操作，包括撤销、回退和终止当前操作等。"));
		list.add(new CollapseItem("效率 Efficiency",
				"简化流程：设计简洁直观的操作流程；\n" +
						"清晰明确：语言表达清晰且表意明确，让用户快速理解进而作出决策；\n" +
						"帮助用户识别：界面简单直白，让用户快速识别而非回忆，减少用户记忆负担。"));
		CollapsePanel c = new CollapsePanel(list);
		p.add(c);

		SwingTestUtil.test();
	}
}
