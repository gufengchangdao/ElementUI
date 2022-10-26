package com.component.svg.icon.regular;

import com.component.radiance.common.api.icon.AbstractRadianceIcon;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.radiance.common.api.icon.RadianceIconUIResource;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class NotePencilSvg extends AbstractRadianceIcon {
	/**
	 * @see AbstractRadianceIcon#AbstractRadianceIcon()
	 */
	public NotePencilSvg() {
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
		generalPath.moveTo(16.0f, 20.0f);
		generalPath.lineTo(12.0f, 20.0f);
		generalPath.lineTo(12.0f, 16.0f);
		generalPath.lineTo(24.0f, 4.0f);
		generalPath.lineTo(28.0f, 8.0f);
		generalPath.lineTo(16.0f, 20.0f);
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
		generalPath.moveTo(21.0f, 7.0f);
		generalPath.lineTo(25.0f, 11.0f);
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
		generalPath.moveTo(27.0f, 15.0f);
		generalPath.lineTo(27.0f, 26.0f);
		generalPath.curveTo(27.0f, 26.2652f, 26.8946f, 26.5196f, 26.7071f, 26.7071f);
		generalPath.curveTo(26.5196f, 26.8946f, 26.2652f, 27.0f, 26.0f, 27.0f);
		generalPath.lineTo(6.0f, 27.0f);
		generalPath.curveTo(5.73478f, 27.0f, 5.48043f, 26.8946f, 5.29289f, 26.7071f);
		generalPath.curveTo(5.10536f, 26.5196f, 5.0f, 26.2652f, 5.0f, 26.0f);
		generalPath.lineTo(5.0f, 6.0f);
		generalPath.curveTo(5.0f, 5.73478f, 5.10536f, 5.48043f, 5.29289f, 5.29289f);
		generalPath.curveTo(5.48043f, 5.10536f, 5.73478f, 5.0f, 6.0f, 5.0f);
		generalPath.lineTo(17.0f, 5.0f);
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
		return 4.0;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigY()
	 */
	public static double getOrigY() {
		return 2.9023690223693848;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigWidth()
	 */
	public static double getOrigWidth() {
		return 25.097631454467773;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigHeight()
	 */
	public static double getOrigHeight() {
		return 25.097631454467773;
	}

	@Override
	public boolean supportsColorFilter() {
		return true;
	}

	/**
	 * @see AbstractRadianceIcon#of(int, int)
	 */
	public static RadianceIcon of(int width, int height) {
		NotePencilSvg base = new NotePencilSvg();
		base.width = width;
		base.height = height;
		return base;
	}

	/**
	 * @see AbstractRadianceIcon#uiResourceOf(int, int)
	 */
	public static RadianceIconUIResource uiResourceOf(int width, int height) {
		NotePencilSvg base = new NotePencilSvg();
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
		return NotePencilSvg::new;
	}
}

