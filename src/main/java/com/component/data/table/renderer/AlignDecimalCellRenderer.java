package com.component.data.table.renderer;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.*;
import java.awt.*;
import java.util.Objects;

/**
 * 十进制小数点对齐渲染器
 */
public class AlignDecimalCellRenderer implements TableCellRenderer {
	private final JPanel panel = new JPanel();
	private final JTextPane textPane = new JTextPane() {
		@Override
		public Dimension getPreferredSize() {
			Dimension d = super.getPreferredSize();
			d.width = 60;
			return d;
		}

		@Override
		public void updateUI() {
			super.updateUI();
			setOpaque(false);
			putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
			EventQueue.invokeLater(() -> {
				// MutableAttributeSet attr = new SimpleAttributeSet();
				Style attr = getStyle(StyleContext.DEFAULT_STYLE);
				TabStop[] ts = {new TabStop(25f, TabStop.ALIGN_DECIMAL, TabStop.LEAD_NONE)};
				StyleConstants.setTabSet(attr, new TabSet(ts));
				setParagraphAttributes(attr, false);
			});
		}
	};

	public AlignDecimalCellRenderer() {
		// 对于小数点后面位数差距大的一组数，向右靠起无法全部展示，这里左对齐是无奈之举
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		// panel.setLayout(new MigLayout("insets 0","grow, right"));
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
	                                               boolean isSelected, boolean hasFocus, int row, int column) {
		textPane.setFont(table.getFont());
		textPane.setText("\t" + Objects.toString(value, ""));
		if (isSelected) {
			textPane.setForeground(table.getSelectionForeground());
			panel.setBackground(table.getSelectionBackground());
		} else {
			textPane.setForeground(table.getForeground());
			panel.setBackground(table.getBackground());
		}
		panel.add(textPane);
		return panel;
	}
}
