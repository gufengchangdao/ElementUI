package com.component.svg.icon.fill;

import com.component.radiance.common.api.icon.AbstractRadianceIcon;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.radiance.common.api.icon.RadianceIconUIResource;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class CrosshairSvg extends AbstractRadianceIcon {
	/**
	 * @see AbstractRadianceIcon#AbstractRadianceIcon()
	 */
	public CrosshairSvg() {
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
		generalPath.moveTo(29.5f, 15.0f);
		generalPath.lineTo(27.9625f, 15.0f);
		generalPath.curveTo(27.722f, 12.1732f, 26.4898f, 9.52237f, 24.4837f, 7.51627f);
		generalPath.curveTo(22.4776f, 5.51017f, 19.8268f, 4.27797f, 17.0f, 4.0375f);
		generalPath.lineTo(17.0f, 2.5f);
		generalPath.curveTo(17.0f, 2.23478f, 16.8946f, 1.98043f, 16.7071f, 1.79289f);
		generalPath.curveTo(16.5196f, 1.60536f, 16.2652f, 1.5f, 16.0f, 1.5f);
		generalPath.curveTo(15.7348f, 1.5f, 15.4804f, 1.60536f, 15.2929f, 1.79289f);
		generalPath.curveTo(15.1054f, 1.98043f, 15.0f, 2.23478f, 15.0f, 2.5f);
		generalPath.lineTo(15.0f, 4.0375f);
		generalPath.curveTo(12.1732f, 4.27797f, 9.52237f, 5.51017f, 7.51627f, 7.51627f);
		generalPath.curveTo(5.51017f, 9.52237f, 4.27797f, 12.1732f, 4.0375f, 15.0f);
		generalPath.lineTo(2.5f, 15.0f);
		generalPath.curveTo(2.23478f, 15.0f, 1.98043f, 15.1054f, 1.79289f, 15.2929f);
		generalPath.curveTo(1.60536f, 15.4804f, 1.5f, 15.7348f, 1.5f, 16.0f);
		generalPath.curveTo(1.5f, 16.2652f, 1.60536f, 16.5196f, 1.79289f, 16.7071f);
		generalPath.curveTo(1.98043f, 16.8946f, 2.23478f, 17.0f, 2.5f, 17.0f);
		generalPath.lineTo(4.0375f, 17.0f);
		generalPath.curveTo(4.27797f, 19.8268f, 5.51017f, 22.4776f, 7.51627f, 24.4837f);
		generalPath.curveTo(9.52237f, 26.4898f, 12.1732f, 27.722f, 15.0f, 27.9625f);
		generalPath.lineTo(15.0f, 29.5f);
		generalPath.curveTo(15.0f, 29.7652f, 15.1054f, 30.0196f, 15.2929f, 30.2071f);
		generalPath.curveTo(15.4804f, 30.3946f, 15.7348f, 30.5f, 16.0f, 30.5f);
		generalPath.curveTo(16.2652f, 30.5f, 16.5196f, 30.3946f, 16.7071f, 30.2071f);
		generalPath.curveTo(16.8946f, 30.0196f, 17.0f, 29.7652f, 17.0f, 29.5f);
		generalPath.lineTo(17.0f, 27.9625f);
		generalPath.curveTo(19.8268f, 27.722f, 22.4776f, 26.4898f, 24.4837f, 24.4837f);
		generalPath.curveTo(26.4898f, 22.4776f, 27.722f, 19.8268f, 27.9625f, 17.0f);
		generalPath.lineTo(29.5f, 17.0f);
		generalPath.curveTo(29.7652f, 17.0f, 30.0196f, 16.8946f, 30.2071f, 16.7071f);
		generalPath.curveTo(30.3946f, 16.5196f, 30.5f, 16.2652f, 30.5f, 16.0f);
		generalPath.curveTo(30.5f, 15.7348f, 30.3946f, 15.4804f, 30.2071f, 15.2929f);
		generalPath.curveTo(30.0196f, 15.1054f, 29.7652f, 15.0f, 29.5f, 15.0f);
		generalPath.closePath();
		generalPath.moveTo(24.5f, 17.0f);
		generalPath.lineTo(25.95f, 17.0f);
		generalPath.curveTo(25.7125f, 19.2926f, 24.6933f, 21.4337f, 23.0635f, 23.0635f);
		generalPath.curveTo(21.4337f, 24.6933f, 19.2926f, 25.7125f, 17.0f, 25.95f);
		generalPath.lineTo(17.0f, 24.5f);
		generalPath.curveTo(17.0f, 24.2348f, 16.8946f, 23.9804f, 16.7071f, 23.7929f);
		generalPath.curveTo(16.5196f, 23.6054f, 16.2652f, 23.5f, 16.0f, 23.5f);
		generalPath.curveTo(15.7348f, 23.5f, 15.4804f, 23.6054f, 15.2929f, 23.7929f);
		generalPath.curveTo(15.1054f, 23.9804f, 15.0f, 24.2348f, 15.0f, 24.5f);
		generalPath.lineTo(15.0f, 25.95f);
		generalPath.curveTo(12.7074f, 25.7125f, 10.5663f, 24.6933f, 8.93651f, 23.0635f);
		generalPath.curveTo(7.3067f, 21.4337f, 6.28746f, 19.2926f, 6.05f, 17.0f);
		generalPath.lineTo(7.5f, 17.0f);
		generalPath.curveTo(7.76522f, 17.0f, 8.01957f, 16.8946f, 8.20711f, 16.7071f);
		generalPath.curveTo(8.39464f, 16.5196f, 8.5f, 16.2652f, 8.5f, 16.0f);
		generalPath.curveTo(8.5f, 15.7348f, 8.39464f, 15.4804f, 8.20711f, 15.2929f);
		generalPath.curveTo(8.01957f, 15.1054f, 7.76522f, 15.0f, 7.5f, 15.0f);
		generalPath.lineTo(6.05f, 15.0f);
		generalPath.curveTo(6.28746f, 12.7074f, 7.3067f, 10.5663f, 8.93651f, 8.93651f);
		generalPath.curveTo(10.5663f, 7.3067f, 12.7074f, 6.28746f, 15.0f, 6.05f);
		generalPath.lineTo(15.0f, 7.5f);
		generalPath.curveTo(15.0f, 7.76522f, 15.1054f, 8.01957f, 15.2929f, 8.20711f);
		generalPath.curveTo(15.4804f, 8.39464f, 15.7348f, 8.5f, 16.0f, 8.5f);
		generalPath.curveTo(16.2652f, 8.5f, 16.5196f, 8.39464f, 16.7071f, 8.20711f);
		generalPath.curveTo(16.8946f, 8.01957f, 17.0f, 7.76522f, 17.0f, 7.5f);
		generalPath.lineTo(17.0f, 6.05f);
		generalPath.curveTo(19.2926f, 6.28746f, 21.4337f, 7.3067f, 23.0635f, 8.93651f);
		generalPath.curveTo(24.6933f, 10.5663f, 25.7125f, 12.7074f, 25.95f, 15.0f);
		generalPath.lineTo(24.5f, 15.0f);
		generalPath.curveTo(24.2348f, 15.0f, 23.9804f, 15.1054f, 23.7929f, 15.2929f);
		generalPath.curveTo(23.6054f, 15.4804f, 23.5f, 15.7348f, 23.5f, 16.0f);
		generalPath.curveTo(23.5f, 16.2652f, 23.6054f, 16.5196f, 23.7929f, 16.7071f);
		generalPath.curveTo(23.9804f, 16.8946f, 24.2348f, 17.0f, 24.5f, 17.0f);
		generalPath.closePath();
		shape = generalPath;
		paint = (colorFilter != null) ? colorFilter.filter(new Color(0, 0, 0, 255)) : new Color(0, 0, 0, 255);
		g.setPaint(paint);
		g.fill(shape);
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0_1
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(16.0f, 21.0f);
		generalPath.curveTo(18.7614f, 21.0f, 21.0f, 18.7614f, 21.0f, 16.0f);
		generalPath.curveTo(21.0f, 13.2386f, 18.7614f, 11.0f, 16.0f, 11.0f);
		generalPath.curveTo(13.2386f, 11.0f, 11.0f, 13.2386f, 11.0f, 16.0f);
		generalPath.curveTo(11.0f, 18.7614f, 13.2386f, 21.0f, 16.0f, 21.0f);
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
		return 1.5;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigY()
	 */
	public static double getOrigY() {
		return 1.5;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigWidth()
	 */
	public static double getOrigWidth() {
		return 29.0;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigHeight()
	 */
	public static double getOrigHeight() {
		return 29.0;
	}

	@Override
	public boolean supportsColorFilter() {
		return true;
	}

	/**
	 * @see AbstractRadianceIcon#of(int, int)
	 */
	public static RadianceIcon of(int width, int height) {
		CrosshairSvg base = new CrosshairSvg();
		base.width = width;
		base.height = height;
		return base;
	}

	/**
	 * @see AbstractRadianceIcon#uiResourceOf(int, int)
	 */
	public static RadianceIconUIResource uiResourceOf(int width, int height) {
		CrosshairSvg base = new CrosshairSvg();
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
		return CrosshairSvg::new;
	}
}

