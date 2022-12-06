package com.component.navigation.steps;

import com.component.basic.color.ColorUtil;
import com.component.common.component.BaseComponent;
import com.component.radiance.common.api.icon.RadianceIcon;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.max;

/**
 * 水平步骤条
 * <p>
 * 引导用户按照流程完成任务的分步导航条，可根据实际应用场景设定步骤，步骤不得少于 2 步。
 */
public class StepsComponent extends BaseComponent {
	/** 步骤相关数据列表 */
	private List<StepInfo> items;
	/** 当前进行到哪一步了，从零开始。默认为0 */
	private int currentStep = 0;
	/** 已完成的步骤主题色 */
	private Color achievedColor = ColorUtil.SUCCESS;
	/** 未开始的步骤主题色 */
	private Color notStartColor = ColorUtil.PLACEHOLDER_TEXT;
	/** 正在进行步骤主题色 */
	private Color currentColor = UIManager.getColor("Label.foreground");
	private RadianceIcon.ColorFilter achievedColorFilter;
	private RadianceIcon.ColorFilter notStartColorFilter = color -> notStartColor;
	private boolean isHorizontal;

	/**
	 * 创建从0开始，线长为100的步骤条
	 *
	 * @param textList     步骤标题，尽量不要让标题过长，较长的文本建议自行换行
	 * @param cl           图标类，内容利用反射进行图标的创建
	 * @param isHorizontal 是否为水平步骤条，为false表示为垂直步骤条
	 */
	public StepsComponent(List<String> textList, Class<? extends RadianceIcon> cl, boolean isHorizontal) {
		this(textList, cl, 0, ColorUtil.SUCCESS, 100, isHorizontal);
	}

	/**
	 * 创建从0开始的步骤条
	 *
	 * @param textList     步骤标题，尽量不要让标题过长，较长的文本建议自行换行
	 * @param cl           图标类，内容利用反射进行图标的创建
	 * @param lineLength   线宽
	 * @param isHorizontal 是否为水平步骤条，为false表示为垂直步骤条
	 */
	public StepsComponent(List<String> textList, Class<? extends RadianceIcon> cl, int lineLength, boolean isHorizontal) {
		this(textList, cl, null, 0, ColorUtil.SUCCESS, lineLength, isHorizontal);
	}

	public StepsComponent(List<String> textList, Class<? extends RadianceIcon> cl,
	                      int currentStep, Color achievedColor, int lineLength, boolean isHorizontal) {
		this(textList, cl, null, currentStep, achievedColor, lineLength, isHorizontal);
	}

	/**
	 * 创建图标都一样的步骤条
	 *
	 * @param textList        步骤标题，尽量不要让标题过长，较长的文本建议自行换行
	 * @param cl              图标类，内容利用反射进行图标的创建
	 * @param descriptionList 描述列表
	 * @param currentStep     当前进行到哪一步了
	 * @param achievedColor   已完成的步骤主题色
	 * @param lineLength      线宽
	 * @param isHorizontal    是否为水平步骤条，为false表示为垂直步骤条
	 */
	public StepsComponent(List<String> textList, Class<? extends RadianceIcon> cl, List<String> descriptionList,
	                      int currentStep, Color achievedColor, int lineLength, boolean isHorizontal) {
		this.items = createItems(textList, cl, descriptionList, lineLength, isHorizontal);

		this.currentStep = currentStep;
		this.achievedColor = achievedColor;
		this.isHorizontal = isHorizontal;
		init();
	}

	/**
	 * @see #StepsComponent(List, List, List, int, Color, int, boolean)
	 */
	public StepsComponent(List<String> textList, List<RadianceIcon> iconList, int currentStep, Color achievedColor,
	                      int lineLength, boolean isHorizontal) {
		this(textList, iconList, null, currentStep, achievedColor, lineLength, isHorizontal);
	}

	public StepsComponent(List<StepInfo> items,
	                      int currentStep, Color achievedColor, boolean isHorizontal) {
		this.items = items;
		this.currentStep = currentStep;
		this.achievedColor = achievedColor;
		this.isHorizontal = isHorizontal;
		init();
	}

	/**
	 * 用于创建不同图标，不同标题、描述的步骤条
	 *
	 * @param textList        步骤标题，尽量不要让标题过长，较长的文本建议自行换行
	 * @param iconList        图标列表，对应上面的标题列表
	 * @param descriptionList 描述列表。
	 * @param currentStep     当前进行到哪一步了
	 * @param achievedColor   已完成的步骤主题色
	 * @param lineLength      线宽
	 * @param isHorizontal    是否为水平步骤条，为false表示为垂直步骤条
	 */
	public StepsComponent(List<String> textList, List<RadianceIcon> iconList, List<String> descriptionList,
	                      int currentStep, Color achievedColor, int lineLength, boolean isHorizontal) {
		this.items = createItems(textList, iconList, descriptionList, lineLength, isHorizontal);

		this.currentStep = currentStep;
		this.achievedColor = achievedColor;
		this.isHorizontal = isHorizontal;
		init();
	}

