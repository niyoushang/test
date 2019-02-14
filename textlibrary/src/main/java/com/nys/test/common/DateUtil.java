package com.nys.test.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	private static int mMonth;
	private static int mDay;
	private static String mWay;
	private static int hour;
	private static int minute;



	public static boolean isToday(long milliseconds) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(milliseconds);
		int day = c.get(Calendar.DAY_OF_YEAR);
		c.setTimeInMillis(System.currentTimeMillis());
		int day2 = c.get(Calendar.DAY_OF_YEAR);
		return day == day2;
	}

	public static Date stringToDate(String parseString, String dateString) {
		try {
			return new SimpleDateFormat(parseString).parse(dateString);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String dateToString(String parseString, Date date) {
		return new SimpleDateFormat(parseString).format(date);
	}

	public static String longToString(String parseString, long milliseconds) {
		return new SimpleDateFormat(parseString).format(new Date(milliseconds));
	}
	public static String longToString(long milliseconds) {
		String timeString;
		if (isToday(milliseconds)) {
			timeString = longToString("HH:mm", milliseconds);
		}else if (isYesToday(milliseconds)) {
			timeString = "昨天";
		} else if (isWeek(milliseconds)) {
			timeString = longToWeek2(milliseconds);
		}else{
			timeString = longToString("yyyy-MM-dd", milliseconds);
		}
		return timeString;
	}

	/**
	 * 月份
	 * @param milliseconds
	 * @return
	 */
	public static String longToStringDate(long milliseconds) {
		String timeString;
		if (isMonth(milliseconds)) {
			timeString = "这个月";
		}else{
			timeString = longToString("yyyy-MM-dd", milliseconds);
		}
		return timeString;
	}

	/**
	 * 格式
	 * 00：00
	 * 昨天 00:00
	 * 5天内 周几 00：005天内 周几 00:00
	 * 大于5天 yyyy年MM月dd日 HH:mm
	 * @param date
	 * @return
	 */
	public static String getMsgTimeFormat(long date) {
		Calendar dateCalendar = Calendar.getInstance();
		dateCalendar.setTimeInMillis(date);
		Date now = new Date();
		Calendar targetCalendar = Calendar.getInstance();
		targetCalendar.setTime(now);
		targetCalendar.set(Calendar.HOUR_OF_DAY, 0);
		targetCalendar.set(Calendar.MINUTE, 0);
		if (dateCalendar.after(targetCalendar)) {//今天
			return longToString("HH:mm",date);
		} else {
			targetCalendar.add(Calendar.DATE, -1);
			if (dateCalendar.after(targetCalendar)) {//昨天
				return longToString("昨天 HH:mm",date);
			}else{
				targetCalendar.add(Calendar.DATE, -5);
				if (dateCalendar.after(targetCalendar)) {//5天内
					return longToWeek2(date)+" "+longToString("HH:mm",date);
				}else{//大于等于5天
					return longToString("yyyy年MM月dd日 HH:mm",date);
				}
			}
		}
	}

	public static boolean isMonth(long milliseconds) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(milliseconds);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		c.setTimeInMillis(System.currentTimeMillis());
		int year2 = c.get(Calendar.YEAR);
		int month2 = c.get(Calendar.MONTH);
		return year == year2&&month==month2;
	}

	/**
	 * 两个时间相差距离多少天多少小时多少分多少秒
	 * 
	 * @param diff 时间参数 2 格式：2009-01-01 12:00:00
	 * @return String 返回值为：xx天xx小时xx分xx秒
	 */
	
	public static String getDistanceTime(long diff,boolean addmin) {
		String time = "";
		if (diff < 0) {
			diff = diff * -1;
		}
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		try {
			day = diff / (24 * 60 * 60 * 1000);
			hour = (diff / (60 * 60 * 1000) - day * 24);
			min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
			sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (day != 0) {
			time += day + "天前";
		}
		if (hour != 0) {
			time += hour + "小时";
		}
		if (min != 0) {
			if(addmin) {
				time += (min + 1) + "分钟";
			}else{
				time += min + "分钟";
			}
		}
		if (day == 0 && hour == 0 && min == 0) {
			time = "1分钟";
		}
		return time;
	}

	/**
	 * 格式 0月0日 星期一 00:00
	 * @param time
	 * @return
	 */
	public static String StringData(long time) {
		final Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
		mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当前月份的日期号码
		mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
		if ("1".equals(mWay)) {
			mWay = "天";
		} else if ("2".equals(mWay)) {
			mWay = "一";
		} else if ("3".equals(mWay)) {
			mWay = "二";
		} else if ("4".equals(mWay)) {
			mWay = "三";
		} else if ("5".equals(mWay)) {
			mWay = "四";
		} else if ("6".equals(mWay)) {
			mWay = "五";
		} else if ("7".equals(mWay)) {
			mWay = "六";
		}
		String hours;
		if (hour < 10) {
			hours = "0" + hour;
		} else {
			hours = "" + hour;
		}
		String minutes;
		if (minute < 10) {
			minutes = "0" + minute;
		} else {
			minutes = "" + minute;
		}
		return mMonth + "月" + mDay + "日  " + "星期" + mWay + "  " + hours + ":"
				+ minutes + "  ";
	}

	/**
	 * 格式 0000-00-00 星期一
	 * @param time
	 * @return
	 */
	public static String StringData2(long time) {
		final Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		int year = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
		mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当前月份的日期号码
		mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
		if ("1".equals(mWay)) {
			mWay = "天";
		} else if ("2".equals(mWay)) {
			mWay = "一";
		} else if ("3".equals(mWay)) {
			mWay = "二";
		} else if ("4".equals(mWay)) {
			mWay = "三";
		} else if ("5".equals(mWay)) {
			mWay = "四";
		} else if ("6".equals(mWay)) {
			mWay = "五";
		} else if ("7".equals(mWay)) {
			mWay = "六";
		}
		return year+"-"+mMonth + "-" + mDay + "  " + "星期" + mWay;
	}

	/**
	 *  星期一
	 * @param time
	 * @return
	 */
	public static String StringData3(long time) {
		final Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
		if ("1".equals(mWay)) {
			mWay = "天";
		} else if ("2".equals(mWay)) {
			mWay = "一";
		} else if ("3".equals(mWay)) {
			mWay = "二";
		} else if ("4".equals(mWay)) {
			mWay = "三";
		} else if ("5".equals(mWay)) {
			mWay = "四";
		} else if ("6".equals(mWay)) {
			mWay = "五";
		} else if ("7".equals(mWay)) {
			mWay = "六";
		}
		return "星期" + mWay;
	}

	/**
	 * 格式 0月0日 星期一 00:00
	 * @param time
	 * @return
	 */
	public static String StringData4(long time) {
		final Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		int year = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
		mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当前月份的日期号码
		mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
		if ("1".equals(mWay)) {
			mWay = "天";
		} else if ("2".equals(mWay)) {
			mWay = "一";
		} else if ("3".equals(mWay)) {
			mWay = "二";
		} else if ("4".equals(mWay)) {
			mWay = "三";
		} else if ("5".equals(mWay)) {
			mWay = "四";
		} else if ("6".equals(mWay)) {
			mWay = "五";
		} else if ("7".equals(mWay)) {
			mWay = "六";
		}
		String hours;
		if (hour < 10) {
			hours = "0" + hour;
		} else {
			hours = "" + hour;
		}
		String minutes;
		if (minute < 10) {
			minutes = "0" + minute;
		} else {
			minutes = "" + minute;
		}
		return year+"年"+mMonth + "月" + mDay + "日  " + "星期" + mWay + "  " + hours + ":"
				+ minutes + "  ";
	}

	/**
	 * 免打扰消息时间验证
	 * 
	 * @param isNodisturb
	 * @return
	 */

	public static boolean isNodisturbTime(boolean isNodisturb) {
		if (isNodisturb) {
			Calendar c = Calendar.getInstance();
			hour = c.get(Calendar.HOUR_OF_DAY);
			if (hour >= 23 || hour < 8) {
				return false;
			}
		}
		return true;
	}

	public static String getTimeString(int time) {
		if (time >= 10) {
			return time + "";
		}
		return "0" + time;
	}

	public static boolean isWeek(long milliseconds) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(milliseconds);
		int year = c.get(Calendar.YEAR);
		int week = c.get(Calendar.WEEK_OF_YEAR);
		c.setTimeInMillis(System.currentTimeMillis());
		int year2 = c.get(Calendar.YEAR);
		int week2 = c.get(Calendar.WEEK_OF_YEAR);
		return year == year2 && week == week2;
	}

	public static boolean isYesToday(long milliseconds) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(milliseconds);
		int year = c.get(Calendar.YEAR);
		int day = c.get(Calendar.DAY_OF_YEAR);
		c.setTimeInMillis(System.currentTimeMillis());
		int year2 = c.get(Calendar.YEAR);
		int day2 = c.get(Calendar.DAY_OF_YEAR);
		return year == year2 && day + 1 == day2;
	}

	public static String longToWeek(long time) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
		if ("1".equals(mWay)) {
			mWay = "天";
		} else if ("2".equals(mWay)) {
			mWay = "一";
		} else if ("3".equals(mWay)) {
			mWay = "二";
		} else if ("4".equals(mWay)) {
			mWay = "三";
		} else if ("5".equals(mWay)) {
			mWay = "四";
		} else if ("6".equals(mWay)) {
			mWay = "五";
		} else if ("7".equals(mWay)) {
			mWay = "六";
		}
