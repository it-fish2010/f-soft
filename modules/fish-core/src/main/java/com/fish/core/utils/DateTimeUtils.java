package com.fish.core.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author Fish
 * @email it.fish2010@foxmail.com
 * @create 2019-03-22
 * @copyright 佳乐软件股份有限公司© 2019-2019
 *
 */
public class DateTimeUtils {

	/***
	 * 获取当前月份的第一天，即: YYYY-MM-01
	 * 
	 * @author Fish
	 * @date 2018-08-07
	 * @return
	 */
	public static final Date getCurrMonthFirstDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getNowDate());
		cal.set(Calendar.DATE, 1);
		return cal.getTime();
	}

	/***
	 * 获取当前月份的最后一天
	 * 
	 * @author Fish
	 * @date 2018-08-07
	 * @return
	 */
	public static final Date getCurrMonthLastDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getNowDate());
		// 下个月
		cal.add(Calendar.MONTH, 1);
		// 第一天
		cal.set(Calendar.DATE, 1);
		// 减1天
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	/**
	 * 获取当前时间
	 * 
	 * @author Fish
	 * @date 2018年6月30日
	 * @return
	 */
	public static final Timestamp getNowTime() {
		return new Timestamp(System.currentTimeMillis());
	}

	/***
	 * 返回当前日期
	 * 
	 * @author Fish
	 * @date 2018年6月30日
	 * @return
	 */
	public static final Date getNowDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		return cal.getTime();
	}

	public static final String FORMAT_DATE_NUM = "yyyyMMdd";
	public static final String FORMAT_DATE = "yyyy-MM-dd";
	public static final String FORMAT_DATETIME = "yyyy-MM-dd hh:mm:ss";
	public static final String FORMAT_DATETIME_NUM = "yyyyMMddhhmmss";

	/**
	 * 返回当前日期的字符串，格式：YYYY-MM-DD
	 * 
	 * @user Fish
	 * @date 2017年11月28日
	 * @return
	 */
	public static final String formatToday() {
		return getToday(FORMAT_DATE);
	}

	/***
	 * 把一个字符串转换成日期类型，字符串格式：YYYY-MM-DD
	 * 
	 * @author Fish
	 * @date 2018年6月30日
	 * @param dateStr
	 * @return
	 */
	public static final Date parseDate(String dateStr) {
		return parse(dateStr, FORMAT_DATE);
	}

	/***
	 * 把一个字符串转换成Timestamp类型，字符串格式：YYYY-MM-DD
	 * 
	 * @author Fish
	 * @date 2018-08-07
	 * @param dateStr
	 * @return
	 */
	public static final Timestamp parseTimestamp(String dateStr) {
		Date dt = parseDate(dateStr);
		return new Timestamp(dt.getTime());

	}

	/***
	 * 把一个字符串转换成日期，
	 * 
	 * @author Fish
	 * @date 2018年6月30日
	 * @param dateStr 日期字符串
	 * @param format 字符串格式，如：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static final Date parse(String dateStr, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			dateStr = "1900-01-01";
			return parseDefault(dateStr);
		}
	}

	private static Date parseDefault(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 返回当前日期的数字格，格式：YYYYMMDD
	 * 
	 * @user Fish
	 * @date 2017年11月28日
	 * @return
	 */
	public static final String formatTodaySeq() {
		return getToday(FORMAT_DATE_NUM);
	}

	/***
	 * 获取默认最大日期: 2099-12-31
	 * 
	 * @user Fish
	 * @date 2017年12月29日
	 * @return
	 */
	public static final String formatMaxDay() {
		return "2099-12-31";
	}

	/***
	 * 获取默认最大日期: 2099-12-31
	 * 
	 * @user Fish
	 * @date 2017年12月29日
	 * @return
	 */
	public static final String formatMaxDaySeq() {
		return "20991231";
	}

	private static String getToday(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(getNowTime());
	}

	public static final String formatNow() {
		return getToday(FORMAT_DATETIME);
	}

	/***
	 * 返回当前时间的数字格，格式：yyyyMMddhhmmss
	 * 
	 * @user Fish
	 * @date 2017年12月6日
	 * @return
	 */
	public static final String formatNowSeq() {
		return getToday(FORMAT_DATETIME_NUM);
	}

	/***
	 * 返回当前时间的数字格，格式：yyyyMMddhh
	 * 
	 * @user Fish
	 * @date 2017年12月6日
	 * @return
	 */
	public static final String formatNowSeqHour() {
		return getToday("yyyyMMddhh");
	}

	public static final String formatDate(Timestamp time) {
		return format(time, FORMAT_DATE);
	}

	public static final String formatDateTime(Timestamp time) {
		return format(time, FORMAT_DATETIME);
	}

	public static final String format(Timestamp time, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(time);
	}

	public static final String formatDateTime(Date date) {
		return format(date, FORMAT_DATETIME);
	}

	public static final String formatDate(Date date) {
		return format(date, FORMAT_DATE);
	}

	public static final String format(Date time, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(time);
	}

}
