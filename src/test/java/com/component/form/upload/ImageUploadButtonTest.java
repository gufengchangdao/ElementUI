package com.component.form.upload;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;

public class ImageUploadButtonTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			ImageUploadButton uploadButton = new ImageUploadButton();

			// 图片过滤
			uploadButton.setImageFilter(file -> {
				if (!file.isFile()) return "文件不存在";
				// 大于2M不要
				if (file.length() > 2 * Math.pow(2, 20)) return "大于2M不要";
				// 只要png
				if (!file.getName().endsWith(".png")) return "只要png";
				return null;
			});

			// 异常处理
			uploadButton.setErrProcess(e -> {
				System.out.println("抛出了异常");
				System.err.println(e.getMessage());
			});

			JButton button = new JButton("获取");
			button.addActionListener(e -> {
				// 设置大小
				uploadButton.setPreferredSize(new Dimension(200, 200));
				uploadButton.setSize(200, 200);
				Container contentPane = SwingTestUtil.getFrame().getContentPane();
				contentPane.validate();
				contentPane.repaint();

				// 数据获取
				System.out.println(uploadButton.getImagePath());
				System.out.println(uploadButton.getSuffix());
			});

			SwingTestUtil.test(uploadButton, button);
		});
	}
}