//		String hours;
//		if (hour < 10) {
//			hours = "0" + hour;
//		} else {
//			hours = "" + hour;
//		}
//		String minutes;
//		if (minute < 10) {
//			minutes = "0" + minute;
//		} else {
//			minutes = "" + minute;
//		}
		return "星期" + mWay;
	}

	/**
	 * 周
	 * @param time
	 * @return
     */
	public static String longToWeek2(long time) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
		if ("1".equals(mWay)) {
			mWay = "日";
		} else if ("2".equals(mWay)) {
			mWay = "一";
		} else if ("3".equals(mWay)) {
			mWay = "二";
		} else if ("4".equals(mWay)) {
			mWay = "三";
		} else if ("5".equals(mWay)) {
			mWay = "四";
		} else if ("6".equals(mWay)) {
			mWay = "五";
		} else if ("7".equals(mWay)) {
			mWay = "六";
		}
//		String hours;
//		if (hour < 10) {
//			hours = "0" + hour;
//		} else {
//			hours = "" + hour;
//		}
//		String minutes;
//		if (minute < 10) {
//			minutes = "0" + minute;
//		} else {
//			minutes = "" + minute;
//		}
		return "周" + mWay;
	}


	/**
	 * 两个时间相差多少天
	 * @param diff 时间参数 2 格式：今日，昨日，大于两天用x日，大于30天，多日
	 * @return String 返回值为：今日，昨日，大于两天用x日,大于30天，多日
	 */
	public static String getDistanceTime3(long diff) {
		String time = "";
		if (diff < 0) {
			diff = diff * -1;
		}
		long day = 0;
		try {
			day = diff / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (day != 0) {
			if(day==1){
				time = "昨日";
			}else if(day <= 30){
				time += day + "日";
			}else{
				time = "多日";
			}
		}else{
			time = "今日";
		}
		return time;
	}
	/**
	 * 获取今天前天昨天
	 * @param date
	 * @return
	 */
	public static String getTime(long date) {
		String todySDF = "今天 HH:mm";
		String yesterDaySDF = "昨天 HH:mm";
		String beforeDaySDF = "前天 HH:mm";
		String otherSDF = "M月d日 HH:mm";
		SimpleDateFormat sfd = null;
		String time = "";
		Calendar dateCalendar = Calendar.getInstance();
		dateCalendar.setTimeInMillis(date);
		Date now = new Date();
		Calendar targetCalendar = Calendar.getInstance();
		targetCalendar.setTime(now);
		targetCalendar.set(Calendar.HOUR_OF_DAY, 0);
		targetCalendar.set(Calendar.MINUTE, 0);
		if (dateCalendar.after(targetCalendar)) {
			sfd = new SimpleDateFormat(todySDF);
			time = sfd.format(date);
			return time;
		} else {
			targetCalendar.add(Calendar.DATE, -1);
			if (dateCalendar.after(targetCalendar)) {
				sfd = new SimpleDateFormat(yesterDaySDF);
				time = sfd.format(date);
				return time;
			}else{
				targetCalendar.add(Calendar.DATE, -2);
				if (dateCalendar.after(targetCalendar)) {
					sfd = new SimpleDateFormat(beforeDaySDF);
					time = sfd.format(date);
					return time;
				}
			}
		}
		sfd = new SimpleDateFormat(otherSDF);
		time = sfd.format(date);
		return time;
	}
	/**
	 * 获取今天前天昨天
	 * @param date
	 * @return
	 */
	public static String getTime2(long date) {
		String todySDF = "今天";
		String yesterDaySDF = "昨天";
		String beforeDaySDF = "前天";
		String otherSDF = "M月d日";
		SimpleDateFormat sfd = null;
		String time = "";
		Calendar dateCalendar = Calendar.getInstance();
		dateCalendar.setTimeInMillis(date);
		Date now = new Date();
		Calendar targetCalendar = Calendar.getInstance();
		targetCalendar.setTime(now);
		targetCalendar.set(Calendar.HOUR_OF_DAY, 0);
		targetCalendar.set(Calendar.MINUTE, 0);
		if (dateCalendar.after(targetCalendar)) {
			sfd = new SimpleDateFormat(todySDF);
			time = sfd.format(date);
			return time;
		} else {
			targetCalendar.add(Calendar.DATE, -1);
			if (dateCalendar.after(targetCalendar)) {
				sfd = new SimpleDateFormat(yesterDaySDF);
				time = sfd.format(date);
				return time;
			}else{
				targetCalendar.add(Calendar.DATE, -2);
				if (dateCalendar.after(targetCalendar)) {
					sfd = new SimpleDateFormat(beforeDaySDF);
					time = sfd.format(date);
					return time;
				}
			}
		}
		sfd = new SimpleDateFormat(otherSDF);
		time = sfd.format(date);
		return time;
	}

	/**
	 *
	 * @param date
	 * @return
     */
	public static String getTime3(long date) {
		String todySDF = "HH:mm";
		String yesterDaySDF = "昨天 HH:mm";
		String beforeDaySDF = "前天 HH:mm";
		SimpleDateFormat sfd = null;
		String time = "";
		Calendar dateCalendar = Calendar.getInstance();
		dateCalendar.setTimeInMillis(date);
		Date now = new Date();
		Calendar targetCalendar = Calendar.getInstance();
		targetCalendar.setTime(now);
		targetCalendar.set(Calendar.HOUR_OF_DAY, 0);
		targetCalendar.set(Calendar.MINUTE, 0);
		if (dateCalendar.after(targetCalendar)) {
			sfd = new SimpleDateFormat(todySDF);
			time = sfd.format(date);
			return time+"\n";
		} else {
			targetCalendar.add(Calendar.DATE, -1);
			if (dateCalendar.after(targetCalendar)) {
				sfd = new SimpleDateFormat(yesterDaySDF);
				time = sfd.format(date);
				return time+"\n";
			}else{
				targetCalendar.add(Calendar.DATE, -1);
				if (dateCalendar.after(targetCalendar)) {
					sfd = new SimpleDateFormat(beforeDaySDF);
					time = sfd.format(date);
					return time+"\n";
				}
			}
		}
		return "";
	}
	
	/**
	 * 30:10
	 * @param timeTalk
	 * @return
	 */
	public static  String getTimeText(int timeTalk){
		StringBuffer sb=new StringBuffer();
		int fen=timeTalk/60;
		int miao=timeTalk%60;
		if(fen<10){
			sb.append("0"+fen+":");
		}else{
			sb.append(fen+":");
		}
		
		if(miao<10){
			sb.append("0"+miao);
		}else{
			sb.append(miao);
		}
		return sb.toString();
	}
	
	/**
	 * 30分10秒
	 * @param timeTalk
	 * @return
	 */
	public static  String getTimeText2(int timeTalk){
		StringBuffer sb=new StringBuffer();
		int fen=timeTalk/60;
		int miao=timeTalk%60;
		if(fen>0){
			sb.append(fen+"分");
		}
		sb.append(miao+"秒");
		return sb.toString();
	}
	
	/**
	 * 时间戳转换成字符窜
	 * @param time
	 * @return
	 */
	public static String getDateToString(long time) {
    	Calendar c = Calendar.getInstance();
    	c.setTimeInMillis(time);
    	String date = getTimeString(c);
        return date;
    }
    /**
	 * 获取时间字符串
	 * 格式 0000-00-00 00:00
	 * @param mDate
	 * @return
	 */
	public static String getTimeString(Calendar mDate) {
		String myear = mDate.get(Calendar.YEAR) + "";
		String mmonth;
		String mday;
		String mhour;
		String mmin;
		if ((mDate.get(Calendar.MONTH) + 1) < 10) {
			mmonth = "0" + (mDate.get(Calendar.MONTH) + 1);
		} else {
			mmonth = (mDate.get(Calendar.MONTH) + 1) + "";
		}
		if (mDate.get(Calendar.DAY_OF_MONTH) < 10) {
			mday = "0" + mDate.get(Calendar.DAY_OF_MONTH);
		} else {
			mday = mDate.get(Calendar.DAY_OF_MONTH) + "";
		}
		if (mDate.get(Calendar.HOUR_OF_DAY) < 10) {
			mhour = "0" + mDate.get(Calendar.HOUR_OF_DAY);
		} else {
			mhour = mDate.get(Calendar.HOUR_OF_DAY) + "";
		}
		if (mDate.get(Calendar.MINUTE) < 10) {
			mmin = "0" + mDate.get(Calendar.MINUTE);
		} else {
			mmin = mDate.get(Calendar.MINUTE) + "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(myear).append("-").append(mmonth).append("-").append(mday)
				.append(" ").append(mhour).append(":").append(mmin);
		return sb.toString();
	}

	/**
	 * 获取时间字符串
	 * 格式 0000-00-00
	 * @param mDate
	 * @return
	 */
	public static String getTimeString2(Calendar mDate) {
		String myear = mDate.get(Calendar.YEAR) + "";
		String mmonth;
		String mday;
		if ((mDate.get(Calendar.MONTH) + 1) < 10) {
			mmonth = "0" + (mDate.get(Calendar.MONTH) + 1);
		} else {
			mmonth = (mDate.get(Calendar.MONTH) + 1) + "";
		}
		if (mDate.get(Calendar.DAY_OF_MONTH) < 10) {
			mday = "0" + mDate.get(Calendar.DAY_OF_MONTH);
		} else {
			mday = mDate.get(Calendar.DAY_OF_MONTH) + "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(myear).append("-").append(mmonth).append("-").append(mday);
		return sb.toString();
	}

	/**
	 * 获取时间字符串
	 * 格式 0000.00.00 00:00
	 * @param time
	 * @return
	 */
	public static String getTimeString3(long  time) {
		Calendar mDate = Calendar.getInstance();
		mDate.setTimeInMillis(time);

		String myear = mDate.get(Calendar.YEAR) + "";
		String mmonth;
		String mday;
		String mhour;
		String mmin;
		if ((mDate.get(Calendar.MONTH) + 1) < 10) {
			mmonth = "0" + (mDate.get(Calendar.MONTH) + 1);
		} else {
			mmonth = (mDate.get(Calendar.MONTH) + 1) + "";
		}
		if (mDate.get(Calendar.DAY_OF_MONTH) < 10) {
			mday = "0" + mDate.get(Calendar.DAY_OF_MONTH);
		} else {
			mday = mDate.get(Calendar.DAY_OF_MONTH) + "";
		}
		if (mDate.get(Calendar.HOUR_OF_DAY) < 10) {
			mhour = "0" + mDate.get(Calendar.HOUR_OF_DAY);
		} else {
			mhour = mDate.get(Calendar.HOUR_OF_DAY) + "";
		}
		if (mDate.get(Calendar.MINUTE) < 10) {
			mmin = "0" + mDate.get(Calendar.MINUTE);
		} else {
			mmin = mDate.get(Calendar.MINUTE) + "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(myear).append(".").append(mmonth).append(".").append(mday)
				.append(" ").append(mhour).append(":").append(mmin);
		return sb.toString();
	}

	/**
	 * 获取时间字符串
	 * 格式 0000-00-00 00:00
	 * @param time
	 * @return
	 */
	public static String getTimeString4(long  time) {
		Calendar mDate = Calendar.getInstance();
		mDate.setTimeInMillis(time);

		String myear = mDate.get(Calendar.YEAR) + "";
		String mmonth;
		String mday;
		String mhour;
		String mmin;
		if ((mDate.get(Calendar.MONTH) + 1) < 10) {
			mmonth = "0" + (mDate.get(Calendar.MONTH) + 1);
		} else {
			mmonth = (mDate.get(Calendar.MONTH) + 1) + "";
		}
		if (mDate.get(Calendar.DAY_OF_MONTH) < 10) {
			mday = "0" + mDate.get(Calendar.DAY_OF_MONTH);
		} else {
			mday = mDate.get(Calendar.DAY_OF_MONTH) + "";
		}
		if (mDate.get(Calendar.HOUR_OF_DAY) < 10) {
			mhour = "0" + mDate.get(Calendar.HOUR_OF_DAY);
		} else {
			mhour = mDate.get(Calendar.HOUR_OF_DAY) + "";
		}
		if (mDate.get(Calendar.MINUTE) < 10) {
			mmin = "0" + mDate.get(Calendar.MINUTE);
		} else {
			mmin = mDate.get(Calendar.MINUTE) + "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(myear).append("-").append(mmonth).append("-").append(mday)
				.append(" ").append(mhour).append(":").append(mmin);
		return sb.toString();
	}

	/**
	 * 获取时间字符串
	 * 格式 0000-00-00
	 * @param time
	 * @return
	 */
	public static String getTimeString5(long  time) {
		Calendar mDate = Calendar.getInstance();
		mDate.setTimeInMillis(time);

		String myear = mDate.get(Calendar.YEAR) + "";
		String mmonth;
		String mday;
		if ((mDate.get(Calendar.MONTH) + 1) < 10) {
			mmonth = "0" + (mDate.get(Calendar.MONTH) + 1);
		} else {
			mmonth = (mDate.get(Calendar.MONTH) + 1) + "";
		}
		if (mDate.get(Calendar.DAY_OF_MONTH) < 10) {
			mday = "0" + mDate.get(Calendar.DAY_OF_MONTH);
		} else {
			mday = mDate.get(Calendar.DAY_OF_MONTH) + "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(myear).append("-").append(mmonth).append("-").append(mday);
		return sb.toString();
	}

	/**
	 * 获取时间字符串
	 * 格式 00月00日 00:00:00
	 * @param time
	 * @return
	 */
	public static String getTimeString6(long  time) {
		Calendar mDate = Calendar.getInstance();
		mDate.setTimeInMillis(time);

		String mmonth;
		String mday;
		String mhour;
		String mmin;
		String second;
		if ((mDate.get(Calendar.MONTH) + 1) < 10) {
			mmonth = "0" + (mDate.get(Calendar.MONTH) + 1);
		} else {
			mmonth = (mDate.get(Calendar.MONTH) + 1) + "";
		}
		if (mDate.get(Calendar.DAY_OF_MONTH) < 10) {
			mday = "0" + mDate.get(Calendar.DAY_OF_MONTH);
		} else {
			mday = mDate.get(Calendar.DAY_OF_MONTH) + "";
		}
		if (mDate.get(Calendar.HOUR_OF_DAY) < 10) {
			mhour = "0" + mDate.get(Calendar.HOUR_OF_DAY);
		} else {
			mhour = mDate.get(Calendar.HOUR_OF_DAY) + "";
		}
		if (mDate.get(Calendar.MINUTE) < 10) {
			mmin = "0" + mDate.get(Calendar.MINUTE);
		} else {
			mmin = mDate.get(Calendar.MINUTE) + "";
		}
		if (mDate.get(Calendar.SECOND) < 10) {
			second = "0" + mDate.get(Calendar.SECOND);
		} else {
			second = mDate.get(Calendar.SECOND) + "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(mmonth).append("月").append(mday)
				.append("日 ").append(mhour).append(":").append(mmin).append(":").append(second);
		return sb.toString();
	}

	/**
	 * 获取时间字符串
	 * 格式 00:00
	 * @param time
	 * @return
	 */
	public static String getTimeString7(long time) {
		Calendar mDate = Calendar.getInstance();
		mDate.setTimeInMillis(time);

		String mhour;
		String mmin;
		if (mDate.get(Calendar.HOUR_OF_DAY) < 10) {
			mhour = "0" + mDate.get(Calendar.HOUR_OF_DAY);
		} else {
			mhour = mDate.get(Calendar.HOUR_OF_DAY) + "";
		}
		if (mDate.get(Calendar.MINUTE) < 10) {
			mmin = "0" + mDate.get(Calendar.MINUTE);
		} else {
			mmin = mDate.get(Calendar.MINUTE) + "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(mhour).append(":").append(mmin);
		return sb.toString();
	}

	/**
	 * 两个时间相差距离多少天多少小时多少分多少秒
	 *
	 * @param diff 时间参数
	 * @return String 返回值为：xxd前 xxh前 xxm前 xxs前
	 */

	public static String getDistanceTime4(long diff) {
		String time = "";
		if (diff < 0) {
			diff = diff * -1;
		}
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		try {
			day = diff / (24 * 60 * 60 * 1000);
			hour = (diff / (60 * 60 * 1000) - day * 24);
			min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
			sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (day != 0) {
			if(day>=1 && day<30){
				time += day + "天前";
			}else if(day<365){
				time += day/30 +"月前";
			}else{
				time += day/365 +"年前";
			}
		}else if (hour != 0) {
			time += hour + "小时前";
		}else if (min > 0) {
			if(min<10){
				time += "刚刚";
			}else if(min>=10 && min<60){
				time += min + "分钟前";
			}
		}else {
			time = "刚刚";
		}
		return time;
	}

	/**
	 * 是否超过当前日期
	 * @param milliseconds
	 * @return
     */
	public static boolean isOverDate(long milliseconds) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		c.setTimeInMillis(milliseconds);
		int year2 = c.get(Calendar.YEAR);
		int month2 = c.get(Calendar.MONTH);
		int day2 = c.get(Calendar.DAY_OF_MONTH);
		if(year > year2){
			return true;
		}else if(year == year2){
			if(month > month2){
				return true;
			}else if(month == month2){
				if(day > day2){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 是否超过当前时间（分钟）
	 * @param milliseconds
	 * @return
	 */
	public static boolean isOverMin(long milliseconds) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
		c.setTimeInMillis(milliseconds);
		int year2 = c.get(Calendar.YEAR);
		int month2 = c.get(Calendar.MONTH);
		int day2 = c.get(Calendar.DAY_OF_MONTH);
		int hour2 = c.get(Calendar.HOUR_OF_DAY);
		int min2 = c.get(Calendar.MINUTE);
		if(year > year2){
			return true;
		}else if(year == year2){
			if(month > month2){
				return true;
			}else if(month == month2){
				if(day > day2){
					return true;
				}else if(day == day2){
					if(hour > hour2){
						return true;
					}else if(hour == hour2){
						if(min > min2){
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public static long stringToLong(String parseString, String dateString) {
		try {
			return new SimpleDateFormat(parseString).parse(dateString).getTime();
		} catch (ParseException e) {
			return System.currentTimeMillis();
		}
	}

	public static boolean isEqualDate(long milliseconds1, long milliseconds2) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(milliseconds1);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		c.setTimeInMillis(milliseconds2);
		int year2 = c.get(Calendar.YEAR);
		int month2 = c.get(Calendar.MONTH);
		int day2 = c.get(Calendar.DAY_OF_MONTH);
		if(year == year2 && month == month2 && day == day2){
			return true;
		}
		return false;
	}

	public static String getTimeBySecond(int second){
		String time;
		if (second < 60) {
			if (second < 10) {
				time ="0:0"+ second;
			} else {
				time ="0:" + second;
			}
		} else {
			time ="1:00";
		}

		return time;
	}

	/**
	 * 格式 0月0日 星期一
	 * @param time
	 * @return
	 */
	public static String MDWDateString(long time) {
		final Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
		mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当前月份的日期号码
		mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
		if ("1".equals(mWay)) {
			mWay = "日";
		} else if ("2".equals(mWay)) {
			mWay = "一";
		} else if ("3".equals(mWay)) {
			mWay = "二";
		} else if ("4".equals(mWay)) {
			mWay = "三";
		} else if ("5".equals(mWay)) {
			mWay = "四";
		} else if ("6".equals(mWay)) {
			mWay = "五";
		} else if ("7".equals(mWay)) {
			mWay = "六";
		}

		return mMonth + "月" + mDay + "日  " + "周" + mWay;
	}

	// 获取当前时间所在年的周数
	public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);

		return c.get(Calendar.WEEK_OF_YEAR);
	}

	// 获取当前时间所在年的最大周数
	public static int getMaxWeekNumOfYear(int year) {
		Calendar c = new GregorianCalendar();
		//设置时间为该年的最后一天最后一刻
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
		return getWeekOfYear(c.getTime());
	}

	private static Calendar getCalendarFormYear(int year){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.set(Calendar.YEAR, year);
		return cal;
	}

	// 获取某年的第几周的开始日期
	public static Date getFirstDayOfWeek(int year, int week) {
		Calendar c = getCalendarFormYear(year);
		c.set(Calendar.WEEK_OF_YEAR, week);
		return c.getTime();
	}

	// 获取某年的第几周的结束日期
	public static Date getLastDayOfWeek(int year, int week) {
		Calendar c = getCalendarFormYear(year);
		c.set(Calendar.WEEK_OF_YEAR, week);
		c.add(Calendar.DAY_OF_WEEK, 6);
		return c.getTime();
	}

	//获取当前时间所在周
	public static int currentDayOfWeekIndex(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);//设置每周的第一天为星期一
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.get(Calendar.WEEK_OF_YEAR);
	}
}
