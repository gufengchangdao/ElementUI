package com.component.animator.popup;

import com.component.basic.button.ButtonFactory;
import com.component.basic.color.ColorUtil;
import com.component.data.empty.EmptyComponent;
import com.component.data.result.ResultFactory;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

public class PopupAnimatorGroupTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			SwingTestUtil.setDefaultTimingSource();

			// 设置玻璃窗格
			JPanel panel = new JPanel(null);
			panel.setOpaque(false);
			SwingTestUtil.getFrame().setGlassPane(panel);

			PopupAnimatorGroup<EmptyComponent> group = new PopupAnimatorGroup<>(panel);

			// 设置组件及其task
			EmptyComponent result = ResultFactory.createSuccessResult("你成功了", ButtonFactory.createDefaultButton("返回", ColorUtil.PRIMARY));
			PopupAnimatorTask<EmptyComponent> task = group.createTask(result, new Point(0, 0), new Point(0, 200));
			task.setProcessComponent(emptyComponent -> System.out.println("淡入完成"));
			task.setDispose(emptyComponent -> System.out.println("淡出完成"));

			JButton b = new JButton("弹出");
			b.addActionListener(e -> {
				panel.setVisible(true);
				// 将task中组件添加到面板中并运行动画
				group.startAnimator(task);
			});
			SwingTestUtil.test(b);
		});
	}
}
