package com.component.form.select;

import com.component.basic.color.ColorUtil;
import com.component.common.component.BaseComponent;
import org.jdesktop.swingx.WrapLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * 带有下拉列表的多选器
 * <p>
 * 注意：
 * 设置无选项时组件的大小使用setmSize()方法而不是setPreferredSize
 * <p>
 * 有一个小问题，当移除面板中的选项时，面板不会立刻缩小，还得点一下才行，可能是WrapLayout布局管理器的问题，暂时不解决
 */
public class MultiSelector extends BaseComponent implements ActionListener, MouseListener {
	private JPanel panel;
	private JComboBox<String> comboBox;
	/** 传入数据，进行了保护性拷贝 */
	private Vector<String> data;
	/** 所选内容 */
	private DefaultComboBoxModel<String> boxModel;
	private WrapLayout layout = new WrapLayout();
	/** 面板的最小大小，即没有选择任何选项时的大小 */
	private Dimension mSize;

	/**
	 * 定长的多选器，大小为(80, 29)，最好自己设置输入框的大小
	 */
	public MultiSelector(Vector<String> data) {
		this(data, new Dimension(80, 29));
	}

	public MultiSelector(List<String> data, Dimension mSize) {
		this.data = new Vector<>(data);
		this.mSize = mSize;

		panel = new JPanel(layout);
		panel.setBounds(0, 0, mSize.width, mSize.height);
		panel.setBorder(BorderFactory.createLineBorder(ColorUtil.BORDER_LEVEL1));

		boxModel = new DefaultComboBoxModel<>(this.data);
		comboBox = new JComboBox<>(boxModel);

		comboBox.setBounds(0, 0, mSize.width, mSize.height);
		comboBox.addActionListener(this);

		validate();
		setLayout(null);

		// 鼠标点击时弹出建议
		panel.addMouseListener(this);

		add(panel);
		add(comboBox);
	}

	@Override
	public void setPreferredSize(Dimension preferredSize) {
		super.setPreferredSize(preferredSize);
		panel.setSize(preferredSize.width, preferredSize.height);
		comboBox.setSize(preferredSize.width, preferredSize.height);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		comboBox.showPopup();
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
	public void actionPerformed(ActionEvent e) {
		Object item = boxModel.getSelectedItem();
		if (item == null) return; // 我也不知道什么时候为null，但是有@Nullable
		JCheckBox checkBox = new JCheckBox(item.toString());

		checkBox.addActionListener(e2 -> {
			// 点击后重新放回下拉列表
			checkBox.removeActionListener(this);
			panel.remove(checkBox);

			validate();
			panel.validate();
			panel.repaint();

			// 这里使用data添加而不是boxModel是因为当全部选中后再取消选中，取消的内容会立即再次进入面板，不知道为何
			// 使用data添加不会有该问题，本来data只是打算用来获取列表中剩余数据的
			data.add(checkBox.getText());
			// boxModel.addElement(checkBox.getText());
		});
		checkBox.setSelected(true);

		// 去除下拉列表中的选择
		boxModel.removeElement(item);

		// 将该项添加到面板中
		panel.add(checkBox);
		panel.validate();

		validate();
	}

	/**
	 * 重新设置组件大小
	 */
	public void validate() {
		Dimension layoutSize = layout.preferredLayoutSize(panel);
		layoutSize.width = Math.max(layoutSize.width + 5, mSize.width);
		layoutSize.height = Math.max(layoutSize.height + 5, mSize.height);
		setPreferredSize(layoutSize);
	}

	public JComboBox<String> getComboBox() {
		return comboBox;
	}

	public List<String> getSelectItem() {
		return Arrays.stream(panel.getComponents())
				.filter(component -> component instanceof JCheckBox)
				.map(component -> ((JCheckBox) component).getText())
				.collect(Collectors.toList());
	}

	public List<String> getUnselectedItem() {
		return data;
	}

	public DefaultComboBoxModel<String> getBoxModel() {
		return boxModel;
	}

	@Override
	public Dimension getSize() {
		return mSize;
	}

	public Dimension getmSize() {
		return mSize;
	}

	public void setmSize(Dimension mSize) {
		this.mSize = mSize;
		validate();
	}
}
