package com.component.notice.loading;

import com.component.svg.icon.regular.CrosshairSvg;
import com.component.util.SwingTestUtil;

import java.awt.*;

public class LoadingLabelTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.setDefaultTimingSource();
			SwingTestUtil.loadSkin();
			SwingTestUtil.test(new LoadingLabel(CrosshairSvg.of(48, 48), 700));
		});
	}

}
