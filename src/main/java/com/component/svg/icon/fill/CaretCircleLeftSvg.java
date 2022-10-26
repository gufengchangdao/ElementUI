package com.component.svg.icon.fill;

import com.component.radiance.common.api.icon.AbstractRadianceIcon;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.radiance.common.api.icon.RadianceIconUIResource;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class CaretCircleLeftSvg extends AbstractRadianceIcon {
	/**
	 * @see AbstractRadianceIcon#AbstractRadianceIcon()
	 */
	public CaretCircleLeftSvg() {
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
		generalPath.moveTo(18.675f, 19.7625f);
		generalPath.curveTo(18.8705f, 19.9416f, 18.987f, 20.1909f, 18.9987f, 20.4558f);
		generalPath.curveTo(19.0104f, 20.7207f, 18.9165f, 20.9794f, 18.7375f, 21.175f);
		generalPath.curveTo(18.6443f, 21.278f, 18.5305f, 21.3602f, 18.4034f, 21.4162f);
		generalPath.curveTo(18.2763f, 21.4722f, 18.1389f, 21.5007f, 18.0f, 21.5f);
		generalPath.curveTo(17.7506f, 21.4976f, 17.5106f, 21.4042f, 17.325f, 21.2375f);
		generalPath.lineTo(12.325f, 16.7375f);
		generalPath.curveTo(12.2227f, 16.6438f, 12.141f, 16.5299f, 12.0851f, 16.4029f);
		generalPath.curveTo(12.0292f, 16.2759f, 12.0004f, 16.1387f, 12.0004f, 16.0f);
		generalPath.curveTo(12.0004f, 15.8613f, 12.0292f, 15.7241f, 12.0851f, 15.5971f);
		generalPath.curveTo(12.141f, 15.4701f, 12.2227f, 15.3562f, 12.325f, 15.2625f);
		generalPath.lineTo(17.325f, 10.7625f);
		generalPath.curveTo(17.4213f, 10.6705f, 17.5349f, 10.5986f, 17.6593f, 10.5512f);
		generalPath.curveTo(17.7837f, 10.5037f, 17.9163f, 10.4815f, 18.0494f, 10.4859f);
		generalPath.curveTo(18.1824f, 10.4904f, 18.3133f, 10.5214f, 18.4342f, 10.577f);
		generalPath.curveTo(18.5552f, 10.6327f, 18.6638f, 10.712f, 18.7537f, 10.8102f);
		generalPath.curveTo(18.8435f, 10.9084f, 18.9129f, 11.0236f, 18.9577f, 11.149f);
		generalPath.curveTo(19.0025f, 11.2744f, 19.0218f, 11.4075f, 19.0145f, 11.5404f);
		generalPath.curveTo(19.0071f, 11.6733f, 18.9733f, 11.8035f, 18.915f, 11.9232f);
		generalPath.curveTo(18.8568f, 12.0429f, 18.7751f, 12.1498f, 18.675f, 12.2375f);
		generalPath.lineTo(14.5f, 16.0f);
		generalPath.lineTo(18.675f, 19.7625f);
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
		CaretCircleLeftSvg base = new CaretCircleLeftSvg();
		base.width = width;
		base.height = height;
		return base;
	}

	/**
	 * @see AbstractRadianceIcon#uiResourceOf(int, int)
	 */
	public static RadianceIconUIResource uiResourceOf(int width, int height) {
		CaretCircleLeftSvg base = new CaretCircleLeftSvg();
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
		return CaretCircleLeftSvg::new;
	}
}

