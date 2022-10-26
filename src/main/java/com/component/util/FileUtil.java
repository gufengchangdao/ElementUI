package com.component.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * 文件处理工具类
 */
public class FileUtil {
	/**
	 * 读取Blob对象中数据并返回byte数组
	 *
	 * @param file 要读取的Blob对象
	 * @return byte数据 或者 null(如果Blob对象为空)
	 * @throws RuntimeException 读取、关闭异常
	 */
	public static byte[] blobToType(Blob file) throws RuntimeException {
		if (file == null) return null;
		try (InputStream is = file.getBinaryStream();
		     ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			int len;
			byte[] buf = new byte[1024];
			while ((len = is.read(buf)) != -1) {
				baos.write(buf, 0, len);
			}
			return baos.toByteArray();
		} catch (SQLException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 读取指定路径的文件数据
	 *
	 * @param filepath 文件的完整路径
	 * @return 字节数组
	 */
	public static byte[] readFileByPath(String filepath) throws RuntimeException {
		try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(filepath));
		     ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream()) {
			int len;
			byte[] buf = new byte[1024];
			while ((len = inputStream.read(buf)) != -1) {
				byteOutStream.write(buf, 0, len);
			}
			return byteOutStream.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 字节数组转BufferedImage对象
	 *
	 * @param imageData 字节数组
	 * @return BufferedImage对象
	 */
	public static BufferedImage byteToImage(byte[] imageData) throws RuntimeException {
		try (ByteArrayInputStream byteInputStream = new ByteArrayInputStream(imageData)) {
			return ImageIO.read(byteInputStream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * BufferedImage对象转字节数组
	 *
	 * @param imageData BufferedImage对象
	 * @param suffix    图像后缀
	 * @return 字节数组
	 * @throws RuntimeException 转换失败
	 */
	public static byte[] imageToByte(BufferedImage imageData, String suffix) throws RuntimeException {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			ImageIO.write(imageData, suffix, baos);
			return baos.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 读取数组中数据并保存到电脑上
	 *
	 * @param fileData 字节数组
	 * @param filePath 要保存文件的全路径
	 * @return 成功就返回true，失败则抛出异常，不会返回false
	 * @throws RuntimeException 失败时抛出该异常
	 */
	public static boolean byteToFile(byte[] fileData, String filePath) throws RuntimeException {
		try (BufferedOutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(Paths.get(filePath)))) {
			outputStream.write(fileData);
			outputStream.flush();
			return true;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean saveImage(BufferedImage image, String suffix, File output) throws RuntimeException {
		try {
			if (ImageIO.write(image, suffix, output)) return true;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return false;
	}

	/**
	 * 条件删除指定目录下所有文件
	 *
	 * @param dir 目录
	 */
	public static void deleteFileDir(String dir) throws IOException {
		Path path = Paths.get(dir);
		// Files.walkFileTree(path, new SimpleFileVisitor<>() {
		// 			// 遍历删除文件
		// 			@Override
		// 			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		// 				Files.delete(file);
		// 				System.out.printf("文件被删除 : %s%n", file);
		// 				return FileVisitResult.CONTINUE;
		// 			}
		// 			// 不操作文件夹
		// 			// @Override
		// 			// public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		// 			// 	Files.delete(dir);
		// 			// 	System.out.printf("文件夹被删除: %s%n", dir);
		// 			// 	return FileVisitResult.CONTINUE;
		// 			// }
		// 		}
		// );
		Files.walkFileTree(path, new FileVisitor<Path>() {
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				return null;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Files.delete(file);
				System.out.printf("文件被删除 : %s%n", file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
				return null;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				return null;
			}
		});
	}
}
