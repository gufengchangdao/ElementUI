package com.component.form.upload;

import com.component.basic.button.SimpleIconButton;
import com.component.radiance.common.api.icon.RadianceIcon;
import com.component.svg.upload.UploadImageSvg;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.ExceptionListener;
import java.io.File;
import java.io.IOException;
import java.util.function.Function;

/**
 * 提供图片上传功能的按钮
 * <p>
 * 功能有
 * <ul>
 *  <li>预览图片</li>
 *  <li>图片自适应按钮大小</li>
 *  <li>可以设置文件过滤、图片过滤</li>
 *  <li>异常处理</li>
 * <ul/>
 */
public class ImageUploadButton extends SimpleIconButton implements ActionListener {
	private RadianceIcon icon;
	/**
	 * 图片更换次数，这个类里面没有使用，但是其他类可以用于一些判断，比如是否选择图片或者是否更改过图片
	 */
	private int selectCount = 0;
	private JFileChooser fileChooser;
	private String imagePath;
	private String suffix;
	/** 存储选了一个图片后执行的操作，不过这里是单线程执行 */
	private Runnable task;
	/**
	 * 文件过滤器，用于限制图片的格式和大小。
	 * 文件选择器只能初步过滤，可以用输入文件路径跳过，这个过滤文件格式最安全。
	 * 通过返回 null ，否则返回错误信息(可以交给错误监听器来处理)
	 */
	private Function<File, String> imageFilter;
	/**
	 * 针对非法操作的异常处理，发生异常后会调用
	 * <p>
	 * 一般是因为文件不存在或图片读取失败
	 */
	private ExceptionListener errProcess;

	/** 调用 ImageUploadButton(RadianceIcon icon) 构造器 */
	public ImageUploadButton() {
		this(UploadImageSvg.of(104, 104));
	}

	public ImageUploadButton(RadianceIcon icon) {
		super(icon);
		this.icon = icon;
		init();
	}

	private void init() {
		setBorder(null);

		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("只支持png、jpg图片", "jpg", "png"));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			selectCount++;
			String path = fileChooser.getSelectedFile().getPath();
			try {
				File file = new File(path);
				// 过滤
				if (imageFilter != null) {
					String errMsg = imageFilter.apply(file);
					if (errMsg != null) throw new RuntimeException(errMsg);
				}

				imagePath = path;
				suffix = path.substring(path.lastIndexOf(".") + 1);
				setImage(ImageIO.read(file));
				if (task != null) task.run(); //执行该方法
			} catch (IOException | RuntimeException ex) { //文件不存在、图片读取失败或者过滤器过滤掉了
				if (errProcess != null) errProcess.exceptionThrown(ex);
				setIcon(icon);
			}
		}
	}

	public String getImagePath() {
		return imagePath;
	}

	public String getSuffix() {
		return suffix;
	}

	public Runnable getTask() {
		return task;
	}

	/**
	 * 设置任务，不同于点击按钮，该任务用于选择图片后执行的操作
	 * 这里没有使用多线程，也没有使用集合存储任务
	 *
	 * @param task 选择图片后执行的操作
	 */
	public void setTask(Runnable task) {
		this.task = task;
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

	@Override
	public void setPreferredSize(Dimension preferredSize) {
		icon.setDimension(preferredSize);
		super.setPreferredSize(preferredSize);
	}

	public Function<File, String> getImageFilter() {
		return imageFilter;
	}

	public void setImageFilter(Function<File, String> imageFilter) {
		this.imageFilter = imageFilter;
	}

	@Override
	public RadianceIcon getIcon() {
		return icon;
	}

	public JFileChooser getFileChooser() {
		return fileChooser;
	}

	public int getSelectCount() {
		return selectCount;
	}

	public ExceptionListener getErrProcess() {
		return errProcess;
	}

	public void setErrProcess(ExceptionListener errProcess) {
		this.errProcess = errProcess;
	}
}
