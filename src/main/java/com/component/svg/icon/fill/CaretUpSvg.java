package com.component.svg.icon.fill;

import com.component.radiance.common.api.icon.AbstractRadianceIcon;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.radiance.common.api.icon.RadianceIconUIResource;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class CaretUpSvg extends AbstractRadianceIcon {
	/**
	 * @see AbstractRadianceIcon#AbstractRadianceIcon()
	 */
	public CaretUpSvg() {
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
		generalPath.moveTo(26.7125f, 19.2875f);
		generalPath.lineTo(16.7125f, 9.28751f);
		generalPath.curveTo(16.5229f, 9.09973f, 16.2669f, 8.99438f, 16.0f, 8.99438f);
		generalPath.curveTo(15.7332f, 8.99438f, 15.4771f, 9.09973f, 15.2875f, 9.28751f);
		generalPath.lineTo(5.28751f, 19.2875f);
		generalPath.curveTo(5.15185f, 19.4321f, 5.0599f, 19.6121f, 5.0223f, 19.8068f);
		generalPath.curveTo(4.98469f, 20.0015f, 5.00297f, 20.2028f, 5.07501f, 20.3875f);
		generalPath.curveTo(5.15151f, 20.5695f, 5.28018f, 20.7248f, 5.44479f, 20.8338f);
		generalPath.curveTo(5.6094f, 20.9428f, 5.80259f, 21.0006f, 6.00001f, 21.0f);
		generalPath.lineTo(26.0f, 21.0f);
		generalPath.curveTo(26.1974f, 21.0006f, 26.3906f, 20.9428f, 26.5552f, 20.8338f);
		generalPath.curveTo(26.7198f, 20.7248f, 26.8485f, 20.5695f, 26.925f, 20.3875f);
		generalPath.curveTo(26.9971f, 20.2028f, 27.0153f, 20.0015f, 26.9777f, 19.8068f);
		generalPath.curveTo(26.9401f, 19.6121f, 26.8482f, 19.4321f, 26.7125f, 19.2875f);
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
		return 4.984690189361572;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigY()
	 */
	public static double getOrigY() {
		return 8.994379997253418;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigWidth()
	 */
	public static double getOrigWidth() {
		return 22.030611038208008;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigHeight()
	 */
	public static double getOrigHeight() {
		return 12.006220817565918;
	}

	@Override
	public boolean supportsColorFilter() {
		return true;
	}

	/**
	 * @see AbstractRadianceIcon#of(int, int)
	 */
	public static RadianceIcon of(int width, int height) {
		CaretUpSvg base = new CaretUpSvg();
		base.width = width;
		base.height = height;
		return base;
	}

	/**
	 * @see AbstractRadianceIcon#uiResourceOf(int, int)
	 */
	public static RadianceIconUIResource uiResourceOf(int width, int height) {
		CaretUpSvg base = new CaretUpSvg();
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
		return CaretUpSvg::new;
	}
}

