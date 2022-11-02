package lab.component.combobox;

import com.component.util.SwingTestUtil;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * 使用html简单实现添加下拉列表右侧文本
 */
public class LRComboBox {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JPanel p = SwingTestUtil.init(new MigLayout());

			DefaultComboBoxModel<PairItem> model = new DefaultComboBoxModel<>();
			model.addElement(new PairItem("aaa", "846876"));
			model.addElement(new PairItem("bbb bbb", "123456"));
			model.addElement(new PairItem("cc cc cc", "iop.23456789"));
			model.addElement(new PairItem("dd dd dd", "64345424684"));
			model.addElement(new PairItem("eee eee", "98765432210"));
			JComboBox<PairItem> combo = new JComboBox<>(model);
			p.add(combo);

			SwingTestUtil.test();
		});
	}
}

class PairItem {
	private final String leftText;
	private final String rightText;

	protected PairItem(String strLeft, String strRight) {
		leftText = strLeft;
		rightText = strRight;
	}

	public String getHtmlText() {
		// 这里指定了表格宽度，大小需要根据文本长度而定
		return String.format("<html><table width='290'><tr><td align='left'>%s</td><td align='right'>%s</td></tr></table></html>", leftText, rightText);
	}

	public String getLeftText() {
		return leftText;
	}

	public String getRightText() {
		return rightText;
	}

	// 这个很重要
	@Override
	public String toString() {
		return getHtmlText();
	}
}