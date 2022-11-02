package display.form;

import com.component.basic.border.RoundBorder;
import com.component.common.component.BaseInputField;
import com.component.form.input.advice.InputAdviceInputField;
import com.component.form.input.compound.LabelComboBox;
import com.component.form.input.compound.LabelInputTextField;
import com.component.form.input.compound.LabelIntegerInputField;
import com.component.form.input.spinner.InputNumberField;
import com.component.form.input.spinner.TimePicker;
import com.component.form.select.MultiSelector;
import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Vector;

public class InputDisplay {
	public static void main(String[] args) {
		Container p = SwingTestUtil.init(new MigLayout("wrap 1", "grow"));

		p.add(new JLabel("带有输入提示的输入框"));
		BaseInputField baseInputField = new BaseInputField("");
		baseInputField.setPlaceholder("请输入密码");
		p.add(baseInputField, "center");

		p.add(new JLabel("带有输入建议的输入框"));
		Vector<String> vector = new Vector<>();
		vector.add("香蕉");
		vector.add("苹果");
		vector.add("艾希");
		InputAdviceInputField<String> field = new InputAdviceInputField<>(10, vector);
		p.add(field, "center");

		p.add(new JLabel("整数输入框"));
		InputNumberField inputNumberField = new InputNumberField(10, 999);
		p.add(inputNumberField, "center");

		p.add(new JLabel("带有下拉列表的多选器"));
		p.add(new MultiSelector(vector), "center");

		p.add(new JLabel("圆角输入框演示"));
		BaseInputField f = new BaseInputField();
		f.setBorder(new RoundBorder(-1));
		f.setPlaceholder("请输入关键词");
		//消除矩形背景
		f.setBackground(null);
		f.setPreferredSize(new Dimension(400, 40));
		p.add(f, "center");


		p.add(new JLabel("时间选择器"));
		Calendar c1 = Calendar.getInstance();
		c1.set(Calendar.HOUR_OF_DAY, 8);
		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.HOUR_OF_DAY, 12);
		Calendar c3 = Calendar.getInstance();
		c3.set(Calendar.HOUR_OF_DAY, 20);
		TimePicker timePicker = new TimePicker(10, Arrays.asList(c1, c2, c3));
		p.add(timePicker, "center");

		p.add(new JLabel("label和下拉列表的组合"));
		LabelComboBox<String> labelComboBox = new LabelComboBox<>("￥原价", new String[]{"测试一", "测试二", "测试三"});
		p.add(labelComboBox, "center");

		p.add(new JLabel("label和Input的组合"));
		LabelInputTextField labelInputTextField = new LabelInputTextField("你好");
		p.add(labelInputTextField, "center");

		p.add(new JLabel("label和数字输入框的组合"));
		LabelIntegerInputField labelIntegerInputField = new LabelIntegerInputField("原价原价");
		p.add(labelIntegerInputField, "center");

		SwingTestUtil.test();
	}
}
