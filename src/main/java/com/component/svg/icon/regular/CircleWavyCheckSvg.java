package com.component.svg.icon.regular;

import com.component.radiance.common.api.icon.AbstractRadianceIcon;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.radiance.common.api.icon.RadianceIconUIResource;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class CircleWavyCheckSvg extends AbstractRadianceIcon {
	/**
	 * @see AbstractRadianceIcon#AbstractRadianceIcon()
	 */
	public CircleWavyCheckSvg() {
		this.width = (int) getOrigWidth();
		this.height = (int) getOrigHeight();
	}


	private void _paint0(Graphics2D g, float origAlpha) {
// 
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0_0
		paint = (colorFilter != null) ? colorFilter.filter(new Color(0, 0, 0, 255)) : new Color(0, 0, 0, 255);
		stroke = new BasicStroke(2.0f, 1, 1, 4.0f, null, 0.0f);
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(6.8125f, 25.1875f);
		generalPath.curveTo(5.6625f, 24.0375f, 6.425f, 21.625f, 5.8375f, 20.2125f);
		generalPath.curveTo(5.25f, 18.8f, 3.0f, 17.5625f, 3.0f, 16.0f);
		generalPath.curveTo(3.0f, 14.4375f, 5.225f, 13.25f, 5.8375f, 11.7875f);
		generalPath.curveTo(6.45f, 10.325f, 5.6625f, 7.9625f, 6.8125f, 6.8125f);
		generalPath.curveTo(7.9625f, 5.6625f, 10.375f, 6.425f, 11.7875f, 5.8375f);
		generalPath.curveTo(13.2f, 5.25f, 14.4375f, 3.0f, 16.0f, 3.0f);
		generalPath.curveTo(17.5625f, 3.0f, 18.75f, 5.225f, 20.2125f, 5.8375f);
		generalPath.curveTo(21.675f, 6.45f, 24.0375f, 5.6625f, 25.1875f, 6.8125f);
		generalPath.curveTo(26.3375f, 7.9625f, 25.575f, 10.375f, 26.1625f, 11.7875f);
		generalPath.curveTo(26.75f, 13.2f, 29.0f, 14.4375f, 29.0f, 16.0f);
		generalPath.curveTo(29.0f, 17.5625f, 26.775f, 18.75f, 26.1625f, 20.2125f);
		generalPath.curveTo(25.55f, 21.675f, 26.3375f, 24.0375f, 25.1875f, 25.1875f);
		generalPath.curveTo(24.0375f, 26.3375f, 21.625f, 25.575f, 20.2125f, 26.1625f);
		generalPath.curveTo(18.8f, 26.75f, 17.5625f, 29.0f, 16.0f, 29.0f);
		generalPath.curveTo(14.4375f, 29.0f, 13.25f, 26.775f, 11.7875f, 26.1625f);
		generalPath.curveTo(10.325f, 25.55f, 7.9625f, 26.3375f, 6.8125f, 25.1875f);
		generalPath.closePath();
		shape = generalPath;
		g.setPaint(paint);
		g.setStroke(stroke);
		g.draw(shape);
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0_1
		paint = (colorFilter != null) ? colorFilter.filter(new Color(0, 0, 0, 255)) : new Color(0, 0, 0, 255);
		stroke = new BasicStroke(2.0f, 1, 1, 4.0f, null, 0.0f);
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(21.5f, 13.0f);
		generalPath.lineTo(14.1625f, 20.0f);
		generalPath.lineTo(10.5f, 16.5f);
		shape = generalPath;
		g.setPaint(paint);
		g.setStroke(stroke);
		g.draw(shape);

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
		return 2.0;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigY()
	 */
	public static double getOrigY() {
		return 2.0;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigWidth()
	 */
	public static double getOrigWidth() {
		return 28.0;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigHeight()
	 */
	public static double getOrigHeight() {
		return 28.0;
	}

	@Override
	public boolean supportsColorFilter() {
		return true;
	}

	/**
	 * @see AbstractRadianceIcon#of(int, int)
	 */
	public static RadianceIcon of(int width, int height) {
		CircleWavyCheckSvg base = new CircleWavyCheckSvg();
		base.width = width;
		base.height = height;
		return base;
	}

	/**
	 * @see AbstractRadianceIcon#uiResourceOf(int, int)
	 */
	public static RadianceIconUIResource uiResourceOf(int width, int height) {
		CircleWavyCheckSvg base = new CircleWavyCheckSvg();
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
		return CircleWavyCheckSvg::new;
	}
}

