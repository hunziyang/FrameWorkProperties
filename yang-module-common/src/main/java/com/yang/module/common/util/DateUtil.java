package com.yang.module.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 解决SimpleDateFormat线程不安全问题
 */
public class DateUtil {
    /**
     * 默认格式化模式
     */
    private static final String DEFAULT_FOMRT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 重写initialValue 方法
     *    当我们使用
     *       ThreadLocal 的 get 方法的时候
     *       ThreadLocal 会先查看我们 是否主动使用了  set方法设置了值
     *       如果没有，或者set进去的被remove了，就使用 initialValue 返回的值
     *       如果不重写，initialValue 默认返回 null
     *
     * 这里重写了 initialValue 我们就不需要手动 set的方式去添加 SimpleDateFormat
     */
    private static final ThreadLocal<SimpleDateFormat> threadLocalSdf = new ThreadLocal<SimpleDateFormat>(){
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat();
        }
    };

    /**
     * 设置格式化模式
     * @param pattern
     */
    public static void setPattern(String pattern){
        threadLocalSdf.get().applyPattern(pattern);
    }

    /**
     * 使用默认的 pattren
     * @param date
     * @return
     */
    public static String format(Date date){
        return format(date, DEFAULT_FOMRT);
    }

    /**
     * 根据传入的pattern设置
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern){
        if(pattern == null || "".equals(pattern)){
            throw new IllegalArgumentException("pattern is not allow null or '' ");
        }
        setPattern(pattern);
        return threadLocalSdf.get().format(date);
    }

    /**
     * 使用默认的pattern
     * @param dateStr
     * @return
     */
    public static Date parse(String dateStr) throws ParseException {
        return parse(dateStr, DEFAULT_FOMRT);
    }

    /**
     * 手动设置模式
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date parse(String dateStr, String pattern) throws ParseException {
        setPattern(pattern);
        Date date = threadLocalSdf.get().parse(dateStr);
        return date;
    }
}
