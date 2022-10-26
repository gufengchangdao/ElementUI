package com.component.basic.border;

import com.component.common.SwingPosition;

import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.GeneralPath;

/**
 * 切角边框
 * <p>
 * 该边框一般不单独使用，因为含有切角的组件一般都设置了背景色，但是背景色会覆盖边框的绘制，建议使用{@link com.component.common.component.AngleComponent}
 */
public class AngleBorder implements Border {

	private final GeneralPath generalPath = new GeneralPath();
	/** 展示方式 */
	private SwingPosition position;
	/** 三角形背景色 */
	private Color color;
	/** 等腰三角形的高度，也是边框的边距 */
	private int size = 10;
	/** 圆角大小 */
	private int angle = 2;
	/** 三角形相对默认位置的偏移 */
	private Point offset = new Point(0, 0);

	public AngleBorder(SwingPosition position, Color color) {
		this.position = position;
		this.color = color;
	}

	public AngleBorder(SwingPosition position, Color color, int size) {
		this.position = position;
		this.color = color;
		this.size = size;
	}

	public AngleBorder(SwingPosition position, Color color, int size, int angle) {
		this.position = position;
		this.color = color;
		this.size = size;
		this.angle = angle;
	}

	public AngleBorder(SwingPosition position, Color color, int size, int angle, Point offset) {
		this.position = position;
		this.color = color;
		this.size = size;
		this.angle = angle;
		this.offset = offset;
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

		// 定位坐标原点
		double tx = 0, ty = 0;
		switch (position) {
			case TOP: { // 上边
				tx = width / 2.0 - size;
				break;
			}
			case TOP_LEFT: { // 上左
				tx = width / 4.0 - size;
				break;
			}
			case TOP_RIGHT: { // 上右
				tx = width * 3.0 / 4 - size;
				break;
			}
			case BOTTOM:
			case BOTTOM_LEFT: { // 下边
				tx = width / 2.0 - size;
				ty = height - size;
				break;
			}// 下左
			case BOTTOM_RIGHT: { // 下右
				tx = width * 3.0 / 4 - size;
				ty = height - size;
				break;
			}
			case LEFT: { // 左边
				tx = 0.3;
				ty = height / 2.0 - size;
				break;
			}
			case LEFT_TOP: {
				tx = 0.3;
				ty = height / 4.0 - size;
				break;
			}
			case LEFT_BOTTOM: {
				tx = 0.3;
				ty = height * 3.0 / 4 - size;
				break;
			}
			case RIGHT: {
				tx = width - size * 2 - 0.3; //这是为了消除边框与组件可能存在的缝隙
				ty = height / 2.0 - size;
				break;
			}
			case RIGHT_TOP: {
				tx = width - size * 2 - 0.4;
				ty = height / 4.0 - size;
				break;
			}
			case RIGHT_BOTTOM: {
				tx = width - size * 2 - 0.4;
				ty = height * 3.0 / 4 - size;
				break;
			}
		}

		tx += offset.x;
		ty += offset.y;
		g2d.translate(tx, ty);

		// 绘图
		generalPath.reset();

		// 向上
		generalPath.moveTo(0, size);
		generalPath.lineTo(size - angle, angle);
		generalPath.curveTo(size - angle, angle, size, 0, size + angle, angle);
		generalPath.lineTo(size * 2, size);

		switch (position) {
			case BOTTOM:
			case BOTTOM_LEFT:
			case BOTTOM_RIGHT: {
				g2d.rotate(Math.toRadians(180), size, size / 2.0);
				break;
			}
			case LEFT:
			case LEFT_TOP:
			case LEFT_BOTTOM: {
				g2d.rotate(Math.toRadians(-90), size, size);
				break;
			}
			case RIGHT:
			case RIGHT_TOP:
			case RIGHT_BOTTOM: {
				g2d.rotate(Math.toRadians(90), size, size);
				break;
			}
		}

		generalPath.closePath();
		g2d.setPaint(color);
		g2d.fill(generalPath);
		// g2d.draw(generalPath); //以后可以做一个只有边缘才有颜色的切角边框
		g2d.dispose();
	}

	@Override
	public Insets getBorderInsets(Component c) {
		Insets insets = new Insets(0, 0, 0, 0);
		switch (position) {
			case TOP:
			case TOP_LEFT:
			case TOP_RIGHT: {
				insets.top = size;
				break;
			}
			case BOTTOM:
			case BOTTOM_LEFT:
			case BOTTOM_RIGHT: {
				insets.bottom = size;
				break;
			}
			case LEFT:
			case LEFT_TOP:
			case LEFT_BOTTOM: {
				insets.left = size;
				break;
			}
			case RIGHT:
			case RIGHT_TOP:
			case RIGHT_BOTTOM: {
				insets.right = size;
				break;
			}
		}
		return insets;
	}

	@Override
	public boolean isBorderOpaque() {
		return false;
	}
}
