package display.util;

import com.component.util.SwingTestUtil;

import java.awt.*;

public class SwingDisplayUtil extends SwingTestUtil {
	public static Container init(LayoutManager layoutManager) {
		loadSkin();
		setDefaultTimingSource();
		setSize(700, 500);
		SwingDisplayUtil.LAYOUT_MANAGER = layoutManager;
		return getFrame().getContentPane();
	}
}
