/*
 * $Id: GraphicsUtilities.java,v 1.1 2006/12/15 13:53:13 gfx Exp $
 *
 * Dual-licensed under LGPL (Sun and Romain Guy) and BSD (Romain Guy).
 *
 * Copyright 2005 Sun Microsystems, Inc., 4150 Network Circle,
 * Santa Clara, California 95054, U.S.A. All rights reserved.
 *
 * Copyright (c) 2006 Romain Guy <romain.guy@mac.com>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.component.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;

/**
 * GraphicsUtilities包含一组工具，可以轻松执行常见的图形操作。这些操作分为几个主题，如下所列。
 * <h3>兼容图像</h3>
 * 兼容的图像可以而且应该用于提高绘图性能。此类提供了许多方法来直接从文件加载兼容图像或将现有图像转换为兼容图像。<br><br>
 * <h3>缩放图像</h3>
 * 此类提供了许多方法来轻松缩小图像。其中一些方法提供了速度和结果质量之间的权衡，应该一直使用。它们还具有生成兼容图像的优势，从而自动产生更好的运行时性能。<br>
 * 所有这些方法都比java.awt.Image.getScaledInstance(int, int, int)更快，并且比Graphics中的各种可用于图像缩放的drawImage()方法产生更好看的结果。<br><br>
 * <h3>图像处理</h3>
 * 此类提供两种方法来获取和设置缓冲图像中的像素。这些方法尽量避免取消管理图像以保持良好的性能。
 *
 * @author Romain Guy <romain.guy@mac.com>
 */
public class GraphicsUtil {
	private static final GraphicsConfiguration CONFIGURATION =
			GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

	private GraphicsUtil() {
	}

	/**
	 * 对ImageIcon图标进行缩放，不改变原对象，
	 * 该方法使用 getScaledInstance() 获取新图像，要缩放图像建议使用 getFasterScaledInstance()
	 *
	 * @param image 要缩放的图标对象
	 * @param i     缩放倍数
	 * @return 缩放后的图标对象
	 */
	public static ImageIcon imageIconScale(ImageIcon image, double i) {
		int width = (int) (image.getIconWidth() * i);
		int height = (int) (image.getIconHeight() * i);
		Image img = image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		return new ImageIcon(img);
	}


	/**
	 * 缩放 ImageIcon对象
	 */
	public static ImageIcon imageIconScale(ImageIcon image, int targetWidth, int targetHeight) {
		Image img = image.getImage().getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
		return new ImageIcon(img);
	}

	/**
	 * 使用与作为参数传递的图像相同的颜色模型返回一个新的BufferedImage 。
	 * 返回的图像仅与作为参数传递的图像兼容。这并不意味着返回的图像与硬件兼容。
	 *
	 * @param image 从中获得新图像的颜色模型的参考图像
	 * @return 兼容image颜色模组的新对象
	 */
	public static BufferedImage createColorModelCompatibleImage(BufferedImage image) {
		ColorModel cm = image.getColorModel();
		return new BufferedImage(cm, cm.createCompatibleWritableRaster(image.getWidth(), image.getHeight()), cm.isAlphaPremultiplied(), null);
	}

	/**
	 * 返回与指定为参数的图像具有相同宽度、高度和透明度的新兼容图像。
	 *
	 * @param image 返回与指定为参数的图像具有相同宽度、高度和透明度的新兼容图像。
	 * @return 相同大小和透明度的兼容对象
	 */
	public static BufferedImage createCompatibleImage(BufferedImage image) {
		return createCompatibleImage(image, image.getWidth(), image.getHeight());
	}

	/**
	 * 返回指定宽度和高度的新兼容图像，以及与作为参数指定的图像相同的透明度设置。
	 *
	 * @param width  新图像的宽度
	 * @param height 新图像的高度
	 * @param image  从中获得新图像透明度的参考图像
	 * @return 一个新的兼容 BufferedImage 与图像具有相同的透明度和指定的尺寸
	 */
	public static BufferedImage createCompatibleImage(BufferedImage image, int width, int height) {
		return CONFIGURATION.createCompatibleImage(width, height, image.getTransparency());
	}

	/**
	 * 返回指定宽度和高度的新不透明兼容图像
	 *
	 * @param width  新图像的宽度
	 * @param height 新图像的高度
	 * @return 指定宽度和高度的新的不透明兼容BufferedImage
	 */
	public static BufferedImage createCompatibleImage(int width, int height) {
		return CONFIGURATION.createCompatibleImage(width, height);
	}

	/**
	 * 返回指定宽度和高度的新半透明兼容图像
	 *
	 * @param width  新图像的宽度
	 * @param height 新图像的高度
	 * @return 指定宽度和高度的新半透明兼容BufferedImage
	 */
	public static BufferedImage createTranslucentCompatibleImage(int width, int height) {
		return CONFIGURATION.createCompatibleImage(width, height, Transparency.TRANSLUCENT);
	}

