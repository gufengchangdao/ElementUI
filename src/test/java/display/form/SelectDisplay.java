package display.form;

import com.component.form.select.CascaderSelector;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SelectDisplay {
	public static void main(String[] args) {
		Container p = SwingTestUtil.init(new MigLayout("center"));

		HashMap<String, Map<String, ?>> childNode2 = new HashMap<>();
		childNode2.put("大苹果", null);
		childNode2.put("中苹果", null);
		childNode2.put("小苹果", null);

		Map<String, Map<String, ?>> childNode1 = new HashMap<>();
		childNode1.put("甜苹果", childNode2);
		childNode1.put("酸苹果", null);
		childNode1.put("青苹果", null);

		HashMap<String, Map<String, ?>> map = new HashMap<>();
		map.put("苹果", childNode1);
		map.put("香蕉", null);
		map.put("梨", null);

		CascaderSelector main = new CascaderSelector(15, "，", map);

		p.add(main);

		SwingTestUtil.test();
	}
}