	/**
	 * 创建用于步骤条的数据
	 *
	 * @see #StepsComponent(List, Class, List, int, Color, int, boolean)
	 */
	public static ArrayList<StepInfo> createItems(List<String> textList, Class<? extends RadianceIcon> cl,
	                                              List<String> descriptionList, int lineLength, boolean isHorizontal) {
		ArrayList<StepInfo> items = new ArrayList<>();
		try {
			Method method = cl.getMethod("of", int.class, int.class);
			Iterator<String> dit = null;
			if (descriptionList != null)
				dit = descriptionList.iterator();
			for (String s : textList) {
				RadianceIcon icon = (RadianceIcon) method.invoke(null, 16, 16);
				items.add(new StepInfo(icon, s, dit == null ? null : dit.next(), lineLength,
						isHorizontal ? SwingConstants.HORIZONTAL : SwingConstants.VERTICAL));
			}
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}

		return items;
	}

	/**
	 * 创建用于步骤条的数据
	 *
	 * @see #StepsComponent(List, List, int, Color, int, boolean)
	 */
	public static ArrayList<StepInfo> createItems(List<String> textList, List<RadianceIcon> iconList,
	                                              List<String> descriptionList, int lineLength, boolean isHorizontal) {
		ArrayList<StepInfo> items = new ArrayList<>();
		Iterator<RadianceIcon> it = iconList.iterator();
		Iterator<String> dit = null;
		if (descriptionList != null) dit = descriptionList.iterator();
		for (String s : textList) {
			items.add(new StepInfo(it.next(), s, dit == null ? null : dit.next(), lineLength,
					isHorizontal ? SwingConstants.HORIZONTAL : SwingConstants.VERTICAL));
		}
		return items;
	}

	protected void init() {
		achievedColorFilter = color -> achievedColor;

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		updateLayout();
		updateStyle();
	}

	/** 组件布局 */
	public void updateLayout() {
		removeAll();
		boolean isContainDescription = false;
		int titleMaxWidth = 0;
		int titleMaxHeight = 0;
		int descriptionMaxWidth = 0;
		int descriptionMaxHeight = 0;
		for (StepInfo item : items) {
			if (item.description != null) {
				isContainDescription = true;
				if (isHorizontal)
					descriptionMaxHeight = max(descriptionMaxHeight, item.description.getPreferredSize().height);
				else
					descriptionMaxWidth = max(descriptionMaxWidth, item.description.getPreferredSize().width);
			}
			if (isHorizontal)
				titleMaxHeight = max(titleMaxHeight, item.text.getPreferredSize().height);
			else
				titleMaxWidth = max(titleMaxWidth, item.text.getPreferredSize().width);
		}
		GridLayout gridLayout;
		if (isHorizontal) {
			if (isContainDescription) gridLayout = new GridLayout(3, 1);
			else gridLayout = new GridLayout(2, 1);
			for (int i = 0, len = items.size(); i < len; i++) {
				StepInfo info = items.get(i);
				JPanel b2 = new JPanel(gridLayout);
				b2.add(info.iconLabel);
				b2.add(info.text);
				if (info.description != null) b2.add(info.description);
				add(b2);

				if (i != len - 1) {
					Box b3 = Box.createVerticalBox();
					b3.add(info.lineLabel);
					if (isContainDescription)
						b3.add(Box.createVerticalStrut(max(titleMaxHeight, descriptionMaxHeight)));
					else
						b3.add(Box.createVerticalStrut(titleMaxHeight));
					add(b3);
				}
			}
		} else {
			if (isContainDescription) gridLayout = new GridLayout(2, 1);
			else gridLayout = new GridLayout(1, 1);
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			for (int i = 0, len = items.size(); i < len; i++) {
				Box b = Box.createHorizontalBox();
				StepInfo info = items.get(i);
				JPanel b2 = new JPanel(gridLayout);
				b2.add(info.text);
				if (info.description != null) b2.add(info.description);
				b.add(info.iconLabel);
				b.add(Box.createHorizontalStrut(5));
				b.add(b2);
				add(b);

				if (i != len - 1) {
					Box b3 = Box.createHorizontalBox();
					b3.add(info.lineLabel);
					if (isContainDescription)
						b3.add(Box.createHorizontalStrut(max(titleMaxWidth, descriptionMaxWidth)));
						// b3.add(Box.createRigidArea(b2.getPreferredSize()));
					else
						b3.add(Box.createHorizontalStrut(titleMaxWidth));
					add(b3);
				}
			}
		}
	}

