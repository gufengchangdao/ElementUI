package com.component.form.upload;

import com.component.basic.color.ColorUtil;
import com.component.common.component.BaseComponent;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.svg.icon.regular.CheckCircleSvg;
import com.component.svg.icon.regular.FileSvg;
import com.component.svg.icon.regular.XCircleSvg;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * 显示已经上传的文件，应该配合JList使用
 *
 * <p>
 * 这里其实也可以使用IconInputField来实现
 */
public class FileUploadItem extends BaseComponent implements ListCellRenderer<File> {
	private RadianceIcon fileIcon = FileSvg.of(16, 16);
	private RadianceIcon successIcon = CheckCircleSvg.of(16, 16);
	private RadianceIcon delIcon = XCircleSvg.of(16, 16);
	/** 左侧标签 */
	private JLabel leftLabel;
	/** 中间文本 */
	private JLabel textLabel;
	/** 右侧图标 */
	private JLabel rightLabel;
	/** 组件的最大宽度，当文本长度过长时根据这个调整组件大小，没有限制就填-1 ，默认值为-1 */
	private int maxWidth = -1;
	/** 鼠标悬停在对应单元格上的索引值，要实现鼠标悬停时修改单元格背景请对JList添加MouseMotionListener，在其中修改该属性 */
	private int hoveredIndex = -1;

	public FileUploadItem() {
		init();
	}

	public FileUploadItem(int maxWidth) {
		this.maxWidth = maxWidth;
		init();
	}

	public FileUploadItem(RadianceIcon fileIcon, RadianceIcon successIcon) {
		this.fileIcon = fileIcon;
		this.successIcon = successIcon;
		init();
	}

	private void init() {
		fileIcon.setColorFilter(color -> ColorUtil.INFO);
		successIcon.setColorFilter(color -> ColorUtil.SUCCESS);
		delIcon.setColorFilter(color -> ColorUtil.INFO);

		setLayout(new BorderLayout(5, 5));

		leftLabel = new JLabel(fileIcon);
		textLabel = new JLabel();
		rightLabel = new JLabel(successIcon);

		leftLabel.setOpaque(true);
		textLabel.setOpaque(true);

		if (maxWidth > 0) {
			Dimension d = textLabel.getPreferredSize();
			d.width = Math.min(maxWidth, Math.max(0, d.width - leftLabel.getWidth() - rightLabel.getWidth()));
			textLabel.setPreferredSize(d);
		}

		add(leftLabel, BorderLayout.WEST);
		add(textLabel);
		add(rightLabel, BorderLayout.EAST);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends File> list, File value, int index, boolean isSelected, boolean cellHasFocus) {
		Color background;
		Color foreground;
		// check if this cell represents the current DnD drop location
		JList.DropLocation dropLocation = list.getDropLocation();
		if (dropLocation != null && !dropLocation.isInsert() && dropLocation.getIndex() == index) {
			background = ColorUtil.PRIMARY;
			foreground = ColorUtil.BACKGROUND;

			rightLabel.setIcon(successIcon);
		} else if (hoveredIndex == index) { //悬停
			background = ColorUtil.BORDER_LEVEL2;
			foreground = ColorUtil.PRIMARY;

			rightLabel.setIcon(delIcon);
		} else if (isSelected) { //选中
			background = ColorUtil.BORDER_LEVEL1;
			foreground = ColorUtil.PRIMARY;

			rightLabel.setIcon(delIcon);
		} else { // unselected, and not the DnD drop location
			background = ColorUtil.BACKGROUND;
			foreground = ColorUtil.PRIMARY_TEXT;

			rightLabel.setIcon(successIcon);
		}

		textLabel.setBackground(background);
		leftLabel.setBackground(background);

		textLabel.setForeground(foreground);

		textLabel.setText(value.getName());

		return this;
	}

	public Icon getFileIcon() {
		return fileIcon;
	}

	public void setFileIcon(RadianceIcon fileIcon) {
		this.fileIcon = fileIcon;
	}

	public RadianceIcon getSuccessIcon() {
		return successIcon;
	}

	public RadianceIcon getDelIcon() {
		return delIcon;
	}

	public JLabel getLeftLabel() {
		return leftLabel;
	}

	public JLabel getTextLabel() {
		return textLabel;
	}

	public JLabel getRightLabel() {
		return rightLabel;
	}

	public int getHoveredIndex() {
		return hoveredIndex;
	}

	/**
	 * 更新鼠标悬停单元格的索引值，将根据该值对对应单元格进行渲染
	 */
	public void setHoveredIndex(int hoveredIndex) {
		this.hoveredIndex = hoveredIndex;
	}

	public int getMaxWidth() {
		return maxWidth;
	}

	/** 重新设置标签最大宽度 */
	public void setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
		Dimension d = textLabel.getPreferredSize();
		d.width = Math.min(maxWidth, Math.max(0, d.width - leftLabel.getWidth() - rightLabel.getWidth()));
		textLabel.setPreferredSize(d);
	}
}
