package com.component.form.picket;

import com.component.util.SwingTestUtil;
import org.jdesktop.swingx.JXColorSelectionButton;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * 颜色选择器
 * <p>
 * 有现有的类 JXColorSelectionButton ，不需要再重写创建
 */
public class ColorPickerTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			// JColorChooser button = new JColorChooser();
			JXColorSelectionButton button = new JXColorSelectionButton();

			Locale loc = button.getLocale();

			// 只显示的面板
			List<String> list = Arrays.asList(
					// UIManager.getString("ColorChooser.swatchesNameText", loc),
					// UIManager.getString("ColorChooser.hsvNameText", loc),
					// UIManager.getString("ColorChooser.hslNameText", loc),
					UIManager.getString("ColorChooser.rgbNameText", loc),
					UIManager.getString("ColorChooser.cmykNameText", loc)
			);

			for (AbstractColorChooserPanel p : button.getChooser().getChooserPanels()) {
				if (!list.contains(p.getDisplayName())) {
					button.getChooser().removeChooserPanel(p);
				}
			}

			SwingTestUtil.test(button);
		});
	}
}
