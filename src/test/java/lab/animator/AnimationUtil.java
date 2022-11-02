package lab.animator;

final class AnimationUtil {
	private static final int N = 3;

	private AnimationUtil() {
		/* Singleton */
	}

	// http://www.anima-entertainment.de/math-easein-easeout-easeinout-and-bezier-curves
	// Math: EaseIn EaseOut, EaseInOut and Bezier Curves | Anima Entertainment GmbH
	public static double easeIn(double t) {
		// range: 0.0 <= t <= 1.0
		return Math.pow(t, N);
	}

	public static double easeOut(double t) {
		return Math.pow(t - 1d, N) + 1d;
	}

	public static double easeInOut(double t) {
		double ret;
		boolean isFirstHalf = t < .5;
		if (isFirstHalf) {
			ret = .5 * intPow(t * 2d, N);
		} else {
			ret = .5 * (intPow(t * 2d - 2d, N) + 2d);
		}
		return ret;
	}

	// https://wiki.c2.com/?IntegerPowerAlgorithm
	public static double intPow(double da, int ib) {
		int b = ib;
		if (b < 0) {
			// return d / intPow(a, -b);
			throw new IllegalArgumentException("B must be a positive integer or zero");
		}
		double a = da;
		double d = 1d;
		for (; b > 0; a *= a, b >>>= 1) {
			if ((b & 1) != 0) {
				d *= a;
			}
		}
		return d;
	}

	// public static double delta(double t) {
	//   return 1d - Math.sin(Math.acos(t));
	// }
}
