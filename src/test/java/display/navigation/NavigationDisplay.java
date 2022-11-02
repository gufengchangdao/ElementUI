package display.navigation;

import com.component.basic.color.ColorUtil;
import com.component.navigation.breadcrumb.Breadcrumb;
import com.component.navigation.navmenu.NavList;
import com.component.navigation.navmenu.NavPanel;
import com.component.navigation.pageheader.PageHeader;
import com.component.navigation.steps.StepsComponent;
import com.component.navigation.tabs.CloseableTab;
import com.component.navigation.tabs.ui.CustomTabbedPaneUI;
import com.component.svg.icon.regular.ArrowFatRightSvg;
import com.component.svg.icon.regular.XCircleSvg;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class NavigationDisplay {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			Container p = SwingTestUtil.init(new MigLayout("wrap 1", "grow"));

			p.add(new JLabel("面包屑"));
			ArrayList<String> list = new ArrayList<>();
			list.add("首页");
			list.add("活动管理");
			list.add("活动列表");
			list.add("活动详情");
			Breadcrumb breadcrumb = new Breadcrumb(
					list, 2,
					// "/"
					ArrowFatRightSvg.of(16, 16)
			);
			p.add(breadcrumb, "center");

			p.add(new JLabel("导航"));
			JTabbedPane tabbedPane = new JTabbedPane();
			tabbedPane.setPreferredSize(new Dimension(500, 400));
			tabbedPane.addTab("御坂1号", new JLabel("御坂1号"));
			tabbedPane.addTab("御坂2号", new JLabel("御坂2号"));
			tabbedPane.addTab("御坂3号", new JLabel("御坂3号"));
			tabbedPane.addTab("御坂4号", new JLabel("御坂4号"));
			tabbedPane.addTab("御坂5号", new JLabel("御坂5号"));
			List<JLabel> tab = Arrays.asList(new JLabel("御坂1号"), new JLabel("御坂2号"), new JLabel("御坂3号"),
					new JLabel("御坂4号"), new JLabel("御坂5号"));
			for (int i = 0; i < tab.size(); i++) {
				tabbedPane.setTabComponentAt(i, tab.get(i));
			}

			NavList<String> list111 = new NavList<>(new String[]{"1121", "1122", "1123"});
			HashMap<Integer, NavList<String>> m1 = new HashMap<>();
			m1.put(1, list111);
			NavList<String> list11 = new NavList<>(new String[]{"111", "112", "113"}, m1);
			NavList<String> list21 = new NavList<>(new String[]{"131", "132", "133"});
			HashMap<Integer, NavList<String>> m2 = new HashMap<>();
			m2.put(0, list11);
			m2.put(2, list21);
			NavList<String> list1 = new NavList<>(new String[]{"11", "12", "13"}, m2);
			NavList<String> list2 = new NavList<>(new String[]{"21", "22", "23"});
			NavList<String> list3 = new NavList<>(new String[]{"31", "32", "33"});
			NavList<String> list4 = new NavList<>(new String[]{"41", "42", "43"});
			NavList<String> list5 = new NavList<>(new String[]{"51", "52", "53"});

			Dimension tabSize = tabbedPane.getPreferredSize();
			// 我不知道如何获取tab大小
			Rectangle rec = new Rectangle(-14, 14, tabSize.width / tabbedPane.getTabCount(), 0);
			NavPanel<String> navPanel = new NavPanel<>(tab, Arrays.asList(list1, list2, list3, list4, list5),
					MouseEvent.MOUSE_ENTERED, rec);

			JPanel contentPanel = new JPanel();
			contentPanel.add(tabbedPane);
			Dimension size = new Dimension(500, 300);

			JLayeredPane layeredPane = new JLayeredPane();
			layeredPane.setPreferredSize(size);
			contentPanel.setBounds(0, 0, size.width, size.height);
			navPanel.setBounds(0, 0, size.width, size.height);
			layeredPane.add(contentPanel, (Integer) 0);
			layeredPane.add(navPanel, (Integer) 1);
			p.add(layeredPane, "center");

			JTabbedPane tb = new JTabbedPane();
			tb.add("Tab1", new JTextArea("Tab1"));
			tb.add("Tab2", new JTextArea("Tab2"));
			tb.add("Tab3", new JTextArea("Tab3"));
			tb.add("Tab4", new JTextArea("Tab4"));
			tb.add("Tab5", new JTextArea("Tab5"));
			tb.setTabComponentAt(0, new CloseableTab(tb));
			tb.setEnabledAt(1, false);
			tb.setPreferredSize(new Dimension(500, 200));
			p.add(tb, "center");

			JTabbedPane tb2 = new JTabbedPane();
			tb2.setUI(new CustomTabbedPaneUI());
			tb2.add("Tab1", new JTextArea("Tab1"));
			tb2.add("Tab2", new JTextArea(""));
			tb2.add("Tab3", new JTextArea(""));
			tb2.add("Tab4", new JTextArea(""));
			tb2.add("Tab5", new JTextArea(""));
			tb2.setEnabledAt(1, false);
			tb2.setEnabledAt(3, false);
			tb2.setPreferredSize(new Dimension(500, 200));
			p.add(tb2, "center");

			p.add(new JLabel("页头"));
			p.add(new PageHeader("详情页面"), "center");

			p.add(new JLabel("步骤条"));
			p.add(new StepsComponent(Arrays.asList("步骤1", "步骤2", "步骤3", "步骤4", "步骤5"),
					XCircleSvg.class,
					Arrays.asList("描述一", "描述二", "描述三", "描述四", "描述五"),
					2, ColorUtil.SUCCESS, 70, true), "center");
			p.add(new StepsComponent(Arrays.asList("步骤1", "步骤2", "步骤3", "步骤4", "步骤5"),
					XCircleSvg.class, 2, ColorUtil.SUCCESS, 70, false), "center");

			SwingTestUtil.test();
		});
	}
}
