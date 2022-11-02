package lab.component.table;// -*- mode:java; encoding:utf-8 -*-
// vim:set fileencoding=utf-8:
// @homepage@

import com.component.util.SwingTestUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Optional;

/**
 * 根据数据个数动态调整表格行高
 */
public final class AdjustRowHeightFillsViewport extends JPanel {
	private AdjustRowHeightFillsViewport() {
		super(new BorderLayout());
		String[] columnNames = {"String", "Integer", "Boolean"};
		Object[][] data = {
				{"aaa", 12, true}, {"bbb", 5, false}, {"CCC", 92, true}, {"DDD", 0, false}
		};
		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			@Override
			public Class<?> getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}
		};
		JTable table = new JTable(model) {
			/**视图高度*/
			private int prevHeight = -1;
			/**行数*/
			private int prevCount = -1;

			private void updateRowsHeight(JViewport vport) {
				int height = vport.getExtentSize().height; //视图高度
				int rowCount = getModel().getRowCount();
				int defaultRowHeight = height / rowCount;
				if ((height != prevHeight || rowCount != prevCount) && defaultRowHeight > 0) {
					// 多出来的长度remainder给前几行每行分一个 1，直到分完为止
					int remainder = height % rowCount;
					for (int i = 0; i < rowCount; i++) {
						int a = Math.min(1, Math.max(0, remainder--));
						setRowHeight(i, defaultRowHeight + a);
					}
				}
				prevHeight = height;
				prevCount = rowCount;
			}

			@Override
			public void doLayout() {
				super.doLayout();
				Class<JViewport> clz = JViewport.class;
				Optional.ofNullable(SwingUtilities.getAncestorOfClass(clz, this)) //找到第一个JViewport
						.filter(clz::isInstance) // 不为null
						.map(clz::cast) //转型为JViewport
						.ifPresent(this::updateRowsHeight); //调用updateRowsHeight更新表格行高
			}
		};

		JScrollPane scroll = new JScrollPane(table);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Component c = e.getComponent();
				if (c instanceof JScrollPane) {
					((JScrollPane) c).getViewport().getView().revalidate(); //使 table重新布局，触发 doLayout
				}
			}
		});

		JButton button = new JButton("add");
		button.addActionListener(e -> model.addRow(new Object[]{"", 0, false}));

		add(scroll);
		add(button, BorderLayout.SOUTH);
		setPreferredSize(new Dimension(320, 240));
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.init(new FlowLayout()).add(new AdjustRowHeightFillsViewport());
			SwingTestUtil.test();
		});
	}
}