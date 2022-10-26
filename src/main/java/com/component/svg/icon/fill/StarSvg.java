package com.component.svg.icon.fill;

import com.component.radiance.common.api.icon.AbstractRadianceIcon;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.radiance.common.api.icon.RadianceIconUIResource;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class StarSvg extends AbstractRadianceIcon {
	/**
	 * @see AbstractRadianceIcon#AbstractRadianceIcon()
	 */
	public StarSvg() {
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
		generalPath.moveTo(29.9f, 12.175f);
		generalPath.curveTo(29.7749f, 11.7812f, 29.5337f, 11.4345f, 29.208f, 11.1802f);
		generalPath.curveTo(28.8823f, 10.9259f, 28.4874f, 10.7759f, 28.075f, 10.75f);
		generalPath.lineTo(20.65f, 10.2375f);
		generalPath.lineTo(17.9f, 3.3f);
		generalPath.curveTo(17.7499f, 2.918f, 17.4885f, 2.58988f, 17.1498f, 2.3581f);
		generalPath.curveTo(16.811f, 2.12633f, 16.4105f, 2.00158f, 16.0f, 2.0f);
		generalPath.curveTo(15.5896f, 2.00158f, 15.1891f, 2.12633f, 14.8503f, 2.3581f);
		generalPath.curveTo(14.5116f, 2.58988f, 14.2502f, 2.918f, 14.1f, 3.3f);
		generalPath.lineTo(11.3f, 10.275f);
		generalPath.lineTo(3.92504f, 10.75f);
		generalPath.curveTo(3.51318f, 10.7776f, 3.11907f, 10.9282f, 2.79371f, 11.1822f);
		generalPath.curveTo(2.46835f, 11.4363f, 2.22671f, 11.7821f, 2.10004f, 12.175f);
		generalPath.curveTo(1.96997f, 12.5739f, 1.96237f, 13.0026f, 2.07823f, 13.4059f);
		generalPath.curveTo(2.19408f, 13.8092f, 2.42808f, 14.1685f, 2.75005f, 14.4375f);
		generalPath.lineTo(8.42505f, 19.2375f);
		generalPath.lineTo(6.73755f, 25.875f);
		generalPath.curveTo(6.62079f, 26.324f, 6.6418f, 26.7978f, 6.79785f, 27.2347f);
		generalPath.curveTo(6.95389f, 27.6716f, 7.23776f, 28.0515f, 7.61255f, 28.325f);
		generalPath.curveTo(7.97633f, 28.5861f, 8.40976f, 28.7327f, 8.85734f, 28.7461f);
		generalPath.curveTo(9.30493f, 28.7594f, 9.74633f, 28.6389f, 10.125f, 28.4f);
		generalPath.lineTo(15.9875f, 24.6875f);
		generalPath.lineTo(16.0125f, 24.6875f);
		generalPath.lineTo(22.325f, 28.675f);
		generalPath.curveTo(22.6489f, 28.8854f, 23.0264f, 28.9983f, 23.4125f, 29.0f);
		generalPath.curveTo(23.7279f, 28.9975f, 24.0384f, 28.9228f, 24.3203f, 28.7815f);
		generalPath.curveTo(24.6022f, 28.6403f, 24.848f, 28.4362f, 25.0387f, 28.1851f);
		generalPath.curveTo(25.2295f, 27.9341f, 25.3601f, 27.6426f, 25.4205f, 27.3331f);
		generalPath.curveTo(25.481f, 27.0236f, 25.4697f, 26.7044f, 25.3875f, 26.4f);
		generalPath.lineTo(23.6f, 19.1375f);
		generalPath.lineTo(29.25f, 14.4375f);
		generalPath.curveTo(29.572f, 14.1685f, 29.806f, 13.8092f, 29.9219f, 13.4059f);
		generalPath.curveTo(30.0377f, 13.0026f, 30.0301f, 12.5739f, 29.9f, 12.175f);
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
		return 1.9623700380325317;
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
		return 28.07533073425293;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigHeight()
	 */
	public static double getOrigHeight() {
		return 27.0;
	}

	@Override
	public boolean supportsColorFilter() {
		return true;
	}

	/**
	 * @see AbstractRadianceIcon#of(int, int)
	 */
	public static RadianceIcon of(int width, int height) {
		StarSvg base = new StarSvg();
		base.width = width;
		base.height = height;
		return base;
	}

	/**
	 * @see AbstractRadianceIcon#uiResourceOf(int, int)
	 */
	public static RadianceIconUIResource uiResourceOf(int width, int height) {
		StarSvg base = new StarSvg();
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
		return StarSvg::new;
	}
}

