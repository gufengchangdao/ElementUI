package display.data;

import com.component.data.progress.CircleProgress;
import com.component.data.progress.LineRoundedProgress;
import com.component.svg.icon.regular.CircleWavyCheckSvg;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class ProgressDisplay {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			Container p = SwingTestUtil.init(new MigLayout("wrap 2", "grow"));

			CircleProgress progress = new CircleProgress();

			new Thread(() -> {
				try {
					for (int i = 0; i < 101; i++) {
						progress.setValue(i);
						TimeUnit.MILLISECONDS.sleep(30);
					}
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}).start();
			// 图标
			progress.setLabel(new JLabel(CircleWavyCheckSvg.of(32, 32)));
			progress.setLineWidth(20);
			p.add(progress, "center");

			LineRoundedProgress pgbar = new LineRoundedProgress();
			pgbar.setStringPainted(true);

			new Thread(() -> {
				try {
					for (int i = 0; i < 101; i++) {
						pgbar.setValue(i);
						TimeUnit.MILLISECONDS.sleep(30);
					}
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}).start();
			p.add(pgbar, "center");

			SwingTestUtil.test();
		});
	}
}
