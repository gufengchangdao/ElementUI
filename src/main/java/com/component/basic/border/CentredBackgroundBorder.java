package com.component.basic.border;

import com.component.util.GraphicsUtil;

import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 用边框实现背景图片
 */
public class CentredBackgroundBorder implements Border {
	/** 源图像 */
	private final BufferedImage image;
	/** 缓冲图像 */
	private BufferedImage bufferedImage;

	protected CentredBackgroundBorder(BufferedImage image) {
		this.image = image;
		this.bufferedImage = image;
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		int imgWidth = bufferedImage.getWidth();
		int imgHeight = bufferedImage.getHeight();
		if (imgWidth != width && imgHeight != height) {
			//更新图片
			float fw = imgWidth * 1f / width;
			float fh = imgHeight * 1f / height;

			if (fw > 1 && fh > 1) {
				if (fw > fh)
					bufferedImage = GraphicsUtil.createScaledCompositeInstance(image, height, true);
				else
					bufferedImage = GraphicsUtil.createScaledCompositeInstance(image, width, true);
			} else if (fw > 1) {
				bufferedImage = GraphicsUtil.createScaledCompositeInstance(image, height, true);
			} else if (fh > 1) {
				bufferedImage = GraphicsUtil.createScaledCompositeInstance(image, width, true);
			} else {
				if (fw > fh)
					bufferedImage = GraphicsUtil.createScaledCompositeInstance(image, height, true);
				else
					bufferedImage = GraphicsUtil.createScaledCompositeInstance(image, width, true);
			}
		}

		Graphics2D g2 = (Graphics2D) g.create();
		// 绘制在中心
		int cx = (width - bufferedImage.getWidth()) / 2;
		int cy = (height - bufferedImage.getHeight()) / 2;

		g2.drawImage(bufferedImage, cx, cy, null);
		// g2.translate(x, y);
		// g2.drawRenderedImage(bufferedImage, AffineTransform.getTranslateInstance(cx, cy));
		g2.dispose();
	}

	@Override
	public Insets getBorderInsets(Component c) {
		return new Insets(0, 0, 0, 0);
	}

	@Override
	public boolean isBorderOpaque() {
		return true;
	}

	public BufferedImage getImage() {
		return image;
	}
}
