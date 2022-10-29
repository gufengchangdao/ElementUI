package display.data;

import com.component.basic.button.ButtonFactory;
import com.component.basic.color.ColorUtil;
import com.component.data.result.ResultFactory;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import java.awt.*;

public class ResultDisplay {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			Container p = SwingTestUtil.init(new MigLayout("wrap 4", "grow, center"));

			p.add(ResultFactory.createSuccessResult("请根据提示进行操作",
					ButtonFactory.createDefaultButton("返回", ColorUtil.PRIMARY)));
			p.add(ResultFactory.createWarningResult("请根据提示进行操作",
					ButtonFactory.createDefaultButton("返回", ColorUtil.PRIMARY)));
			p.add(ResultFactory.createDangerResult("请根据提示进行操作",
					ButtonFactory.createDefaultButton("返回", ColorUtil.PRIMARY)));
			p.add(ResultFactory.createInfoResult("请根据提示进行操作",
					ButtonFactory.createDefaultButton("返回", ColorUtil.PRIMARY)));

			SwingTestUtil.test();
		});
	}
}
