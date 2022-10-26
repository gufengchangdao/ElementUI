package com.component.svg.empty;

import com.component.radiance.common.api.icon.AbstractRadianceIcon;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.radiance.common.api.icon.RadianceIconUIResource;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class EmptyImgGraySvg extends AbstractRadianceIcon {
	/**
	 * @see AbstractRadianceIcon#AbstractRadianceIcon()
	 */
	public EmptyImgGraySvg() {
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
		generalPath.moveTo(60.6033f, 99.4921f);
		generalPath.curveTo(85.237f, 99.4921f, 105.207f, 95.7607f, 105.207f, 91.1579f);
		generalPath.curveTo(105.207f, 86.555f, 85.237f, 82.8237f, 60.6033f, 82.8237f);
		generalPath.curveTo(35.9696f, 82.8237f, 16.0f, 86.555f, 16.0f, 91.1579f);
		generalPath.curveTo(16.0f, 95.7607f, 35.9696f, 99.4921f, 60.6033f, 99.4921f);
		generalPath.closePath();
		shape = generalPath;
		paint = (colorFilter != null) ? colorFilter.filter(new Color(245, 245, 247, 204)) : new Color(245, 245, 247, 204);
		g.setPaint(paint);
		g.fill(shape);
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0_1
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(96.2855f, 66.6737f);
		generalPath.lineTo(80.5454f, 47.302f);
		generalPath.curveTo(79.7901f, 46.3901f, 78.6862f, 45.8382f, 77.5237f, 45.8382f);
		generalPath.lineTo(43.6816f, 45.8382f);
		generalPath.curveTo(42.5197f, 45.8382f, 41.4158f, 46.3901f, 40.6605f, 47.302f);
		generalPath.lineTo(24.9211f, 66.6737f);
		generalPath.lineTo(24.9211f, 76.7941f);
		generalPath.lineTo(96.2862f, 76.7941f);
		generalPath.lineTo(96.2862f, 66.6737f);
		generalPath.lineTo(96.2855f, 66.6737f);
		generalPath.closePath();
		shape = generalPath;
		paint = (colorFilter != null) ? colorFilter.filter(new Color(174, 184, 194, 255)) : new Color(174, 184, 194, 255);
		g.setPaint(paint);
		g.fill(shape);
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0_2
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(38.2566f, 20.8355f);
		generalPath.lineTo(82.9493f, 20.8355f);
		generalPath.curveTo(83.6473f, 20.8355f, 84.3166f, 21.1128f, 84.8102f, 21.6063f);
		generalPath.curveTo(85.3037f, 22.0998f, 85.5809f, 22.7692f, 85.5809f, 23.4671f);
		generalPath.lineTo(85.5809f, 84.8776f);
		generalPath.curveTo(85.5809f, 85.5756f, 85.3037f, 86.2449f, 84.8102f, 86.7384f);
		generalPath.curveTo(84.3166f, 87.2319f, 83.6473f, 87.5092f, 82.9493f, 87.5092f);
		generalPath.lineTo(38.2566f, 87.5092f);
		generalPath.curveTo(37.5586f, 87.5092f, 36.8893f, 87.2319f, 36.3958f, 86.7384f);
		generalPath.curveTo(35.9023f, 86.2449f, 35.625f, 85.5756f, 35.625f, 84.8776f);
		generalPath.lineTo(35.625f, 23.4671f);
		generalPath.curveTo(35.625f, 22.7692f, 35.9023f, 22.0998f, 36.3958f, 21.6063f);
		generalPath.curveTo(36.8893f, 21.1128f, 37.5586f, 20.8355f, 38.2566f, 20.8355f);
		generalPath.lineTo(38.2566f, 20.8355f);
		generalPath.closePath();
		shape = generalPath;
		paint = (colorFilter != null) ? colorFilter.filter(new Color(245, 245, 247, 255)) : new Color(245, 245, 247, 255);
		g.setPaint(paint);
		g.fill(shape);
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0_3
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(44.0776f, 27.3835f);
		generalPath.lineTo(77.1283f, 27.3835f);
		generalPath.curveTo(77.4772f, 27.3835f, 77.8119f, 27.5222f, 78.0587f, 27.7689f);
		generalPath.curveTo(78.3054f, 28.0157f, 78.4441f, 28.3504f, 78.4441f, 28.6993f);
		generalPath.lineTo(78.4441f, 45.1184f);
		generalPath.curveTo(78.4441f, 45.4674f, 78.3054f, 45.8021f, 78.0587f, 46.0488f);
		generalPath.curveTo(77.8119f, 46.2956f, 77.4772f, 46.4342f, 77.1283f, 46.4342f);
		generalPath.lineTo(44.0776f, 46.4342f);
		generalPath.curveTo(43.7286f, 46.4342f, 43.394f, 46.2956f, 43.1472f, 46.0488f);
		generalPath.curveTo(42.9005f, 45.8021f, 42.7618f, 45.4674f, 42.7618f, 45.1184f);
		generalPath.lineTo(42.7618f, 28.6993f);
		generalPath.curveTo(42.7618f, 28.3504f, 42.9005f, 28.0157f, 43.1472f, 27.7689f);
		generalPath.curveTo(43.394f, 27.5222f, 43.7286f, 27.3835f, 44.0776f, 27.3835f);
		generalPath.lineTo(44.0776f, 27.3835f);
		generalPath.closePath();
		generalPath.moveTo(44.25f, 53.577f);
		generalPath.lineTo(76.9559f, 53.577f);
		generalPath.curveTo(77.3506f, 53.577f, 77.7291f, 53.7338f, 78.0082f, 54.0128f);
		generalPath.curveTo(78.2873f, 54.2919f, 78.4441f, 54.6704f, 78.4441f, 55.0651f);
		generalPath.curveTo(78.4441f, 55.4598f, 78.2873f, 55.8383f, 78.0082f, 56.1174f);
		generalPath.curveTo(77.7291f, 56.3965f, 77.3506f, 56.5533f, 76.9559f, 56.5533f);
		generalPath.lineTo(44.25f, 56.5533f);
		generalPath.curveTo(43.8553f, 56.5533f, 43.4768f, 56.3965f, 43.1977f, 56.1174f);
		generalPath.curveTo(42.9186f, 55.8383f, 42.7618f, 55.4598f, 42.7618f, 55.0651f);
		generalPath.curveTo(42.7618f, 54.6704f, 42.9186f, 54.2919f, 43.1977f, 54.0128f);
		generalPath.curveTo(43.4768f, 53.7338f, 43.8553f, 53.577f, 44.25f, 53.577f);
		generalPath.lineTo(44.25f, 53.577f);
		generalPath.closePath();
		generalPath.moveTo(44.25f, 61.3158f);
		generalPath.lineTo(76.9559f, 61.3158f);
		generalPath.curveTo(77.3507f, 61.3158f, 77.7293f, 61.4726f, 78.0084f, 61.7518f);
		generalPath.curveTo(78.2876f, 62.0309f, 78.4444f, 62.4095f, 78.4444f, 62.8043f);
		generalPath.curveTo(78.4444f, 63.199f, 78.2876f, 63.5776f, 78.0084f, 63.8568f);
		generalPath.curveTo(77.7293f, 64.1359f, 77.3507f, 64.2928f, 76.9559f, 64.2928f);
		generalPath.lineTo(44.25f, 64.2928f);
		generalPath.curveTo(43.8552f, 64.2928f, 43.4766f, 64.1359f, 43.1975f, 63.8568f);
		generalPath.curveTo(42.9183f, 63.5776f, 42.7615f, 63.199f, 42.7615f, 62.8043f);
		generalPath.curveTo(42.7615f, 62.4095f, 42.9183f, 62.0309f, 43.1975f, 61.7518f);
		generalPath.curveTo(43.4766f, 61.4726f, 43.8552f, 61.3158f, 44.25f, 61.3158f);
		generalPath.lineTo(44.25f, 61.3158f);
		generalPath.closePath();
		generalPath.moveTo(96.1401f, 89.9355f);
		generalPath.curveTo(95.6303f, 91.9559f, 93.8395f, 93.4618f, 91.7092f, 93.4618f);
		generalPath.lineTo(29.4967f, 93.4618f);
		generalPath.curveTo(27.3664f, 93.4618f, 25.5756f, 91.9553f, 25.0664f, 89.9355f);
		generalPath.curveTo(24.9693f, 89.5505f, 24.9203f, 89.155f, 24.9204f, 88.7579f);
		generalPath.lineTo(24.9204f, 66.6743f);
		generalPath.lineTo(42.2349f, 66.6743f);
		generalPath.curveTo(44.1474f, 66.6743f, 45.6888f, 68.2849f, 45.6888f, 70.2401f);
		generalPath.lineTo(45.6888f, 70.2664f);
		generalPath.curveTo(45.6888f, 72.221f, 47.248f, 73.7993f, 49.1605f, 73.7993f);
		generalPath.lineTo(72.0454f, 73.7993f);
		generalPath.curveTo(73.9579f, 73.7993f, 75.5171f, 72.2066f, 75.5171f, 70.2513f);
		generalPath.lineTo(75.5171f, 70.2434f);
		generalPath.curveTo(75.5171f, 68.2881f, 77.0585f, 66.6737f, 78.971f, 66.6737f);
		generalPath.lineTo(96.2855f, 66.6737f);
		generalPath.lineTo(96.2855f, 88.7585f);
		generalPath.curveTo(96.2855f, 89.1645f, 96.2349f, 89.5585f, 96.1401f, 89.9355f);
		generalPath.lineTo(96.1401f, 89.9355f);
		generalPath.closePath();
		shape = generalPath;
		paint = (colorFilter != null) ? colorFilter.filter(new Color(220, 224, 230, 255)) : new Color(220, 224, 230, 255);
		g.setPaint(paint);
		g.fill(shape);
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0_4
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(98.3165f, 21.9026f);
		generalPath.lineTo(93.823f, 23.6461f);
		generalPath.curveTo(93.7076f, 23.6909f, 93.5818f, 23.7023f, 93.4602f, 23.6789f);
		generalPath.curveTo(93.3386f, 23.6555f, 93.2261f, 23.5982f, 93.1356f, 23.5137f);
		generalPath.curveTo(93.045f, 23.4292f, 92.9802f, 23.3208f, 92.9486f, 23.2011f);
		generalPath.curveTo(92.9169f, 23.0813f, 92.9197f, 22.9551f, 92.9566f, 22.8368f);
		generalPath.lineTo(94.2309f, 18.7533f);
		generalPath.curveTo(92.5276f, 16.8164f, 91.5276f, 14.4546f, 91.5276f, 11.9059f);
		generalPath.curveTo(91.5276f, 5.33026f, 98.1842f, 0.0f, 106.396f, 0.0f);
		generalPath.curveTo(114.606f, 0.0f, 121.263f, 5.33026f, 121.263f, 11.9059f);
		generalPath.curveTo(121.263f, 18.4816f, 114.607f, 23.8118f, 106.395f, 23.8118f);
		generalPath.curveTo(103.416f, 23.8118f, 100.643f, 23.1105f, 98.3165f, 21.9026f);
		generalPath.closePath();
		shape = generalPath;
		paint = (colorFilter != null) ? colorFilter.filter(new Color(220, 224, 230, 255)) : new Color(220, 224, 230, 255);
		g.setPaint(paint);
		g.fill(shape);
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0_5
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(112.253f, 14.0559f);
		generalPath.curveTo(113.288f, 14.0559f, 114.127f, 13.2268f, 114.127f, 12.204f);
		generalPath.curveTo(114.127f, 11.1811f, 113.288f, 10.352f, 112.253f, 10.352f);
		generalPath.curveTo(111.217f, 10.352f, 110.378f, 11.1811f, 110.378f, 12.204f);
		generalPath.curveTo(110.378f, 13.2268f, 111.217f, 14.0559f, 112.253f, 14.0559f);
		generalPath.closePath();
		shape = generalPath;
		paint = (colorFilter != null) ? colorFilter.filter(new Color(255, 255, 255, 255)) : new Color(255, 255, 255, 255);
		g.setPaint(paint);
		g.fill(shape);
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0_6
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(102.413f, 13.8244f);
		generalPath.lineTo(98.6644f, 13.8244f);
		generalPath.lineTo(100.571f, 10.5836f);
		generalPath.lineTo(102.413f, 13.8244f);
		generalPath.closePath();
		generalPath.moveTo(104.756f, 10.5836f);
		generalPath.lineTo(108.035f, 10.5836f);
		generalPath.lineTo(108.035f, 13.8244f);
		generalPath.lineTo(104.756f, 13.8244f);
		generalPath.lineTo(104.756f, 10.5836f);
		generalPath.closePath();
		shape = generalPath;
		paint = (colorFilter != null) ? colorFilter.filter(new Color(255, 255, 255, 255)) : new Color(255, 255, 255, 255);
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
		return 16.0;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigY()
	 */
	public static double getOrigY() {
		return 0.0;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigWidth()
	 */
	public static double getOrigWidth() {
		return 105.26300048828125;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigHeight()
	 */
	public static double getOrigHeight() {
		return 99.49210357666016;
	}

	@Override
	public boolean supportsColorFilter() {
		return true;
	}

	/**
	 * @see AbstractRadianceIcon#of(int, int)
	 */
	public static RadianceIcon of(int width, int height) {
		EmptyImgGraySvg base = new EmptyImgGraySvg();
		base.width = width;
		base.height = height;
		return base;
	}

	/**
	 * @see AbstractRadianceIcon#uiResourceOf(int, int)
	 */
	public static RadianceIconUIResource uiResourceOf(int width, int height) {
		EmptyImgGraySvg base = new EmptyImgGraySvg();
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
		return EmptyImgGraySvg::new;
	}
}

