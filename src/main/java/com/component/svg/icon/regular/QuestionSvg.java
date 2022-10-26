package com.component.svg.icon.regular;

import com.component.radiance.common.api.icon.AbstractRadianceIcon;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.radiance.common.api.icon.RadianceIconUIResource;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class QuestionSvg extends AbstractRadianceIcon {
	/**
	 * @see AbstractRadianceIcon#AbstractRadianceIcon()
	 */
	public QuestionSvg() {
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
		generalPath.moveTo(16.0f, 28.0f);
		generalPath.curveTo(22.6274f, 28.0f, 28.0f, 22.6274f, 28.0f, 16.0f);
		generalPath.curveTo(28.0f, 9.37258f, 22.6274f, 4.0f, 16.0f, 4.0f);
		generalPath.curveTo(9.37258f, 4.0f, 4.0f, 9.37258f, 4.0f, 16.0f);
		generalPath.curveTo(4.0f, 22.6274f, 9.37258f, 28.0f, 16.0f, 28.0f);
		generalPath.closePath();
		shape = generalPath;
		g.setPaint(paint);
		g.setStroke(stroke);
		g.draw(shape);
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0_1
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(16.0f, 24.0f);
		generalPath.curveTo(16.8284f, 24.0f, 17.5f, 23.3284f, 17.5f, 22.5f);
		generalPath.curveTo(17.5f, 21.6716f, 16.8284f, 21.0f, 16.0f, 21.0f);
		generalPath.curveTo(15.1716f, 21.0f, 14.5f, 21.6716f, 14.5f, 22.5f);
		generalPath.curveTo(14.5f, 23.3284f, 15.1716f, 24.0f, 16.0f, 24.0f);
		generalPath.closePath();
		shape = generalPath;
		paint = (colorFilter != null) ? colorFilter.filter(new Color(0, 0, 0, 255)) : new Color(0, 0, 0, 255);
		g.setPaint(paint);
		g.fill(shape);
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0_2
		paint = (colorFilter != null) ? colorFilter.filter(new Color(0, 0, 0, 255)) : new Color(0, 0, 0, 255);
		stroke = new BasicStroke(2.0f, 1, 1, 4.0f, null, 0.0f);
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(16.0f, 18.0f);
		generalPath.lineTo(16.0f, 17.0f);
		generalPath.curveTo(16.6922f, 17.0f, 17.3689f, 16.7947f, 17.9445f, 16.4101f);
		generalPath.curveTo(18.5201f, 16.0256f, 18.9687f, 15.4789f, 19.2336f, 14.8394f);
		generalPath.curveTo(19.4985f, 14.1999f, 19.5678f, 13.4961f, 19.4327f, 12.8172f);
		generalPath.curveTo(19.2977f, 12.1383f, 18.9644f, 11.5146f, 18.4749f, 11.0251f);
		generalPath.curveTo(17.9854f, 10.5356f, 17.3617f, 10.2023f, 16.6828f, 10.0673f);
		generalPath.curveTo(16.0039f, 9.9322f, 15.3001f, 10.0015f, 14.6606f, 10.2664f);
		generalPath.curveTo(14.0211f, 10.5313f, 13.4744f, 10.9799f, 13.0899f, 11.5555f);
		generalPath.curveTo(12.7053f, 12.1311f, 12.5f, 12.8078f, 12.5f, 13.5f);
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
		return 3.0;
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
		return 26.0;
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
		QuestionSvg base = new QuestionSvg();
		base.width = width;
		base.height = height;
		return base;
	}

	/**
	 * @see AbstractRadianceIcon#uiResourceOf(int, int)
	 */
	public static RadianceIconUIResource uiResourceOf(int width, int height) {
		QuestionSvg base = new QuestionSvg();
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
		return QuestionSvg::new;
	}
}

