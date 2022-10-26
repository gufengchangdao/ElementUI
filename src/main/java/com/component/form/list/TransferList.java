package com.component.form.list;

import com.component.basic.border.RoundBorder;
import com.component.basic.color.ColorUtil;
import com.component.common.component.BaseComponent;
import com.component.form.list.renderer.CheckBoxCellRenderer;
import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.VerticalLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 穿梭框
 */
public class TransferList extends BaseComponent implements ActionListener {
	private JComponent box1 = (JComponent) Box.createVerticalStrut(6);
	private JComponent box2 = (JComponent) Box.createVerticalStrut(6);
	/** 标题复选框 */
	private JCheckBox titleCheckBox;
	/** 标题右侧计数标签 */
	private JLabel numberLabel;
	/** 传入数据 */
	private List<String> data;
	private JList<String> list;
	private DefaultListModel<String> listModel;
	/** list 的高度 */
	private int listHeight = 100;
	/** 搜索输入框 */
	private JXSearchField searchField;
	private JScrollPane scrollPane;

	public TransferList(List<String> data) {
		this(null, false, null, data);
	}

	public TransferList(String title, List<String> data) {
		this(title, false, null, data);
	}

	/**
	 * @param title             穿梭框标题
	 * @param isAbleSearch      是否显示搜索框
	 * @param searchPlaceholder 搜索框提示文本
	 * @param data              列表数据
	 */
	public TransferList(String title,
	                    boolean isAbleSearch,
	                    String searchPlaceholder,
	                    List<String> data) {
		this.data = data;
		if (title != null) {
			titleCheckBox = new JCheckBox(title);
		}
		if (isAbleSearch) {
			searchField = new JXSearchField();
			if (searchPlaceholder != null) searchField.setPrompt(searchPlaceholder);
		}
		listModel = new DefaultListModel<>();
		for (String d : data) listModel.addElement(d);
		init();
	}

	private void init() throws RuntimeException {
		// setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setLayout(new VerticalLayout());
		// 顶部
		// 应用了边框布局管理器，设置外边距
		JPanel topPanel = new JPanel(new BorderLayout()) {
			@Override
			public Insets getInsets() {
				return new Insets(3, 0, 3, 10);
			}
		};
		if (titleCheckBox != null) {
			topPanel.add(titleCheckBox, BorderLayout.WEST);
		}
		numberLabel = new JLabel("0/" + listModel.size());
		numberLabel.setForeground(ColorUtil.PLACEHOLDER_TEXT);

		topPanel.add(numberLabel, BorderLayout.EAST);
		topPanel.setBackground(ColorUtil.BORDER_LEVEL1);
		// 搜索框
		// 设置延迟，拒绝频繁查询
		if (searchField != null) {
			searchField.setInstantSearchDelay(400);
			searchField.setBorder(new RoundBorder(-1));
			searchField.setOpaque(true);
			searchField.setBackground(Color.WHITE);
		}

		// 列表
		if (listModel != null) {
			list = new JList<>(listModel);
			list.setCellRenderer(new CheckBoxCellRenderer());
		}

		box1.setOpaque(true);
		Color color = UIManager.getColor("List.background");
		box1.setBackground(color);
		box2.setOpaque(true);
		box2.setBackground(color);

		add(topPanel);
		add(box1);
		if (searchField != null) {
			add(searchField);
			add(box2);
		}
		if (list != null) {
			scrollPane = new JScrollPane(list);//绑定滚动条
			Dimension size = list.getPreferredSize();
			size.height = listHeight;
			scrollPane.setPreferredSize(size);//显示区域
			scrollPane.setBorder(null);
			add(scrollPane);
		}

		addListener();

		// 设置一个默认大小，不然组件大小会在列表数据清空时改变
		setPreferredSize(new Dimension(200, 300));
	}

	private void addListener() {
		if (titleCheckBox != null) {
			titleCheckBox.addActionListener(e -> {
				if (titleCheckBox.isSelected()) {
					list.setSelectedIndices(IntStream.rangeClosed(0, listModel.getSize()).toArray());
					numberLabel.setText(listModel.getSize() + "/" + listModel.getSize());
				} else {
					list.setSelectedIndices(new int[0]);
					numberLabel.setText("0/" + listModel.getSize());
				}
			});
		}
		if (list != null) {
			list.addListSelectionListener(e -> {
				if (e.getValueIsAdjusting()) return;
				int selectedCount = list.getSelectedIndices().length;
				if (titleCheckBox != null) {
					if (selectedCount == listModel.getSize()) {
						titleCheckBox.setSelected(true);
					} else {
						if (titleCheckBox.isSelected()) titleCheckBox.setSelected(false);
					}
				}
				numberLabel.setText(selectedCount + "/" + listModel.getSize());
			});
		}
		if (searchField != null) {
			searchField.addActionListener(this);
		}
	}

	public String getText() {
		return searchField.getText();
	}

	public JCheckBox getTitleCheckBox() {
		return titleCheckBox;
	}

	public JLabel getNumberLabel() {
		return numberLabel;
	}

	public JList<String> getList() {
		return list;
	}

	public DefaultListModel<String> getListModel() {
		return listModel;
	}

	public JXSearchField getSearchField() {
		return searchField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String content = e.getActionCommand();
		listModel.removeAllElements();
		for (String s : data.stream().filter(s -> s.contains(content)).collect(Collectors.toList())) {
			listModel.addElement(s);
		}
	}

	public int getListHeight() {
		return listHeight;
	}

	/** 设置列表高度 */
	public void setListHeight(int listHeight) {
		this.listHeight = listHeight;
		Dimension size = list.getPreferredSize();
		size.height = listHeight;
		System.out.println(list.getParent());
		scrollPane.setPreferredSize(size);
	}

}