	/**
	 * 从 URL 返回一个新的兼容图像。图像从指定位置加载，然后在必要时转换为兼容图像。
	 *
	 * @param resource 要作为兼容图像加载的图片的 URL
	 * @return 指定宽度和高度的新半透明兼容BufferedImage
	 * @throws IOException 如果无法读取或加载图像
	 */
	public static BufferedImage loadCompatibleImage(URL resource) throws IOException {
		BufferedImage image = ImageIO.read(resource);
		return toCompatibleImage(image);
	}

	/**
	 * 返回包含指定图像副本的新兼容图像。此方法可确保图像与硬件兼容，因此针对快速 blitting 操作进行了优化。
	 *
	 * @param image 要复制到新的兼容图像中的图像
	 * @return 具有相同宽度和高度以及透明度和内容的image的新兼容副本
	 */
	public static BufferedImage toCompatibleImage(BufferedImage image) {
		if (image.getColorModel().equals(CONFIGURATION.getColorModel())) {
			return image;
		}

		BufferedImage compatibleImage = CONFIGURATION.createCompatibleImage(
				image.getWidth(), image.getHeight(), image.getTransparency());
		Graphics g = compatibleImage.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();

		return compatibleImage;
	}

	/**
	 * <p>返回源图像的缩略图。 newSize定义缩略图最长尺寸的长度。然后根据原始图片的尺寸比计算另一个尺寸。</p>
	 * <p>这种方法在速度和质量之间提供了良好的折衷。</p>
	 *
	 * @param image   源图像
	 * @param newSize 缩略图最大尺寸的长度
	 * @return 包含image缩略图的新兼容BufferedImage
	 * @throws IllegalArgumentException 如果newSize大于image的最大尺寸或 <= 0
	 */
	public static BufferedImage createThumbnail(BufferedImage image, int newSize) {
		int width = image.getWidth();
		int height = image.getHeight();

		boolean isWidthGreater = width > height;

		if (isWidthGreater) {
			if (newSize >= width) {
				throw new IllegalArgumentException("newSize must be lower than the image width");
			}
		} else if (newSize >= height) {
			throw new IllegalArgumentException("newSize must be lower than the image height");
		}

		if (newSize <= 0) {
			throw new IllegalArgumentException("newSize must be greater than 0");
		}

		float ratioWH = (float) width / (float) height;
		float ratioHW = (float) height / (float) width;

		BufferedImage thumb = image;

		do {
			if (isWidthGreater) {
				width /= 2;
				if (width < newSize) {
					width = newSize;
				}
				height = (int) (width / ratioWH);
			} else {
				height /= 2;
				if (height < newSize) {
					height = newSize;
				}
				width = (int) (height / ratioHW);
			}

			BufferedImage temp = createCompatibleImage(image, width, height);
			Graphics2D g2 = temp.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.drawImage(thumb, 0, 0, temp.getWidth(), temp.getHeight(), null);
			g2.dispose();

			thumb = temp;
		} while (newSize != (isWidthGreater ? width : height));

		return thumb;
	}

	/**
	 * 双线式图像缩放，按图像比例缩放
	 *
	 * @param image     源图像
	 * @param newSize   新的图像大小
	 * @param isByShort newSize 是否为新图像的最短边
	 * @return 最长边(或最短边)为 newSize 的新图像
	 */
	public static BufferedImage createScaledCompositeInstance(BufferedImage image, int newSize, boolean isByShort) {
		int width = image.getWidth();
		int height = image.getHeight();
		int newWidth, newHeight;

		if (width >= height) {
			if (isByShort) {
				newWidth = newSize * width / height;
				newHeight = newSize;
			} else {
				newWidth = newSize;
				newHeight = newSize * height / width;
			}
		} else {
			if (isByShort) {
				newWidth = newSize;
				newHeight = newSize * height / width;
			} else {
				newWidth = newSize * width / height;
				newHeight = newSize;
			}
		}
		return createScaledCompositeInstance(image, newWidth, newHeight);
	}

	/**
	 * 使用双线式图像缩放，返回指定宽度的缩放图像，图像高度按照宽度缩放倍率进行缩放
	 *
	 * @param image     源图像
	 * @param newWidth  新图像宽度
	 * @param maxHeight 缩放图像的最大高度，也就是说不能完全依靠图像的长宽比进行缩放
	 * @return 包含image缩放图的新兼容BufferedImage
	 */
	public static BufferedImage createFixedWidthScaledCompositeInstance(BufferedImage image, int newWidth, int maxHeight) {
		return createScaledCompositeInstance(image, newWidth, Math.min(image.getHeight() * newWidth / image.getWidth(), maxHeight));
	}

