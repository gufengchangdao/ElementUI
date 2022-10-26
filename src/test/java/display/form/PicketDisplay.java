package display.form;

import com.component.form.picket.DatePicker;
import display.util.SwingDisplayUtil;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXMonthView;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class PicketDisplay {
	public static void main(String[] args) {
		Container p = SwingDisplayUtil.init(new MigLayout("center"));
		DatePicker view = new DatePicker(10, "选择日期");
		JXMonthView monthView = view.getMonthView();
		// 是否可遍历
		monthView.setTraversable(true);

		JButton button = new JButton("获取");
		button.addActionListener(e -> {
			System.out.println(view.getText());
			Calendar c = view.getCalendar();
			if (c != null)
				System.out.println(view.getCalendar().get(Calendar.DAY_OF_MONTH));
		});

		p.add(view);
		SwingDisplayUtil.test();
	}
}
