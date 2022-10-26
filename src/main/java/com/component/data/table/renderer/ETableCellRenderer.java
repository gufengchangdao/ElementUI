package com.component.data.table.renderer;

import com.component.basic.color.ColorUtil;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ETableCellRenderer extends JLabel implements TableCellRenderer {
	private Color backgroundColor = Color.WHITE;
	private Color stripeColor;
	/** 是否包含斑马线 */
	private boolean isContainStripe;
	/** 是否显示边框 */
	private boolean isContainBorder;
	/**
	 * 每行的状态，用颜色表示。
	 * 获取改成一个 BiFunction<row, val , color> 会更好
	 */
	private Map<Integer, Color> rowColor;
	// privat BiFunction<Integer, Object,Color> rowColor;
	/** 不显示边框时只在底部绘制一条线 */
	public static final Border border1 = BorderFactory.createMatteBorder(0, 0, 1, 0, ColorUtil.BORDER_LEVEL3);
	/** 显示边框时在右边和底部绘制 */
	public static final Border border2 = BorderFactory.createMatteBorder(0, 0, 1, 1, ColorUtil.BORDER_LEVEL3);
	/** 获得焦点的单元格 */
	public static final Border focusBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK);


	public ETableCellRenderer() {
		this(false, false, new HashMap<>());
	}

	public ETableCellRenderer(boolean isContainStripe, boolean isContainBorder) {
		this(isContainStripe, isContainBorder, new HashMap<>());
	}

	public ETableCellRenderer(boolean isContainStripe, boolean isContainBorder, Map<Integer, Color> rowColor) {
		this.isContainStripe = isContainStripe;
		setContainBorder(isContainBorder);
		this.rowColor = rowColor;

		setOpaque(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
	                                               boolean isSelected, boolean hasFocus,
	                                               int row, int column) {
		// 根据优先级逐个判断单元格状态，并设置其信息
		Color fg = null;
		Color bg = null;
		Border bor = null;
		JTable.DropLocation dropLocation = table.getDropLocation();
		if (dropLocation != null
				&& !dropLocation.isInsertRow()
				&& !dropLocation.isInsertColumn()
				&& dropLocation.getRow() == row
				&& dropLocation.getColumn() == column) {

			bg = UIManager.getColor("Table.dropCellBackground");
			isSelected = true;
		}

		// 斑马线
		if (row % 2 == 1 && isContainStripe) bg = stripeColor;

		// 文本
		setText(value.toString());

		// 根据数据状态选择背景
		Color c = rowColor.get(row);
		if (c != null) bg = c;

		// 所选行
		if (isSelected) {
			bg = ColorUtil.PRIMARY;
			fg = Color.WHITE;
		} else {
			fg = Color.BLACK;
		}

		// 所选单元格
		if (hasFocus) {
			bor = focusBorder;
		} else {
			if (isContainBorder) bor = border2;
			else bor = border1;
		}

		if (fg != null) setForeground(fg);
		setBackground(bg);
		if (bor != null && getBorder() != bor) setBorder(bor);

		return this;
	}

	public void addState(int row, Color color) {
		rowColor.put(row, color);
	}

	public void removeState(int row) {
		rowColor.remove(row);
	}

	public boolean isContainStripe() {
		return isContainStripe;
	}

	public void setContainStripe(boolean containStripe) {
		isContainStripe = containStripe;
		Color background = getBackground();
		stripeColor = ColorUtil.blend(background, Color.BLACK, 0.01f);
	}

	/** 设置条纹颜色的同时运行绘制斑马纹 */
	public void setStripeColor(Color stripeColor) {
		isContainStripe = true;
		this.stripeColor = stripeColor;
	}

	public Color getStripeColor() {
		return stripeColor;
	}

	public boolean isContainBorder() {
		return isContainBorder;
	}

	public void setContainBorder(boolean containBorder) {
		isContainBorder = containBorder;
		if (containBorder) setBorder(border2);
		else setBorder(border1);
	}
}
