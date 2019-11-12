package com.blingabc.auto.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-09-20:16:29
 * Modify date: 2019-09-20:16:29
 */
public class DateUtil {

    public static String dateFormat(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static void main(String[] args) {
        String date = DateUtil.dateFormat(new Date());
        System.out.println(date);
    }

    public static Date parse(long timestramp){
        Date date = new Date(timestramp);
        return date;
    }

    public static Integer timeDifferLong(long startTime, long returnTime) {
        double hours = (double)(returnTime-startTime)/3600/1000;
        int mis = (int) (hours*3600);
        return mis;
    }
}
