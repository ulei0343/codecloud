package club.codecloud.base.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author ulei
 * @date 2018/4/19
 */
public class DateUtils {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final String DATE_FORMAT_1 = "yyyy/MM/dd";

    public static final String DATE_FORMAT_2 = "yyyyMMdd";

    public static final String DATE_FORMAT_3 = "yyyy.MM.dd";

    public static final String DATE_FORMAT_CN = "yyyy年MM月dd日";

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_TIME_FORMAT_EN = "yyyy/MM/dd HH:mm:ss";

    public static final String DATE_TIME_FORMAT_CN = "yyyy年MM月dd日 HH:mm";

    public static final String DATE_TIME_FORMAT_CN_1 = "yyyy年MM月dd日 HH时mm分";

    public static final String DATE_TIME_FORMAT_CN_2 = "yyyy年MM月dd日 HH时mm分ss秒";

    public static final String DATE_TIME_FORMAT_1 = "yyyy-MM-dd HH:mm";

    public static final String TIME_FORMAT = "HH:mm";

    public static final String MONTH_DAY_FORMAT = "MM-dd";

    public static final String MONTH_DAY_FORMAT_CN = "MM月dd日";

    public static final String MONTH_DAY_FORMAT_EN = "MMdd";

    public static final String YEAR_MONTH_FORMAT = "yyyy-MM";

    public static final String YEAR_MONTH_FORMAT_CN = "yyyy年MM月";

    public static final String YEAR_MONTH_FORMAT_EN = "yyyyMM";

    public static final long DAY_MILLISECONDS = 86400000L;


    /**
     * 获取当前日期
     *
     * @return
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 日期转成默认格式 yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String toDateString(Date date) {
        return toString(date, DATE_FORMAT);
    }

    public static String toDateTimeString(Date date) {
        return toString(date, DATE_TIME_FORMAT);
    }

    /**
     * 时间戳转成默认格式 yyyy-MM-dd
     *
     * @param timestamp
     * @return
     */
    public static String toDateString(long timestamp) {
        return toString(new Date(timestamp), DATE_FORMAT);
    }


    /**
     * 日期转成指定格式
     *
     * @param date
     * @param format
     * @return
     */
    public static String toString(Date date, String format) {
        if (date == null || format == null) {
            return null;
        }

        return createDateFormat(format).format(date);
    }

    /**
     * 时间戳转成默认格式 yyyy-MM-dd
     *
     * @param timestamp
     * @param format
     * @return
     */
    public static String toString(long timestamp, String format) {
        return toString(new Date(timestamp), format);
    }

    /**
     * 日期格式化
     *
     * @param format
     * @return
     */
    private static DateFormat createDateFormat(String format) {
        return new SimpleDateFormat(format, Locale.SIMPLIFIED_CHINESE);
    }

    /**
     * yyyy-MM-dd 字符串转化成日期
     *
     * @param dateText
     * @return
     */
    public static Date toDate(String dateText) throws ParseException {
        return toDate(dateText, DATE_FORMAT);

    }

    /**
     * 转换成sqlDate
     *
     * @param date
     * @return
     */
    public static java.sql.Date toSqlDate(Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }

    /**
     * 转换成utilDate
     *
     * @param date
     * @return
     */
    public static Date toUtildate(java.sql.Date date) {
        if (date == null) {
            return null;
        }
        return new Date(date.getTime());
    }

    /**
     * 指定格式化方式，将字符串转化成日期
     *
     * @param dateText
     * @param format
     * @return
     */
    public static Date toDate(String dateText, String format) throws ParseException {
        DateFormat dateFormat = createDateFormat(format);
        return dateFormat.parse(dateText);
    }

    /**
     * 判断当前日是不是周末
     *
     * @param date
     * @throws ParseException
     * @author
     */
    public static boolean isWeekend(Date date) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    /**
     * 判断当前日是否是今天
     * 只判断年月日，不考虑时分秒
     *
     * @param date
     * @return
     */
    public static boolean isToday(Date date) {
        if (date == null) {
            return false;
        }
        String dateAsText = toDateString(date);
        String todayAsText = toDateString(now());
        return dateAsText.equals(todayAsText);
    }

    /**
     * 计算两日期之间相差天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long daysBetween(Date startDate, Date endDate) throws ParseException {
        DateFormat sdf = createDateFormat(DATE_FORMAT);
        startDate = sdf.parse(sdf.format(startDate));
        endDate = sdf.parse(sdf.format(endDate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        long startTime = cal.getTimeInMillis();
        cal.setTime(endDate);
        long endTime = cal.getTimeInMillis();
        return (endTime - startTime) / DAY_MILLISECONDS;
    }

    /**
     * 调整天数
     *
     * @param date
     * @param day  天数 ，支持负值
     * @return
     */
    public static Date addDay(Date date, int day) {
        return addDate(date, Calendar.DATE, day);
    }

    /**
     * 调整时间
     *
     * @param date
     * @param field
     * @param amount
     * @return
     */
    public static Date addDate(Date date, int field, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(field, amount);
        return cal.getTime();
    }
}
