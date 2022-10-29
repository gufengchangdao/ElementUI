package display.basic;

import com.component.basic.button.RadioButton;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.util.Arrays;

public class CheckboxDisplay {
	public static void main(String[] args) {
		Container p = SwingTestUtil.init(new MigLayout());

		RadioButton radioButton = new RadioButton(Arrays.asList("上海", "北京", "广州", "深圳"));
		radioButton.setRadio(true);
		radioButton.setButtonPreferredSize(new Dimension(100, 40));
		p.add(radioButton);

		SwingTestUtil.test();
	}
}
