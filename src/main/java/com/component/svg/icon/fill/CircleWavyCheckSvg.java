package com.component.svg.icon.fill;

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
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(28.2375f, 12.85f);
		generalPath.curveTo(27.7625f, 12.3625f, 27.275f, 11.85f, 27.0875f, 11.4125f);
		generalPath.curveTo(26.9f, 10.975f, 26.9125f, 10.325f, 26.9f, 9.6625f);
		generalPath.curveTo(26.8875f, 8.45f, 26.8625f, 7.0625f, 25.9f, 6.1f);
		generalPath.curveTo(24.9375f, 5.1375f, 23.55f, 5.1125f, 22.3375f, 5.1f);
		generalPath.curveTo(21.675f, 5.0875f, 21.0f, 5.075f, 20.5875f, 4.9125f);
		generalPath.curveTo(20.175f, 4.75f, 19.6375f, 4.2375f, 19.15f, 3.7625f);
		generalPath.curveTo(18.2875f, 2.9375f, 17.3f, 2.0f, 16.0f, 2.0f);
		generalPath.curveTo(14.7f, 2.0f, 13.7125f, 2.9375f, 12.85f, 3.7625f);
		generalPath.curveTo(12.3625f, 4.2375f, 11.85f, 4.725f, 11.4125f, 4.9125f);
		generalPath.curveTo(10.975f, 5.1f, 10.325f, 5.0875f, 9.6625f, 5.1f);
		generalPath.curveTo(8.45f, 5.1125f, 7.0625f, 5.1375f, 6.1f, 6.1f);
		generalPath.curveTo(5.1375f, 7.0625f, 5.1125f, 8.45f, 5.1f, 9.6625f);
		generalPath.curveTo(5.0875f, 10.325f, 5.075f, 11.0f, 4.9125f, 11.4125f);
		generalPath.curveTo(4.75f, 11.825f, 4.2375f, 12.3625f, 3.7625f, 12.85f);
		generalPath.curveTo(2.9375f, 13.7125f, 2.0f, 14.7f, 2.0f, 16.0f);
		generalPath.curveTo(2.0f, 17.3f, 2.9375f, 18.2875f, 3.7625f, 19.15f);
		generalPath.curveTo(4.2375f, 19.6375f, 4.725f, 20.15f, 4.9125f, 20.5875f);
		generalPath.curveTo(5.1f, 21.025f, 5.0875f, 21.675f, 5.1f, 22.3375f);
		generalPath.curveTo(5.1125f, 23.55f, 5.1375f, 24.9375f, 6.1f, 25.9f);
		generalPath.curveTo(7.0625f, 26.8625f, 8.45f, 26.8875f, 9.6625f, 26.9f);
		generalPath.curveTo(10.325f, 26.9125f, 11.0f, 26.925f, 11.4125f, 27.0875f);
		generalPath.curveTo(11.825f, 27.25f, 12.3625f, 27.7625f, 12.85f, 28.2375f);
		generalPath.curveTo(13.7125f, 29.0625f, 14.7f, 30.0f, 16.0f, 30.0f);
		generalPath.curveTo(17.3f, 30.0f, 18.2875f, 29.0625f, 19.15f, 28.2375f);
		generalPath.curveTo(19.6375f, 27.7625f, 20.15f, 27.275f, 20.5875f, 27.0875f);
		generalPath.curveTo(21.025f, 26.9f, 21.675f, 26.9125f, 22.3375f, 26.9f);
		generalPath.curveTo(23.55f, 26.8875f, 24.9375f, 26.8625f, 25.9f, 25.9f);
		generalPath.curveTo(26.8625f, 24.9375f, 26.8875f, 23.55f, 26.9f, 22.3375f);
		generalPath.curveTo(26.9125f, 21.675f, 26.925f, 21.0f, 27.0875f, 20.5875f);
		generalPath.curveTo(27.25f, 20.175f, 27.7625f, 19.6375f, 28.2375f, 19.15f);
		generalPath.curveTo(29.0625f, 18.2875f, 30.0f, 17.3f, 30.0f, 16.0f);
		generalPath.curveTo(30.0f, 14.7f, 29.0625f, 13.7125f, 28.2375f, 12.85f);
		generalPath.closePath();
		generalPath.moveTo(22.1875f, 13.725f);
		generalPath.lineTo(14.8625f, 20.725f);
		generalPath.curveTo(14.6731f, 20.9031f, 14.4225f, 21.0016f, 14.1625f, 21.0f);
		generalPath.curveTo(13.9063f, 21.001f, 13.6598f, 20.9024f, 13.475f, 20.725f);
		generalPath.lineTo(9.8125f, 17.225f);
		generalPath.curveTo(9.71092f, 17.1364f, 9.6283f, 17.0281f, 9.56962f, 16.9067f);
		generalPath.curveTo(9.51094f, 16.7854f, 9.47741f, 16.6534f, 9.47104f, 16.5187f);
		generalPath.curveTo(9.46466f, 16.3841f, 9.48559f, 16.2495f, 9.53255f, 16.1231f);
		generalPath.curveTo(9.5795f, 15.9968f, 9.65153f, 15.8812f, 9.74429f, 15.7834f);
		generalPath.curveTo(9.83706f, 15.6856f, 9.94864f, 15.6075f, 10.0723f, 15.5539f);
		generalPath.curveTo(10.196f, 15.5003f, 10.3293f, 15.4723f, 10.4641f, 15.4715f);
		generalPath.curveTo(10.5989f, 15.4707f, 10.7325f, 15.4972f, 10.8568f, 15.5493f);
		generalPath.curveTo(10.9811f, 15.6015f, 11.0936f, 15.6783f, 11.1875f, 15.775f);
		generalPath.lineTo(14.1625f, 18.6125f);
		generalPath.lineTo(20.8125f, 12.275f);
		generalPath.curveTo(21.0069f, 12.1054f, 21.2594f, 12.0174f, 21.5172f, 12.0296f);
		generalPath.curveTo(21.7749f, 12.0418f, 22.018f, 12.1532f, 22.1955f, 12.3404f);
		generalPath.curveTo(22.3731f, 12.5277f, 22.4714f, 12.7763f, 22.4699f, 13.0343f);
		generalPath.curveTo(22.4684f, 13.2924f, 22.3672f, 13.5398f, 22.1875f, 13.725f);
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

