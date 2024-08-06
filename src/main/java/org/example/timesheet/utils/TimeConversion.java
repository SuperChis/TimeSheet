package org.example.timesheet.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.time.format.DateTimeFormatter;

public class TimeConversion {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    /**
     * LocalTime to String
     *
     * @LocalTime
     * @String
     */
    public static String convertLocalTimeToString(LocalTime time) {
        return time.format(TIME_FORMATTER);
    }

    /**
     * String to LocalTime
     *
     * @From: String
     * @To: LocalTime
     * @Author: Chidt
     */
    public static LocalTime convertStringToLocalTime(String timeString) {
        return LocalTime.parse(timeString, TIME_FORMATTER);
    }

    /**
     * LocalTime to Date
     *
     * @LocalTime
     * @Date
     */
    public static Date convertLocalTimeToDate(LocalTime localTime) {
        LocalDate currentDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(currentDate, localTime);

        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return date;
    }

    public static Date parseStringToDate(String timeString) {
        LocalTime localTime = convertStringToLocalTime(timeString);
        LocalDate currentDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(currentDate, localTime);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static void main(String[] args) {
        LocalTime localTime = LocalTime.of(14, 30); // 14:30

        Date date = convertLocalTimeToDate(localTime);

        System.out.println(date);
    }

    public static Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}
