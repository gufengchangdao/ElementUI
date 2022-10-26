package com.component.svg.icon.fill;

import com.component.radiance.common.api.icon.AbstractRadianceIcon;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.radiance.common.api.icon.RadianceIconUIResource;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class CaretDownSvg extends AbstractRadianceIcon {
	/**
	 * @see AbstractRadianceIcon#AbstractRadianceIcon()
	 */
	public CaretDownSvg() {
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
		generalPath.moveTo(26.925f, 11.6125f);
		generalPath.curveTo(26.8485f, 11.4305f, 26.7198f, 11.2752f, 26.5552f, 11.1662f);
		generalPath.curveTo(26.3906f, 11.0572f, 26.1974f, 10.9994f, 26.0f, 11.0f);
		generalPath.lineTo(6.0f, 11.0f);
		generalPath.curveTo(5.80257f, 10.9994f, 5.60938f, 11.0572f, 5.44477f, 11.1662f);
		generalPath.curveTo(5.28016f, 11.2752f, 5.1515f, 11.4305f, 5.075f, 11.6125f);
		generalPath.curveTo(5.00295f, 11.7972f, 4.98468f, 11.9985f, 5.02228f, 12.1932f);
		generalPath.curveTo(5.05989f, 12.3879f, 5.15184f, 12.5679f, 5.2875f, 12.7125f);
		generalPath.lineTo(15.2875f, 22.7125f);
		generalPath.curveTo(15.4786f, 22.8973f, 15.7341f, 23.0006f, 16.0f, 23.0006f);
		generalPath.curveTo(16.2659f, 23.0006f, 16.5214f, 22.8973f, 16.7125f, 22.7125f);
		generalPath.lineTo(26.7125f, 12.7125f);
		generalPath.curveTo(26.8482f, 12.5679f, 26.9401f, 12.3879f, 26.9777f, 12.1932f);
		generalPath.curveTo(27.0153f, 11.9985f, 26.997f, 11.7972f, 26.925f, 11.6125f);
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
		return 4.98468017578125;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigY()
	 */
	public static double getOrigY() {
		return 10.99940013885498;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigWidth()
	 */
	public static double getOrigWidth() {
		return 22.030620574951172;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigHeight()
	 */
	public static double getOrigHeight() {
		return 12.001200675964355;
	}

	@Override
	public boolean supportsColorFilter() {
		return true;
	}

	/**
	 * @see AbstractRadianceIcon#of(int, int)
	 */
	public static RadianceIcon of(int width, int height) {
		CaretDownSvg base = new CaretDownSvg();
		base.width = width;
		base.height = height;
		return base;
	}

	/**
	 * @see AbstractRadianceIcon#uiResourceOf(int, int)
	 */
	public static RadianceIconUIResource uiResourceOf(int width, int height) {
		CaretDownSvg base = new CaretDownSvg();
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
		return CaretDownSvg::new;
	}
}