	/**
	 * 双线式图像缩放<br>
	 * 缩放原理：对于缩小倍率较大的图像，使用一次drawImage()缩放并且当缩小比例大于 50% 时，图像质量会发生严重的问题，
	 * 因此这里采用多次缩小的方法，每次最多缩小50%，在效率和质量上均衡。该方法要优于getScaledImage() (时间上优于)
	 *
	 * @param image     源图像
	 * @param newWidth  新图像宽度
	 * @param newHeight 新图像高度
	 * @return 包含image缩放图的新兼容BufferedImage
	 */
	public static BufferedImage createScaledCompositeInstance(BufferedImage image, int newWidth, int newHeight) {
		int width = image.getWidth();
		int height = image.getHeight();
		if (newWidth == width && newHeight == height) {
			return image;
		}
		if (newWidth >= width) {
			width = newWidth;
		}
		if (newHeight >= height) {
			height = newHeight;
		}
		if (newWidth <= 0 || newHeight <= 0) {
			throw new IllegalArgumentException("newWidth and newHeight must be greater than 0");
		}

		BufferedImage result = image;

		do {
			if (width > newWidth) {
				width /= 2;
				if (width < newWidth) {
					width = newWidth;
				}
			}

			if (height > newHeight) {
				height /= 2;
				if (height < newHeight) {
					height = newHeight;
				}
			}

			//在当前图像上绘制当前图像会出问题，不能复用，所以这里每次都使用新图像
			BufferedImage temp = createCompatibleImage(image, width, height);
			Graphics2D g2 = temp.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.drawImage(result, 0, 0, temp.getWidth(), temp.getHeight(), null);
			g2.dispose();

			result = temp;
		} while (width != newWidth || height != newHeight);

		return result;
	}

	/**
	 * <p>从BufferedImage返回一个像素数组，以整数形式存储。像素是从由位置和尺寸的矩形区域中获取的。
	 * 对不同于BufferedImage.TYPE_INT_ARGB和BufferedImage.TYPE_INT_RGB类型的图像调用此方法将取消Swing2D对图像的管理。
	 *
	 * @param img    源图像
	 * @param x      像素开始抓取的 x 位置
	 * @param y      像素开始抓取的 y 位置
	 * @param w      要抓取的像素矩形的宽度
	 * @param h      要抓取的像素矩形的高度
	 * @param pixels 大小为 w*h 的预分配像素数组；可以为空
	 * @return 如果非空，则为pixels ，否则为新的整数数组
	 * @throws IllegalArgumentException pixels为非空且长度<w*h
	 */
	public static int[] getPixels(BufferedImage img, int x, int y, int w, int h, int[] pixels) {
		if (w == 0 || h == 0) {
			return new int[0];
		}

		if (pixels == null) {
			pixels = new int[w * h];
		} else if (pixels.length < w * h) {
			throw new IllegalArgumentException("pixels array must have a length" + " >= w*h");
		}

		int imageType = img.getType();
		if (imageType == BufferedImage.TYPE_INT_ARGB ||
				imageType == BufferedImage.TYPE_INT_RGB) {
			Raster raster = img.getRaster();
			return (int[]) raster.getDataElements(x, y, w, h, pixels);
		}

		// Unmanages the image
		return img.getRGB(x, y, w, h, pixels, 0, w);
	}

	/**
	 * 在目标BufferedImage中写入一个矩形像素区域。对不同于BufferedImage.TYPE_INT_ARGB和BufferedImage.TYPE_INT_RGB类型的图像调用此方法将取消对图像的管理。
	 *
	 * @param img    目标图像
	 * @param x      像素存储开始的 x 位置
	 * @param y      像素存储开始的 y 位置
	 * @param w      要存储的像素矩形的宽度
	 * @param h      要存储的像素矩形的高度
	 * @param pixels 像素数组，存储为整数
	 * @throws IllegalArgumentException pixels为非空且长度 < w*h
	 */
	public static void setPixels(BufferedImage img,
	                             int x, int y, int w, int h, int[] pixels) {
		if (pixels == null || w == 0 || h == 0) {
			return;
		} else if (pixels.length < w * h) {
			throw new IllegalArgumentException("pixels array must have a length" +
					" >= w*h");
		}

		int imageType = img.getType();
		if (imageType == BufferedImage.TYPE_INT_ARGB ||
				imageType == BufferedImage.TYPE_INT_RGB) {
			WritableRaster raster = img.getRaster();
			raster.setDataElements(x, y, w, h, pixels);
		} else {
			// Unmanages the image
			img.setRGB(x, y, w, h, pixels, 0, w);
		}
	}
}
