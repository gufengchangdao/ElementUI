package com.component.form.rate;

import com.component.basic.color.ColorUtil;
import com.component.basic.color.GradientColorFilter;
import com.component.common.component.BaseComponent;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.svg.icon.fill.StarSvg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.List;

/**
 * 评分
 * <p>
 * 功能有
 * <ul>
 *     <li>更换图标</li>
 *     <li>图标颜色渐变</li>
 *     <li>右侧辅助文字</li>
 *     <li>其他icon</li>
 * </ul>
 */
public class RateSelector extends BaseComponent implements MouseMotionListener, MouseListener {
	private RadianceIcon icon;
	/** 图标个数，即满分是多少分，默认5分 */
	private int maxCount;
	/** 当前分数，受鼠标移动改变，默认0分 */
	private int currentCount;
	/** 选择的分数，点击才改变，范围为 [1,maxCount]，0表示未选中 */
	private int selectedCount;
	/** 已选中的图标颜色，默认橘黄色 */
	private Color selectedColor;
	/** 分数对应的文本列表 */
	private List<String> tipList;
	private Font font;
	private static AffineTransform atf = new AffineTransform();
	private static FontRenderContext frc = new FontRenderContext(atf, true, true);
	/** 最长辅助文本的宽度 */
	private int textWidth;

	/** 图标水平间隔，由于第一个图标的x坐标是 hgap 的一半，建议为偶数。默认为 6 */
	private int hgap = 6;
	/** 是否区分颜色，低分到高分图标颜色渐变，但是如果满分为1分将不起作用，默认为false */
	private boolean isTurnColor;
	/** 区分颜色时低分图标颜色，默认为灰色 */
	private Color lowScoreColor;
	private GradientColorFilter colorFilter;

	public RateSelector() {
		this(0);
	}

	public RateSelector(int selectedCount) {
		this(Math.max(5, selectedCount), selectedCount);
	}

	public RateSelector(int maxCount, int selectedCount) {
		this(StarSvg.of(16, 16), maxCount, selectedCount, null);
	}

	public RateSelector(int selectedCount, List<String> tipList) {
		this(StarSvg.of(16, 16), 5, selectedCount, tipList);
	}

	public RateSelector(RadianceIcon icon, int maxCount, int selectedCount, List<String> tipList) {
		this(icon, maxCount, selectedCount, ColorUtil.WARNING, tipList, false, ColorUtil.INFO);
	}

	public RateSelector(RadianceIcon icon, int maxCount, int selectedCount, Color selectedColor,
	                    List<String> tipList, boolean isTurnColor, Color lowScoreColor) {
		this.icon = icon;
		this.maxCount = maxCount;
		this.selectedCount = selectedCount;
		this.selectedColor = selectedColor;
		this.tipList = tipList;
		this.isTurnColor = isTurnColor;
		this.lowScoreColor = lowScoreColor;
		this.colorFilter = new GradientColorFilter(lowScoreColor, selectedColor);
		init();
	}

	private void init() {
		addMouseMotionListener(this);
		addMouseListener(this);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		adjustSize();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g.create();
		// 模仿类库代码这样写试试
		try {


			int x = hgap / 2;
			Dimension size = getPreferredSize();
			// 绘制边框
			int oldIconWidth = icon.getIconWidth();
			int oldIconHeight = icon.getIconHeight();
			icon.setDimension(new Dimension(oldIconWidth + 2, oldIconHeight + 2));
			int y = (size.height - icon.getIconHeight()) / 2;
			for (int i = 0; i < maxCount; i++) {
				if ((currentCount != 0 && i + 1 > currentCount)
						|| (currentCount == 0 && (i + 1) > selectedCount)) //打过分的不绘制边框
					icon.paintIcon(this, g, x, y);
				x += hgap + icon.getIconWidth();
			}
			icon.setDimension(new Dimension(oldIconWidth, oldIconHeight));

			// 绘制文本
			if (tipList != null) {
				g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g2.drawString(tipList.get(currentCount == 0 ? selectedCount : currentCount), x, icon.getIconHeight());
			}

			// 绘制五角星
			x = hgap / 2 + 1;
			if (isTurnColor) icon.setColorFilter(colorFilter);
			else icon.setColorFilter(color -> selectedColor);
			//鼠标移开时currentCount等于0，但是可能已经选中了分数
			int len = currentCount == 0 ? selectedCount : currentCount;
			for (int i = 0; i < len; i++) {
				if (isTurnColor) colorFilter.setRadio((float) (i + 1) / maxCount);
				icon.paintIcon(this, g, x, y);
				x += hgap + icon.getIconWidth() + 2;
			}

			icon.setColorFilter(null);

		} finally {
			g2.dispose();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (!isEnabled()) return;
		int width = getPreferredSize().width - textWidth;
		// 超出图标范围
		if (e.getX() > width) return;
		int av = width / maxCount;
		int score = Math.min(e.getX() / av + 1, maxCount);
		if (score != currentCount) {
			currentCount = score;
			repaint(); //可优化，局部重绘
		}
	}

	/**
	 * 设置评分辅助文本。对应每个评分，例如索引为0的元素对应0分，如果0分没有文本就设为空串，不要null！
	 *
	 * @param tipList 辅助文本
	 */
	public void setTipList(List<String> tipList) {
		this.tipList = tipList;
		adjustSize();
	}

	public void adjustSize() {
		if (tipList != null) {
			//找到最长的字符串
			String maxS = "";
			for (String s : tipList)
				if (s.length() > maxS.length()) maxS = s;

			font = UIManager.getFont("Label.font");
			textWidth = (int) font.getStringBounds(maxS, frc).getWidth();
		}
		setPreferredSize(new Dimension((icon.getIconWidth() + hgap + 2) * maxCount + textWidth + hgap,
				icon.getIconHeight() + hgap));
	}

	public boolean isTurnColor() {
		return isTurnColor;
	}

	public void setTurnColor(boolean turnColor) {
		isTurnColor = turnColor;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		selectedCount = currentCount;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (!isEnabled()) return;
		currentCount = 0;
		repaint();
	}

	public RadianceIcon getIcon() {
		return icon;
	}

	public int getMaxCount() {
		return maxCount;
	}

	public int getCurrentCount() {
		return currentCount;
	}

	/** 获取已打的分数，打分之前为 0 */
	public int getSelectedCount() {
		return selectedCount;
	}

	public Color getSelectedColor() {
		return selectedColor;
	}

	public int getHgap() {
		return hgap;
	}

	public Color getLowScoreColor() {
		return lowScoreColor;
	}

	public GradientColorFilter getColorFilter() {
		return colorFilter;
	}

	public void setIcon(RadianceIcon icon) {
		this.icon = icon;
	}

	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}

	/**
	 * 设置初始得分
	 *
	 * @param selectedCount
	 */
	public void setSelectedCount(int selectedCount) {
		this.selectedCount = selectedCount;
	}

	public void setSelectedColor(Color selectedColor) {
		this.selectedColor = selectedColor;
	}

	public void setHgap(int hgap) {
		this.hgap = hgap;
	}

	public void setLowScoreColor(Color lowScoreColor) {
		this.lowScoreColor = lowScoreColor;
	}

	public void setColorFilter(GradientColorFilter colorFilter) {
		this.colorFilter = colorFilter;
	}

	public List<String> getTipList() {
		return tipList;
	}

}
