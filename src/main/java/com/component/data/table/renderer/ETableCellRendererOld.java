package com.component.data.table.renderer;

import com.component.basic.color.ColorUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * 表格常用的单元格渲染器
 * 支持的功能有
 * <ul>
 *     <li>边框类型</li>
 * </ul>
 *
 * @deprecated 本来不想重新造轮子，但是 JXTable和 DefaultTableCellRenderer 组合时
 * getTableCellRendererComponent() 方法无法获取到正确的row，导致斑马线还是状态信息都无法进行设置
 */
@Deprecated
public class ETableCellRendererOld extends DefaultTableCellRenderer {
	/** 是否显示边框 */
	private boolean isContainBorder;

	public ETableCellRendererOld() {
		init();
	}

	private void init() {
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
	                                               boolean isSelected, boolean hasFocus,
	                                               int row, int column) {
		JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		// 边框
		if (isContainBorder)
			setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, ColorUtil.BORDER_LEVEL3));
		else
			setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ColorUtil.BORDER_LEVEL3));

		return c;
	}

	public boolean isContainBorder() {
		return isContainBorder;
	}

	public void setContainBorder(boolean containBorder) {
		isContainBorder = containBorder;
	}
}
