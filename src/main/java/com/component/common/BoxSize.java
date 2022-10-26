package com.component.common;

/**
 * 定义背景的绘制范围
 * <p>
 * 关闭同时设置了组件的insets或边框的insets时，有下面几种情况
 * <ul>
 *     <li>
 *         只设置了边框insets<br>
 *         BORDER_SIZE会起作用(如果边框是带有圆角的，不过不带圆角子组件非透明也会起作用)、
 *         CONTENT_SIZE会其作用(如果子组件本身是非不透明的)
 *     </li>
 *     <li>
 *         同时组件insets和边框insets，但前者小于后者<br>
 *         BORDER_SIZE会起作用(如果边框是带有圆角的)，但是子组件即便非不透明背景也是边框的颜色
 *     </li>
 *     <li>
 *         同时组件insets和边框insets，但前者大于后者<br>
 *         此时边框会绘制在靠着组件insets边缘绘制，
 *         三者都起作用
 *     </li>
 * </ul>
 */
public enum BoxSize {
	/** 默认，背景绘制范围与边框同大，且在边框下面 */
	BORDER_SIZE,
	/**
	 * 背景在边框之内，当设置了组件的insets时，边框与组件之间会有一部分空间，可以使用该值。也建议当设置了非线性边框时使用该值
	 */
	PADDING_SIZE,
	/** 只有内容区域绘制背景 */
	CONTENT_SIZE
}
