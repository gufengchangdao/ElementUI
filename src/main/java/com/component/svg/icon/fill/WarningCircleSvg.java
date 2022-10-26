package com.component.svg.icon.fill;

import com.component.radiance.common.api.icon.AbstractRadianceIcon;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.radiance.common.api.icon.RadianceIconUIResource;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class WarningCircleSvg extends AbstractRadianceIcon {
	/**
	 * @see AbstractRadianceIcon#AbstractRadianceIcon()
	 */
	public WarningCircleSvg() {
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
		generalPath.moveTo(15.0f, 10.0f);
		generalPath.curveTo(15.0f, 9.73478f, 15.1054f, 9.48043f, 15.2929f, 9.29289f);
		generalPath.curveTo(15.4804f, 9.10536f, 15.7348f, 9.0f, 16.0f, 9.0f);
		generalPath.curveTo(16.2652f, 9.0f, 16.5196f, 9.10536f, 16.7071f, 9.29289f);
		generalPath.curveTo(16.8946f, 9.48043f, 17.0f, 9.73478f, 17.0f, 10.0f);
		generalPath.lineTo(17.0f, 17.0f);
		generalPath.curveTo(17.0f, 17.2652f, 16.8946f, 17.5196f, 16.7071f, 17.7071f);
		generalPath.curveTo(16.5196f, 17.8946f, 16.2652f, 18.0f, 16.0f, 18.0f);
		generalPath.curveTo(15.7348f, 18.0f, 15.4804f, 17.8946f, 15.2929f, 17.7071f);
		generalPath.curveTo(15.1054f, 17.5196f, 15.0f, 17.2652f, 15.0f, 17.0f);
		generalPath.lineTo(15.0f, 10.0f);
		generalPath.closePath();
		generalPath.moveTo(16.0f, 23.0f);
		generalPath.curveTo(15.7033f, 23.0f, 15.4133f, 22.912f, 15.1667f, 22.7472f);
		generalPath.curveTo(14.92f, 22.5824f, 14.7277f, 22.3481f, 14.6142f, 22.074f);
		generalPath.curveTo(14.5007f, 21.7999f, 14.471f, 21.4983f, 14.5288f, 21.2074f);
		generalPath.curveTo(14.5867f, 20.9164f, 14.7296f, 20.6491f, 14.9393f, 20.4393f);
		generalPath.curveTo(15.1491f, 20.2296f, 15.4164f, 20.0867f, 15.7074f, 20.0288f);
		generalPath.curveTo(15.9983f, 19.9709f, 16.2999f, 20.0007f, 16.574f, 20.1142f);
		generalPath.curveTo(16.8481f, 20.2277f, 17.0824f, 20.42f, 17.2472f, 20.6666f);
		generalPath.curveTo(17.412f, 20.9133f, 17.5f, 21.2033f, 17.5f, 21.5f);
		generalPath.curveTo(17.5f, 21.8978f, 17.342f, 22.2794f, 17.0607f, 22.5607f);
		generalPath.curveTo(16.7794f, 22.842f, 16.3978f, 23.0f, 16.0f, 23.0f);
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
		WarningCircleSvg base = new WarningCircleSvg();
		base.width = width;
		base.height = height;
		return base;
	}

	/**
	 * @see AbstractRadianceIcon#uiResourceOf(int, int)
	 */
	public static RadianceIconUIResource uiResourceOf(int width, int height) {
		WarningCircleSvg base = new WarningCircleSvg();
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
		return WarningCircleSvg::new;
	}
}

