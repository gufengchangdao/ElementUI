package com.component.others.mask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * 对话框实现的遮罩效果
 * <p>
 * 遮罩，既可以使用玻璃窗格实现，也可以用对话框实现，并且用对话框实现会更方便。
 * <p>
 * 注意，应该在窗口显示后再创建遮罩对象，不然拿不到组件的位置和大小。
 */
public class DialogMask implements ComponentListener {
	private JDialog dialog;
	private JComponent panel;
	private Container container;
	Window w;

	/**
	 * @param container 显示遮罩的容器，生成的遮罩和该容器一样大小
	 * @param panel     遮罩显示的内容，该面板会和遮罩一样大小
	 */
	public DialogMask(Container container, JComponent panel) {
		this.panel = panel;
		this.container = container;
		w = SwingUtilities.getWindowAncestor(container);
		dialog = new JDialog(w, Dialog.ModalityType.MODELESS);
		// dialog = new JDialog(w, Dialog.ModalityType.APPLICATION_MODAL);
		dialog.setUndecorated(true);
		// 遮罩位置
		adjustMaskPosition();

		dialog.add(panel);
		panel.setBounds(w.getBounds());
		dialog.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	}

	public void openMask() {
		dialog.setVisible(true);
		// 窗口变化时改变对话框位置
		w.addComponentListener(this);
	}

	public void closeMask() {
		dialog.setVisible(false);
		w.removeComponentListener(this);
	}

	public JDialog getDialog() {
		return dialog;
	}

	public JComponent getPanel() {
		return panel;
	}

	@Override
	public void componentResized(ComponentEvent e) {
		adjustMaskPosition();
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		adjustMaskPosition();
	}

	/**
	 * 调整遮罩位置
	 */
	public void adjustMaskPosition() {
		Point l = container.getLocationOnScreen();
		dialog.setBounds(new Rectangle(l.x, l.y, container.getWidth(), container.getHeight()));
	}

	@Override
	public void componentShown(ComponentEvent e) {

	}

	@Override
	public void componentHidden(ComponentEvent e) {

	}
}
