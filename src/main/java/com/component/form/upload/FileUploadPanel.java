package com.component.form.upload;

import com.component.basic.button.ButtonFactory;
import com.component.basic.color.ColorUtil;
import org.jdesktop.swingx.VerticalLayout;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 文件上传面板
 * <p>
 * 组成有上传按钮、提示文本、已选文件列表
 * <p>
 * 注意，已选文件列表并没有去重，如果想要实现去重的功能，建议自定义JListModel
 */
public class FileUploadPanel extends JPanel implements ActionListener, MouseMotionListener, ListSelectionListener {
	private JButton uploadButton;
	private JFileChooser fc = new JFileChooser();
	/** 提示文本 */
	private JLabel tipLabel;
	/** 单元格渲染器 */
	private FileUploadItem cellRenderer;
	private DefaultListModel<File> model;
	private JList<File> fileList;
	/** 文件选择监听器，source是选择的File对象 */
	private List<ActionListener> fileSelectionListeners = new LinkedList<>();

	/** 默认大小 */
	public static final Dimension DEFAULT_SIZE = new Dimension(360, 250);

	public FileUploadPanel() {
		init("上传文件", null);
	}

	/**
	 * 创建上传文件的面板，含有上传按钮、提示文本、已选文件列表
	 *
	 * @param buttonText 按钮文本
	 * @param tip        关于上传文件的提示
	 */
	public FileUploadPanel(String buttonText, String tip) {
		init(buttonText, tip);
	}

	private void init(String buttonText, String tip) {
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (buttonText != null)
			uploadButton = ButtonFactory.createDefaultButton(buttonText, ColorUtil.PRIMARY);
		else
			uploadButton = ButtonFactory.createDefaultButton("点击上传", ColorUtil.PRIMARY);
		if (tip != null) {
			tipLabel = new JLabel(tip);
			tipLabel.setForeground(ColorUtil.SECONDARY_TEXT);
		}

		model = new DefaultListModel<>();
		fileList = new JList<>(model);
		// 设置渲染器并设置单元格的宽度与列表一致
		cellRenderer = new FileUploadItem(DEFAULT_SIZE.width);
		fileList.setCellRenderer(cellRenderer);
		fileList.setBackground(null);

		JScrollPane scrollPane = new JScrollPane(fileList);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBorder(null);

		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(uploadButton);

		addListener();

		setLayout(new VerticalLayout(5));
		add(topPanel, BorderLayout.NORTH);
		if (tipLabel != null) add(tipLabel, BorderLayout.CENTER);
		add(scrollPane);

		setPreferredSize(DEFAULT_SIZE);
	}

	@Override
	public void setPreferredSize(Dimension preferredSize) {
		super.setPreferredSize(preferredSize);
		cellRenderer.setMaxWidth(preferredSize.width);
	}

	private void addListener() {
		uploadButton.addActionListener(this);

		fileList.addMouseMotionListener(this);
		// 点击移除文件
		fileList.addListSelectionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (fc.showOpenDialog(this) == JFileChooser.OPEN_DIALOG) {
			File file = fc.getSelectedFile();

			// 执行事件
			for (ActionListener l : fileSelectionListeners) {
				l.actionPerformed(new ActionEvent(file, -1, null));
			}

			model.addElement(file);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Point p = new Point(e.getX(), e.getY());
		int index = fileList.locationToIndex(p);
		if (index != cellRenderer.getHoveredIndex()) {
			cellRenderer.setHoveredIndex(index);
			fileList.repaint();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// 注意当用户单击某行时,您会收到两个valueChanged事件
		int selectedIndex = fileList.getSelectedIndex();
		if (selectedIndex == -1) return;
		if (!fileList.getValueIsAdjusting()) model.remove(selectedIndex);

		//这个不判断也不会报错，一行代码解决问题
		// model.removeElement(fileList.getSelectedValue());
	}

	public JButton getUploadButton() {
		return uploadButton;
	}

	public JFileChooser getFc() {
		return fc;
	}

	public JLabel getTipLabel() {
		return tipLabel;
	}

	public FileUploadItem getCellRenderer() {
		return cellRenderer;
	}

	public DefaultListModel<File> getModel() {
		return model;
	}

	public JList<File> getFileList() {
		return fileList;
	}

	public void setFc(JFileChooser fc) {
		this.fc = fc;
	}

	public void setTipLabel(JLabel tipLabel) {
		this.tipLabel = tipLabel;
	}

	public void addFileSelectionListener(ActionListener listener) {
		fileSelectionListeners.add(listener);
	}

	public void removeFileSelectionListener(ActionListener listener) {
		fileSelectionListeners.remove(listener);
	}

	public void clearFileSelectionListener() {
		fileSelectionListeners.clear();
	}

	/**
	 * 不可修改的文件选择监听器列表
	 */
	// 我不知道这里是不是该返回不可变集合
	public List<ActionListener> getFileSelectionListeners() {
		return Collections.unmodifiableList(fileSelectionListeners);
	}
}
