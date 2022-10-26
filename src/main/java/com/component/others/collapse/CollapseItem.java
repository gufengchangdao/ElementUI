package com.component.others.collapse;

import com.component.basic.button.IconButton;
import com.component.basic.color.ColorUtil;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.svg.icon.regular.CaretDownSvg;
import com.component.svg.icon.regular.CaretRightSvg;
import org.jdesktop.core.animation.timing.Animator;
import org.jdesktop.core.animation.timing.TimingTarget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;

public class CollapseItem extends JPanel implements TimingTarget, MouseListener {
	JLabel title;
	JTextArea content;
	int contentHeight = 0;
	IconButton openButton;
	boolean isOpen;
	private Animator animator;
	int duration = 250;
	int vgap = 10;

	RadianceIcon icon1 = CaretRightSvg.of(16, 16);
	RadianceIcon icon2 = CaretDownSvg.of(16, 16);

	public CollapseItem(String title, String content) {
		this.title = new JLabel(title);
		this.content = new JTextArea(content);
		isOpen = true;
		init();
	}

	public CollapseItem(JLabel title, JTextArea content) {
		this.title = title;
		this.content = content;
		isOpen = true;
		init();
	}

	public CollapseItem(JLabel title, JTextArea content, boolean isOpen) {
		this.title = title;
		this.content = content;
		this.isOpen = isOpen;
		init();
	}

	public CollapseItem(String title, String content, boolean isOpen) {
		this.title = new JLabel(title);
		this.content = new JTextArea(content);
		this.isOpen = isOpen;
		init();
	}

	public CollapseItem(JLabel title, String content, boolean isOpen, int duration, int vgap) {
		this.title = title;
		this.content = new JTextArea(content);
		this.isOpen = isOpen;
		this.duration = duration;
		this.vgap = vgap;
		init();
	}

	private void init() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		if (isOpen) openButton = new IconButton(icon2, color -> ColorUtil.INFO);
		else openButton = new IconButton(icon1, color -> ColorUtil.INFO);
		content.setEditable(false);

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		topPanel.add(title);
		topPanel.add(Box.createHorizontalGlue());
		topPanel.add(openButton);

		add(Box.createVerticalStrut(vgap));
		add(topPanel);
		add(Box.createVerticalStrut(vgap));
		Dimension size = getPreferredSize(); //关闭时大小
		add(content);
		add(Box.createVerticalStrut(vgap));

		size.width = getPreferredSize().width; //完整宽度
		contentHeight = content.getPreferredSize().height + vgap; //展开时需要的高度
		if (isOpen)
			size.height += contentHeight;
		setPreferredSize(size);

		animator = new Animator.Builder()
				.setDuration(duration, TimeUnit.MILLISECONDS)
				.addTarget(this)
				.build();

		openButton.addMouseListener(this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(ColorUtil.PLACEHOLDER_TEXT);
		g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
	}

	public JLabel getTitle() {
		return title;
	}

	public void setTitle(JLabel title) {
		this.title = title;
	}

	public JTextArea getContent() {
		return content;
	}

	public void setContent(JTextArea content) {
		this.content = content;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean open) {
		isOpen = open;
	}

	@Override
	public String toString() {
		return "CollapseItem{" +
				"title=" + title +
				", content=" + content +
				", isOpen=" + isOpen +
				'}';
	}

	int w, h;

	@Override
	public void begin(Animator animator) {
		w = getPreferredSize().width;
		h = getPreferredSize().height;
	}

	@Override
	public void end(Animator animator) {

	}

	@Override
	public void repeat(Animator animator) {

	}

	@Override
	public void reverse(Animator animator) {

	}

	@Override
	public void timingEvent(Animator animator, double v) {
		setPreferredSize(new Dimension(w, (int) (h + v * contentHeight * (isOpen ? 1 : -1))));
		CollapseItem.this.updateUI();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (animator.isRunning()) return;
		isOpen = !isOpen;
		if (isOpen) {
			openButton.setBeginIcon(icon2);
			openButton.setEndIcon(icon2);
		} else {
			openButton.setBeginIcon(icon1);
			openButton.setEndIcon(icon1);
		}
		animator.start();
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
