package com.component.data.table.listener;

import com.component.common.component.DefaultTableModel2;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.stream.IntStream;

/**
 * DragScrollingListener Tester.
 *
 * @author VsRoom
 * @version 1.0
 * @since <pre>11月 1, 2022</pre>
 */
public class DragScrollingListenerTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout());

			String[] columnNames = {"String", "Integer", "Boolean"};
			DefaultTableModel2 model = new DefaultTableModel2(null, columnNames);
			model.setCellEditable(true);
			IntStream.range(0, 1000)
					.mapToObj(i -> new Object[]{"Java Swing", i, i % 2 == 0})
					.forEach(model::addRow);

			JTable table = new JTable(model) {
				private transient MouseAdapter handler;

				@Override
				public void updateUI() {
					removeMouseMotionListener(handler);
					removeMouseListener(handler);
					super.updateUI();
					handler = new DragScrollingListener(this);
					addMouseMotionListener(handler);
					addMouseListener(handler);
				}
			};
			JScrollPane scroll = new JScrollPane(table);
			scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
			p.add(scroll);

			SwingTestUtil.test();
		});
	}
} 
