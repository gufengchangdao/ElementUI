package com.component.svg.icon.fill;

import com.component.radiance.common.api.icon.AbstractRadianceIcon;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.radiance.common.api.icon.RadianceIconUIResource;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class ArrowLeftSvg extends AbstractRadianceIcon {
	/**
	 * @see AbstractRadianceIcon#AbstractRadianceIcon()
	 */
	public ArrowLeftSvg() {
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
		generalPath.moveTo(28.0f, 16.0f);
		generalPath.curveTo(28.0f, 16.2652f, 27.8947f, 16.5196f, 27.7071f, 16.7071f);
		generalPath.curveTo(27.5196f, 16.8947f, 27.2652f, 17.0f, 27.0f, 17.0f);
		generalPath.lineTo(15.0f, 17.0f);
		generalPath.lineTo(15.0f, 25.0f);
		generalPath.curveTo(15.0006f, 25.1974f, 14.9428f, 25.3906f, 14.8338f, 25.5552f);
		generalPath.curveTo(14.7248f, 25.7198f, 14.5695f, 25.8485f, 14.3875f, 25.925f);
		generalPath.curveTo(14.2642f, 25.974f, 14.1327f, 25.9994f, 14.0f, 26.0f);
		generalPath.curveTo(13.7345f, 25.9989f, 13.4795f, 25.896f, 13.2875f, 25.7125f);
		generalPath.lineTo(4.28751f, 16.7125f);
		generalPath.curveTo(4.09973f, 16.5229f, 3.99438f, 16.2669f, 3.99438f, 16.0f);
		generalPath.curveTo(3.99438f, 15.7332f, 4.09973f, 15.4771f, 4.28751f, 15.2875f);
		generalPath.lineTo(13.2875f, 6.28751f);
		generalPath.curveTo(13.4321f, 6.15185f, 13.6121f, 6.0599f, 13.8068f, 6.0223f);
		generalPath.curveTo(14.0015f, 5.98469f, 14.2028f, 6.00297f, 14.3875f, 6.07501f);
		generalPath.curveTo(14.5695f, 6.15151f, 14.7248f, 6.28018f, 14.8338f, 6.44479f);
		generalPath.curveTo(14.9428f, 6.6094f, 15.0006f, 6.80259f, 15.0f, 7.00001f);
		generalPath.lineTo(15.0f, 15.0f);
		generalPath.lineTo(27.0f, 15.0f);
		generalPath.curveTo(27.2652f, 15.0f, 27.5196f, 15.1054f, 27.7071f, 15.2929f);
		generalPath.curveTo(27.8947f, 15.4804f, 28.0f, 15.7348f, 28.0f, 16.0f);
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
		return 3.994379997253418;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigY()
	 */
	public static double getOrigY() {
		return 5.984690189361572;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigWidth()
	 */
	public static double getOrigWidth() {
		return 24.005619049072266;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigHeight()
	 */
	public static double getOrigHeight() {
		return 20.015310287475586;
	}

	@Override
	public boolean supportsColorFilter() {
		return true;
	}

	/**
	 * @see AbstractRadianceIcon#of(int, int)
	 */
	public static RadianceIcon of(int width, int height) {
		ArrowLeftSvg base = new ArrowLeftSvg();
		base.width = width;
		base.height = height;
		return base;
	}

	/**
	 * @see AbstractRadianceIcon#uiResourceOf(int, int)
	 */
	public static RadianceIconUIResource uiResourceOf(int width, int height) {
		ArrowLeftSvg base = new ArrowLeftSvg();
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
		return ArrowLeftSvg::new;
	}
}

