package com.hac.test;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author hac
 * @date 2025/4/7 20:12
 */
public class TestJavaTime {
    /**
     * 传统日期类 (java.util 包)
     */
    public static void main1(String[] args) {
        /**
         * Date 类示例
         */
        Date date = new Date();
        System.out.println(date); // 当前时间  底层使用System.currentTimeMillis()获取时间
        System.out.println(date.getTime()); // Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Date object.

        /*
        SimpleDateFormat 要实现线程安全需要额外开销：
        ①每次创建新实例
        ②使用ThreadLocal的维护成本
         */
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 线程不安全 ❌

        System.out.println(sdf.format(date));

        Date date1 = new Date(2036 - 1900, 5, 14, 13, 43, 4);
        System.out.println(date1);
        System.out.println(sdf.format(date1));
        /**
         *  Calendar 类示例
         */
        Calendar calendar = Calendar.getInstance();  // Calendar是抽象类，不能直接创建对象，需要使用getInstance()方法获取实例
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH) + 1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println(calendar.get(Calendar.MINUTE));
        System.out.println(calendar.get(Calendar.SECOND));

    }

    /**
     * Java 8+ 的日期时间 API  [java.time]
     */
    public static void main2(String[] args) {
        /**
         * Instant 类 (时间戳)
         */
        // 2025-04-07T12:26:25.300Z  T：日期和时间的分隔符   Z：表示时区
        Instant now = Instant.now(); // Clock.systemUTC().instant()
        System.out.println(now);


        System.out.println(now.plus(Duration.ofDays(1)));// plus() 方法用于将当前时间加上指定的时间量，返回一个新的Instant对象。
        System.out.println(now.minus(Duration.ofDays(1)));
        System.out.println(now.minus(5, ChronoUnit.DAYS)); // ChronoUnit 提供了多种时间单位

        /**
         * LocalDate 类 (日期)
         */
        LocalDate today = LocalDate.now();
        System.out.println("今天: " + today);

        LocalDate birthday = LocalDate.of(1990, Month.JULY, 15);
        System.out.println("生日: " + birthday);

        LocalDate nextWeek = today.plusWeeks(1);
        System.out.println(nextWeek);
        LocalDate previousMonthSameDay = today.minusMonths(1);
        System.out.println(previousMonthSameDay);

        // 日期比较
        if (today.isAfter(birthday)) {
            System.out.println("生日已经过去了");
        }

        // 计算两个日期之间的差
        Period period = Period.between(birthday, today);
        System.out.printf("年龄: %d年 %d个月 %d天%n",
                period.getYears(), period.getMonths(), period.getDays());

        // 日期格式化
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String formattedDate = today.format(formatter);
        System.out.println("格式化日期: " + formattedDate);

        /**
         *  LocalTime 类 (时间)
         *  LocalDateTime 类 (日期和时间)
         */
        LocalTime now1 = LocalTime.now();
        System.out.println("当前时间: " + now);


        LocalDateTime now2 = LocalDateTime.now();
        System.out.println("当前日期时间: " + now);

        /**
         * Period 和 Duration 类  Period 和 Duration 是两个用于表示时间间隔的类
         * Period 用于表示基于日期的间隔，例如：1年2个月3天。
         * Duration 用于表示基于时间的间隔，例如：1小时2分钟3秒。
         */

        // Period示例 (基于日期)
        LocalDate date1 = LocalDate.of(2020, 1, 1);
        LocalDate date2 = LocalDate.of(2023, 6, 15);
        Period period1 = Period.between(date1, date2);
        System.out.printf("时间差: %d年 %d个月 %d天%n",
                period1.getYears(), period1.getMonths(), period1.getDays());

        // Duration示例 (基于时间)
        LocalTime time1 = LocalTime.of(8, 0);
        LocalTime time2 = LocalTime.of(17, 30);
        Duration duration = Duration.between(time1, time2);
        System.out.println("工作时间(小时): " + duration.toHours());
        System.out.println("工作时间(分钟): " + duration.toMinutes());

        /**
         * DateTimeFormatter 类 (日期时间格式化) 线程安全【有final修饰】
         */
        LocalDateTime now3 = LocalDateTime.now();

        // 预定义格式
        DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_DATE_TIME;
        System.out.println("ISO格式: " + now3.format(isoFormatter));

        // 本地化格式
        DateTimeFormatter localizedFormatter =
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                        .withLocale(Locale.CHINA);
        System.out.println("本地化格式: " + now3.format(localizedFormatter));
    }

    /**
     * Hutool 的 DateUtil
     * @param args
     */
    public static void main(String[] args) {
        String now = DateUtil.now();
        DateTime offset = DateUtil.offsetDay(new Date(), -1);
        System.out.println(now);
        System.out.println(offset);
    }
}
