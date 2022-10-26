package com.component.svg.icon.regular;

import com.component.radiance.common.api.icon.AbstractRadianceIcon;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.radiance.common.api.icon.RadianceIconUIResource;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class SwordSvg extends AbstractRadianceIcon {
	/**
	 * @see AbstractRadianceIcon#AbstractRadianceIcon()
	 */
	public SwordSvg() {
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
		generalPath.moveTo(9.52502f, 16.525f);
		generalPath.lineTo(19.025f, 5.025f);
		generalPath.lineTo(27.0f, 5.0f);
		generalPath.lineTo(26.975f, 12.975f);
		generalPath.lineTo(15.475f, 22.475f);
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
		generalPath.moveTo(12.5f, 19.5f);
		generalPath.lineTo(20.0f, 12.0f);
		shape = generalPath;
		g.setPaint(paint);
		g.setStroke(stroke);
		g.draw(shape);
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0_2
		paint = (colorFilter != null) ? colorFilter.filter(new Color(0, 0, 0, 255)) : new Color(0, 0, 0, 255);
		stroke = new BasicStroke(2.0f, 1, 1, 4.0f, null, 0.0f);
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(10.2625f, 24.6875f);
		generalPath.lineTo(6.525f, 28.425f);
		generalPath.curveTo(6.33753f, 28.612f, 6.08354f, 28.717f, 5.81875f, 28.717f);
		generalPath.curveTo(5.55395f, 28.717f, 5.29996f, 28.612f, 5.1125f, 28.425f);
		generalPath.lineTo(3.575f, 26.8875f);
		generalPath.curveTo(3.38798f, 26.7f, 3.28296f, 26.4461f, 3.28296f, 26.1813f);
		generalPath.curveTo(3.28296f, 25.9165f, 3.38798f, 25.6625f, 3.575f, 25.475f);
		generalPath.lineTo(7.3125f, 21.7375f);
		generalPath.curveTo(7.40698f, 21.6445f, 7.48202f, 21.5336f, 7.53323f, 21.4112f);
		generalPath.curveTo(7.58445f, 21.2889f, 7.61082f, 21.1576f, 7.61082f, 21.025f);
		generalPath.curveTo(7.61082f, 20.8924f, 7.58445f, 20.7611f, 7.53323f, 20.6388f);
		generalPath.curveTo(7.48202f, 20.5165f, 7.40698f, 20.4056f, 7.3125f, 20.3125f);
		generalPath.lineTo(4.7125f, 17.7125f);
		generalPath.curveTo(4.61801f, 17.6195f, 4.54298f, 17.5086f, 4.49176f, 17.3862f);
		generalPath.curveTo(4.44054f, 17.2639f, 4.41417f, 17.1326f, 4.41417f, 17.0f);
		generalPath.curveTo(4.41417f, 16.8674f, 4.44054f, 16.7361f, 4.49176f, 16.6138f);
		generalPath.curveTo(4.54298f, 16.4915f, 4.61801f, 16.3806f, 4.7125f, 16.2875f);
		generalPath.lineTo(6.2875f, 14.7125f);
		generalPath.curveTo(6.38055f, 14.618f, 6.49146f, 14.543f, 6.61378f, 14.4918f);
		generalPath.curveTo(6.7361f, 14.4406f, 6.86739f, 14.4142f, 7.0f, 14.4142f);
		generalPath.curveTo(7.13261f, 14.4142f, 7.26389f, 14.4406f, 7.38621f, 14.4918f);
		generalPath.curveTo(7.50854f, 14.543f, 7.61945f, 14.618f, 7.7125f, 14.7125f);
		generalPath.lineTo(17.2875f, 24.2875f);
		generalPath.curveTo(17.382f, 24.3806f, 17.457f, 24.4915f, 17.5082f, 24.6138f);
		generalPath.curveTo(17.5594f, 24.7361f, 17.5858f, 24.8674f, 17.5858f, 25.0f);
		generalPath.curveTo(17.5858f, 25.1326f, 17.5594f, 25.2639f, 17.5082f, 25.3862f);
		generalPath.curveTo(17.457f, 25.5086f, 17.382f, 25.6195f, 17.2875f, 25.7125f);
		generalPath.lineTo(15.7125f, 27.2875f);
		generalPath.curveTo(15.6194f, 27.382f, 15.5085f, 27.457f, 15.3862f, 27.5082f);
		generalPath.curveTo(15.2639f, 27.5595f, 15.1326f, 27.5858f, 15.0f, 27.5858f);
		generalPath.curveTo(14.8674f, 27.5858f, 14.7361f, 27.5595f, 14.6138f, 27.5082f);
		generalPath.curveTo(14.4915f, 27.457f, 14.3805f, 27.382f, 14.2875f, 27.2875f);
		generalPath.lineTo(11.6875f, 24.6875f);
		generalPath.curveTo(11.5944f, 24.593f, 11.4835f, 24.518f, 11.3612f, 24.4668f);
		generalPath.curveTo(11.2389f, 24.4156f, 11.1076f, 24.3892f, 10.975f, 24.3892f);
		generalPath.curveTo(10.8424f, 24.3892f, 10.7111f, 24.4156f, 10.5888f, 24.4668f);
		generalPath.curveTo(10.4665f, 24.518f, 10.3555f, 24.593f, 10.2625f, 24.6875f);
		generalPath.closePath();
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
		return 2.2829599380493164;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigY()
	 */
	public static double getOrigY() {
		return 3.9991700649261475;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigWidth()
	 */
	public static double getOrigWidth() {
		return 25.717870712280273;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigHeight()
	 */
	public static double getOrigHeight() {
		return 25.71782875061035;
	}

	@Override
	public boolean supportsColorFilter() {
		return true;
	}

	/**
	 * @see AbstractRadianceIcon#of(int, int)
	 */
	public static RadianceIcon of(int width, int height) {
		SwordSvg base = new SwordSvg();
		base.width = width;
		base.height = height;
		return base;
	}

	/**
	 * @see AbstractRadianceIcon#uiResourceOf(int, int)
	 */
	public static RadianceIconUIResource uiResourceOf(int width, int height) {
		SwordSvg base = new SwordSvg();
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
		return SwordSvg::new;
	}
}

