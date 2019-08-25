package cn.edu.upc.yb.innersystem.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class util {
    // 判断日期格式函数
    public static boolean isValidDate(String s) {
        try {
            // 指定日期格式为四位年/两位月份/两位日期
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            dateFormat.parse(s);
            return isDateExpired(s);
        }catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }

    public static boolean isDateExpired (String s) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat();
            sdf.applyPattern("yyyy-MM-dd");
            Date date = new Date();
            if (s.compareTo(sdf.format(date)) < 0) {
                return true;
            }
            return false; //如果为false说明未逾期
        }catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }
}
