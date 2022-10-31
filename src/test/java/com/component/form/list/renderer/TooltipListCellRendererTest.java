package com.component.form.list.renderer;

import com.component.others.tooltip.BalloonToolTip;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class TooltipListCellRendererTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout());
			DefaultListModel<String> model = new DefaultListModel<>();
			model.addElement("ABC DEF GHI JKL MNO PQR STU VWX YZ");
			model.addElement("111");
			model.addElement("111222");

			JList<String> list = new JList(model) {
				@Override
				public JToolTip createToolTip() {
					// 气泡提示
					JToolTip tip = new BalloonToolTip();
					tip.setComponent(this);
					return tip;
				}

				@Override
				public void updateUI() {
					super.updateUI();
					// 带有提示的渲染器
					setCellRenderer(new TooltipListCellRenderer<>());
				}
			};

			p.add(list);
			SwingTestUtil.test();
		});
	}
}
