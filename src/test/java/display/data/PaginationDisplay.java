package display.data;

import com.component.data.pagination.IconPaginationList;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import java.awt.*;

public class PaginationDisplay {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			Container p = SwingTestUtil.init(new MigLayout("wrap 1"));

			IconPaginationList list1 = new IconPaginationList(35);
			// 绘制背景
			list1.getList().getCellRenderer().setBackgroundPainted(true);
			p.add(list1, "gapleft 30");

			IconPaginationList list2 = new IconPaginationList(40);
			// 绘制背景
			list2.getList().getCellRenderer().setBackgroundPainted(true);
			p.add(list2, "gapleft 30");

			IconPaginationList list3 = new IconPaginationList(100);
			// 绘制背景
			list3.getList().getCellRenderer().setBackgroundPainted(true);
			p.add(list3, "gapleft 30");

			SwingTestUtil.test();
		});
	}
}
