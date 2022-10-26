package com.component.form.picket;

import com.component.util.SwingTestUtil;
import org.jdesktop.swingx.JXColorSelectionButton;

import java.awt.*;

/**
 * 颜色选择器
 * <p>
 * 有现有的类 JXColorSelectionButton ，不需要再重写创建
 */
public class ColorPickerTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() ->{
			SwingTestUtil.loadSkin();
			JXColorSelectionButton button = new JXColorSelectionButton();

			SwingTestUtil.test(button);
		});
	}
}
