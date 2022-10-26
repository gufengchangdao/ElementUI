package com.component.util;

import java.awt.*;

import static com.component.util.SizeAdjustUtil.adjustDimensionSize;

public class SizeAdjustUtilTest {
	public static void main(String[] args) {
		Dimension container = new Dimension(200, 300);
		Dimension child = new Dimension(100, 150);
		adjustDimensionSize(container, child);
		System.out.println(child);
	}
}
