package com.component.form.input.model;

import javax.swing.*;
import java.math.BigDecimal;

/**
 * 更高精度控制器模型
 */
public class BigDecimalSpinnerModel extends SpinnerNumberModel {
	public BigDecimalSpinnerModel(double value, double minimum, double maximum, double stepSize) {
		super(value, minimum, maximum, stepSize);
	}

	@Override
	public Object getPreviousValue() {
		return incrValue2(-1);
	}

	@Override
	public Object getNextValue() {
		return incrValue2(+1);
	}

	// @see SpinnerNumberModel#incrValue(int dir)
	private Number incrValue2(int dir) {
		BigDecimal value = BigDecimal.valueOf((Double) getNumber());
		BigDecimal stepSize = BigDecimal.valueOf((Double) getStepSize());
		BigDecimal newValue = dir > 0 ? value.add(stepSize) : value.subtract(stepSize);

		BigDecimal maximum = BigDecimal.valueOf((Double) getMaximum());
		if (maximum.compareTo(newValue) < 0) {
			return null;
		}

		BigDecimal minimum = BigDecimal.valueOf((Double) getMinimum());
		if (minimum.compareTo(newValue) > 0) {
			return null;
		}
		return newValue;
	}
}
