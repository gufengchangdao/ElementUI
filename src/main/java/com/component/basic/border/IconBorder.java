package com.component.basic.border;

import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.util.SizeAdjustUtil;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import java.awt.*;

/**
 * 可设置左右图标的边框，因为不同组件对图标需求不一样，这里边框是可变的，调用对应方法即可
 * <p>
 * 如果修改了组件大小，请手动调用 {@link #adjustIconSize(JComponent)} 更新图标的大小，建议使用该类的自定义组件重写setPreferredSize()方法，像这样：
 * <pre>
 * public void setPreferredSize(Dimension preferredSize) {
 *  super.setPreferredSize(preferredSize);
 *  border.adjustIconSize(this);
 * }
 * </pre>
 */
public class IconBorder extends AbstractBorder {
	/** 绘制边框的边框对象，该类只负责绘制图标，不负责边框，如果不传则使用默认TextField的边框 */
	private Border border;
	private RadianceIcon leftIcon;
	private RadianceIcon rightIcon;
	/** 是否现在图标 */
	private boolean isShowIcon = true;

	/**
	 * @param icon   图标
	 * @param isLeft true表示是左侧图标，否则为右侧图标
	 */
	public IconBorder(RadianceIcon icon, boolean isLeft) {
		this.border = UIManager.getBorder("TextField.border");
		if (isLeft) this.leftIcon = icon;
		else this.rightIcon = icon;
	}

	public IconBorder(Border border, RadianceIcon leftIcon, RadianceIcon rightIcon) {
		this.border = border;
		this.leftIcon = leftIcon;
		this.rightIcon = rightIcon;
	}

	@Override
	public Insets getBorderInsets(Component c, Insets insets) {
		Insets i = border == null ? super.getBorderInsets(c, insets) : border.getBorderInsets(c);
		if (isShowIcon) {
			if (leftIcon != null) i.left += leftIcon.getIconWidth();
			if (rightIcon != null) i.right += rightIcon.getIconHeight();
		}
		return i;
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		if (border == null) super.paintBorder(c, g, x, y, width, height);
		else border.paintBorder(c, g, x, y, width, height);
		Insets insets = getBorderInsets(c);

		if (isShowIcon) {
			if (leftIcon != null)
				leftIcon.paintIcon(c, g, x + (insets.left - leftIcon.getIconWidth()) / 2, y + insets.top);
			if (rightIcon != null)
				rightIcon.paintIcon(c, g, width - (insets.right + rightIcon.getIconWidth()) / 2, y + insets.top);
		}
	}

	public RadianceIcon getLeftIcon() {
		return leftIcon;
	}

	public void setLeftIcon(RadianceIcon leftIcon) {
		this.leftIcon = leftIcon;
	}

	public RadianceIcon getRightIcon() {
		return rightIcon;
	}

	public void setRightIcon(RadianceIcon rightIcon) {
		this.rightIcon = rightIcon;
	}

	public boolean isShowIcon() {
		return isShowIcon;
	}

	public void setShowIcon(boolean showIcon) {
		isShowIcon = showIcon;
	}

	/**
	 * 按图标比例调整图标大小， 当修改了组件大小，并且希望图标大小也能改变的话需要调用这个
	 *
	 * @param c 应用该边框的对象，需要该对象获取边框大小
	 */
	public void adjustIconSize(JComponent c) {
		Dimension size = c.getPreferredSize();
		if (leftIcon != null) {
			Dimension leftIconSize = new Dimension(leftIcon.getIconWidth(), leftIcon.getIconHeight());
			leftIcon.setDimension(SizeAdjustUtil.adjustDimensionSize(size, leftIconSize, c.getInsets()));
		}
		if (rightIcon != null) {
			Dimension rightIconSize = new Dimension(rightIcon.getIconWidth(), rightIcon.getIconHeight());
			rightIcon.setDimension(SizeAdjustUtil.adjustDimensionSize(size, rightIconSize, c.getInsets()));
		}
	}
}
