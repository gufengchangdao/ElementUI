package com.component.svg.upload;

import com.component.radiance.common.api.icon.AbstractRadianceIcon;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.radiance.common.api.icon.RadianceIconUIResource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Base64;

public class ImageSvg extends AbstractRadianceIcon {
	/**
	 * @see AbstractRadianceIcon#AbstractRadianceIcon()
	 */
	public ImageSvg() {
		this.width = (int) getOrigWidth();
		this.height = (int) getOrigHeight();
	}

	private static WeakReference<BufferedImage> image519194ea1a59eb678032b0e10b802a3d;

	private static BufferedImage getImage519194ea1a59eb678032b0e10b802a3d() {
		BufferedImage result = (image519194ea1a59eb678032b0e10b802a3d != null)
				? image519194ea1a59eb678032b0e10b802a3d.get() : null;
		if (result != null) {
			return result;
		}
		StringBuilder imageData = new StringBuilder(6272);
		imageData.append("iVBORw0KGgoAAAANSUhEUgAAAyAAAAJYCAYAAACadoJwAAASKUlEQVR4Xu3dsY7jOBYFUEYddVZJBRtuWFHHnXayWScb7v9/xtRDoTCzdLlN2xLJR54DXAx2Z0qWLRvglUipFAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		imageData.append("AAAAAAAAAACY3zcRSR8AgGnFYOXne36/538iskz+855/FQCAiXwviofI6nkrAACT+FUuBysisl5eCwDAYC/lcpAiImsmplkCAAz173I5SBGRNRNTLQEAhlJARPbJfwsAwGAKiMg+UUAAgOEUEJF9ooAAAMMpICL7RAEBAIZrLSDxMLO4Xa+IzJn6N/tVFBAAYLjWAuJJyjC3OElQ/27rKCAAwHAKCKxBAQEAUlBAYA0KCACQggICa1BAAIAUFBBYgwICAKSggMAaFBAAIAUFBNaggAAAKSggsAYFBABIQQGBNSggAEAKCgisQQEBAFJQQGANCggAkIICAmtQQACAFBQQWIMCAgCkoIDAGhQQACAFBQTWoIAAACnMXEC+v+elCvA1BQQASGGmAhIF463cHkj9fs/P0mefIItbv5uIAgIADDdDAYlttwyevkoMqKK0fCuwt5bfkAICAAw3soDEFKtf5fK1HkkMrM7YR8hCAQEAUhhVQFpf997E1CxXQ9iRAgIApNBaBI4sIFES6u0fmVgjEldXYCcKCACQQu8Ccnb5+EyUEFdC2IkCAgCk0LOAxGLxertnJgZksAsFBABIoVcBeS2X2+yRHwX2oIAAACn0KiAxJareZq9E+YHVKSAAQAo9CkjvqVd1TMViBwoIAJBCjwISg556e73jKgirU0AAgBTOLiCt2z87cfctWJkCAgCk0FoQHi0gRz3p/Ii4LS8rU0AAgBTOLiD1dkbm0fcAGSggAEAKZxaQl3K5nZFxS15WpoAAACmcWUBat90rMR0MVqWAAAAptJaERwpIXHGotzMy8SwSWJUCAgCksFMBMfhiZQoIAJCCAgJrUEAAgBR2KiCmYLEyBQQASOHMAtK67V6xCJ2VKSAAQAqtJeGRAvJaLrczMp6GzsoUEAAghTMLSDx5vN7OyMR7hVUpIABACmcWkNAyKOqV7wXW1fJbU0AAgOHOLiCt2z871n+wOgUEAEihtSA8WkBmmYZl");
		imageData.append("+hWrU0AAgBTOLiBh9O143X63ryid8X15Kx/HPr5jpr+dTwEBAFLoUUBiQBoDn3qbvfLMvtMujvOfymYMkB2L8yggAEAKPQpIaH2do+PWu33ELZdbS2YcE1dEjqeAAAAptBaDZwtIiIFnvd0zE1Ov4qw854rvRv3Z30oMhF/ijzmMAgIApNCzgIS4G1W97TNigNvHsw+bdIyOo4AAACn0LiBxReLsEqJ89BHTqFqnXV1L/L3pWMdQQACAFHoXkE9nTceKQZgBbR8tA96WxHZ4XsvxUEAAgOFGFZAQ23z2DPo/E3dgoo8/3e3qkTh2z1NAAIAURhaQEFOy4nkRzxQRd1Xq65FF5y056zu2CwUEAEhhdAH5FEUk9qV1alYMtqK4KB59HbHu41qsB3mOAgIApDBLAanFIvJ4zdi/fyb+f7fWHefsGwjE9nmMAgIApDBrAWE+R6/7uBbrQR6jgAAAKSggtHj2eR/3Jl6P+yggAEAKCgi3nLnu41qsB7mfAgIApKCAcMvZ6z6uxXqQ+yggAEAKCgh/Encaq78LPROvTxsFBABIQQHhmrjjWP09GJHYD25TQACAFBQQvhK3Ov5dLr8HIxL74dbLtykgAEAKCghfaX0gZK/E/vBnCggAkIICQq31O9E7sV9cp4AAACm0DjYVkD3Msu7jWqwHuU4BAQBSUED4NNO6j2uxHuQ6BQQASEEB4dNs6z6uxXqQrykgAEAKCgih9XswS6wHuaSAAAAptA48FZB1fS8fA9P6mM+c2N/Yb/6mgAAAKSggtAxcZ0zsN39rOY4KCAAwnAKytx/l8lhnSuw/HxQQACAFBWRfcUzr45wxvpsfFBAAIAUFZE8Z131ci/UgHxQQACAFBWRPv8rlMc6ceD+7U0AAgBQUkP28lcvju0Life1MAQEAUlBA9vJaLo/tSon3tysFBABIQQHZx7eyzrqPa/ldPt7njhQQACAFBWQfP8vlcV0x8T53pIAAACkoIHtoPc6r");
		imageData.append("JN7vbhQQACCF1oGpApLXS7k8njsk3vdOFBAAIAUFZG2xHiLWRdTHc4fsth5EAQEAUlBA1rbLuo9r2Wk9iAICAKSggKyr9diunl3WgyggAEAKrYNUBSSX7+XyGO6c+DxWp4AAACkoIP8vHmQX03ZiMBdrCOKf8b+zvf+WwehOic9jdS3HXAEBAIZTQD7EHZNuDeDi32d40vaPcrnv8vG5rOzW9zeigAAAwykgH++tfr9/yswLm+99L7slQ4F8lAICAKSwewFpff91fpX5bvEa6xxigFnvq/yd+HxmO25HUUAAgBRaB+ArFpA4G16/z3sSA76ZBrMtA1D5KI8rajn+CggAMNyuBeSoqwWzTMd6K5f7JtcTn9dqFBAAIIVdC0jLYK01owezz17J2TWrrQdp+U4rIADAcDsWkDPuEhV30RrhqCs5O2a19SAKCACQwm4F5KyrBfHMkBGD2VjPUO+LtGel9SAKCACQwk4F5OyrBb3Xg1j3cUxGT6E7igICAKSwUwHpcbUgPs8eYspX/dryeEZNoTuSAgIApLBLAel5teDswWxM9YopX/XryuMZNYXuSAoIAJDCDgWk99WCswezMdWrfk15Pr2n0B1NAQEAUli9gIy6WnDWYLb1eMlj6TWF7gwKCACQQuuANmsBGXm14OjBbO8rObsmblaQkQICAKSwcgFpfW9n5qjB7KgrOTsmBvIZKSAAQAqtg/RsBWSWqwVHDWZHXsnZMWdNoTuTAgIApLBiAZntakE8ef0ZrcdIjk2m73xQQACAFFoHt5kGYzNeLXj08zv74YlyPfG5HzWFrgcFBABIYbUC0vp+eufRwWzLoFLOy1FT6Hpo+a4oIADAcK0D9gwFZParBfEk9nvE1K16G9I/z06h60UBAQBSWKmAtAzARieeyN4iPu/6b2VcXsv8Wr7/CggAMNwqBSTT1YJbg9nZr+TsmEen0PWkgAAAKaxQQLJdLYhBYNyp65qYqlX/jYzPvVPoelNA");
		imageData.append("AIAUsheQrFcLrj1nIqZo1f+tzJPWKXQjKCAAQArZC0jmqwX1YDamZtX/jcyXeMjljBQQACCFzAVkhasFn4PZ2R6eKNcTx+lPU+hGUUAAgBSyFpBVrhZ8DmZnfHiiXM+1KXQjKSAAQAoZC8hqVwtaBo4yX+K3M5OW75ECAgAMl7GAuFogs2Sm9SAKCACQQrYC0rq/Ij0y03oQBQQASKF1QD9DAYmzzfV+iYzOLOtBFBAAIIUsBWS1dR+yVmZYD6KAAAApZCkg1n3IzImBfTwUcyQFBABIIUMBad1HkZGJAjCSAgIApNA6uB9VQOKscr0vIrPmRxlHAQEAUpi9gLQMqkRmysy/FQUEABhu5gISZ5Pr/RCZPaPWgyggAEAKsxaQeL16H0Sy5FfpTwEBAFKYsYDE2eMYKNX7IJIpb6UvBQQASGHGAhJnj+vXF8mY19KPAgIApDBbAYmzxvVri2RNPDwzHqLZgwICAKQwUwGJs8X164pkTzxEswcFBABIYZYCEmeJrfuQVRO/s7MpIABACrMUEOs+ZPW8lHMpIABACjMUEOs+ZIecvR5EAQEAUhhdQOKscP1aIqvmzPUgCggAkMLIAhJng+OscP1aIivnrPUgCggAkMLIAhJng+vXEdkh8bDNoykgAEAKowpI6+uKrJgoC0dTQACAFFqLwJEFJM7+1tsX2S0/yrEUEAAghREFpGWgJLJDev+uFBAAYLjeBSTO+tbbFtk1UQiOWg+igAAAKfQsILGNersiuycewnkEBQQASKFXAYmzvDH4qbcrIh8P43yWAgIApNCrgLQMjkR2zmt5TstvTAEBAIbrUUCs+xC5nSgH8XDORykgAEAKZxeQOKtbb0tEvs4z60EUEAAghTMLSJzNte5D5L48uh5EAQEAUjizgMTZ3Ho7InI7L+V+CggAkMJZBSTO4tbbEJG2/C73rwdRQACAFM4oIHH2tv57EbkvP8t9FBAAIIWjC0ictY2zt/Xfi8j9id9n");
		imageData.append("KwUEAEjh6AISZ23rvxWRxxMP8WyhgAAAKRxZQFq3JSLtiWLRQgEBAFJoLQ23Ckicpa3/RkSOSct6EAUEAEjhqALSMvgRkcdzxG9QAQEAhjuigMTC87jzlYicl1trQRQQACCFIwoIMJ4CAgCkoIDAGhQQACAFBQTWoIAAACkoILAGBQQASEEBgTUoIABACq0F5O09ryIybX6Xy99tHQUEABiutYCISP4oIADAcAqIyD5RQACA4RQQkX2igAAAwykgIvtEAQEAhlNARPaJAgIADBe3160HKSKyZuJWvQAAQ30rl4MUEVkzPwoAwATiGR/1QEVE1ko8JyROOAAATEEJEVk3UT6+FwCAycQAJRalRxkRkTUS67wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		imageData.append("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAoMVfiU17ZuqvyIEAAAAASUVORK5C");
		try {
			result = ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(imageData.toString())));
			image519194ea1a59eb678032b0e10b802a3d = new WeakReference<>(result);
			return result;
		} catch (IOException ioe) {
		}
		return null;
	}


