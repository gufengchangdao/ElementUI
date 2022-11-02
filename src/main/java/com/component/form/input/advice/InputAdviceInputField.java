package com.component.form.input.advice;

import com.component.common.component.BaseComponent;
import com.component.form.input.model.InputComboBoxModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 带输入建议的输入框
 * <p>
 * 支持泛型，选项内容就是对象 T 的toString()表示，与之相关的还有两个函数，一个containsFunction和parseTextFunction，
 * 分别用于判断列表中元素是否包含输入框内容，和T元素写入到输入框。如果 T 类型不是String，则应该设置这两个函数
 * <p>
 * 之前尝试使用菜单实现这一功能， 对输入框添加鼠标事件，左键时在输入框正下方显示下拉列表，但是点击列表项无法触发点击事件，也就无法正确更改输入框内容
 */
public class InputAdviceInputField<T> extends BaseComponent implements ActionListener,
		MouseListener, DocumentListener, FocusListener, Comparator<String> {
	private JTextField textField;
	private JComboBox<T> comboBox;
	/** 传入数据，进行了保护性拷贝 */
	private List<T> data;
	private InputComboBoxModel<T> boxModel;
	/** 判断函数，用于判断第一个参数中是否包含第二个参数(输入框内容)，默认使用toString().contains()判断 */
	private BiFunction<T, String, Boolean> containsFunction;
	/** 将 T 对象解析为字符串内容 */
	private Function<T, String> parseTextFunction;
	/** 当输入框失去焦点时是否接受输入的值，不合格返回空串，否则返回格式化后的内容 */
	private Function<String, String> checkInputFunction;
	/** 所选对象，不建议使用model和combox，因为每次展开列表时都会清空列表项，然后根据输入内容筛选添加，这个过程也会清空所选项 */
	private T selectedItem;

	/** 给String类型调用 */
	public InputAdviceInputField(int columns, List<T> data) {
		this(columns, data, null);
	}

	/**
	 * 带输入建议的输入框
	 *
	 * @param columns 用于计算首选宽度的列数;如果columns被设置为零，首选宽度将是组件实现的自然结果
	 * @param data    建议列表
	 */
	public InputAdviceInputField(int columns, List<T> data, Function<T, String> parseTextFunction) {
		if (parseTextFunction != null) this.parseTextFunction = parseTextFunction;
		textField = new JTextField(columns);
		Dimension size = textField.getPreferredSize();
		textField.setBounds(0, 0, size.width, size.height);

		if (data != null) {
			this.data = new LinkedList<>(data);
			boxModel = new InputComboBoxModel<>();
			for (T d : data) boxModel.addElement(d);
			comboBox = new JComboBox<>(boxModel);
			comboBox.setBounds(0, 0, size.width, size.height);
			comboBox.addActionListener(this);
		}

		super.setPreferredSize(new Dimension(size.width, size.height));
		setLayout(null);


		// 鼠标失去焦点时检测输入是否合法
		textField.addFocusListener(this);
		// 鼠标点击时弹出建议
		textField.addMouseListener(this);

		// 所选值改变时执行，但是这样下拉列表第一个就得设为空串，不然开始的时候选第一个没效果
		// comboBox.addItemListener(e -> {
		// 	if (e.getStateChange() != ItemEvent.SELECTED) return;
		// 	textField.setText(e.getItem().toString());
		// });
		// 文本改变时更新建议
		textField.getDocument().addDocumentListener(this);

		add(textField);
		if (comboBox != null) add(comboBox);
	}

	/** 弹出建议列表 */
	private void showAdvice() {
		if (comboBox == null) return;

		// 过滤的方式，过滤掉无关内容
		String keyword = textField.getText();
		selectedItem = boxModel.getSelectedItem();
		//会清空列表的所有内容的，这个也会清空所选内容
		boxModel.removeAllElements();
		boxModel.addAll(data.stream()//类似于一次拷贝
				.filter(t -> {
					if (containsFunction != null)
						return containsFunction.apply(t, keyword);
					else
						return t.toString().contains(keyword);
				})
				.collect(Collectors.toList()));

		// 排序的方式，包含的排在前面
		// data.sort(this);

		// 这个不可少！不管是使用排序还是过滤，没有这个排序会出现相同项，过滤情况下选项只少不多
		comboBox.updateUI();
		comboBox.showPopup();

	}

	public String getText() {
		return textField.getText();
	}

	@Override
	public void setPreferredSize(Dimension preferredSize) {
		super.setPreferredSize(preferredSize);
		textField.setSize(preferredSize.width, preferredSize.height);
		if (comboBox != null) comboBox.setSize(preferredSize.width, preferredSize.height);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		showAdvice();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		showAdvice();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		showAdvice();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (comboBox == null) return;
		selectedItem = boxModel.getSelectedItem();
		if (selectedItem == null) return; // 我也不知道什么时候为null，但是有@Nullable
		textField.setText(parseTextFunction == null ? selectedItem.toString() : parseTextFunction.apply(selectedItem));
	}

	/**
	 * 获取选中项，不要使用model和comboBox的getSelectedItem()方法，使用这个方法代替
	 * <p>
	 * 需要注意的是，这个是获取所选项，如果没有选择单元格，就算和单元格内容一样也是返回null
	 */
	public T getSelectedItem() {
		return selectedItem;
	}

	public JTextField getTextField() {
		return textField;
	}

	public JComboBox<T> getComboBox() {
		return comboBox;
	}

	public List<T> getData() {
		return data;
	}

	public InputComboBoxModel<T> getBoxModel() {
		return boxModel;
	}

	/**
	 * 如果使用排序的方式更新列表，就使用这个比较器，比较o1和o2哪个与输入内容最接近
	 *
	 * @param o1 the first object to be compared.
	 * @param o2 the second object to be compared.
	 * @return 顺序关系
	 * @deprecated 这个方法是在添加泛型之前写的，虽然没有使用，但是我没有删
	 */
	@Override
	@Deprecated
	public int compare(String o1, String o2) {
		String keyword = textField.getText();

		boolean b1 = o1.contains(keyword);
		boolean b2 = o2.contains(keyword);
		if (b1 && !b2) return -1;
		if (!b1 && b2) return 1;
		if (b1 && b2) return o1.length() - o2.length(); //如果都含有，越短越在前面
		return 0; //否则视为相等
	}

	public BiFunction<T, String, Boolean> getContainsFunction() {
		return containsFunction;
	}

	/** 添加函数判断，用于判断元素之间的包含关系，如果第一个参数包含第二个参数就返回true */
	public void setContainsFunction(BiFunction<T, String, Boolean> containsFunction) {
		this.containsFunction = containsFunction;
	}

	public Function<T, String> getParseTextFunction() {
		return parseTextFunction;
	}

	public void setParseTextFunction(Function<T, String> parseTextFunction) {
		this.parseTextFunction = parseTextFunction;
	}

	public Function<String, String> getCheckInputFunction() {
		return checkInputFunction;
	}

	public void setCheckInputFunction(Function<String, String> checkInputFunction) {
		this.checkInputFunction = checkInputFunction;
	}

	@Override
	public void focusGained(FocusEvent e) {

	}

	@Override
	public void focusLost(FocusEvent e) {
		if (checkInputFunction != null)
			textField.setText(checkInputFunction.apply(textField.getText()));
	}
}
