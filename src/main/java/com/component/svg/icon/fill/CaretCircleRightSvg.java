package com.component.svg.icon.fill;

import com.component.radiance.common.api.icon.AbstractRadianceIcon;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.radiance.common.api.icon.RadianceIconUIResource;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class CaretCircleRightSvg extends AbstractRadianceIcon {
	/**
	 * @see AbstractRadianceIcon#AbstractRadianceIcon()
	 */
	public CaretCircleRightSvg() {
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
		generalPath.moveTo(16.0f, 3.0f);
		generalPath.curveTo(13.4288f, 3.0f, 10.9154f, 3.76244f, 8.77759f, 5.1909f);
		generalPath.curveTo(6.63975f, 6.61935f, 4.97351f, 8.64968f, 3.98957f, 11.0251f);
		generalPath.curveTo(3.00563f, 13.4006f, 2.74819f, 16.0144f, 3.2498f, 18.5362f);
		generalPath.curveTo(3.75141f, 21.0579f, 4.98953f, 23.3743f, 6.80762f, 25.1924f);
		generalPath.curveTo(8.6257f, 27.0105f, 10.9421f, 28.2486f, 13.4638f, 28.7502f);
		generalPath.curveTo(15.9856f, 29.2518f, 18.5995f, 28.9944f, 20.9749f, 28.0104f);
		generalPath.curveTo(23.3503f, 27.0265f, 25.3807f, 25.3603f, 26.8091f, 23.2224f);
		generalPath.curveTo(28.2376f, 21.0846f, 29.0f, 18.5712f, 29.0f, 16.0f);
		generalPath.curveTo(28.9934f, 12.5542f, 27.6216f, 9.25145f, 25.1851f, 6.81491f);
		generalPath.curveTo(22.7486f, 4.37837f, 19.4458f, 3.00661f, 16.0f, 3.0f);
		generalPath.closePath();
		generalPath.moveTo(20.175f, 16.7375f);
		generalPath.lineTo(15.175f, 21.2375f);
		generalPath.curveTo(14.9894f, 21.4042f, 14.7495f, 21.4976f, 14.5f, 21.5f);
		generalPath.curveTo(14.3611f, 21.5007f, 14.2237f, 21.4722f, 14.0966f, 21.4162f);
		generalPath.curveTo(13.9695f, 21.3602f, 13.8557f, 21.278f, 13.7625f, 21.175f);
		generalPath.curveTo(13.5835f, 20.9794f, 13.4896f, 20.7207f, 13.5013f, 20.4558f);
		generalPath.curveTo(13.513f, 20.1909f, 13.6295f, 19.9416f, 13.825f, 19.7625f);
		generalPath.lineTo(18.0f, 16.0f);
		generalPath.lineTo(13.825f, 12.2375f);
		generalPath.curveTo(13.636f, 12.0568f, 13.5253f, 11.8093f, 13.5165f, 11.548f);
		generalPath.curveTo(13.5078f, 11.2867f, 13.6018f, 11.0323f, 13.7783f, 10.8395f);
		generalPath.curveTo(13.9548f, 10.6466f, 14.1999f, 10.5305f, 14.461f, 10.5161f);
		generalPath.curveTo(14.722f, 10.5017f, 14.9784f, 10.5902f, 15.175f, 10.7625f);
		generalPath.lineTo(20.175f, 15.2625f);
		generalPath.curveTo(20.2773f, 15.3562f, 20.359f, 15.4701f, 20.4149f, 15.5971f);
		generalPath.curveTo(20.4708f, 15.7241f, 20.4997f, 15.8613f, 20.4997f, 16.0f);
		generalPath.curveTo(20.4997f, 16.1387f, 20.4708f, 16.2759f, 20.4149f, 16.4029f);
		generalPath.curveTo(20.359f, 16.5299f, 20.2773f, 16.6438f, 20.175f, 16.7375f);
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
		return 2.748189926147461;
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
		return 26.25181007385254;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigHeight()
	 */
	public static double getOrigHeight() {
		return 26.251800537109375;
	}

	@Override
	public boolean supportsColorFilter() {
		return true;
	}

	/**
	 * @see AbstractRadianceIcon#of(int, int)
	 */
	public static RadianceIcon of(int width, int height) {
		CaretCircleRightSvg base = new CaretCircleRightSvg();
		base.width = width;
		base.height = height;
		return base;
	}

	/**
	 * @see AbstractRadianceIcon#uiResourceOf(int, int)
	 */
	public static RadianceIconUIResource uiResourceOf(int width, int height) {
		CaretCircleRightSvg base = new CaretCircleRightSvg();
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
		return CaretCircleRightSvg::new;
	}
}

