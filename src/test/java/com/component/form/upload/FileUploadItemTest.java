package com.component.form.upload;

import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FileUploadItemTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();
			JList<File> list = new JList<>(new File[]{new File("file1.png"), new File("file2.png"), new File("file3.png")});
			list.setCellRenderer(new FileUploadItem());
			JButton b = new JButton("添加");
			b.addActionListener(e -> {
			});
			SwingTestUtil.test(list, b);
		});
	}
}
