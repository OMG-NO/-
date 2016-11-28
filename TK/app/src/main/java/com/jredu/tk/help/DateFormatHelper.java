package com.jredu.tk.help;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/6.
 * 获取当前时间并且转换成24小时制
 */

public class DateFormatHelper {
   public static String dateFormat(){
       Date date = new Date();
       SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制
       String time = sdformat.format(date);
       return time;
   }
}
