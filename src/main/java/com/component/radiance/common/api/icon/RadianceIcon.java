package com.component.radiance.common.api.icon;

import com.component.radiance.common.api.RadianceCommonCortex;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Extension of the core {@link Icon} interface that adds more capabilities like
 * resizing and color filtering.
 *
 * @author Kirill Grouchnikov
 */
public interface RadianceIcon extends Icon {
	/**
	 * Changes the dimension of <code>this</code> icon.
	 *
	 * @param newDimension New dimension for <code>this</code> icon.
	 */
	void setDimension(Dimension newDimension);

	default BufferedImage toImage(double scale) {
		BufferedImage result = RadianceCommonCortex.getBlankScaledImage(scale,
				this.getIconWidth(), this.getIconHeight());
		this.paintIcon(null, result.getGraphics(), 0, 0);
		return result;
	}

	boolean supportsColorFilter();

	void setColorFilter(ColorFilter colorFilter);

	ColorFilter getColorFilter();

	/**
	 * Interface for creating Radiance icons.
	 *
	 * @author Kirill Grouchnikov
	 */
	interface Factory {
		/**
		 * Returns a new instance of the icon managed by this factory.
		 *
		 * @return A new instance of the icon managed by this factory.
		 */
		RadianceIcon createNewIcon();
	}

	interface ColorFilter {
		Color filter(Color color);
	}
}
