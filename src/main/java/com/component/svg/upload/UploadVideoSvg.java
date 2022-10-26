package com.component.svg.upload;

import com.component.radiance.common.api.icon.AbstractRadianceIcon;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.radiance.common.api.icon.RadianceIconUIResource;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

public class UploadVideoSvg extends AbstractRadianceIcon {
	/**
	 * @see AbstractRadianceIcon#AbstractRadianceIcon()
	 */
	public UploadVideoSvg() {
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
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 11.0f));
// _0_0_2_0
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(64.0f, 40.24f);
		generalPath.lineTo(64.0f, 1.75f);
		generalPath.lineTo(49.78f, 15.75f);
		generalPath.lineTo(49.78f, 3.5f);
		generalPath.curveTo(49.78f, 1.58f, 48.18f, 0.0f, 46.22f, 0.0f);
		generalPath.lineTo(3.56f, 0.0f);
		generalPath.curveTo(1.6f, 0.0f, 0.0f, 1.58f, 0.0f, 3.5f);
		generalPath.lineTo(0.0f, 38.5f);
		generalPath.curveTo(0.0f, 40.43f, 1.6f, 42.0f, 3.56f, 42.0f);
		generalPath.lineTo(46.22f, 42.0f);
		generalPath.curveTo(48.18f, 42.0f, 49.78f, 40.43f, 49.78f, 38.5f);
		generalPath.lineTo(49.78f, 26.25f);
		generalPath.lineTo(64.0f, 40.24f);
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
		UploadVideoSvg base = new UploadVideoSvg();
		base.width = width;
		base.height = height;
		return base;
	}

	/**
	 * @see AbstractRadianceIcon#uiResourceOf(int, int)
	 */
	public static RadianceIconUIResource uiResourceOf(int width, int height) {
		UploadVideoSvg base = new UploadVideoSvg();
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
		return UploadVideoSvg::new;
	}
}

