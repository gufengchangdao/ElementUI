package com.component.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间工具类
 */
public class DateTimeUtil {

	/**
	 * 获取当前时间的字符串形式
	 *
	 * @return 当前时间的字符串形式
	 */
	public static String getDateTimeNowStr() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	public static String getDateNowStr() {
		return new SimpleDateFormat("MM月dd日").format(new Date());
	}

	/**
	 * 以yyyy-MM-dd HH:mm:ss格式将字符串转化为Date对象
	 *
	 * @param datetime 符合格式的字符串
	 * @return 转化后的date对象
	 */
	public static Date dateOf(String datetime) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datetime);
	}

	/**
	 * 创建指定日期时间的Date对象
	 *
	 * @param year  年
	 * @param month 月
	 * @param day   日
	 * @param time  HH:mm:ss格式的时间字符串
	 * @return Date
	 */
	public static Date dateOf(int year, int month, int day, String time) throws ParseException {
		String datetime = String.format("%4d-%02d-%02d %s", year, month, day, time);
		return dateOf(datetime);
	}

	/**
	 * 获取指定日期的最后一分钟所在时间，
	 * 比如2021-12-12 23:59:59
	 *
	 * @param year  年
	 * @param month 月
	 * @param day   日
	 * @return 格式化字符串
	 */
	public static String stringOf(int year, int month, int day) {
		return String.format("%4d-%02d-%02d 23:59:59", year, month, day);
	}

	/**
	 * 返回格式化date对象后的字符串
	 *
	 * @param date 待格式化的date对象
	 * @return 格式化后的时间
	 */
	public static String date2String(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	/**
	 * 检查时间字符串是否符合HH:mm:ss格式，符合就返回转换后的date对象
	 *
	 * @param time 待检测的字符串
	 * @return 转换的date对象或null(不符合)
	 */
	public static Date checkTimeStr(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		try {
			return sdf.parse(time);// 将时间字符串转为Date对象
		} catch (ParseException e) {
			return null;// 发生异常则表示字符串格式错误
		}
	}

	/**
	 * 获取指定月份的总天数
	 *
	 * @param year  年
	 * @param month 月
	 * @return 指定年和月的最大天数(最后一天)
	 */
	public static int getLastDay(int year, int month) {
		Calendar c = Calendar.getInstance();                // 日历对象
		c.set(Calendar.YEAR, year);                         // 指定年
		c.set(Calendar.MONTH, month - 1);                   // 指定月
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);   // 返回这月的最后一天
	}

	/**
	 * 获取由当前年、月、日、时、分、秒数字所组成的数组
	 *
	 * @return Integer[] 各个时间字段组成的长度为6的数组
	 */
	public static Integer[] now() {
		// 保存年、月、日、时、分、秒的数组
		Integer[] now = new Integer[6];
		Calendar c = Calendar.getInstance();// 日历对象
		now[0] = c.get(Calendar.YEAR);// 年
		now[1] = c.get(Calendar.MONTH) + 1;// 月
		now[2] = c.get(Calendar.DAY_OF_MONTH);// 日
		now[3] = c.get(Calendar.HOUR_OF_DAY);// 时
		now[4] = c.get(Calendar.MINUTE);// 分
		now[5] = c.get(Calendar.SECOND);// 秒
		return now;
	}
}
