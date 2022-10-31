package com.component.others.scrollbar;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.stream.IntStream;

public class TranslucentScrollTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout());

			p.add(ScrollBarOnHoverScroll.getLayer(makeList()));
			p.add(TranslucentScroll.getLayer(makeList()));

			SwingTestUtil.test();
		});
	}

	private static Component makeList() {
		DefaultListModel<String> m = new DefaultListModel<>();
		IntStream.range(0, 50)
				.mapToObj(i -> String.format("%05d: %s", i, LocalDateTime.now(ZoneId.systemDefault())))
				.forEach(m::addElement);
		return new JList<>(m);
	}
}
