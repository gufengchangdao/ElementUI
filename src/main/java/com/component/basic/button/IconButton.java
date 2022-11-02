package com.component.basic.button;

import com.component.common.component.BaseComponent;
import com.component.radiance.common.api.icon.RadianceIcon;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 带有三种状态的图标按钮，两个图标分别对应鼠标移出移进的状态
 */
public class IconButton extends BaseComponent implements MouseListener {
	/** 鼠标未悬停状态 */
	private RadianceIcon beginIcon;
	/** 鼠标悬停状态 */
	private RadianceIcon endIcon;
	private RadianceIcon currentIcon;
	/** 鼠标悬停状态的过滤器，保存下来使鼠标松开时能回归原样，因为 endIcon 有可能有设置初始过滤器(组件色) */
	private RadianceIcon.ColorFilter endIconFilter;
	/** 鼠标按下状态 */
	private RadianceIcon.ColorFilter filter;

	public IconButton(RadianceIcon beginIcon, RadianceIcon.ColorFilter filter) {
		this.beginIcon = beginIcon;
		this.endIcon = beginIcon;
		endIconFilter = endIcon.getColorFilter();
		this.filter = filter;
		currentIcon = beginIcon;
		init();
	}

	public IconButton(RadianceIcon beginIcon, RadianceIcon endIcon) {
		this.beginIcon = beginIcon;
		this.endIcon = endIcon;
		currentIcon = beginIcon;
		init();
	}

	/**
	 * @param beginIcon 默认图标
	 * @param endIcon   鼠标悬停时图标
	 * @param filter    鼠标按下时图标颜色变化
	 */
	public IconButton(RadianceIcon beginIcon, RadianceIcon endIcon, RadianceIcon.ColorFilter filter) {
		this.beginIcon = beginIcon;
		this.endIcon = endIcon;
		endIconFilter = endIcon.getColorFilter();
		this.filter = filter;
		currentIcon = beginIcon;
		init();
	}

	private void init() {
		Dimension size = new Dimension();
		size.width = Math.max(beginIcon.getIconWidth(), endIcon.getIconWidth());
		size.height = Math.max(beginIcon.getIconHeight(), endIcon.getIconHeight());
		super.setPreferredSize(size);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		addMouseListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		currentIcon.paintIcon(this, g, (getWidth() - currentIcon.getIconWidth()) / 2,
				(getHeight() - currentIcon.getIconHeight()) / 2);
	}

	@Override
	public void setPreferredSize(Dimension preferredSize) {
		super.setPreferredSize(preferredSize);
		beginIcon.setDimension(preferredSize);
		endIcon.setDimension(preferredSize);
	}

	@Override
	public boolean contains(int x, int y) {
		// 可以重写该方法，获取到icon的shape来判断
		return super.contains(x, y);
	}

	@Override
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	@Override
	public Dimension getMaximumSize() {
		return getPreferredSize();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (filter != null) {
			endIcon.setColorFilter(filter);
			repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (filter != null) {
			endIcon.setColorFilter(endIconFilter);
			repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		currentIcon = endIcon;
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		currentIcon = beginIcon;
		repaint();
	}

	public RadianceIcon getBeginIcon() {
		return beginIcon;
	}

	public void setBeginIcon(RadianceIcon beginIcon) {
		this.beginIcon = beginIcon;
		currentIcon = beginIcon;
	}

	public RadianceIcon getEndIcon() {
		return endIcon;
	}

	public void setEndIcon(RadianceIcon endIcon) {
		this.endIcon = endIcon;
	}

	public RadianceIcon.ColorFilter getFilter() {
		return filter;
	}

	public void setFilter(RadianceIcon.ColorFilter filter) {
		endIconFilter = endIcon.getColorFilter();
		this.filter = filter;
	}
}
