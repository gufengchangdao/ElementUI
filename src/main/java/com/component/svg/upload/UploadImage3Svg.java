package com.component.svg.upload;

import com.component.radiance.common.api.icon.AbstractRadianceIcon;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.radiance.common.api.icon.RadianceIconUIResource;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

public class UploadImage3Svg extends AbstractRadianceIcon {
	/**
	 * @see AbstractRadianceIcon#AbstractRadianceIcon()
	 */
	public UploadImage3Svg() {
		this.width = (int) getOrigWidth();
		this.height = (int) getOrigHeight();
	}


	private void _paint0(Graphics2D g, float origAlpha) {
// 
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0_0
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0_0_0
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(3.6739403E-16f, 152.0f);
		generalPath.curveTo(0.0f, 153.5913f, 0.63214105f, 155.11742f, 1.7573593f, 156.24265f);
		generalPath.curveTo(2.8825777f, 157.36786f, 4.408701f, 158.0f, 6.0f, 158.0f);
		generalPath.lineTo(152.0f, 158.0f);
		generalPath.curveTo(155.3137f, 158.0f, 158.0f, 155.3137f, 158.0f, 152.0f);
		generalPath.lineTo(158.0f, 6.0f);
		generalPath.curveTo(158.0f, 4.408701f, 157.36786f, 2.8825777f, 156.24265f, 1.7573593f);
		generalPath.curveTo(155.11742f, 0.63214105f, 153.5913f, -8.881784E-16f, 152.0f, -8.881784E-16f);
		generalPath.lineTo(6.0f, 0.0f);
		generalPath.curveTo(4.408701f, 0.0f, 2.8825777f, 0.63214105f, 1.7573593f, 1.7573593f);
		generalPath.curveTo(0.63214105f, 2.8825777f, 0.0f, 4.408701f, 0.0f, 6.0f);
		generalPath.closePath();
		shape = generalPath;
		paint = (colorFilter != null) ? colorFilter.filter(new Color(255, 255, 255, 255)) : new Color(255, 255, 255, 255);
		g.setPaint(paint);
		g.fill(shape);
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0_0_1
		paint = (colorFilter != null) ? colorFilter.filter(new Color(221, 221, 221, 255)) : new Color(221, 221, 221, 255);
		stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(3.6739403E-16f, 152.0f);
		generalPath.curveTo(0.0f, 153.5913f, 0.63214105f, 155.11742f, 1.7573593f, 156.24265f);
		generalPath.curveTo(2.8825777f, 157.36786f, 4.408701f, 158.0f, 6.0f, 158.0f);
		generalPath.lineTo(152.0f, 158.0f);
		generalPath.curveTo(155.3137f, 158.0f, 158.0f, 155.3137f, 158.0f, 152.0f);
		generalPath.lineTo(158.0f, 6.0f);
		generalPath.curveTo(158.0f, 4.408701f, 157.36786f, 2.8825777f, 156.24265f, 1.7573593f);
		generalPath.curveTo(155.11742f, 0.63214105f, 153.5913f, -8.881784E-16f, 152.0f, -8.881784E-16f);
		generalPath.lineTo(6.0f, 0.0f);
		generalPath.curveTo(4.408701f, 0.0f, 2.8825777f, 0.63214105f, 1.7573593f, 1.7573593f);
		generalPath.curveTo(0.63214105f, 2.8825777f, 0.0f, 4.408701f, 0.0f, 6.0f);
		generalPath.closePath();
		shape = generalPath;
		g.setPaint(paint);
		g.setStroke(stroke);
		g.draw(shape);
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
		transformsStack.push(g.getTransform());
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 47.0f, 48.0f));
// _0_0_2
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
		transformsStack.push(g.getTransform());
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 4.0f));
// _0_0_2_0
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(37.6038f, 26.315f);
		generalPath.curveTo(36.0838f, 24.805f, 34.0838f, 23.975f, 31.9438f, 23.975f);
		generalPath.curveTo(29.8038f, 23.975f, 27.8038f, 24.805f, 26.2838f, 26.315f);
		generalPath.curveTo(24.7738f, 27.835f, 23.9438f, 29.835f, 23.9438f, 31.975f);
		generalPath.curveTo(23.9438f, 34.115f, 24.7738f, 36.115f, 26.2838f, 37.635f);
		generalPath.curveTo(27.8038f, 39.145f, 29.8038f, 39.975f, 31.9438f, 39.975f);
		generalPath.curveTo(34.0838f, 39.975f, 36.0838f, 39.145f, 37.6038f, 37.635f);
		generalPath.curveTo(39.1138f, 36.115f, 39.9437f, 34.115f, 39.9437f, 31.975f);
		generalPath.curveTo(39.9437f, 29.835f, 39.1138f, 27.835f, 37.6038f, 26.315f);
		generalPath.closePath();
		generalPath.moveTo(16.39f, 7.94f);
		generalPath.lineTo(4.0f, 7.94f);
		generalPath.curveTo(1.79f, 7.94f, 0.0f, 9.73f, 0.0f, 11.94f);
		generalPath.lineTo(0.0f, 51.94f);
		generalPath.curveTo(0.0f, 54.14f, 1.79f, 55.94f, 4.0f, 55.94f);
		generalPath.lineTo(60.0f, 55.94f);
		generalPath.curveTo(62.21f, 55.94f, 64.0f, 54.14f, 64.0f, 51.94f);
		generalPath.lineTo(64.0f, 11.94f);
		generalPath.curveTo(64.0f, 9.73f, 62.21f, 7.94f, 60.0f, 7.94f);
		generalPath.lineTo(47.57f, 7.94f);
		generalPath.lineTo(40.61f, 0.55f);
		generalPath.curveTo(40.22f, 0.2f, 39.66f, -7.81597E-14f, 39.07f, -7.81597E-14f);
		generalPath.lineTo(24.91f, -7.81597E-14f);
		generalPath.curveTo(24.31f, -7.81597E-14f, 23.75f, 0.2f, 23.37f, 0.55f);
		generalPath.lineTo(16.39f, 7.94f);
		generalPath.closePath();
		generalPath.moveTo(22.0375f, 7.93625f);
		generalPath.lineTo(41.9275f, 7.93625f);
		generalPath.lineTo(38.3175f, 4.10625f);
		generalPath.lineTo(25.6675f, 4.10625f);
		generalPath.lineTo(22.0375f, 7.93625f);
		generalPath.closePath();
		generalPath.moveTo(43.9437f, 31.975f);
		generalPath.curveTo(43.9437f, 25.355f, 38.5638f, 19.975f, 31.9438f, 19.975f);
		generalPath.curveTo(25.3237f, 19.975f, 19.9438f, 25.355f, 19.9438f, 31.975f);
		generalPath.curveTo(19.9438f, 38.595f, 25.3237f, 43.975f, 31.9438f, 43.975f);
		generalPath.curveTo(38.5638f, 43.975f, 43.9437f, 38.595f, 43.9437f, 31.975f);
		generalPath.closePath();
		shape = generalPath;
		paint = (colorFilter != null) ? colorFilter.filter(new Color(153, 153, 153, 255)) : new Color(153, 153, 153, 255);
		g.setPaint(paint);
		g.fill(shape);
		g.setTransform(transformsStack.pop());
		g.setTransform(transformsStack.pop());

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
		return 0.0;
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
		return 158.0;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigHeight()
	 */
	public static double getOrigHeight() {
		return 158.0;
	}

	@Override
	public boolean supportsColorFilter() {
		return true;
	}

	/**
	 * @see AbstractRadianceIcon#of(int, int)
	 */
	public static RadianceIcon of(int width, int height) {
		UploadImage3Svg base = new UploadImage3Svg();
		base.width = width;
		base.height = height;
		return base;
	}

	/**
	 * @see AbstractRadianceIcon#uiResourceOf(int, int)
	 */
	public static RadianceIconUIResource uiResourceOf(int width, int height) {
		UploadImage3Svg base = new UploadImage3Svg();
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
		return UploadImage3Svg::new;
	}
}

