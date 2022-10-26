package com.component.notice.loading;

import com.component.util.ImageUtil;
import org.jdesktop.swingx.image.StackBlurFilter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;

/**
 * 加载
 * <p>
 * 加载数据时显示动效。
 *
 * @param <E> 中间动画标签，，传入标签是为了方便调用标签(也可以不是Label)的方法，省去了再规范接口的麻烦
 */
public class LoadingPanel<E extends JComponent> extends JComponent {
	/** 中间动画组件 */
	private E loadingComponent;
	/** 模糊变暗后的背景图 */
	private BufferedImage image;
	private StackBlurFilter blurFilter;
	/** 背景是否模糊 */
	private boolean isBlur = true;

	/**
	 * @param loadingComponent 加载面板位于中间的组件
	 */
	public LoadingPanel(E loadingComponent) {
		this.loadingComponent = loadingComponent;

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(Box.createHorizontalGlue());
		Box b = Box.createVerticalBox();
		b.add(Box.createVerticalGlue());
		b.add(loadingComponent);
		b.add(Box.createVerticalGlue());
		add(b);
		add(Box.createHorizontalGlue());

		// 该类执行的速度与模糊半径无关，性能优异
		blurFilter = new StackBlurFilter(2, 3);

		// 加载面板要屏蔽输入事件
		addMouseListener(new MouseAdapter() {
		});
		addMouseMotionListener(new MouseAdapter() {
		});
		addKeyListener(new KeyAdapter() {
		});
	}

	/**
	 * 使用该面板的父组件作为加载面板的背景。调用该方法重新获取父组件快照
	 */
	public void flushBackground() {
		// 获取背景
		image = ImageUtil.componentToImage((JComponent) getParent());
		// 模糊
		if (isBlur) image = blurFilter.filter(image, null);
		// 变暗
		Graphics2D graphics = (Graphics2D) image.getGraphics();

		Composite olgComposite = graphics.getComposite();
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .4f));
		graphics.setColor(Color.GRAY);
		graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
		graphics.setComposite(olgComposite); //合成对象用完就返回去是个好习惯
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (image != null) {
			g.drawImage(image, 0, 0, this);
		}
		super.paintComponent(g);
	}

	/**
	 * 获取加载面板中心的组件
	 */
	public E getLoadingComponent() {
		return loadingComponent;
	}

	/**
	 * 获取背景图片
	 */
	public BufferedImage getImage() {
		return image;
	}

	public StackBlurFilter getBlurFilter() {
		return blurFilter;
	}

	/**
	 * 设置背景图片，如果需要自定义背景，可以在这里进行设置
	 *
	 * @param image  做为背景的图片
	 * @param isBlur 该背景是否进行模糊操作。注意，该值不会赋值给 isBlur，只是临时的
	 */
	public void setImage(BufferedImage image, boolean isBlur) {
		if (isBlur) image = blurFilter.filter(image, null);
		this.image = image;
	}

	public void setBlurFilter(StackBlurFilter blurFilter) {
		this.blurFilter = blurFilter;
	}

	public boolean isBlur() {
		return isBlur;
	}

	public void setBlur(boolean blur) {
		isBlur = blur;
	}
}
