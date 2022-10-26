package com.component.svg.app;

import com.component.radiance.svgtranscoder.api.SvgTranscoder;
import com.component.radiance.svgtranscoder.api.TranscoderListener;
import com.component.radiance.svgtranscoder.api.java.JavaLanguageRenderer;

import javax.swing.*;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.concurrent.ExecutionException;

class ConvertWork extends SwingWorker<StringBuilder, String> {
	private final JButton button;
	private final JTextPane textArea;
	private final StringBuilder logBuilder = new StringBuilder(); //这个对象是用来作为doInBackground()返回值的
	private final StringBuilder errBuilder = new StringBuilder(PRE2);
	public static final String PRE1 = "<span style=\"color: green;\">";
	public static final String PRE2 = "<span style=\"color: red;font-weight: bold\">";

	private final File file;
	private final File targetPath;
	private final String packageName;

	public ConvertWork(File file, File targetPath, String packageName, JButton button, JTextPane textArea) {
		this.file = file;
		this.targetPath = targetPath;
		this.packageName = packageName;
		this.button = button;
		this.textArea = textArea;
	}

	@Override
	protected StringBuilder doInBackground() throws Exception {
		BasicFileAttributes basicFileAttributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
		if (basicFileAttributes.isRegularFile()) {
			processFile(file, targetPath, packageName);
		} else if (basicFileAttributes.isDirectory()) {
			File dir = new File(targetPath.getAbsolutePath() +
					File.separator + transformPackage(file.getName()));
			dir.mkdirs();
			recursionProcessFile(file, dir.getAbsolutePath(), packageName);
		}
		return logBuilder;
	}

	@Override
	protected void process(List<String> chunks) {
		if (isCancelled()) return;
		for (String s : chunks) {
			logBuilder.append(s).append("<br/>");
		}
		textArea.setText(PRE1 + logBuilder + "</span></html>");
	}

	@Override
	protected void done() {
		try {
			//  这里不调用也可以，这里对UI做最后的更新
			StringBuilder result = get(); //这里获取的 result 和 doInBackground()的返回值 是同一个对象

			// 输出错误信息
			if (!errBuilder.toString().equals(PRE2)) {
				errBuilder.append("</span></html>\n");
				if (result.length() != 0) result.append("</span><br/>");
				result.append(errBuilder);
				textArea.setText(PRE1 + result);
			} else {
				textArea.setText(PRE1 + result + "</span></html>");
			}

			JOptionPane.showConfirmDialog(null, "转换工作完成", "提示",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}

		button.setEnabled(true);
	}

	/**
	 * svg转java
	 *
	 * @param file        svg文件路径，不能是个目录
	 * @param targetDir   java文件输出路径，不存在时会创建目录
	 * @param packageName 类对应的包名
	 */
	public void processFile(File file, File targetDir, String packageName) {
		String filename = "";
		try {
			if (!file.exists())
				throw new IllegalArgumentException("file不存在");
			if (!file.isFile() || !file.getName().endsWith(".svg"))
				throw new IllegalArgumentException("file不是一个svg文件");
			if (targetDir != null && !targetDir.isDirectory()) targetDir.mkdirs();

			// 去除后缀的文件名
			String svgClassName = file.getName().substring(0, file.getName().length() - 4);
			svgClassName = toCamelCase(svgClassName) + "Svg";

			assert targetDir != null;
			// 文件全路径
			String javaClassFilename;
			if (targetDir.getAbsolutePath() != null)
				javaClassFilename = filename = targetDir.getAbsolutePath() + File.separator + svgClassName + ".java";
			else
				javaClassFilename = filename = file.getParent() + File.separator + svgClassName + ".java";
			try (PrintWriter pw = new PrintWriter(javaClassFilename);
			     // 使用指定模板
			     InputStream inputStream = BatchProcessFrame.class.getResourceAsStream(
					     "/radiance/tools/svgtranscoder/api/java/SvgTranscoderTemplateRadiance.templ")) {
				SvgTranscoder transcoder = new SvgTranscoder(file.toURI().toURL().toString(),
						svgClassName, new JavaLanguageRenderer());
				transcoder.setListener(new TranscoderListener() {
					public Writer getWriter() {
						return pw;
					}

					public void finished() {
						publish("生成文件 " + javaClassFilename);
					}
				});
				// 设置包名
				if (packageName != null && !"".equals(packageName)) transcoder.setPackageName(packageName);
				transcoder.transcode(inputStream);
			}
		} catch (Exception e) {
			errBuilder.append("转换文件失败：")
					.append(file)
					.append("<br/>")
					.append(e.getMessage().replace("<", "\"").replace(">", "\""))
					.append("<br/>");
			new File(filename).delete(); //失败了就删除创建的文件
		}
	}

	/**
	 * 递归处理目录下所有文件
	 *
	 * @param dir         文件所在目录
	 * @param targetPath  生成路径
	 * @param packageName 一级目录下文件的包名
	 */
	private void recursionProcessFile(File dir, String targetPath, String packageName) {
		File[] files = dir.listFiles();

		for (File f : files) {
			if (f.isFile() && f.getName().endsWith(".svg")) {
				processFile(f, new File(targetPath), packageName);
			} else if (f.isDirectory()) {
				String dirName = transformPackage(f.getName());
				recursionProcessFile(f, targetPath + File.separator + dirName,
						("".equals(packageName) ? "" : (packageName + ".")) + dirName);
			}
		}
	}

	/**
	 * 驼峰式命名法
	 */
	public static String toCamelCase(String s) {
		if (s == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder(s.length());
		boolean upperCase = false;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == '_' || c == '-' || c == ' ' || c == '=') {
				upperCase = true;
			} else if (upperCase || i == 0) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/** 对目录名转包名，为了省事，这里没有使用正则 */
	public static String transformPackage(String dir) {
		if (dir == null) return null;
		return dir.toLowerCase()
				.replace(" ", "")
				.replace("_", "")
				.replace("-", "");
	}

}
