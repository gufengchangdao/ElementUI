package com.component.svg.empty;

import com.component.radiance.common.api.icon.AbstractRadianceIcon;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.radiance.common.api.icon.RadianceIconUIResource;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class EmptyImgSimpleSvg extends AbstractRadianceIcon {
	/**
	 * @see AbstractRadianceIcon#AbstractRadianceIcon()
	 */
	public EmptyImgSimpleSvg() {
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
		generalPath.moveTo(61.0f, 57.7031f);
		generalPath.curveTo(78.6731f, 57.7031f, 93.0f, 54.5923f, 93.0f, 50.755f);
		generalPath.curveTo(93.0f, 46.9177f, 78.6731f, 43.807f, 61.0f, 43.807f);
		generalPath.curveTo(43.3269f, 43.807f, 29.0f, 46.9177f, 29.0f, 50.755f);
		generalPath.curveTo(29.0f, 54.5923f, 43.3269f, 57.7031f, 61.0f, 57.7031f);
		generalPath.closePath();
		shape = generalPath;
		paint = (colorFilter != null) ? colorFilter.filter(new Color(245, 245, 245, 255)) : new Color(245, 245, 245, 255);
		g.setPaint(paint);
		g.fill(shape);
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0_1
		paint = (colorFilter != null) ? colorFilter.filter(new Color(217, 217, 217, 255)) : new Color(217, 217, 217, 255);
		stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(84.0f, 31.6653f);
		generalPath.lineTo(73.854f, 20.2487f);
		generalPath.curveTo(73.367f, 19.4705f, 72.656f, 19.0f, 71.907f, 19.0f);
		generalPath.lineTo(50.093f, 19.0f);
		generalPath.curveTo(49.344f, 19.0f, 48.633f, 19.4705f, 48.146f, 20.2477f);
		generalPath.lineTo(38.0f, 31.6663f);
		generalPath.lineTo(38.0f, 40.8367f);
		generalPath.lineTo(84.0f, 40.8367f);
		generalPath.lineTo(84.0f, 31.6653f);
		generalPath.closePath();
		shape = generalPath;
		g.setPaint(paint);
		g.setStroke(stroke);
		g.draw(shape);
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0_2
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(70.613f, 34.8127f);
		generalPath.curveTo(70.613f, 33.2197f, 71.607f, 31.9045f, 72.84f, 31.9035f);
		generalPath.lineTo(84.0f, 31.9035f);
		generalPath.lineTo(84.0f, 49.9059f);
		generalPath.curveTo(84.0f, 52.0131f, 82.68f, 53.7402f, 81.05f, 53.7402f);
		generalPath.lineTo(40.95f, 53.7402f);
		generalPath.curveTo(39.32f, 53.7402f, 38.0f, 52.0121f, 38.0f, 49.9059f);
		generalPath.lineTo(38.0f, 31.9035f);
		generalPath.lineTo(49.16f, 31.9035f);
		generalPath.curveTo(50.393f, 31.9035f, 51.387f, 33.2167f, 51.387f, 34.8098f);
		generalPath.lineTo(51.387f, 34.8316f);
		generalPath.curveTo(51.387f, 36.4247f, 52.392f, 37.7111f, 53.624f, 37.7111f);
		generalPath.lineTo(68.376f, 37.7111f);
		generalPath.curveTo(69.608f, 37.7111f, 70.613f, 36.4128f, 70.613f, 34.8197f);
		generalPath.lineTo(70.613f, 34.8127f);
		generalPath.lineTo(70.613f, 34.8127f);
		generalPath.closePath();
		shape = generalPath;
		paint = (colorFilter != null) ? colorFilter.filter(new Color(250, 250, 250, 255)) : new Color(250, 250, 250, 255);
		g.setPaint(paint);
		g.fill(shape);
		paint = (colorFilter != null) ? colorFilter.filter(new Color(217, 217, 217, 255)) : new Color(217, 217, 217, 255);
		stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(70.613f, 34.8127f);
		generalPath.curveTo(70.613f, 33.2197f, 71.607f, 31.9045f, 72.84f, 31.9035f);
		generalPath.lineTo(84.0f, 31.9035f);
		generalPath.lineTo(84.0f, 49.9059f);
		generalPath.curveTo(84.0f, 52.0131f, 82.68f, 53.7402f, 81.05f, 53.7402f);
		generalPath.lineTo(40.95f, 53.7402f);
		generalPath.curveTo(39.32f, 53.7402f, 38.0f, 52.0121f, 38.0f, 49.9059f);
		generalPath.lineTo(38.0f, 31.9035f);
		generalPath.lineTo(49.16f, 31.9035f);
		generalPath.curveTo(50.393f, 31.9035f, 51.387f, 33.2167f, 51.387f, 34.8098f);
		generalPath.lineTo(51.387f, 34.8316f);
		generalPath.curveTo(51.387f, 36.4247f, 52.392f, 37.7111f, 53.624f, 37.7111f);
		generalPath.lineTo(68.376f, 37.7111f);
		generalPath.curveTo(69.608f, 37.7111f, 70.613f, 36.4128f, 70.613f, 34.8197f);
		generalPath.lineTo(70.613f, 34.8127f);
		generalPath.lineTo(70.613f, 34.8127f);
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
		return 29.0;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigY()
	 */
	public static double getOrigY() {
		return 18.5;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigWidth()
	 */
	public static double getOrigWidth() {
		return 64.0;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigHeight()
	 */
	public static double getOrigHeight() {
		return 39.20309829711914;
	}

	@Override
	public boolean supportsColorFilter() {
		return true;
	}

	/**
	 * @see AbstractRadianceIcon#of(int, int)
	 */
	public static RadianceIcon of(int width, int height) {
		EmptyImgSimpleSvg base = new EmptyImgSimpleSvg();
		base.width = width;
		base.height = height;
		return base;
	}

	/**
	 * @see AbstractRadianceIcon#uiResourceOf(int, int)
	 */
	public static RadianceIconUIResource uiResourceOf(int width, int height) {
		EmptyImgSimpleSvg base = new EmptyImgSimpleSvg();
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
		return EmptyImgSimpleSvg::new;
	}
}

