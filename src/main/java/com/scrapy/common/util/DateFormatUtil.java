package com.scrapy.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
public class DateFormatUtil {
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone("Asia/Seoul");

    /**
     * 주어진 패턴으로 현재 날짜를 문자열로 변환
     */
    public static String getCurrentDate(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(DEFAULT_TIMEZONE);
        return sdf.format(new Date());
    }

    /**
     * 기본 패턴(yyyy-MM-dd HH:mm:ss)으로 현재 날짜를 문자열로 변환
     */
    public static String getCurrentDate() {
        return getCurrentDate(DEFAULT_PATTERN);
    }

    /**
     * Date 객체를 특정 패턴의 문자열로 변환
     */
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(DEFAULT_TIMEZONE);
        return sdf.format(date);
    }

    /**
     * 특정 패턴의 문자열을 Date 객체로 변환
     */
    public static Date parseDate(String dateStr, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setTimeZone(DEFAULT_TIMEZONE);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("날짜 형식이 올바르지 않습니다: " + dateStr, e);
        }
    }

    /**
     * 기본 패턴(yyyy-MM-dd HH:mm:ss)으로 문자열을 Date 객체로 변환
     */
    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, DEFAULT_PATTERN);
    }
}
