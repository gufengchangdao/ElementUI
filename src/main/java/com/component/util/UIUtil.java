/*
 * Copyright 2019 FormDev Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.component.util;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.IdentityHashMap;
import java.util.WeakHashMap;
import java.util.function.Supplier;

/**
 * Utility methods for UI delegates.
 *
 * @author Karl Tauber
 */
public class UIUtil {
	public static final boolean MAC_USE_QUARTZ = Boolean.getBoolean("apple.awt.graphics.UseQuartz");

	private static boolean useSharedUIs = true;
	private static final WeakHashMap<LookAndFeel, IdentityHashMap<Object, ComponentUI>> sharedUIinstances = new WeakHashMap<>();

	public static Rectangle addInsets(Rectangle r, Insets insets) {
		return new Rectangle(
				r.x - insets.left,
				r.y - insets.top,
				r.width + insets.left + insets.right,
				r.height + insets.top + insets.bottom);
	}

	public static Rectangle subtractInsets(Rectangle r, Insets insets) {
		return new Rectangle(
				r.x + insets.left,
				r.y + insets.top,
				r.width - insets.left - insets.right,
				r.height - insets.top - insets.bottom);
	}

	public static Dimension addInsets(Dimension dim, Insets insets) {
		return new Dimension(
				dim.width + insets.left + insets.right,
				dim.height + insets.top + insets.bottom);
	}

	public static Insets addInsets(Insets insets1, Insets insets2) {
		if (insets1 == null)
			return insets2;
		if (insets2 == null)
			return insets1;

		return new Insets(
				insets1.top + insets2.top,
				insets1.left + insets2.left,
				insets1.bottom + insets2.bottom,
				insets1.right + insets2.right);
	}

	public static void setInsets(Insets dest, Insets src) {
		dest.top = src.top;
		dest.left = src.left;
		dest.bottom = src.bottom;
		dest.right = src.right;
	}

	public static Color getUIColor(String key, int defaultColorRGB) {
		Color color = UIManager.getColor(key);
		return (color != null) ? color : new Color(defaultColorRGB);
	}

	public static Color getUIColor(String key, Color defaultColor) {
		Color color = UIManager.getColor(key);
		return (color != null) ? color : defaultColor;
	}

	public static Color getUIColor(String key, String defaultKey) {
		Color color = UIManager.getColor(key);
		return (color != null) ? color : UIManager.getColor(defaultKey);
	}

	/** @since 1.1 */
	public static boolean getUIBoolean(String key, boolean defaultValue) {
		Object value = UIManager.get(key);
		return (value instanceof Boolean) ? (Boolean) value : defaultValue;
	}

	public static int getUIInt(String key, int defaultValue) {
		Object value = UIManager.get(key);
		return (value instanceof Integer) ? (Integer) value : defaultValue;
	}

	public static float getUIFloat(String key, float defaultValue) {
		Object value = UIManager.get(key);
		return (value instanceof Number) ? ((Number) value).floatValue() : defaultValue;
	}