	private void _paint0(Graphics2D g, float origAlpha) {
// 
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0_0
		shape = new Rectangle2D.Double(0.0, 0.0, 480.0, 360.0140075683594);
		paint = (colorFilter != null) ? colorFilter.filter(new Color(250, 250, 250, 255)) : new Color(250, 250, 250, 255);
		g.setPaint(paint);
		g.fill(shape);
		g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
// _0_1
		shape = new Rectangle2D.Double(0.0, 0.0, 480.0, 360.0140075683594);
		clip = g.getClip();
		g.clip(shape);
		{
			Rectangle2D rect2D = new Rectangle2D.Double(0.0, 0.0, 1.000040054321289, 1.0000020265579224);
			Graphics2D gTiled = ((Graphics2D) g.create());
			AffineTransform tTiled = new AffineTransform(480.0f, 0.0f, 0.0f, 360.0140075683594f, 0.0f, 0.0f);
			gTiled.transform(tTiled);
			Point2D src = new Point2D.Double(0, 0);
			Point2D dst = new Point2D.Double(0, 0);
			double startX = rect2D.getX();
			while (true) {
				double startY = rect2D.getY();
				while (true) {
					gTiled.translate(startX, startY);
					Shape shapeTile = null;
					gTiled.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
					gTiled.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
					BufferedImage image519194ea1a59eb678032b0e10b802a3d = getImage519194ea1a59eb678032b0e10b802a3d();
					if (image519194ea1a59eb678032b0e10b802a3d != null) {
						gTiled.drawImage(image519194ea1a59eb678032b0e10b802a3d, 0, 0, null);
					}
					gTiled.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
					gTiled.translate(-startX, -startY);
					startY += rect2D.getHeight();
					src.setLocation(startX, startY);
					tTiled.transform(src, dst);
					if (dst.getY() > shape.getBounds().getMaxY()) {
						break;
					}
				}
				startX += rect2D.getWidth();
				src.setLocation(startX, startY);
				tTiled.transform(src, dst);
				if (dst.getX() > shape.getBounds().getMaxX()) {
					break;
				}
			}
			gTiled.dispose();
		}
		g.setClip(clip);

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
		return 480.0;
	}

	/**
	 * @see AbstractRadianceIcon#getOrigHeight()
	 */
	public static double getOrigHeight() {
		return 360.0140075683594;
	}

	@Override
	public boolean supportsColorFilter() {
		return false;
	}

	/**
	 * @see AbstractRadianceIcon#of(int, int)
	 */
	public static RadianceIcon of(int width, int height) {
		ImageSvg base = new ImageSvg();
		base.width = width;
		base.height = height;
		return base;
	}

	/**
	 * @see AbstractRadianceIcon#uiResourceOf(int, int)
	 */
	public static RadianceIconUIResource uiResourceOf(int width, int height) {
		ImageSvg base = new ImageSvg();
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
		return ImageSvg::new;
	}
}

