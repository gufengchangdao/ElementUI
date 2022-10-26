package lab;

import com.component.util.SwingTestUtil;
import org.jdesktop.swingx.JXLabel;

import javax.swing.*;
import javax.swing.plaf.basic.BasicLabelUI;
import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * This is the template for Classes.
 *
 * @author Greg Hinkle,January 2002
 * @version $Revision: 1.4 $($Author: dvoet $/ $Date: 2003/05/05 21:21:27 $)
 * @copyright 2002 Sapient
 * @since carbon 1.0
 */

public class VerticalLabelUI extends BasicLabelUI {
	static {
		labelUI = new VerticalLabelUI(false);
	}

	protected boolean clockwise;


	public VerticalLabelUI(boolean clockwise) {
		super();
		this.clockwise = clockwise;
	}


	public Dimension getPreferredSize(JComponent c) {
		Dimension dim = super.getPreferredSize(c);
		return new Dimension(dim.height, dim.width);
	}

	private static Rectangle paintIconR = new Rectangle();
	private static Rectangle paintTextR = new Rectangle();
	private static Rectangle paintViewR = new Rectangle();
	private static Insets paintViewInsets = new Insets(0, 0, 0, 0);

	public void paint(Graphics g, JComponent c) {

		JLabel label = (JLabel) c;
		String text = label.getText();
		Icon icon = (label.isEnabled()) ? label.getIcon() : label.getDisabledIcon();

		if ((icon == null) && (text == null)) {
			return;
		}

		FontMetrics fm = g.getFontMetrics();
		paintViewInsets = c.getInsets(paintViewInsets);

		paintViewR.x = paintViewInsets.left;
		paintViewR.y = paintViewInsets.top;

		// Use inverted height & width
		paintViewR.height = c.getWidth() - (paintViewInsets.left + paintViewInsets.right);
		paintViewR.width = c.getHeight() - (paintViewInsets.top + paintViewInsets.bottom);

		paintIconR.x = paintIconR.y = paintIconR.width = paintIconR.height = 0;
		paintTextR.x = paintTextR.y = paintTextR.width = paintTextR.height = 0;

		String clippedText =
				layoutCL(label, fm, text, icon, paintViewR, paintIconR, paintTextR);

		Graphics2D g2 = (Graphics2D) g;
		AffineTransform tr = g2.getTransform();
		if (clockwise) {
			g2.rotate(Math.PI / 2);
			g2.translate(0, -c.getWidth());
		} else {
			g2.rotate(-Math.PI / 2);
			g2.translate(-c.getHeight(), 0);
		}

		if (icon != null) {
			icon.paintIcon(c, g, paintIconR.x, paintIconR.y);
		}

		if (text != null) {
			int textx = paintTextR.x;
			int textY = paintTextR.y + fm.getAscent();

			if (label.isEnabled()) {
				paintEnabledText(label, g2, clippedText, textx, textY);
			} else {
				paintEnabledText(label, g2, clippedText, textx, textY);
			}
		}

		g2.setTransform(tr);
	}

	public static void main(String[] args) {
		// JLabel label = new JLabel("这是一段文本而已");
		// label.setUI(new lab.VerticalLabelUI(false));
		JXLabel label = new JXLabel("MY TEXT");
		label.setTextRotation(3 * Math.PI / 2);
		// label.setTextRotation(Math.PI/2);
		SwingTestUtil.test(label);
	}
}