package display.notice;

import com.component.basic.color.ColorUtil;
import com.component.notice.loading.LoadingLabel;
import com.component.notice.loading.LoadingLabel2;
import com.component.notice.loading.LoadingPanel;
import com.component.svg.icon.regular.CrosshairSvg;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoadingDisplay {
	public static void main(String[] args) {
		Container p = SwingTestUtil.init(new MigLayout("wrap 1", "grow, center"));

		p.add(new LoadingLabel(CrosshairSvg.of(48, 48), 700));

		p.add(new LoadingLabel2(ColorUtil.PRIMARY, 4));

		LoadingPanel<LoadingLabel2> panel = new LoadingPanel<>(
				new LoadingLabel2(ColorUtil.PRIMARY, 3));
		panel.setBorder(BorderFactory.createLineBorder(Color.RED));
		JFrame frame = SwingTestUtil.getFrame();
		frame.setGlassPane(panel);

		JButton b = new JButton("开启加载");
		b.addActionListener(e -> {
			//获取图像得在该面板加入面板之前，不然会把加载动画也印上去
			panel.flushBackground();
			panel.setVisible(true);
		});
		p.add(b);
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setVisible(false);
			}
		});

		SwingTestUtil.test();
	}
}
