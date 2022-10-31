package display.other;

import com.component.basic.color.ColorUtil;
import com.component.navigation.steps.StepInfo;
import com.component.navigation.steps.StepsComponent;
import com.component.others.timeline.TimelineTest;
import com.component.svg.icon.regular.SwordSvg;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TimelineDisplay {
	public static void main(String[] args) {
		Container p = SwingTestUtil.init(new MigLayout("", "center, grow"));

		ArrayList<String> text = new ArrayList<>();
		text.add("创建成功");
		text.add("通过审核");
		text.add("活动按期开始");
		ArrayList<String> date = new ArrayList<>();
		date.add("2018-04-11");
		date.add("2018-04-13");
		date.add("2018-04-15");

		ArrayList<StepInfo> items = StepsComponent.createItems(text, SwordSvg.class, date,
				100, false);
		// 正序，对列表排序
		items.sort(Comparator.comparing(o -> o.getText().getText()));
		TimelineTest c = new TimelineTest(items, -1, ColorUtil.SUCCESS, false);

		JButton b1 = new JButton("正序");
		JButton b2 = new JButton("倒叙");
		b1.addActionListener(e -> {
			List<StepInfo> i = c.getItems();
			i.sort(Comparator.comparing(o -> o.getDescription().getText()));
			c.setItems(i); //虽然列表没变，但是该方法会重新布局
		});
		b2.addActionListener(e -> {
			List<StepInfo> i = c.getItems();
			i.sort((o1, o2) -> o2.getDescription().getText().compareTo(o1.getDescription().getText()));
			c.setItems(i); //虽然列表没变，但是该方法会重新布局
		});
		p.add(c, "wrap");
		p.add(b1, "split 2");
		p.add(b2);

		SwingTestUtil.test();
	}
}
