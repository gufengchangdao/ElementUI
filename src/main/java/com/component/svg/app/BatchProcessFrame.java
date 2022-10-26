package com.component.svg.app;

import com.component.basic.button.ButtonFactory;
import com.component.basic.color.ColorUtil;
import com.component.form.input.TipInputField;
import com.component.util.SwingTestUtil;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * 批量处理SVG
 */
public class BatchProcessFrame extends JFrame {
	private JFileChooser fc;
	private JPanel topPanel = new JPanel(new BorderLayout());
	private JPanel centerPanel = new JPanel();
	private JScrollPane bottomPanel;
	private JTextField dirField;
	private JButton dirButton;
	private JTextField targetField;
	private JButton targetButton;
	private JTextField packageField;
	private JButton button;
	private JTextPane logArea;

	public BatchProcessFrame() {
		initComponent();
		addListener();
		initView();
	}

	private void initView() {
		setTitle("svg批量转换工具");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(600, 500);
		Container contentPane = getContentPane();
		contentPane.add(topPanel, BorderLayout.NORTH);
		contentPane.add(centerPanel);
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		setLocationRelativeTo(null);
	}

	private void initComponent() {
		fc = new JFileChooser();
		// 上部分
		dirField = new TipInputField(35, "目录或文件名");//目录或文件名
		dirButton = new JButton("选择svg所在目录或文件");
		dirButton.setPreferredSize(new Dimension(160, 25));

		targetField = new TipInputField(35, "文件生成目录");//生成目录
		targetButton = new JButton("选择生成目录");
		targetButton.setPreferredSize(new Dimension(160, 25));

		packageField = new TipInputField(35, "类所在包名，例如 com.component");//类所在包名
		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel1.add(dirField);
		panel1.add(dirButton);
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel2.add(targetField);
		panel2.add(targetButton);
		JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel3.add(packageField);
		topPanel.add(panel1, BorderLayout.NORTH);
		topPanel.add(panel2);
		topPanel.add(panel3, BorderLayout.SOUTH);

		// 中部分

		button = ButtonFactory.createDefaultButton("转换", ColorUtil.SUCCESS);
		button.setPreferredSize(new Dimension(100, 40));

		centerPanel.add(button);

		// 下部分
		logArea = new JTextPane();
		logArea.setContentType("text/html");
		bottomPanel = new JScrollPane(logArea);
		bottomPanel.setPreferredSize(new Dimension(600, 300));
	}

	private void addListener() {
		dirButton.addActionListener(e -> {
			fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int choice = fc.showOpenDialog(this);
			if (choice == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				dirField.setText(file.getAbsolutePath());
			}
		});
		targetButton.addActionListener(e -> {
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int choice = fc.showOpenDialog(this);
			if (choice == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				targetField.setText(file.getAbsolutePath());
			}
		});
		button.addActionListener(e -> {
			File dirFile = new File(dirField.getText());
			if (!dirFile.exists()) {
				JOptionPane.showMessageDialog(this, "源文件路径不存在", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}

			File targetFile = "".equals(targetField.getText()) ? dirFile.getParentFile()
					: new File(targetField.getText());
			if (!targetFile.isDirectory()) {
				if (JOptionPane.showConfirmDialog(this, "生成目录不存在，是否创建对应目录", "提示", JOptionPane.YES_NO_OPTION)
						== JOptionPane.NO_OPTION) return;
				else targetFile.mkdirs();
			}
			String packageName = packageField.getText();
			if (packageName.startsWith("package ")) packageName = packageName.substring(8);
			if (packageName.endsWith(";")) packageName = packageName.substring(0, packageName.length() - 1);
			// 这里可以考虑换成SwingWorker
			new ConvertWork(dirFile, targetFile, packageName, button, logArea).execute();
			button.setEnabled(false);
		});
	}

	public static void main(String[] args) {
		SwingTestUtil.loadSkin();
		EventQueue.invokeLater(() -> new BatchProcessFrame().setVisible(true));
	}

}

