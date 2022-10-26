package com.component.basic.button;

import com.component.util.GraphicsUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 简单的图标按钮，便于图像的设置和获取
 */
public class SimpleIconButton extends JButton {
	protected BufferedImage image;

	public SimpleIconButton() {
	}

	public SimpleIconButton(Icon icon) {
		this(icon, null);
	}

	public SimpleIconButton(BufferedImage image) {
		this(new ImageIcon(image), null);
	}

	public SimpleIconButton(Icon icon, Color backgroundColor) {
		super(icon);
		if (backgroundColor != null) setBackground(backgroundColor);
		setFocusPainted(false);
		setBorderPainted(false);
		setBorder(null);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		super.paint(g);
	}

	@Override
	public void setPreferredSize(Dimension preferredSize) {
		super.setPreferredSize(preferredSize);
		if (image != null)
			setIcon(new ImageIcon(GraphicsUtil
					.createScaledCompositeInstance(image, preferredSize.width, preferredSize.height)));
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
		Dimension size = getPreferredSize();
		setIcon(new ImageIcon(GraphicsUtil.createScaledCompositeInstance(image, size.width, size.height)));
	}
}
