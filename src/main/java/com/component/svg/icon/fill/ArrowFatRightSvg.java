package com.component.svg.icon.fill;

import com.component.radiance.common.api.icon.AbstractRadianceIcon;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.radiance.common.api.icon.RadianceIconUIResource;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class ArrowFatRightSvg extends AbstractRadianceIcon {
	/**
	 * @see AbstractRadianceIcon#AbstractRadianceIcon()
	 */
	public ArrowFatRightSvg() {
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
		generalPath.moveTo(16.6125f, 28.925f);
		generalPath.curveTo(16.4305f, 28.8485f, 16.2752f, 28.7198f, 16.1662f, 28.5552f);
		generalPath.curveTo(16.0572f, 28.3906f, 15.9994f, 28.1974f, 16.0f, 28.0f);
		generalPath.lineTo(16.0f, 23.0f);
		generalPath.lineTo(6.0f, 23.0f);
		generalPath.curveTo(5.46957f, 23.0f, 4.96086f, 22.7893f, 4.58579f, 22.4142f);
		generalPath.curveTo(4.21071f, 22.0392f, 4.0f, 21.5304f, 4.0f, 21.0f);
		generalPath.lineTo(4.0f, 11.0f);
		generalPath.curveTo(4.0f, 10.4696f, 4.21071f, 9.96087f, 4.58579f, 9.5858f);
		generalPath.curveTo(4.96086f, 9.21073f, 5.46957f, 9.00001f, 6.0f, 9.00001f);
		generalPath.lineTo(16.0f, 9.00001f);
		generalPath.lineTo(16.0f, 4.00001f);
		generalPath.curveTo(15.9994f, 3.80259f, 16.0572f, 3.6094f, 16.1662f, 3.44479f);
		generalPath.curveTo(16.2752f, 3.28018f, 16.4305f, 3.15151f, 16.6125f, 3.07501f);
		generalPath.curveTo(16.7972f, 3.00297f, 16.9985f, 2.98469f, 17.1932f, 3.0223f);
		generalPath.curveTo(17.3879f, 3.0599f, 17.5679f, 3.15185f, 17.7125f, 3.28751f);
		generalPath.lineTo(29.7125f, 15.2875f);
		generalPath.curveTo(29.9003f, 15.4771f, 30.0056f, 15.7332f, 30.0056f, 16.0f);
		generalPath.curveTo(30.0056f, 16.2669f, 29.9003f, 16.5229f, 29.7125f, 16.7125f);
		generalPath.lineTo(17.7125f, 28.7125f);
		generalPath.curveTo(17.5679f, 28.8482f, 17.3879f, 28.9401f, 17.1932f, 28.9777f);
		generalPath.curveTo(16.9985f, 29.0153f, 16.7972f, 28.9971f, 16.6125f, 28.925f);
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
		return 4.0;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigY()
	 */
	public static double getOrigY() {
		return 2.984689950942993;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigWidth()
	 */
	public static double getOrigWidth() {
		return 26.005599975585938;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigHeight()
	 */
	public static double getOrigHeight() {
		return 26.030611038208008;
	}

	@Override
	public boolean supportsColorFilter() {
		return true;
	}

	/**
	 * @see AbstractRadianceIcon#of(int, int)
	 */
	public static RadianceIcon of(int width, int height) {
		ArrowFatRightSvg base = new ArrowFatRightSvg();
		base.width = width;
		base.height = height;
		return base;
	}

	/**
	 * @see AbstractRadianceIcon#uiResourceOf(int, int)
	 */
	public static RadianceIconUIResource uiResourceOf(int width, int height) {
		ArrowFatRightSvg base = new ArrowFatRightSvg();
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
		return ArrowFatRightSvg::new;
	}
}

