package com.component.data.result;

import com.component.basic.button.ButtonFactory;
import com.component.basic.color.ColorUtil;
import com.component.util.SwingTestUtil;

import java.awt.*;

public class ResultFactoryTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();

			SwingTestUtil.test(
					ResultFactory.createSuccessResult("请根据提示进行操作",
							ButtonFactory.createDefaultButton("返回", ColorUtil.PRIMARY)),
					ResultFactory.createWarningResult("请根据提示进行操作",
							ButtonFactory.createDefaultButton("返回", ColorUtil.PRIMARY)),
					ResultFactory.createDangerResult("请根据提示进行操作",
							ButtonFactory.createDefaultButton("返回", ColorUtil.PRIMARY)),
					ResultFactory.createInfoResult("请根据提示进行操作",
							ButtonFactory.createDefaultButton("返回", ColorUtil.PRIMARY))
			);
		});
	}
}