	/**
	 * 设置形状反锯齿
	 *
	 * @param g 绘图图形上下文
	 * @return 原有的渲染提示{KEY_ANTIALIASING, KEY_STROKE_CONTROL}
	 */
	public static Object[] setRenderingHints(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Object[] oldRenderingHints = {
				g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING),
				g2.getRenderingHint(RenderingHints.KEY_STROKE_CONTROL),
		};

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				MAC_USE_QUARTZ ? RenderingHints.VALUE_STROKE_PURE : RenderingHints.VALUE_STROKE_NORMALIZE);

		return oldRenderingHints;
	}

	/**
	 * 重置先前用{@link #setRenderingHints}设置的渲染提示
	 */
	public static void resetRenderingHints(Graphics g, Object[] oldRenderingHints) {
		Graphics2D g2 = (Graphics2D) g;
		if (oldRenderingHints[0] != null)
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, oldRenderingHints[0]);
		if (oldRenderingHints[1] != null)
			g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, oldRenderingHints[1]);
	}

	/**
	 * 临时重置使用setRenderingHints设置的呈现提示，并运行给定的可运行程序。这是为了在设置渲染提示时绘制文本。
	 * <p>
	 * 如果禁用了文本反锯齿(在OS系统设置中或通过-Dawt.useSystemAAFontSettings=off)，但是启用了一般的反锯齿，那么文本仍然使用某种“灰度”反锯齿绘制，这可能会使文本看起来粗体(取决于字体和字体大小)。
	 * 为了避免这种情况，请暂时禁用一般的反锯齿。如果启用了文本反锯齿(通常为默认值)，这不会影响文本呈现。
	 *
	 * @param g                 画笔
	 * @param oldRenderingHints 运行 runnable 之间使用的渲染提示
	 * @param runnable          在应用了给定渲染提示后被调用，然后在调用完毕后会将画笔重新设置为原先提示
	 */
	public static void runWithoutRenderingHints(Graphics g, Object[] oldRenderingHints, Runnable runnable) {
		if (oldRenderingHints == null) {
			runnable.run();
			return;
		}

		Graphics2D g2 = (Graphics2D) g;
		Object[] oldRenderingHints2 = {
				g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING),
				g2.getRenderingHint(RenderingHints.KEY_STROKE_CONTROL),
		};

		resetRenderingHints(g2, oldRenderingHints);
		runnable.run();
		resetRenderingHints(g2, oldRenderingHints2);
	}

	/**
	 * Paints an outline at the given bounds using the given line width.
	 * Depending on the given arc, a rectangle, rounded rectangle or circle (if w == h) is painted.
	 *
	 * @param g         the graphics context used for painting
	 * @param x         the x coordinate of the outline
	 * @param y         the y coordinate of the outline
	 * @param w         the width of the outline
	 * @param h         the height of the outline
	 * @param lineWidth the width of the outline
	 * @param arc       the arc diameter used for the outside shape of the outline
	 * @since 2
	 */
	public static void paintOutline(Graphics2D g, float x, float y, float w, float h,
	                                float lineWidth, float arc) {
		paintOutline(g, x, y, w, h, lineWidth, arc, arc - (lineWidth * 2));
	}

	/**
	 * 使用给定的线宽在给定的边界绘制轮廓。根据给定的圆弧，绘制矩形、圆角矩形或圆形(如果w == h)。
	 *
	 * @param g         用于绘画的图形上下文
	 * @param x         轮廓的x坐标
	 * @param y         轮廓的y坐标
	 * @param w         轮廓的宽度
	 * @param h         轮廓的高度
	 * @param lineWidth 线宽
	 * @param arc       用于外轮廓的圆弧直径
	 * @param innerArc  用于轮廓内部形状的圆弧直径
	 * @since 2
	 */
	public static void paintOutline(Graphics2D g, float x, float y, float w, float h,
	                                float lineWidth, float arc, float innerArc) {
		if (lineWidth == 0 || w <= 0 || h <= 0)
			return;

		float t = lineWidth;
		float t2x = t * 2;

		Path2D border = new Path2D.Float(Path2D.WIND_EVEN_ODD);
		border.append(createComponentRectangle(x, y, w, h, arc), false);
		border.append(createComponentRectangle(x + t, y + t, w - t2x, h - t2x, innerArc), false);
		g.fill(border);
	}

	/**
	 * Creates a (rounded) rectangle used to paint components (border, background, etc).
	 * The given arc diameter is limited to min(width,height).
	 */
	public static Shape createComponentRectangle(float x, float y, float w, float h, float arc) {
		if (arc <= 0)
			return new Rectangle2D.Float(x, y, w, h);

		if (w == h && arc >= w)
			return new Ellipse2D.Float(x, y, w, h);

		arc = Math.min(arc, Math.min(w, h));
		return new RoundRectangle2D.Float(x, y, w, h, arc, arc);
	}

	/**
	 * Find the first parent that is opaque.
	 */
	private static Container findOpaqueParent(Container c) {
		while ((c = c.getParent()) != null) {
			if (c.isOpaque())
				return c;
		}
		return null;
	}

	/**
	 * 创建具有给定线宽的非填充矩形形状
	 */
	public static Path2D createRectangle(float x, float y, float width, float height, float lineWidth) {
		Path2D path = new Path2D.Float(Path2D.WIND_EVEN_ODD);
		path.append(new Rectangle2D.Float(x, y, width, height), false);
		path.append(new Rectangle2D.Float(x + lineWidth, y + lineWidth,
				width - (lineWidth * 2), height - (lineWidth * 2)), false);
		return path;
	}

	/**
	 * 创建一个未填充的圆角矩形形状，并允许指定线宽和半径或每个角
	 */
	public static Path2D createRoundRectangle(float x, float y, float width, float height,
	                                          float lineWidth, float arcTopLeft, float arcTopRight, float arcBottomLeft, float arcBottomRight) {
		Path2D path = new Path2D.Float(Path2D.WIND_EVEN_ODD);
		path.append(createRoundRectanglePath(x, y, width, height, arcTopLeft, arcTopRight, arcBottomLeft, arcBottomRight), false);
		path.append(createRoundRectanglePath(x + lineWidth, y + lineWidth, width - (lineWidth * 2), height - (lineWidth * 2),
				arcTopLeft - lineWidth, arcTopRight - lineWidth, arcBottomLeft - lineWidth, arcBottomRight - lineWidth), false);
		return path;
	}

	/**
	 * 创建填充圆角矩形形状，并允许指定每个角的半径。
	 */
	public static Shape createRoundRectanglePath(float x, float y, float width, float height,
	                                             float arcTopLeft, float arcTopRight, float arcBottomLeft, float arcBottomRight) {
		if (arcTopLeft <= 0 && arcTopRight <= 0 && arcBottomLeft <= 0 && arcBottomRight <= 0)
			return new Rectangle2D.Float(x, y, width, height);

		// limit arcs to min(width,height)
		float maxArc = Math.min(width, height) / 2;
		arcTopLeft = (arcTopLeft > 0) ? Math.min(arcTopLeft, maxArc) : 0;
		arcTopRight = (arcTopRight > 0) ? Math.min(arcTopRight, maxArc) : 0;
		arcBottomLeft = (arcBottomLeft > 0) ? Math.min(arcBottomLeft, maxArc) : 0;
		arcBottomRight = (arcBottomRight > 0) ? Math.min(arcBottomRight, maxArc) : 0;

		float x2 = x + width;
		float y2 = y + height;

		// same constant as in java.awt.geom.EllipseIterator.CtrlVal used to paint circles
		double c = 0.5522847498307933;
		double ci = 1. - c;
		double ciTopLeft = arcTopLeft * ci;
		double ciTopRight = arcTopRight * ci;
		double ciBottomLeft = arcBottomLeft * ci;
		double ciBottomRight = arcBottomRight * ci;

		Path2D rect = new Path2D.Float();
		rect.moveTo(x2 - arcTopRight, y);
		rect.curveTo(x2 - ciTopRight, y,
				x2, y + ciTopRight,
				x2, y + arcTopRight);
		rect.lineTo(x2, y2 - arcBottomRight);
		rect.curveTo(x2, y2 - ciBottomRight,
				x2 - ciBottomRight, y2,
				x2 - arcBottomRight, y2);
		rect.lineTo(x + arcBottomLeft, y2);
		rect.curveTo(x + ciBottomLeft, y2,
				x, y2 - ciBottomLeft,
				x, y2 - arcBottomLeft);
		rect.lineTo(x, y + arcTopLeft);
		rect.curveTo(x, y + ciTopLeft,
				x + ciTopLeft, y,
				x + arcTopLeft, y);
		rect.closePath();

		return rect;
	}

	/**
	 * Creates a chevron or triangle arrow shape for the given direction and size.
	 * <p>
	 * The chevron shape is an open path that can be painted with {@link Graphics2D#draw(Shape)}.
	 * The triangle shape is a close path that can be painted with {@link Graphics2D#fill(Shape)}.
	 *
	 * @param direction the arrow direction ({@link SwingConstants#NORTH}, {@link SwingConstants#SOUTH}
	 *                  {@link SwingConstants#WEST} or {@link SwingConstants#EAST})
	 * @param chevron   {@code true} for chevron arrow, {@code false} for triangle arrow
	 * @param w         the width of the returned shape
	 * @param h         the height of the returned shape
	 * @since 1.1
	 */
	public static Shape createArrowShape(int direction, boolean chevron, float w, float h) {
		switch (direction) {
			case SwingConstants.NORTH:
				return createPath(!chevron, 0, h, (w / 2f), 0, w, h);
			case SwingConstants.SOUTH:
				return createPath(!chevron, 0, 0, (w / 2f), h, w, 0);
			case SwingConstants.WEST:
				return createPath(!chevron, w, 0, 0, (h / 2f), w, h);
			case SwingConstants.EAST:
				return createPath(!chevron, 0, 0, w, (h / 2f), 0, h);
			default:
				return new Path2D.Float();
		}
	}

	/**
	 * 为给定的点创建封闭路径。
	 */
	public static Path2D createPath(double... points) {
		return createPath(true, points);
	}

	/**
	 * 为给定的点创建打开或关闭的路径。
	 */
	public static Path2D createPath(boolean close, double... points) {
		Path2D path = new Path2D.Float();
		path.moveTo(points[0], points[1]);
		for (int i = 2; i < points.length; i += 2)
			path.lineTo(points[i], points[i + 1]);
		if (close)
			path.closePath();
		return path;
	}

	/**
	 * 绘制带有禁用笔画正常化的给定形状。形状的x/y坐标转换了半个像素。
	 *
	 * @since 2.1
	 */
	public static void drawShapePure(Graphics2D g, Shape shape) {
		Object oldStrokeControl = g.getRenderingHint(RenderingHints.KEY_STROKE_CONTROL);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

		g.translate(0.5, 0.5);
		g.draw(shape);
		g.translate(-0.5, -0.5);

		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, oldStrokeControl);
	}

	public static boolean hasOpaqueBeenExplicitlySet(JComponent c) {
		boolean oldOpaque = c.isOpaque();
		LookAndFeel.installProperty(c, "opaque", !oldOpaque);
		boolean explicitlySet = c.isOpaque() == oldOpaque;
		LookAndFeel.installProperty(c, "opaque", oldOpaque);
		return explicitlySet;
	}

	/**
	 * 返回是否使用共享UI委托。
	 *
	 * @since 1.6
	 */
	public static boolean isUseSharedUIs() {
		return useSharedUIs;
	}

	/**
	 * 为给定的键和当前Laf创建一个共享组件UI。每个Laf实例都有自己的共享组件UI实例。
	 * <p>
	 * 这是为支持Laf切换并可能同时使用多个Laf实例的GUI构建器准备的。
	 */
	public static ComponentUI createSharedUI(Object key, Supplier<ComponentUI> newInstanceSupplier) {
		if (!useSharedUIs)
			return newInstanceSupplier.get();

		return sharedUIinstances
				.computeIfAbsent(UIManager.getLookAndFeel(), k -> new IdentityHashMap<>())
				.computeIfAbsent(key, k -> newInstanceSupplier.get());
	}
}
