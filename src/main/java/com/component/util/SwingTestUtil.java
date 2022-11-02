package com.component.util;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 组件测试的工具类，用于测试组件功能
 */
public class SwingTestUtil {
	public static int WIDTH = 500;
	public static int HEIGHT = 400;
	public static LayoutManager LAYOUT_MANAGER;
	private static JFrame frame;

	static {
		// 有WrapLayout布局管理器就用WrapLayout
		try {
			LAYOUT_MANAGER = (LayoutManager) Class.forName("org.jdesktop.swingx.WrapLayout")
					.getConstructor()
					.newInstance();
		} catch (Exception e) {
			LAYOUT_MANAGER = new FlowLayout();
		}
	}

	public static void loadSkin() {
		try {
			Class<?> flatLightLaf = Class.forName("com.formdev.flatlaf.FlatLightLaf");
			flatLightLaf.getMethod("setup").invoke(flatLightLaf);
		} catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
		         InvocationTargetException e) {
			System.out.println("没有导入FlatLaf的jar包");
		}
	}

	public static void setDefaultTimingSource() {
		try {
			Class<?> animator = Class.forName("org.jdesktop.core.animation.timing.Animator");
			Class<?> swingTimerTimingSource = Class.forName("org.jdesktop.swing.animation.timing.sources.SwingTimerTimingSource");
			if (animator.getMethod("getDefaultTimingSource").invoke(animator) == null) {
				Object ts = swingTimerTimingSource.getConstructor().newInstance();
				animator.getMethod("setDefaultTimingSource",
						Class.forName("org.jdesktop.core.animation.timing.TimingSource")).invoke(null, ts);
				swingTimerTimingSource.getMethod("init").invoke(ts);
			}
			// if (Animator.getDefaultTimingSource() == null) {
			// 	SwingTimerTimingSource ts = new SwingTimerTimingSource();
			// 	Animator.setDefaultTimingSource(ts);
			// 	ts.init();
			// }
		} catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
		         IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
			System.out.println("没有导入Animator的jar包");
		}
	}

	/**
	 * 懒人写法：加载美化、设置默认时间源、设置布局
	 *
	 * @param layoutManager 内容窗格要应用的布局管理器
	 * @return 内容窗格
	 */
	public static JPanel init(LayoutManager layoutManager) {
		loadSkin();
		setDefaultTimingSource();
		setSize(700, 500);
		LAYOUT_MANAGER = layoutManager;
		return (JPanel) getFrame().getContentPane();
	}

	public static void test(Component component) {
		test(Collections.singletonList(component), true);
	}

	public static void test(Component component, LayoutManager layoutManager) {
		LAYOUT_MANAGER = layoutManager;
		test(Collections.singletonList(component), true);
	}

	/**
	 * 测试重写后的paint()相关的方法，设置组件首选项大小为窗口大小
	 */
	public static void testPaintMethod(Component component, int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		testPaintMethod(component);
	}

	/**
	 * 测试重写后的paint()相关的方法，为传入组件设置好大小
	 */
	public static void testPaintMethod(Component component) {
		component.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		test(Collections.singletonList(component), false);
	}

	public static void test(Component component1, Component component2) {
		test(Arrays.asList(component1, component2), true);
	}

	public static void test(Component... components) {
		test(Arrays.asList(components), true);
	}

	public static void test(List<Component> components, boolean isShowGap) {
		// 这里加了个检测，检测当前线程是否是事件分发线程
		if (SwingUtilities.isEventDispatchThread()) {
			invoke(components, isShowGap);
		} else {
			EventQueue.invokeLater(() -> invoke(components, isShowGap));
		}
	}

	public static void setSize(int width, int height) {
		WIDTH = width;
		HEIGHT = height;
		if (frame != null) {
			frame.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		}
	}

	public static JFrame getFrame() {
		if (frame == null) {
			frame = new JFrame("测试组件");
			if (LAYOUT_MANAGER != null)
				frame.getContentPane().setLayout(LAYOUT_MANAGER);
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			// 设置窗口居中
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setLocation((screenSize.width - WIDTH) / 2, (screenSize.height - HEIGHT) / 2);
			// 设置内容窗格
			Container contentPane = frame.getContentPane();
			contentPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		}
		return frame;
	}

	private static void invoke(List<Component> components, boolean isShowGap) {
		Container contentPane = getFrame().getContentPane();

		if (isShowGap)
			contentPane.setLayout(LAYOUT_MANAGER);
		else
			contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		components.forEach(contentPane::add);

		frame.pack();
		frame.setVisible(true);
	}

}
