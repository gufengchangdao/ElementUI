package com.component.svg.icon.fill;

import com.component.radiance.common.api.icon.AbstractRadianceIcon;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.radiance.common.api.icon.RadianceIconUIResource;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class CaretLeftSvg extends AbstractRadianceIcon {
	/**
	 * @see AbstractRadianceIcon#AbstractRadianceIcon()
	 */
	public CaretLeftSvg() {
		this.width = (int) getOrigWidth();
		this.height = (int) getOrigHeight();
	}


	private void _paint0(Graphics2D g, float origAlpha) {
// 
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0_0
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(20.3875f, 5.07495f);
		generalPath.curveTo(20.2028f, 5.00291f, 20.0015f, 4.98463f, 19.8068f, 5.02223f);
		generalPath.curveTo(19.6121f, 5.05984f, 19.4321f, 5.15179f, 19.2875f, 5.28745f);
		generalPath.lineTo(9.28751f, 15.2875f);
		generalPath.curveTo(9.09973f, 15.477f, 8.99438f, 15.7331f, 8.99438f, 16.0f);
		generalPath.curveTo(8.99438f, 16.2668f, 9.09973f, 16.5229f, 9.28751f, 16.7125f);
		generalPath.lineTo(19.2875f, 26.7125f);
		generalPath.curveTo(19.4795f, 26.8959f, 19.7345f, 26.9988f, 20.0f, 27.0f);
		generalPath.curveTo(20.1327f, 26.9994f, 20.2642f, 26.9739f, 20.3875f, 26.925f);
		generalPath.curveTo(20.5695f, 26.8484f, 20.7248f, 26.7198f, 20.8338f, 26.5552f);
		generalPath.curveTo(20.9428f, 26.3906f, 21.0006f, 26.1974f, 21.0f, 26.0f);
		generalPath.lineTo(21.0f, 5.99995f);
		generalPath.curveTo(21.0006f, 5.80253f, 20.9428f, 5.60934f, 20.8338f, 5.44473f);
		generalPath.curveTo(20.7248f, 5.28012f, 20.5695f, 5.15145f, 20.3875f, 5.07495f);
		generalPath.closePath();
		shape = generalPath;
		paint = (colorFilter != null) ? colorFilter.filter(new Color(0, 0, 0, 255)) : new Color(0, 0, 0, 255);
		g.setPaint(paint);
		g.fill(shape);

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
		return 8.994379997253418;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigY()
	 */
	public static double getOrigY() {
		return 4.984630107879639;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigWidth()
	 */
	public static double getOrigWidth() {
		return 12.006220817565918;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigHeight()
	 */
	public static double getOrigHeight() {
		return 22.015369415283203;
	}

	@Override
	public boolean supportsColorFilter() {
		return true;
	}

	/**
	 * @see AbstractRadianceIcon#of(int, int)
	 */
	public static RadianceIcon of(int width, int height) {
		CaretLeftSvg base = new CaretLeftSvg();
		base.width = width;
		base.height = height;
		return base;
	}

	/**
	 * @see AbstractRadianceIcon#uiResourceOf(int, int)
	 */
	public static RadianceIconUIResource uiResourceOf(int width, int height) {
		CaretLeftSvg base = new CaretLeftSvg();
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
		return CaretLeftSvg::new;
	}
}

