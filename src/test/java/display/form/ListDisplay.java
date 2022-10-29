package display.form;

import com.component.form.list.TransferList;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.util.Arrays;

public class ListDisplay {
	public static void main(String[] args) {
		Container p = SwingTestUtil.init(new MigLayout());
		TransferList list = new TransferList("列表", true, "请输入数字",
				Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"));
		list.setListHeight(200);

		p.add(list);
		SwingTestUtil.test();
	}
}