	/**
	 * 更新步骤条样式的简单实现
	 */
	public void updateStyle() {
		// 已完成标签样式
		for (int i = 0; i < currentStep; i++) {
			StepInfo info = items.get(i);
			info.icon.setColorFilter(achievedColorFilter);
			info.text.setForeground(achievedColor);
			if (info.description != null) info.description.setForeground(achievedColor);
			if (i != currentStep - 1) { //线条
				info.lineLabel.setForeground(achievedColor);
			}
		}

		// 正在进行样式
		if (currentStep != -1) {
			StepInfo info = items.get(currentStep);
			info.icon.setColorFilter(null);
			info.text.setForeground(currentColor);
			if (info.description != null) info.description.setForeground(ColorUtil.SECONDARY_TEXT);
		}

		// 尚未开始样式
		// 如果直接使用setItems设置item的数量，已完成数量由大变小，需要重绘第一条线条的颜色
		if (currentStep == 0)
			items.get(0).lineLabel.setForeground(notStartColor);
		for (int i = max(currentStep + 1, 0); i < items.size(); i++) {
			StepInfo info = items.get(i);
			info.icon.setColorFilter(notStartColorFilter);
			info.text.setForeground(notStartColor);
			if (info.description != null) info.description.setForeground(ColorUtil.SECONDARY_TEXT);
			info.lineLabel.setForeground(notStartColor);
		}

		repaint();
	}

	/**
	 * 下一步
	 */
	public void addStep() {
		if (currentStep == items.size()) return;

		// 已完成标签样式
		StepInfo info = items.get(currentStep);
		info.icon.setColorFilter(achievedColorFilter);
		info.text.setForeground(achievedColor);
		if (info.description != null) info.description.setForeground(achievedColor);
		if (currentStep != 0)
			items.get(currentStep - 1).lineLabel.setForeground(achievedColor);

		currentStep++;

		// 正在进行样式
		if (currentStep != items.size()) {
			info = items.get(currentStep);
			info.icon.setColorFilter(null);
			info.text.setForeground(currentColor);
			if (info.description != null) info.description.setForeground(ColorUtil.SECONDARY_TEXT);
		}

		repaint();
	}

	/**
	 * 上一步
	 */
	public void reduceStep() {
		if (currentStep == 0) return;
		currentStep--;

		// 正在进行样式
		StepInfo info = items.get(currentStep);
		info.icon.setColorFilter(null);
		info.text.setForeground(currentColor);
		if (info.description != null) info.description.setForeground(ColorUtil.SECONDARY_TEXT);
		// 尚未开始样式
		if (currentStep != items.size() - 1) {
			info = items.get(currentStep + 1);
			info.icon.setColorFilter(notStartColorFilter);
			info.text.setForeground(notStartColor);
			if (info.description != null) info.description.setForeground(ColorUtil.SECONDARY_TEXT);
		}
		if (currentStep != 0)
			items.get(currentStep - 1).lineLabel.setForeground(notStartColor);

		repaint();
	}

	/**
	 * 设置步骤索引。
	 * <p>
	 * 注意，索引超出范围会抛异常
	 *
	 * @param index 当前进行的步骤索引值
	 */
	public void setStep(int index) {
		if (index < 0 || index > items.size())
			throw new IllegalArgumentException("index 的范围必须在[o, " + items.size() + "]");
		currentStep = index;
		updateStyle();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// 绘制图标下文本，文本抗锯齿效果不太好，故不采用
		// Graphics2D g2 = (Graphics2D) g.create();
		// g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT);
		// FontRenderContext context = g2.getFontRenderContext();
		// for (StepInfo s : stepInfos) {
		// 	JLabel icon = s.icon;
		// 	int w = icon.getWidth(); //虽然每个图标一样大小
		// 	int h = icon.getHeight();
		// 	Rectangle2D bounds = getFont().getStringBounds(s.text, context);
		// 	// g2.drawString(s.text, (int) (icon.getX() + (w - bounds.getWidth()) / 2),
		// 	// 		(int) (icon.getY() + h - bounds.getY()));
		// }
		// g2.dispose();
	}

	public int getCurrentStep() {
		return currentStep;
	}

	public Color getAchievedColor() {
		return achievedColor;
	}

	public Color getNotStartColor() {
		return notStartColor;
	}

	public Color getCurrentColor() {
		return currentColor;
	}

	public void setAchievedColor(Color achievedColor) {
		this.achievedColor = achievedColor;
		achievedColorFilter = color -> achievedColor;
	}

	public void setNotStartColor(Color notStartColor) {
		this.notStartColor = notStartColor;
		notStartColorFilter = color -> notStartColor;
	}

	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
	}

	public List<StepInfo> getItems() {
		return items;
	}

	/**
	 * 重新设置步骤条数据
	 *
	 * @param items 步骤条数据列表
	 */
	public void setItems(List<StepInfo> items) {
		this.items = items;
		currentStep = -1;
		updateLayout();
		updateStyle();
		validate();
	}
}

