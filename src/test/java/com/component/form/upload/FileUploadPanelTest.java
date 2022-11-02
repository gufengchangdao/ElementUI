package com.component.form.upload;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FileUploadPanelTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();

			FileUploadPanel panel = new FileUploadPanel("上传文件", "文件大小不要超过20M");

			// 文件选择
			panel.addFileSelectionListener(e -> {
				File file = (File) e.getSource();
				System.out.println(file);
			});

			JButton button = new JButton("获取");
			button.addActionListener(e -> {
				DefaultListModel<File> m = panel.getModel();
				for (int i = 0, len = m.size(); i < len; i++) {
					System.out.println(m.get(i));
				}

			});
			SwingTestUtil.test(panel, button);
		});
	}
}
