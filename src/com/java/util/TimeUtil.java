package com.java.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class TimeUtil {

	/**
	 * 将日期格式化成String类型
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatTime(Date date,String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		//设置为东八区
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));		
		return sdf.format(date);
	}

}
