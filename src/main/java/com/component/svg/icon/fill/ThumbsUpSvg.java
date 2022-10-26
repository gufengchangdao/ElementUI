package com.component.svg.icon.fill;

import com.component.radiance.common.api.icon.AbstractRadianceIcon;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.radiance.common.api.icon.RadianceIconUIResource;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class ThumbsUpSvg extends AbstractRadianceIcon {
	/**
	 * @see AbstractRadianceIcon#AbstractRadianceIcon()
	 */
	public ThumbsUpSvg() {
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
		generalPath.moveTo(28.9875f, 10.0125f);
		generalPath.curveTo(28.7037f, 9.69632f, 28.3569f, 9.44293f, 27.9695f, 9.26858f);
		generalPath.curveTo(27.582f, 9.09422f, 27.1624f, 9.00274f, 26.7375f, 9.0f);
		generalPath.lineTo(20.0f, 9.0f);
		generalPath.lineTo(20.0f, 7.0f);
		generalPath.curveTo(19.9967f, 5.67493f, 19.4689f, 4.40508f, 18.5319f, 3.46811f);
		generalPath.curveTo(17.5949f, 2.53114f, 16.3251f, 2.0033f, 15.0f, 2.0f);
		generalPath.curveTo(14.8142f, 2.00151f, 14.6324f, 2.05348f, 14.4738f, 2.15035f);
		generalPath.curveTo(14.3153f, 2.24723f, 14.1861f, 2.38537f, 14.1f, 2.55f);
		generalPath.lineTo(9.375f, 12.0f);
		generalPath.lineTo(4.0f, 12.0f);
		generalPath.curveTo(3.46957f, 12.0f, 2.96086f, 12.2107f, 2.58579f, 12.5858f);
		generalPath.curveTo(2.21071f, 12.9609f, 2.0f, 13.4696f, 2.0f, 14.0f);
		generalPath.lineTo(2.0f, 25.0f);
		generalPath.curveTo(2.0f, 25.5304f, 2.21071f, 26.0391f, 2.58579f, 26.4142f);
		generalPath.curveTo(2.96086f, 26.7893f, 3.46957f, 27.0f, 4.0f, 27.0f);
		generalPath.lineTo(25.2375f, 27.0f);
		generalPath.curveTo(25.9672f, 26.997f, 26.671f, 26.7292f, 27.2182f, 26.2464f);
		generalPath.curveTo(27.7653f, 25.7636f, 28.1186f, 25.0986f, 28.2125f, 24.375f);
		generalPath.lineTo(29.7125f, 12.375f);
		generalPath.curveTo(29.7624f, 11.9525f, 29.7237f, 11.5241f, 29.5989f, 11.1174f);
		generalPath.curveTo(29.4741f, 10.7106f, 29.2658f, 10.3343f, 28.9875f, 10.0125f);
		generalPath.closePath();
		generalPath.moveTo(4.0f, 14.0f);
		generalPath.lineTo(9.0f, 14.0f);
		generalPath.lineTo(9.0f, 25.0f);
		generalPath.lineTo(4.0f, 25.0f);
		generalPath.lineTo(4.0f, 14.0f);
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
		return 27.762399673461914;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigHeight()
	 */
	public static double getOrigHeight() {
		return 25.0;
	}

	@Override
	public boolean supportsColorFilter() {
		return true;
	}

	/**
	 * @see AbstractRadianceIcon#of(int, int)
	 */
	public static RadianceIcon of(int width, int height) {
		ThumbsUpSvg base = new ThumbsUpSvg();
		base.width = width;
		base.height = height;
		return base;
	}

	/**
	 * @see AbstractRadianceIcon#uiResourceOf(int, int)
	 */
	public static RadianceIconUIResource uiResourceOf(int width, int height) {
		ThumbsUpSvg base = new ThumbsUpSvg();
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
		return ThumbsUpSvg::new;
	}
}

