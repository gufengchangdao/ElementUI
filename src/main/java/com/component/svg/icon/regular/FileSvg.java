package com.component.svg.icon.regular;

import com.component.radiance.common.api.icon.AbstractRadianceIcon;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.radiance.common.api.icon.RadianceIconUIResource;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class FileSvg extends AbstractRadianceIcon {
	/**
	 * @see AbstractRadianceIcon#AbstractRadianceIcon()
	 */
	public FileSvg() {
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
		generalPath.moveTo(25.0f, 28.0f);
		generalPath.lineTo(7.0f, 28.0f);
		generalPath.curveTo(6.73478f, 28.0f, 6.48043f, 27.8946f, 6.29289f, 27.7071f);
		generalPath.curveTo(6.10536f, 27.5196f, 6.0f, 27.2652f, 6.0f, 27.0f);
		generalPath.lineTo(6.0f, 5.0f);
		generalPath.curveTo(6.0f, 4.73478f, 6.10536f, 4.48043f, 6.29289f, 4.29289f);
		generalPath.curveTo(6.48043f, 4.10536f, 6.73478f, 4.0f, 7.0f, 4.0f);
		generalPath.lineTo(19.0f, 4.0f);
		generalPath.lineTo(26.0f, 11.0f);
		generalPath.lineTo(26.0f, 27.0f);
		generalPath.curveTo(26.0f, 27.2652f, 25.8946f, 27.5196f, 25.7071f, 27.7071f);
		generalPath.curveTo(25.5196f, 27.8946f, 25.2652f, 28.0f, 25.0f, 28.0f);
		generalPath.closePath();
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
		generalPath.moveTo(19.0f, 4.0f);
		generalPath.lineTo(19.0f, 11.0f);
		generalPath.lineTo(26.0f, 11.0f);
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
		return 5.0;
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
		return 22.0;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigHeight()
	 */
	public static double getOrigHeight() {
		return 26.0;
	}

	@Override
	public boolean supportsColorFilter() {
		return true;
	}

	/**
	 * @see AbstractRadianceIcon#of(int, int)
	 */
	public static RadianceIcon of(int width, int height) {
		FileSvg base = new FileSvg();
		base.width = width;
		base.height = height;
		return base;
	}

	/**
	 * @see AbstractRadianceIcon#uiResourceOf(int, int)
	 */
	public static RadianceIconUIResource uiResourceOf(int width, int height) {
		FileSvg base = new FileSvg();
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
		return FileSvg::new;
	}
}

