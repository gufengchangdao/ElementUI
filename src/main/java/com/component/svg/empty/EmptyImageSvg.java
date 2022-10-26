package com.component.svg.empty;

import com.component.radiance.common.api.icon.AbstractRadianceIcon;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.radiance.common.api.icon.RadianceIconUIResource;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

public class EmptyImageSvg extends AbstractRadianceIcon {
	/**
	 * @see AbstractRadianceIcon#AbstractRadianceIcon()
	 */
	public EmptyImageSvg() {
		this.width = (int) getOrigWidth();
		this.height = (int) getOrigHeight();
	}


	private void _paint0(Graphics2D g, float origAlpha) {
// 
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0
		g.setComposite(AlphaComposite.getInstance(3, 0.8f * origAlpha));
// _0_0
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(24.2949f, 13.8637f);
		generalPath.curveTo(25.5391f, 9.95404f, 26.6971f, 7.51981f, 27.7668f, 6.5601f);
		generalPath.curveTo(30.7292f, 3.90389f, 34.8251f, 5.34951f, 35.6959f, 5.55656f);
		generalPath.curveTo(38.755f, 6.2859f, 37.7603f, 1.48644f, 40.3849f, 0.510884f);
		generalPath.curveTo(42.134f, -0.139178f, 43.5725f, 0.655446f, 44.7013f, 2.89476f);
		generalPath.curveTo(45.7007f, 0.805604f, 47.2236f, -0.151303f, 49.2702f, 0.0193738f);
		generalPath.curveTo(52.3405f, 0.27772f, 53.4149f, 10.5845f, 57.6337f, 8.32842f);
		generalPath.curveTo(61.8535f, 6.07139f, 67.0267f, 5.55563f, 69.2365f, 8.90946f);
		generalPath.curveTo(69.7142f, 9.63507f, 69.8971f, 8.50935f, 73.1457f, 4.66587f);
		generalPath.curveTo(76.3943f, 0.821459f, 79.6335f, -0.872247f, 86.279f, 1.36613f);
		generalPath.curveTo(89.3005f, 2.38273f, 91.7852f, 5.13873f, 93.7361f, 9.63321f);
		generalPath.curveTo(93.7361f, 16.048f, 98.4842f, 19.8458f, 107.978f, 21.0238f);
		generalPath.curveTo(122.222f, 22.7911f, 111.166f, 38.0112f, 93.7361f, 42.4683f);
		generalPath.curveTo(76.3052f, 46.9265f, 36.1745f, 49.3327f, 14.3971f, 38.0699f);
		generalPath.curveTo(-0.121156f, 30.563f, 3.1781f, 22.4936f, 24.2939f, 13.8637f);
		generalPath.lineTo(24.2949f, 13.8637f);
		generalPath.closePath();
		shape = generalPath;
		paint = new LinearGradientPaint(new Point2D.Double(62.57120132446289, 34.461700439453125), new Point2D.Double(62.57120132446289, -8.145350456237793), new float[]{0.0f, 1.0f}, new Color[]{((colorFilter != null) ? colorFilter.filter(new Color(222, 222, 222, 0)) : new Color(222, 222, 222, 0)), ((colorFilter != null) ? colorFilter.filter(new Color(169, 169, 169, 77)) : new Color(169, 169, 169, 77))}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		g.setPaint(paint);
		g.fill(shape);
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0_1
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(60.0087f, 70.5507f);
		generalPath.curveTo(74.5127f, 70.5507f, 86.2705f, 67.8457f, 86.2705f, 64.5089f);
		generalPath.curveTo(86.2705f, 61.1721f, 74.5127f, 58.4672f, 60.0087f, 58.4672f);
		generalPath.curveTo(45.5048f, 58.4672f, 33.7469f, 61.1721f, 33.7469f, 64.5089f);
		generalPath.curveTo(33.7469f, 67.8457f, 45.5048f, 70.5507f, 60.0087f, 70.5507f);
		generalPath.closePath();
		shape = generalPath;
		paint = new LinearGradientPaint(new Point2D.Double(57.272300720214844, 70.55069732666016), new Point2D.Double(57.272300720214844, 58.467201232910156), new float[]{0.0f, 1.0f}, new Color[]{((colorFilter != null) ? colorFilter.filter(new Color(255, 255, 255, 0)) : new Color(255, 255, 255, 0)), ((colorFilter != null) ? colorFilter.filter(new Color(150, 161, 197, 95)) : new Color(150, 161, 197, 95))}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		g.setPaint(paint);
		g.fill(shape);
		g.setComposite(AlphaComposite.getInstance(3, 0.675f * origAlpha));
// _0_2
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(104.346f, 72.0476f);
		generalPath.curveTo(84.6003f, 76.9701f, 17.2009f, 71.3397f, 10.0272f, 68.7684f);
		generalPath.curveTo(6.53556f, 67.5158f, 3.38831f, 65.1608f, 0.586382f, 61.7025f);
		generalPath.curveTo(0.272783f, 61.3158f, 0.0756665f, 60.8489f, 0.0178102f, 60.3555f);
		generalPath.curveTo(-0.040046f, 59.8622f, 0.0437195f, 59.3627f, 0.259427f, 58.9147f);
		generalPath.curveTo(0.475135f, 58.4666f, 0.81398f, 58.0884f, 1.23681f, 57.8238f);
		generalPath.curveTo(1.65963f, 57.5591f, 2.14919f, 57.4187f, 2.64889f, 57.4188f);
		generalPath.lineTo(120.426f, 57.4188f);
		generalPath.curveTo(122.87f, 63.8896f, 117.51f, 68.7656f, 104.346f, 72.0476f);
		generalPath.closePath();
		shape = generalPath;
		paint = new LinearGradientPaint(new Point2D.Double(60.5, 74.0), new Point2D.Double(60.5, 54.15650177001953), new float[]{0.0f, 1.0f}, new Color[]{((colorFilter != null) ? colorFilter.filter(new Color(255, 255, 255, 0)) : new Color(255, 255, 255, 0)), ((colorFilter != null) ? colorFilter.filter(new Color(145, 145, 145, 38)) : new Color(145, 145, 145, 38))}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		g.setPaint(paint);
		g.fill(shape);
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0_3
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(80.1609f, 48.317f);
		generalPath.lineTo(71.1799f, 37.8787f);
		generalPath.curveTo(70.9675f, 37.6297f, 70.7036f, 37.4291f, 70.4061f, 37.2907f);
		generalPath.curveTo(70.1086f, 37.1523f, 69.7846f, 37.0793f, 69.4562f, 37.0766f);
		generalPath.lineTo(50.1448f, 37.0766f);
		generalPath.curveTo(49.4823f, 37.0766f, 48.8527f, 37.3788f, 48.421f, 37.8787f);
		generalPath.lineTo(39.44f, 48.317f);
		generalPath.lineTo(39.44f, 54.0473f);
		generalPath.lineTo(80.1609f, 54.0473f);
		generalPath.lineTo(80.1609f, 48.317f);
		generalPath.closePath();
		shape = generalPath;
		paint = new LinearGradientPaint(new Point2D.Double(59.800498962402344, 37.07659912109375), new Point2D.Double(59.800498962402344, 44.704898834228516), new float[]{0.0f, 1.0f}, new Color[]{((colorFilter != null) ? colorFilter.filter(new Color(83, 137, 245, 255)) : new Color(83, 137, 245, 255)), ((colorFilter != null) ? colorFilter.filter(new Color(65, 111, 220, 255)) : new Color(65, 111, 220, 255))}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		g.setPaint(paint);
		g.fill(shape);
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0_4
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(77.6161f, 54.258f);
		generalPath.lineTo(69.7555f, 45.1665f);
		generalPath.curveTo(69.5655f, 44.9518f, 69.3312f, 44.7803f, 69.0686f, 44.6637f);
		generalPath.curveTo(68.806f, 44.547f, 68.5211f, 44.4879f, 68.2335f, 44.4903f);
		generalPath.lineTo(51.3675f, 44.4903f);
		generalPath.curveTo(50.7885f, 44.4903f, 50.2227f, 44.7291f, 49.8454f, 45.1665f);
		generalPath.lineTo(41.9848f, 54.258f);
		generalPath.lineTo(41.9848f, 59.2515f);
		generalPath.lineTo(77.6161f, 59.2515f);
		generalPath.lineTo(77.6161f, 54.258f);
		generalPath.closePath();
		shape = generalPath;
		paint = new LinearGradientPaint(new Point2D.Double(64.55549621582031, 59.25149917602539), new Point2D.Double(64.55549621582031, 43.705501556396484), new float[]{0.0f, 1.0f}, new Color[]{((colorFilter != null) ? colorFilter.filter(new Color(220, 233, 255, 255)) : new Color(220, 233, 255, 255)), ((colorFilter != null) ? colorFilter.filter(new Color(182, 207, 255, 255)) : new Color(182, 207, 255, 255))}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		g.setPaint(paint);
		g.fill(shape);
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0_5
		if (generalPath == null) {
			generalPath = new GeneralPath();
		} else {
			generalPath.reset();
		}
		generalPath.moveTo(80.1609f, 60.6141f);
		generalPath.curveTo(80.1609f, 61.4097f, 79.8006f, 62.125f, 79.2319f, 62.6044f);
		generalPath.lineTo(79.1165f, 62.6977f);
		generalPath.curveTo(78.6657f, 63.0373f, 78.1152f, 63.2208f, 77.5495f, 63.22f);
		generalPath.lineTo(42.0524f, 63.22f);
		generalPath.curveTo(41.7315f, 63.22f, 41.4246f, 63.1621f, 41.1412f, 63.0558f);
		generalPath.lineTo(41.0005f, 62.9999f);
		generalPath.curveTo(40.5361f, 62.7945f, 40.1416f, 62.4598f, 39.8646f, 62.0362f);
		generalPath.curveTo(39.5877f, 61.6126f, 39.4402f, 61.1184f, 39.44f, 60.6132f);
		generalPath.lineTo(39.44f, 48.3795f);
		generalPath.lineTo(49.32f, 48.3795f);
		generalPath.curveTo(50.4113f, 48.3795f, 51.2905f, 49.2721f, 51.2905f, 50.3549f);
		generalPath.lineTo(51.2905f, 50.3689f);
		generalPath.curveTo(51.2905f, 51.4526f, 52.1801f, 52.3265f, 53.2714f, 52.3265f);
		generalPath.lineTo(66.3295f, 52.3265f);
		generalPath.curveTo(66.8541f, 52.326f, 67.3571f, 52.119f, 67.7284f, 51.7506f);
		generalPath.curveTo(68.0996f, 51.3823f, 68.3089f, 50.8828f, 68.3104f, 50.3614f);
		generalPath.curveTo(68.3104f, 49.2739f, 69.1906f, 48.3795f, 70.281f, 48.3795f);
		generalPath.lineTo(80.1619f, 48.3795f);
		generalPath.lineTo(80.1609f, 60.6141f);
		generalPath.closePath();
		shape = generalPath;
		paint = new LinearGradientPaint(new Point2D.Double(59.800899505615234, 48.37950134277344), new Point2D.Double(59.800899505615234, 63.220001220703125), new float[]{0.0f, 1.0f}, new Color[]{((colorFilter != null) ? colorFilter.filter(new Color(124, 165, 247, 255)) : new Color(124, 165, 247, 255)), ((colorFilter != null) ? colorFilter.filter(new Color(196, 214, 252, 255)) : new Color(196, 214, 252, 255))}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
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
		return 121.0;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigHeight()
	 */
	public static double getOrigHeight() {
		return 74.0;
	}

	@Override
	public boolean supportsColorFilter() {
		return true;
	}

	/**
	 * @see AbstractRadianceIcon#of(int, int)
	 */
	public static RadianceIcon of(int width, int height) {
		EmptyImageSvg base = new EmptyImageSvg();
		base.width = width;
		base.height = height;
		return base;
	}

	/**
	 * @see AbstractRadianceIcon#uiResourceOf(int, int)
	 */
	public static RadianceIconUIResource uiResourceOf(int width, int height) {
		EmptyImageSvg base = new EmptyImageSvg();
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
		return EmptyImageSvg::new;
	}
}

