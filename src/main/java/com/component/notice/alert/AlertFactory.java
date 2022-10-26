package com.component.notice.alert;

import com.component.basic.color.ColorUtil;
import com.component.common.template.X2Component;
import com.component.svg.icon.fill.CheckCircleSvg;
import com.component.svg.icon.fill.WarningCircleSvg;
import com.component.svg.icon.fill.XCircleSvg;

import java.awt.*;

/**
 * 警告工厂类
 */
public class AlertFactory {
	public static AlertComponent createSuccessAlert(String text, boolean isContainIcon, boolean closeable,
	                                                X2Component.GrowStyle style) {
		return new AlertComponent(isContainIcon ? CheckCircleSvg.of(16, 16) : null,
				text, ColorUtil.SUCCESS, closeable, style, new Insets(8, 16, 8, 16));
	}

	public static AlertComponent createWarningAlert(String text, boolean isContainIcon, boolean closeable,
	                                                X2Component.GrowStyle style) {
		return new AlertComponent(isContainIcon ? WarningCircleSvg.of(16, 16) : null,
				text, ColorUtil.WARNING, closeable, style, new Insets(8, 16, 8, 16));
	}

	public static AlertComponent createDangerAlert(String text, boolean isContainIcon, boolean closeable,
	                                               X2Component.GrowStyle style) {
		return new AlertComponent(isContainIcon ? XCircleSvg.of(16, 16) : null,
				text, ColorUtil.DANGER, closeable, style, new Insets(8, 16, 8, 16));
	}

	public static AlertComponent createInfoAlert(String text, boolean isContainIcon, boolean closeable,
	                                             X2Component.GrowStyle style) {
		return new AlertComponent(isContainIcon ? WarningCircleSvg.of(16, 16) : null,
				text, ColorUtil.INFO, closeable, style, new Insets(8, 16, 8, 16));
	}
}
