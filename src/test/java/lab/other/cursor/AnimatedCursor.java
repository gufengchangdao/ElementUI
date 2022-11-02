package lab.other.cursor;
// vim:set fileencoding=utf-8:
// @homepage@


import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyEvent;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 自定义鼠标动画
 */
public final class AnimatedCursor extends JPanel {
	private AnimatedCursor() {
		super(new BorderLayout());

		Point pt = new Point();
		Class<?> clz = getClass();
		Toolkit tk = getToolkit();
		List<Cursor> list = Stream.of("00", "01", "02")
				// 读取图片，这里使用Toolkit.createImage()来读取图片
				// 也可以使用ImageIO读取图片，但是如果是jdk版本低的话图片会有红色背景(jdk8会有)
				.map(s -> tk.createCustomCursor(tk.createImage(clz.getResource(s + ".png")), pt, s))
				.collect(Collectors.toList());

		Timer animator = new Timer(100, null);
		JButton button = new JButton("Start");
		// 动画不启动只显示第一张图片
		button.setCursor(list.get(0));
		button.addActionListener(e -> {
			JButton b = (JButton) e.getSource();
			if (animator.isRunning()) {
				b.setText("Start");
				animator.stop();
			} else {
				b.setText("Stop");
				animator.start();
			}
		});
		// 移除组件时停止动画
		button.addHierarchyListener(e -> {
			boolean b = (e.getChangeFlags() & HierarchyEvent.DISPLAYABILITY_CHANGED) != 0;
			if (b && !e.getComponent().isDisplayable()) {
				animator.stop();
			}
		});
		animator.addActionListener(new CursorActionListener(button, list));

		JPanel p = new JPanel(new BorderLayout());
		p.setBorder(BorderFactory.createEmptyBorder(32, 32, 32, 32));
		p.add(button);
		add(p);
		setBorder(BorderFactory.createTitledBorder("delay=100ms"));
		setPreferredSize(new Dimension(320, 240));
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.init(new FlowLayout()).add(new AnimatedCursor());
			SwingTestUtil.test();
		});
	}
}

/**
 * 计时器执行任务：改变悬停组件时鼠标样式
 */
class CursorActionListener implements ActionListener {
	private int counter;
	private final Component comp;
	private final List<Cursor> frames;

	protected CursorActionListener(Component comp, List<Cursor> frames) {
		this.comp = comp;
		this.frames = frames;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		comp.setCursor(frames.get(counter));
		counter = (counter + 1) % frames.size();
	}
}
