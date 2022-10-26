package com.component.svg.upload;

import com.component.radiance.common.api.icon.AbstractRadianceIcon;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.radiance.common.api.icon.RadianceIconUIResource;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

public class UploadImage4Svg extends AbstractRadianceIcon {
	/**
	 * @see AbstractRadianceIcon#AbstractRadianceIcon()
	 */
	public UploadImage4Svg() {
		this.width = (int) getOrigWidth();
		this.height = (int) getOrigHeight();
	}


	private void _paint0(Graphics2D g, float origAlpha) {
// 
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0_0
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0_0_0
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(3.6739403E-16f, 152.0f);
		generalPath.curveTo(0.0f, 153.5913f, 0.63214105f, 155.11742f, 1.7573593f, 156.24265f);
		generalPath.curveTo(2.8825777f, 157.36786f, 4.408701f, 158.0f, 6.0f, 158.0f);
		generalPath.lineTo(152.0f, 158.0f);
		generalPath.curveTo(155.3137f, 158.0f, 158.0f, 155.3137f, 158.0f, 152.0f);
		generalPath.lineTo(158.0f, 6.0f);
		generalPath.curveTo(158.0f, 4.408701f, 157.36786f, 2.8825777f, 156.24265f, 1.7573593f);
		generalPath.curveTo(155.11742f, 0.63214105f, 153.5913f, -8.881784E-16f, 152.0f, -8.881784E-16f);
		generalPath.lineTo(6.0f, 0.0f);
		generalPath.curveTo(4.408701f, 0.0f, 2.8825777f, 0.63214105f, 1.7573593f, 1.7573593f);
		generalPath.curveTo(0.63214105f, 2.8825777f, 0.0f, 4.408701f, 0.0f, 6.0f);
		generalPath.closePath();
		shape = generalPath;
		paint = (colorFilter != null) ? colorFilter.filter(new Color(255, 255, 255, 255)) : new Color(255, 255, 255, 255);
		g.setPaint(paint);
		g.fill(shape);
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0_0_1
		paint = (colorFilter != null) ? colorFilter.filter(new Color(221, 221, 221, 255)) : new Color(221, 221, 221, 255);
		stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(3.6739403E-16f, 152.0f);
		generalPath.curveTo(0.0f, 153.5913f, 0.63214105f, 155.11742f, 1.7573593f, 156.24265f);
		generalPath.curveTo(2.8825777f, 157.36786f, 4.408701f, 158.0f, 6.0f, 158.0f);
		generalPath.lineTo(152.0f, 158.0f);
		generalPath.curveTo(155.3137f, 158.0f, 158.0f, 155.3137f, 158.0f, 152.0f);
		generalPath.lineTo(158.0f, 6.0f);
		generalPath.curveTo(158.0f, 4.408701f, 157.36786f, 2.8825777f, 156.24265f, 1.7573593f);
		generalPath.curveTo(155.11742f, 0.63214105f, 153.5913f, -8.881784E-16f, 152.0f, -8.881784E-16f);
		generalPath.lineTo(6.0f, 0.0f);
		generalPath.curveTo(4.408701f, 0.0f, 2.8825777f, 0.63214105f, 1.7573593f, 1.7573593f);
		generalPath.curveTo(0.63214105f, 2.8825777f, 0.0f, 4.408701f, 0.0f, 6.0f);
		generalPath.closePath();
		shape = generalPath;
		g.setPaint(paint);
		g.setStroke(stroke);
		g.draw(shape);
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
		transformsStack.push(g.getTransform());
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 47.0f, 48.0f));
// _0_0_2
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
		transformsStack.push(g.getTransform());
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 4.0f));
// _0_0_2_0
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(4.0f, -2.77112E-13f);
		generalPath.curveTo(1.79f, -2.77112E-13f, 0.0f, 1.79f, 0.0f, 4.0f);
		generalPath.lineTo(0.0f, 52.0f);
		generalPath.curveTo(0.0f, 54.21f, 1.79f, 56.0f, 4.0f, 56.0f);
		generalPath.lineTo(60.0f, 56.0f);
		generalPath.curveTo(62.21f, 56.0f, 64.0f, 54.21f, 64.0f, 52.0f);
		generalPath.lineTo(64.0f, 4.0f);
		generalPath.curveTo(64.0f, 1.79f, 62.21f, -2.77112E-13f, 60.0f, -2.77112E-13f);
		generalPath.lineTo(4.0f, -2.77112E-13f);
		generalPath.closePath();
		generalPath.moveTo(4.0f, 47.29f);
		generalPath.lineTo(18.48f, 19.33f);
		generalPath.curveTo(19.16f, 18.01f, 21.01f, 17.87f, 21.88f, 19.08f);
		generalPath.lineTo(32.76f, 34.24f);
		generalPath.lineTo(42.36f, 26.45f);
		generalPath.curveTo(43.27f, 25.71f, 44.62f, 25.91f, 45.28f, 26.88f);
		generalPath.lineTo(60.0f, 48.63f);
		generalPath.lineTo(60.0f, 4.0f);
		generalPath.lineTo(4.0f, 4.0f);
		generalPath.lineTo(4.0f, 47.29f);
		generalPath.closePath();
		generalPath.moveTo(46.0f, 14.0f);
		generalPath.curveTo(46.0f, 16.21f, 47.79f, 18.0f, 50.0f, 18.0f);
		generalPath.curveTo(52.21f, 18.0f, 54.0f, 16.21f, 54.0f, 14.0f);
		generalPath.curveTo(54.0f, 11.79f, 52.21f, 10.0f, 50.0f, 10.0f);
		generalPath.curveTo(47.79f, 10.0f, 46.0f, 11.79f, 46.0f, 14.0f);
		generalPath.closePath();
		shape = generalPath;
		paint = (colorFilter != null) ? colorFilter.filter(new Color(153, 153, 153, 255)) : new Color(153, 153, 153, 255);
		g.setPaint(paint);
		g.fill(shape);
		g.setTransform(transformsStack.pop());
		g.setTransform(transformsStack.pop());

	}


	protected void innerPaint(Graphics2D g) {
		float origAlpha = getOrigAlpha(g);

		_paint0(g, origAlpha);


		super.innerPaint(g);
	}

	/**
	 * @see AbstractRadianceIcon#getOrigX()
	 */
	public static double getOrigX() {
		return 0.0;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigY()
	 */
	public static double getOrigY() {
		return 0.0;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigWidth()
	 */
	public static double getOrigWidth() {
		return 158.0;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigHeight()
	 */
	public static double getOrigHeight() {
		return 158.0;
	}

	@Override
	public boolean supportsColorFilter() {
		return true;
	}

	/**
	 * @see AbstractRadianceIcon#of(int, int)
	 */
	public static RadianceIcon of(int width, int height) {
		UploadImage4Svg base = new UploadImage4Svg();
		base.width = width;
		base.height = height;
		return base;
	}

	/**
	 * @see AbstractRadianceIcon#uiResourceOf(int, int)
	 */
	public static RadianceIconUIResource uiResourceOf(int width, int height) {
		UploadImage4Svg base = new UploadImage4Svg();
		base.width = width;
		base.height = height;
		return new RadianceIconUIResource(base);
	}

	@Override
	public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = paint(c, g, x, y, getOrigWidth(), getOrigHeight(), getOrigX(), getOrigY());
		innerPaint(g2);
		g2.dispose();
	}

	/**
	 * @see AbstractRadianceIcon#factory()
	 */
	public static Factory factory() {
		return UploadImage4Svg::new;
	}
}

