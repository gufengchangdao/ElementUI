package com.component.svg.icon.fill;

import com.component.radiance.common.api.icon.AbstractRadianceIcon;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.radiance.common.api.icon.RadianceIconUIResource;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class CaretRightSvg extends AbstractRadianceIcon {
	/**
	 * @see AbstractRadianceIcon#AbstractRadianceIcon()
	 */
	public CaretRightSvg() {
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
		generalPath.moveTo(22.7125f, 15.2875f);
		generalPath.lineTo(12.7125f, 5.28745f);
		generalPath.curveTo(12.5679f, 5.15179f, 12.3879f, 5.05984f, 12.1932f, 5.02223f);
		generalPath.curveTo(11.9985f, 4.98463f, 11.7972f, 5.00291f, 11.6125f, 5.07495f);
		generalPath.curveTo(11.4305f, 5.15145f, 11.2752f, 5.28012f, 11.1662f, 5.44473f);
		generalPath.curveTo(11.0572f, 5.60934f, 10.9994f, 5.80253f, 11.0f, 5.99995f);
		generalPath.lineTo(11.0f, 26.0f);
		generalPath.curveTo(10.9994f, 26.1974f, 11.0572f, 26.3906f, 11.1662f, 26.5552f);
		generalPath.curveTo(11.2752f, 26.7198f, 11.4305f, 26.8484f, 11.6125f, 26.925f);
		generalPath.curveTo(11.7359f, 26.9739f, 11.8673f, 26.9994f, 12.0f, 27.0f);
		generalPath.curveTo(12.2655f, 26.9988f, 12.5205f, 26.8959f, 12.7125f, 26.7125f);
		generalPath.lineTo(22.7125f, 16.7125f);
		generalPath.curveTo(22.9003f, 16.5229f, 23.0056f, 16.2668f, 23.0056f, 16.0f);
		generalPath.curveTo(23.0056f, 15.7331f, 22.9003f, 15.477f, 22.7125f, 15.2875f);
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
		return 10.99940013885498;
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
		return 12.006199836730957;
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
		CaretRightSvg base = new CaretRightSvg();
		base.width = width;
		base.height = height;
		return base;
	}

	/**
	 * @see AbstractRadianceIcon#uiResourceOf(int, int)
	 */
	public static RadianceIconUIResource uiResourceOf(int width, int height) {
		CaretRightSvg base = new CaretRightSvg();
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
		return CaretRightSvg::new;
	}
}

