package com.hodanet.sanre.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>
 * Spirng日期格式转化
 * </pre>
 */
public class DateConverterUtil {

    public static final SimpleDateFormat FORMAT               = new SimpleDateFormat("yyyy-MM-dd");

    public static final String           yyyyMMdd             = "yyyy-MM-dd";

    public static final String           yyyyMMddHHmmss       = "yyyyMMddHHmmss";

    public static final String           yyyyMMddHHmmss_SSS   = "yyyyMMddHHmmss_SSS";

    public static final String           yyyyYearMMMonthddDay = "yyyy年MM月dd日";

    public static String format(Date date, String pattern) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            return dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String format(String pattern) {
        return format(new Date(), pattern);
    }

    public static Date parse(String source, String pattern) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.parse(source);
    }

    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(yyyyMMddHHmmss_SSS);
        System.out.println(dateFormat.format(new Date()));
    }
}
