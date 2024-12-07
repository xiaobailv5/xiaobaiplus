package com.lv.xiaobaiplus.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 日期工具类
 *
 */
public class DateUtil {

    /**
     * 默认日期格式
     */
    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_FORMAT_UUID = "yyyyMMddHHmmssSSSS";




    public static String getFormatData(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(Calendar.getInstance().getTime());
    }

    public static  String getUUID(){
        int num = new Random().nextInt(9999);
        try {
            TimeUnit.NANOSECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String uuid =  getFormatData(DEFAULT_FORMAT_UUID);
        return uuid+num+"";
    }
}