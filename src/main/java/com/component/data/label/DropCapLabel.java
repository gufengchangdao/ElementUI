package com.component.data.label;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

/**
 * 首字符放大整数倍，其他文字浮动的label
 */
public class DropCapLabel extends JLabel {
	private int fold = 5;

	protected DropCapLabel(String text) {
		super(text);
	}

	public DropCapLabel(String text, int fold) {
		super(text);
		this.fold = fold;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setPaint(getBackground());
		g2.fillRect(0, 0, getWidth(), getHeight());

		// 内容宽度
		Rectangle rect = SwingUtilities.calculateInnerArea(this, null);
		float x0 = rect.x;
		float y0 = rect.y;
		System.out.println(rect);
		Font font = getFont();
		String txt = getText();
		System.out.println(getWidth());
		// 设置第一个字，使其放大 fold 倍
		FontRenderContext frc = g2.getFontRenderContext();
		Shape shape = new TextLayout(txt.substring(0, 1), font, frc).getOutline(null);

		AffineTransform at1 = AffineTransform.getScaleInstance(fold, fold);
		Shape s1 = at1.createTransformedShape(shape);
		Rectangle firstLetter = s1.getBounds();
		firstLetter.grow(6, 2); //间距

		AffineTransform at2 = AffineTransform.getTranslateInstance(x0, y0 + firstLetter.height);
		Shape s2 = at2.createTransformedShape(s1);
		g2.setPaint(getForeground());
		g2.fill(s2);

		float x = x0 + firstLetter.width;
		float y = y0;
		int w = rect.width - firstLetter.width;

		AttributedString as = new AttributedString(txt.substring(1));
		as.addAttribute(TextAttribute.FONT, font);
		AttributedCharacterIterator aci = as.getIterator();
		LineBreakMeasurer lbm = new LineBreakMeasurer(aci, frc);
		while (lbm.getPosition() < aci.getEndIndex()) {
			TextLayout tl = lbm.nextLayout(w);
			tl.draw(g2, x, y + tl.getAscent());
			y += tl.getDescent() + tl.getLeading() + tl.getAscent();
			if (y0 + firstLetter.height < y) { //换行后
				x = x0;
				w = rect.width;
			}
		}
		g2.dispose();
	}
}
