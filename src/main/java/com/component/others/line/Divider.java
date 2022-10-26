package com.component.others.line;

import com.component.common.component.BaseComponent;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.max;

/**
 * 分割线
 * <p>
 * 区隔内容的分割线。
 */
public class Divider extends BaseComponent {
	private JLabel label;
	/** 设置分割线文案的位置，从上到下，从左到右的百分比位置 */
	private float position = 0.5f;
	private boolean isHorizontal = true;
	private int gap = 5;
	private int length;
	private int stroke = 1;
	private boolean isRound = true;
	private LineLabel line1;
	private LineLabel line2;

	public Divider(boolean isHorizontal, int length) {
		this.isHorizontal = isHorizontal;
		this.length = length;
		init();
	}

	public Divider(JLabel label, float position, boolean isHorizontal, int length) {
		this.label = label;
		this.position = position;
		this.isHorizontal = isHorizontal;
		this.length = length;
		init();
	}

	private void init() {
		if (isHorizontal) {
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			if (label != null) {
				Dimension s = label.getPreferredSize();
				if (length == 0)
					length = 200 + gap * 2 + s.width;

				line1 = new LineLabel(stroke, isRound);
				line1.setPreferredSize(new Dimension(max((int) (length * position - gap - s.width / 2), 0), s.height));
				add(line1);
				add(Box.createHorizontalStrut(gap));
				add(label);
				add(Box.createHorizontalStrut(gap));
				line2 = new LineLabel(stroke, isRound);
				line2.setPreferredSize(new Dimension(max((int) (length * (1 - position) - gap - s.width / 2), 0), s.height));
				add(line2);
			} else {
				if (length == 0) length = 200;
				line1 = new LineLabel(stroke, isRound);
				line1.setPreferredSize(new Dimension(length, UIManager.getFont("Label.font").getSize()));
				add(line1);
			}
		} else {
			setLayout(new MigLayout("wrap 1, fill,insets 0", "", "[]0[]0[]0[]0[]"));
			if (label != null) {
				Dimension s = label.getPreferredSize();
				if (length == 0)
					length = 200 + gap * 2 + s.height;

				line1 = new LineLabel(stroke, LineLabel.VERTICAL, isRound);
				line1.setPreferredSize(new Dimension(s.width, max((int) (length * position - gap - s.height / 2), 0)));
				add(line1, "center");
				add(Box.createVerticalStrut(gap), "center, gap top 0");
				add(label, "center");
				add(Box.createVerticalStrut(gap), "center");
				line2 = new LineLabel(stroke, LineLabel.VERTICAL, isRound);
				line2.setPreferredSize(new Dimension(s.width, max((int) (length * (1 - position) - gap - s.height / 2), 0)));
				add(line2, "center");
			} else {

				if (length == 0) length = 200;
				line1 = new LineLabel(stroke, LineLabel.VERTICAL, isRound);
				line1.setPreferredSize(new Dimension(UIManager.getFont("Label.font").getSize(), length));
				add(line1, "center");
			}
		}
	}

	public void setForeground(Color fg) {
		if (line1 != null)
			line1.setForeground(fg);
		if (line2 != null)
			line2.setForeground(fg);
		if (label != null)
			label.setForeground(fg);
	}
}
