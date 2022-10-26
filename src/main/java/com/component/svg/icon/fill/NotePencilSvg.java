package com.component.svg.icon.fill;

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
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(28.0f, 15.0f);
		generalPath.lineTo(28.0f, 26.0f);
		generalPath.curveTo(28.0f, 26.5304f, 27.7893f, 27.0392f, 27.4142f, 27.4142f);
		generalPath.curveTo(27.0391f, 27.7893f, 26.5304f, 28.0f, 26.0f, 28.0f);
		generalPath.lineTo(6.0f, 28.0f);
		generalPath.curveTo(5.46957f, 28.0f, 4.96086f, 27.7893f, 4.58579f, 27.4142f);
		generalPath.curveTo(4.21071f, 27.0392f, 4.0f, 26.5304f, 4.0f, 26.0f);
		generalPath.lineTo(4.0f, 6.00001f);
		generalPath.curveTo(4.0f, 5.46958f, 4.21071f, 4.96087f, 4.58579f, 4.5858f);
		generalPath.curveTo(4.96086f, 4.21072f, 5.46957f, 4.00001f, 6.0f, 4.00001f);
		generalPath.lineTo(17.0f, 4.00001f);
		generalPath.curveTo(17.2652f, 4.00001f, 17.5196f, 4.10537f, 17.7071f, 4.2929f);
		generalPath.curveTo(17.8946f, 4.48044f, 18.0f, 4.73479f, 18.0f, 5.00001f);
		generalPath.curveTo(18.0f, 5.26523f, 17.8946f, 5.51958f, 17.7071f, 5.70712f);
		generalPath.curveTo(17.5196f, 5.89465f, 17.2652f, 6.00001f, 17.0f, 6.00001f);
		generalPath.lineTo(6.0f, 6.00001f);
		generalPath.lineTo(6.0f, 26.0f);
		generalPath.lineTo(26.0f, 26.0f);
		generalPath.lineTo(26.0f, 15.0f);
		generalPath.curveTo(26.0f, 14.7348f, 26.1054f, 14.4804f, 26.2929f, 14.2929f);
		generalPath.curveTo(26.4804f, 14.1054f, 26.7348f, 14.0f, 27.0f, 14.0f);
		generalPath.curveTo(27.2652f, 14.0f, 27.5196f, 14.1054f, 27.7071f, 14.2929f);
		generalPath.curveTo(27.8946f, 14.4804f, 28.0f, 14.7348f, 28.0f, 15.0f);
		generalPath.closePath();
		generalPath.moveTo(28.7125f, 8.71251f);
		generalPath.lineTo(16.7125f, 20.7125f);
		generalPath.curveTo(16.522f, 20.8982f, 16.266f, 21.0015f, 16.0f, 21.0f);
		generalPath.lineTo(12.0f, 21.0f);
		generalPath.curveTo(11.7348f, 21.0f, 11.4804f, 20.8947f, 11.2929f, 20.7071f);
		generalPath.curveTo(11.1054f, 20.5196f, 11.0f, 20.2652f, 11.0f, 20.0f);
		generalPath.lineTo(11.0f, 16.0f);
		generalPath.curveTo(10.9985f, 15.734f, 11.1018f, 15.478f, 11.2875f, 15.2875f);
		generalPath.lineTo(23.2875f, 3.28751f);
		generalPath.curveTo(23.4771f, 3.09973f, 23.7332f, 2.99438f, 24.0f, 2.99438f);
		generalPath.curveTo(24.2668f, 2.99438f, 24.5229f, 3.09973f, 24.7125f, 3.28751f);
		generalPath.lineTo(28.7125f, 7.28751f);
		generalPath.curveTo(28.9003f, 7.4771f, 29.0056f, 7.73316f, 29.0056f, 8.00001f);
		generalPath.curveTo(29.0056f, 8.26686f, 28.9003f, 8.52292f, 28.7125f, 8.71251f);
		generalPath.closePath();
		generalPath.moveTo(26.5875f, 8.00001f);
		generalPath.lineTo(24.0f, 5.41251f);
		generalPath.lineTo(22.4125f, 7.00001f);
		generalPath.lineTo(25.0f, 9.58751f);
		generalPath.lineTo(26.5875f, 8.00001f);
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
		return 2.994379997253418;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigWidth()
	 */
	public static double getOrigWidth() {
		return 25.005599975585938;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigHeight()
	 */
	public static double getOrigHeight() {
		return 25.005619049072266;
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

