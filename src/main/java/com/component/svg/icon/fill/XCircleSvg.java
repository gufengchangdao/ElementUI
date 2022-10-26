package com.component.svg.icon.fill;

import com.component.radiance.common.api.icon.AbstractRadianceIcon;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.radiance.common.api.icon.RadianceIconUIResource;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class XCircleSvg extends AbstractRadianceIcon {
	/**
	 * @see AbstractRadianceIcon#AbstractRadianceIcon()
	 */
	public XCircleSvg() {
		this.width = (int) getOrigWidth();
		this.height = (int) getOrigHeight();
	}


	private void _paint0(Graphics2D g, float origAlpha) {
// 
		g.setComposite(AlphaComposite.getInstance(3, origAlpha));
// _0
		g.setComposite(AlphaComposite.getInstance(3, origAlpha));
// _0_0
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(16.0f, 3.0f);
		generalPath.curveTo(13.4288f, 3.0f, 10.9154f, 3.76244f, 8.77759f, 5.1909f);
		generalPath.curveTo(6.63975f, 6.61935f, 4.97351f, 8.64968f, 3.98957f, 11.0251f);
		generalPath.curveTo(3.00563f, 13.4006f, 2.74819f, 16.0144f, 3.2498f, 18.5362f);
		generalPath.curveTo(3.75141f, 21.0579f, 4.98953f, 23.3743f, 6.80762f, 25.1924f);
		generalPath.curveTo(8.6257f, 27.0105f, 10.9421f, 28.2486f, 13.4638f, 28.7502f);
		generalPath.curveTo(15.9856f, 29.2518f, 18.5995f, 28.9944f, 20.9749f, 28.0104f);
		generalPath.curveTo(23.3503f, 27.0265f, 25.3807f, 25.3603f, 26.8091f, 23.2224f);
		generalPath.curveTo(28.2376f, 21.0846f, 29.0f, 18.5712f, 29.0f, 16.0f);
		generalPath.curveTo(28.9934f, 12.5542f, 27.6216f, 9.25145f, 25.1851f, 6.81491f);
		generalPath.curveTo(22.7486f, 4.37837f, 19.4458f, 3.00661f, 16.0f, 3.0f);
		generalPath.closePath();
		generalPath.moveTo(20.7125f, 19.2875f);
		generalPath.curveTo(20.9003f, 19.4771f, 21.0056f, 19.7332f, 21.0056f, 20.0f);
		generalPath.curveTo(21.0056f, 20.2668f, 20.9003f, 20.5229f, 20.7125f, 20.7125f);
		generalPath.curveTo(20.5214f, 20.8973f, 20.2659f, 21.0006f, 20.0f, 21.0006f);
		generalPath.curveTo(19.7341f, 21.0006f, 19.4786f, 20.8973f, 19.2875f, 20.7125f);
		generalPath.lineTo(16.0f, 17.4125f);
		generalPath.lineTo(12.7125f, 20.7125f);
		generalPath.curveTo(12.5214f, 20.8973f, 12.2659f, 21.0006f, 12.0f, 21.0006f);
		generalPath.curveTo(11.7341f, 21.0006f, 11.4786f, 20.8973f, 11.2875f, 20.7125f);
		generalPath.curveTo(11.0997f, 20.5229f, 10.9944f, 20.2668f, 10.9944f, 20.0f);
		generalPath.curveTo(10.9944f, 19.7332f, 11.0997f, 19.4771f, 11.2875f, 19.2875f);
		generalPath.lineTo(14.5875f, 16.0f);
		generalPath.lineTo(11.2875f, 12.7125f);
		generalPath.curveTo(11.128f, 12.5182f, 11.0466f, 12.2715f, 11.0589f, 12.0205f);
		generalPath.curveTo(11.0712f, 11.7694f, 11.1765f, 11.5319f, 11.3542f, 11.3542f);
		generalPath.curveTo(11.532f, 11.1765f, 11.7694f, 11.0712f, 12.0205f, 11.0589f);
		generalPath.curveTo(12.2715f, 11.0465f, 12.5182f, 11.128f, 12.7125f, 11.2875f);
		generalPath.lineTo(16.0f, 14.5875f);
		generalPath.lineTo(19.2875f, 11.2875f);
		generalPath.curveTo(19.4818f, 11.128f, 19.7285f, 11.0465f, 19.9795f, 11.0589f);
		generalPath.curveTo(20.2306f, 11.0712f, 20.4681f, 11.1765f, 20.6458f, 11.3542f);
		generalPath.curveTo(20.8235f, 11.5319f, 20.9288f, 11.7694f, 20.9411f, 12.0205f);
		generalPath.curveTo(20.9535f, 12.2715f, 20.872f, 12.5182f, 20.7125f, 12.7125f);
		generalPath.lineTo(17.4125f, 16.0f);
		generalPath.lineTo(20.7125f, 19.2875f);
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
		return 2.748189926147461;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigY()
	 */
	public static double getOrigY() {
		return 3.0;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigWidth()
	 */
	public static double getOrigWidth() {
		return 26.25181007385254;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigHeight()
	 */
	public static double getOrigHeight() {
		return 26.251800537109375;
	}

	@Override
	public boolean supportsColorFilter() {
		return true;
	}

	/**
	 * @see AbstractRadianceIcon#of(int, int)
	 */
	public static RadianceIcon of(int width, int height) {
		XCircleSvg base = new XCircleSvg();
		base.width = width;
		base.height = height;
		return base;
	}

	/**
	 * @see AbstractRadianceIcon#uiResourceOf(int, int)
	 */
	public static RadianceIconUIResource uiResourceOf(int width, int height) {
		XCircleSvg base = new XCircleSvg();
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
		return XCircleSvg::new;
	}
}

