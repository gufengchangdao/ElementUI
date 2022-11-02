package com.component.form.upload;

import com.component.util.SwingTestUtil;
import org.jdesktop.swingx.JXImageView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;

/**
 * 预览图书使用，暂时还不是组件，作为未来组件的参考对象
 */
public class JXImageViewTest extends JXImageView {
	private JFrame frame;
	// 用户当前是否按下Ctrl
	private boolean isPressCtrl = false;
	// 当前缩放倍率
	private float currentScale = 1f;
	// 缩放因子
	private static float ENLARGE_FACTORY = 0.1f;
	// 垂直方向平移因子
	private static int TRANSLATE_FACTORY = 8;
	// 当前图像中心Y值
	private int imageY = Integer.MAX_VALUE;
	private static String DEFAULT_TIP = "双击打开文件或将文件拖拽进来";
	private FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);

	/**
	 * @param frame 传入JFrame是为了添加监听Ctrl键盘的事件
	 */
	public JXImageViewTest(JFrame frame) {
		this.frame = frame;
		addListener();
	}

	private void addListener() {
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 17) //Ctrl
					isPressCtrl = true;
			}

			@Override
			public void keyReleased(KeyEvent e) {
				isPressCtrl = false;
			}
		});
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					//双击选择图像
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setFileFilter(new FileNameExtensionFilter("必须是图片文件", "jpg", "png"));
					if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
						try {
							setImage(new File(fileChooser.getSelectedFile().getAbsolutePath()));
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		});
		addMouseWheelListener(new MouseAdapter() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (isPressCtrl) {
					// 缩放
					currentScale -= ENLARGE_FACTORY * e.getWheelRotation();
					if (currentScale > 1) currentScale = 1;
					if (currentScale < 0.1) currentScale = 0;
					setScale(currentScale);
				} else {
					// 平移图像
					if (imageY == Integer.MAX_VALUE) {
						imageY = getHeight() / 2;
					}
					imageY -= TRANSLATE_FACTORY * e.getWheelRotation();
					setImageLocation(new Point2D.Float(getWidth() / 2, imageY));
				}
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		super.paintComponent(g);
		if (getImage() == null) {
			// 绘制提示文本
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			Font font = new Font("宋体", Font.BOLD, 20);
			int fontWidth = (int) font.getStringBounds(DEFAULT_TIP, frc).getWidth();
			g2.setFont(font);
			g2.drawString(DEFAULT_TIP, (getWidth() - fontWidth) / 2, (int) (getHeight() * 0.7));
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			SwingTestUtil.loadSkin();

			JFrame frame = SwingTestUtil.getFrame();
			JXImageViewTest test = new JXImageViewTest(frame);
			test.setPreferredSize(new Dimension(400, 400));
			SwingTestUtil.test(test);
		});
	}
}