package com.component.data.label.badge;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.plaf.LayerUI;
import java.awt.*;
import java.util.stream.Stream;

public class NumberBadgeLabelTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout("wrap 3"));

			Icon informationIcon = UIManager.getIcon("OptionPane.informationIcon");
			Icon errorIcon = UIManager.getIcon("OptionPane.errorIcon");
			Icon questionIcon = UIManager.getIcon("OptionPane.questionIcon");
			Icon warningIcon = UIManager.getIcon("OptionPane.warningIcon");

			NumberBadgeLabel information = new NumberBadgeLabel(informationIcon, BadgePosition.SOUTH_EAST, 0);
			NumberBadgeLabel error = new NumberBadgeLabel(errorIcon, BadgePosition.SOUTH_EAST, 8);
			NumberBadgeLabel question = new NumberBadgeLabel(questionIcon, BadgePosition.SOUTH_WEST, 64);
			NumberBadgeLabel warning = new NumberBadgeLabel(warningIcon, BadgePosition.NORTH_EAST, 256);
			NumberBadgeLabel information2 = new NumberBadgeLabel(informationIcon, BadgePosition.NORTH_WEST, 1_024);
			NumberBadgeLabel textLabel = new NumberBadgeLabel("这是一段文本", BadgePosition.NORTH_EAST, 20);
			LayerUI<NumberBadgeLabel> ui = new NumberBadgeLayerUI();
			Stream.of(information, error, question, warning, information2, textLabel).forEach(label -> {
				label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				p.add(new JLayer<>(label, ui));
			});

			SwingTestUtil.test();
		});
	}
}