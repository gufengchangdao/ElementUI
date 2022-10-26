package com.component.svg.icon.fill;

import com.component.radiance.common.api.icon.AbstractRadianceIcon;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.radiance.common.api.icon.RadianceIconUIResource;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class CheckCircleSvg extends AbstractRadianceIcon {
	/**
	 * @see AbstractRadianceIcon#AbstractRadianceIcon()
	 */
	public CheckCircleSvg() {
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
		generalPath.moveTo(22.1875f, 13.725f);
		generalPath.lineTo(14.8625f, 20.725f);
		generalPath.curveTo(14.6731f, 20.9031f, 14.4225f, 21.0016f, 14.1625f, 21.0f);
		generalPath.curveTo(14.0354f, 21.0018f, 13.9093f, 20.9785f, 13.7913f, 20.9313f);
		generalPath.curveTo(13.6733f, 20.8841f, 13.5658f, 20.814f, 13.475f, 20.725f);
		generalPath.lineTo(9.81251f, 17.225f);
		generalPath.curveTo(9.71092f, 17.1364f, 9.62831f, 17.0281f, 9.56963f, 16.9067f);
		generalPath.curveTo(9.51095f, 16.7854f, 9.47741f, 16.6534f, 9.47104f, 16.5187f);
		generalPath.curveTo(9.46467f, 16.3841f, 9.48559f, 16.2495f, 9.53255f, 16.1231f);
		generalPath.curveTo(9.57951f, 15.9968f, 9.65154f, 15.8812f, 9.7443f, 15.7834f);
		generalPath.curveTo(9.83706f, 15.6856f, 9.94865f, 15.6075f, 10.0723f, 15.5539f);
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
		CheckCircleSvg base = new CheckCircleSvg();
		base.width = width;
		base.height = height;
		return base;
	}

	/**
	 * @see AbstractRadianceIcon#uiResourceOf(int, int)
	 */
	public static RadianceIconUIResource uiResourceOf(int width, int height) {
		CheckCircleSvg base = new CheckCircleSvg();
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
		return CheckCircleSvg::new;
	}
}

