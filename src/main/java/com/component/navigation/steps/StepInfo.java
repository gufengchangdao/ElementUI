package com.component.navigation.steps;

import com.component.basic.color.ColorUtil;
import com.component.others.line.LineLabel;
import com.component.radiance.common.api.icon.RadianceIcon;

import javax.swing.*;
import java.awt.*;

/**
 * 步骤条数据，包含图标、描述、右边的一条线段
 */
public class StepInfo {
	RadianceIcon icon;
	JLabel iconLabel;
	JLabel text;
	LineLabel lineLabel;
	JLabel description;

	public StepInfo(RadianceIcon icon, String text, int lineLength, int direction) {
		this(icon, text, null, null, lineLength, direction);
	}

	public StepInfo(RadianceIcon icon, String text, LineLabel lineLabel, int lineLength, int direction) {
		this(icon, text, lineLabel, null, lineLength, direction);
	}

	public StepInfo(RadianceIcon icon, String text, String description, int lineLength, int direction) {
		this(icon, text, null, description, lineLength, direction);
	}

	public StepInfo(RadianceIcon icon, String text, LineLabel lineLabel, String description, int lineLength, int direction) {
		this.icon = icon;
		this.iconLabel = new JLabel(icon);
		this.text = new JLabel(text);
		// this.text.setMaximumSize(); //不想让描述过长可以设置最大值
		if (lineLabel == null) {
			this.lineLabel = new LineLabel(3, direction);
			if (direction == SwingConstants.HORIZONTAL)
				this.lineLabel.setPreferredSize(new Dimension(lineLength, this.iconLabel.getPreferredSize().height));
			else
				this.lineLabel.setPreferredSize(new Dimension(this.iconLabel.getPreferredSize().width, lineLength));
		} else {
			this.lineLabel = lineLabel;
		}

		if (description != null) {
			this.description = new JLabel(description);
			this.description.setForeground(ColorUtil.SECONDARY_TEXT);
		}
	}

	public JLabel getIconLabel() {
		return iconLabel;
	}

	public void setIconLabel(JLabel iconLabel) {
		this.iconLabel = iconLabel;
	}

	public JLabel getText() {
		return text;
	}

	public void setText(JLabel text) {
		this.text = text;
	}

	public LineLabel getLineLabel() {
		return lineLabel;
	}

	public void setLineLabel(LineLabel lineLabel) {
		this.lineLabel = lineLabel;
	}

	public RadianceIcon getIcon() {
		return icon;
	}

	public void setIcon(RadianceIcon icon) {
		this.icon = icon;
	}

	public JLabel getDescription() {
		return description;
	}

	public void setDescription(JLabel description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "StepInfo{" +
				"icon=" + iconLabel +
				", text='" + text + '\'' +
				", lineLabel=" + lineLabel +
				'}';
	}
}
