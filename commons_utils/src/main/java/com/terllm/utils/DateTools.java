package com.terllm.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.GregorianCalendar;


public class DateTools {

    private static final Logger LOG            = LoggerFactory.getLogger(DateTools.class);


    private static ThreadLocal<SimpleDateFormat> ACCURATE_SECONDS   = new ThreadLocal<SimpleDateFormat>() {
                                                                        @Override
                                                                        public SimpleDateFormat initialValue() {
                                                                            return new SimpleDateFormat(
                                                                                "yyyy-MM-dd HH:mm:ss");
                                                                        }
                                                                    };
    private static ThreadLocal<SimpleDateFormat> ACCURATE_DAYS      = new ThreadLocal<SimpleDateFormat>() {
                                                                        @Override
                                                                        public SimpleDateFormat initialValue() {
                                                                            return new SimpleDateFormat(
                                                                                "yyyy-MM-dd");
                                                                        }
                                                                    };
    private static ThreadLocal<SimpleDateFormat> ACCURATE_DAYS_NULL = new ThreadLocal<SimpleDateFormat>() {
                                                                        @Override
                                                                        public SimpleDateFormat initialValue() {
                                                                            return new SimpleDateFormat(
                                                                                "yyyyMMdd");
                                                                        }
                                                                    };

    public static final  String                   DATEYYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final  String                   DATEYYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    public static final  String                   DATEYYYYMMDD       = "yyyyMMdd";
    public static final String                      HHMMSS  =   "HHmmss";
    
   
    
    public static final String DATEFORMAT="yyyy/MM/dd HH:mm:ss";
   
    
    public static final String  SEPARATOR="/";
    public static final String  SPACESEPARATOR=" ";
    public static final String  COLONSEPARATOR=":";

    /**
     * DATEYYYYMMDDHHMMSS
     * 
     * @return
     */
    public static String getCurDate() {
        return dateToDateString(Calendar.getInstance().getTime(), DATEYYYYMMDDHHMMSS);
    }
    
    public static String getCurDateMil() {
        return dateToDateString(Calendar.getInstance().getTime(), DATEYYYYMMDDHHMMSSSSS);
    }
    /**
     * 
     * 
     * @return
     */
    public static String getCurDateYYYYMMDD() {
        return dateToDateString(Calendar.getInstance().getTime(), DATEYYYYMMDD);
    }
    
    /**
     * 
     * 
     * @return
     */
    public static String getCurDateHHMMSS() {
        return dateToDateString(Calendar.getInstance().getTime(), HHMMSS);
    }


    /**
     * 将Date转换成字符串
     * @param date
     * @param formatStr
     * @return
     */
    public static String dateToDateString(Date date, String formatStr) {
        if (date == null) {
            return null;
        }
        java.text.DateFormat df = getDateFormat(formatStr);
        return date != null ? df.format(date) : "";
    }

    public static SimpleDateFormat getDateFormat(final String formatStr) {
        return new SimpleDateFormat(formatStr);
    }
    
    /**
    * //获取星期
    *
    * @return
    * @throws ParseException
    */
    public static String getWeek(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String week = sdf.format(date);
        return week;
    }

    /**
    * 将如期转为 yyyy-MM-dd HH:mm:ss
    *
    * @param date
    * @return
    */
    public static String formatACCURATE_SECONDS(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sf.format(date);
        } catch (Exception e) {
            LOG.error("sf.parse sysetm error;param=" + date + ";message:" + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
    * 将字符串转换为日期的类型，yyyyMMddHHmmss；支持多并发
    *
    * @param date
    * @return
    */
    public static String formatYYYYMMDDHHMMSS(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat(DATEYYYYMMDDHHMMSS);
        try {
            return sf.format(date);
        } catch (Exception e) {
            LOG.error("sf.parse sysetm error;param=" + date + ";message:" + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
    * 将字符串转换为日期的类型，yyyyMMddHHmmss；支持多并发
    *
    * @param date
    * @return
    */
    public static Date parseYYYYMMDDHHMMSS(String date) {
        SimpleDateFormat sf = new SimpleDateFormat(DATEYYYYMMDDHHMMSS);
        try {
            return sf.parse(date);
        } catch (ParseException e) {
            LOG.error("sf.parse sysetm error;param=" + date + ";message:" + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
    * 字符串转日期，精确到秒
    *
    * @param arg
    * @return
    * @throws ParseException
    */
    public static Date accurateSecond(String arg) throws ParseException {
        return ACCURATE_SECONDS.get().parse(arg);
    }

    /**
    * 字符日期判断，返回最大的日期
    *
    * @param arg0
    * @param arg1
    * @return Date
    * @throws ParseException
    */
    public static Date MaxDateAccurateSecond(String arg0, String arg1) throws ParseException {
        Date date0 = ACCURATE_SECONDS.get().parse(arg0);
        Date date1 = ACCURATE_SECONDS.get().parse(arg1);
        return date0.compareTo(date1) == 1 ? date0 : date1;
    }

    /**
    * 字符串转日期，精确到天 日期格式为yyyy-MM-dd
    *
    * @param arg
    * @return
    * @throws ParseException
    */
    public static Date accurateDay(String arg) throws ParseException {
        return ACCURATE_DAYS.get().parse(arg);
    }

    /**
    * 字符串转日期，精确到天 日期格式为yyyyMMdd
    *
    * @param arg
    * @return
    * @throws ParseException
    */
    public static Date accurateDayByNo(String arg) throws ParseException {
        return ACCURATE_DAYS_NULL.get().parse(arg);
    }

    /**
    * 日期串转字符，精确到天
    *
    * @param arg
    * @return
    * @throws ParseException
    */
    public static String accurateDay(Date arg) throws ParseException {
        return ACCURATE_DAYS.get().format(arg);
    }

    /**
    * 字符串转日期，模糊判断， 超过10的则精确到秒，反之精确到天
    *
    * @param arg
    * @return
    * @throws ParseException
    */
    public static Date ignoreDate(String arg) throws ParseException {
        return arg.length() > 10 ? ACCURATE_SECONDS.get().parse(arg)
            : ACCURATE_DAYS.get().parse(arg);
    }

    /**
    * 获得当前系统时间,精确到秒
    *
    * @return
    */
    public static String getSysAccurateSecond() {
        return ACCURATE_SECONDS.get().format(new Date());
    }

    /**
    * 获得当前系统时间,精确到天
    *
    * @return
    */
    public static String getSysAccurateDay() {
        return ACCURATE_DAYS.get().format(new Date());
    }

    /**
    * 获得当前系统时间,精确到天
    *
    * @return yyyyMMdd
    */
    public static String getSysAccurateDayNull() {
        return ACCURATE_DAYS_NULL.get().format(new Date());
    }

    /**
    * 获得时间,精确到天
    *
    * @return yyyyMMdd
    */
    public static String getSysAccurateDay(Date date) {
        return ACCURATE_DAYS_NULL.get().format(date);
    }

    /**
    * 获得相对于当前系统日期的时间,精确到天
    *
    * @param day
    * @return yyyyMMdd
    */
    public static String getSysAccurateDayNull(int day) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, day);
        return ACCURATE_DAYS_NULL.get().format(c.getTime());
    }

    /**
    *根据day日期进行加法计算
    *
    * @param date
    * @param day
    * @return
    * @returnType Date
    */
    public static Date addDate(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, day);
        return c.getTime();
    }

    /**
    * 返回格式为yyyy-MM-dd时间
    *
    * @param date
    * @return
    * @throws ParseException
    * @returnType Date
    */
    public static Date getTime(Date date) throws ParseException {
        return DateTools.accurateDay(DateTools.accurateDay(date));
    }

    /**
    * 处理时间，只保留到天
    *
    * @param arg
    * @return Calendar
    */
    public static Calendar retainDay(Date arg) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(arg);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
    * 计算arg0相对于arg1相差天数
    *
    * @param arg0 较小
    * @param arg1
    * @return
    */
    public static int diffDay(Date arg0, Date arg1) {
        Calendar arg2 = retainDay(arg0);
        Calendar arg3 = retainDay(arg1);
        int result = 0;
        if (arg2.getTimeInMillis() > arg3.getTimeInMillis()) {
            throw new RuntimeException("无效的参数：arg0较大");
        }

        while (arg2.compareTo(arg3) != 0) {
            arg2.add(Calendar.DAY_OF_MONTH, 1);
            result++;
        }
        return result;
    }

    /**
    * 计算两个时间相差的天数<br>
    * 包括当天
    *
    * @param date1 起期
    * @param date2 止期
    * @return
    */
    public static long calcDays(Date date1, Date date2) {
        return (date2.getTime() - date1.getTime()) / (1000 * 24 * 60 * 60) + 1;
    }

    /**
    * 修改日期
    *
    * @param date 日期
    * @param day 天数
    * @return
    */
    public static Calendar modifyDay(Date date, int day) {
        Calendar celendar = Calendar.getInstance();
        celendar.setTime(date);
        celendar.add(Calendar.DAY_OF_YEAR, day);
        return celendar;
    }

    public static String formatDate(Date date) {
        return DateFormatUtils.format(date, "yyyyMMdd");
    }

    public static String formatDate(Date date, String format) {
        return DateFormatUtils.format(date, format);
    }

    public static Date parseString(String date) {
        try {
          return DateUtils.parseDate(date,
                new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy-MM-dd",
                               "yyyy/MM/dd", "yy-MM-dd HH:mm:ss", "yy/MM/dd hh:mm:ss",
                               "yyyyMMdd","yyyyMMddHHmmss" });
        	
        } catch (Exception e) {
            LOG.error("parse date exception, dateStr:" + date, e);
        }
        return null;
    }

    /**
    *
     * 瓶装开始时间
    * 00：00：00
    *
    * @return
    * @returnType Date
    * @see
    */
    public static Date getStartDate(Date strDate) {
        if (strDate == null) {
            return null;
        }
        Calendar start = new GregorianCalendar();
        start.setTime(strDate);
        start.set(Calendar.HOUR_OF_DAY, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        start.set(Calendar.MILLISECOND, 0);
        return start.getTime();

    }

    /**
    * 拼装当天结束时间
    * 23：59：59
    *
    * @return
    * @returnType Date
    */
    public static Date getEndDate(Date strDate) {
        if (strDate == null) {
            return null;
        }
        Calendar end = new GregorianCalendar();
        end.setTime(strDate);
        end.set(Calendar.HOUR_OF_DAY, 23);
        end.set(Calendar.MINUTE, 59);
        end.set(Calendar.SECOND, 59);
        end.set(Calendar.MILLISECOND, 0);
        return end.getTime();
    }

    /**
    * @Description:比较两个时间点是否相等
    * @param firstDate
    * @param secondDate
    * @ReturnType boolean
    * @author:
    */
    public static boolean isEqual(Date firstDate, Date secondDate) {
        return compare(firstDate, secondDate) == 0 ? true : false;
    }

    /**
    * 比较两个时间     secondDate表示的时间等于此 firstDate 表示的时间，则返回 0 值； 如果
    *                      firstDate 的时间在参数<secondDate>表示的时间之前，则返回小于 0 的值； 则大于0
    * @param firstDate
    * @param secondDate
    * @ReturnType int
    * @author:
    *
    */
    public static int compare(Date firstDate, Date secondDate) {
        if (firstDate == null || secondDate == null) {
            throw new RuntimeException("比较两个时间点 不允许为空");
        }
        Calendar firstCalendar = Calendar.getInstance();
        firstCalendar.setTime(firstDate);

        Calendar secondCalendar = Calendar.getInstance();
        secondCalendar.setTime(secondDate);

        return firstCalendar.compareTo(secondCalendar);
    }

    public static void main(String[] args) {
        System.out.println(compare(new Date(), parseString("2016-08-23")));
    }

    /**
    * 计算两个时间的相隔天数
    *


    *
    * @param date1 起期
    * @param date2 止期
    * @param day 需要加上的天数
    * @return
    */
    @Deprecated
    public static int calcDays(Date date1, Date date2, int day) {
        return (int) ((date2.getTime() - date1.getTime()) / (1000 * 24 * 60 * 60) + day);
    }

    /**
    * 获取时间，获取num天后的日期
    *
    * @return
    */
    public static Date getFormatDate(int num) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, num);
        String d = ACCURATE_DAYS.get().format(c.getTime());
        try {
            return ACCURATE_SECONDS.get().parse(d + " 00:00:00");
        } catch (ParseException e) {
            LOG.error("日期转换错误。。。", e);
            throw new RuntimeException();
        }
    }
    
    /**
     * 查询某个时间之前多少分钟的时间
     *
     * @return
     * @returnType Date
     */
     public static Date getStartDateBefore(Date strDate,int minute) {
         if (strDate == null) {
             return null;
         }
         return DateUtils.addMinutes(strDate, minute);

     }
     
     /**
      * 格式转换
      * @param parseDate
      * @return
      */
     public static String convert2Date14(String parseDate){
    	 
    	 if(StringUtils.isBlank(parseDate)){
    		 return "";
    	 }
    	 
    	 StringBuilder dateBuilder= new StringBuilder();
    	 dateBuilder.append(parseDate.substring(0, 4));
    	 dateBuilder.append(SEPARATOR);
    	 dateBuilder.append(parseDate.substring(4, 6));
    	 dateBuilder.append(SEPARATOR);
    	 dateBuilder.append(parseDate.substring(6, 8));
    	 dateBuilder.append(SPACESEPARATOR);
    	 dateBuilder.append(parseDate.substring(8, 10));
    	 dateBuilder.append(COLONSEPARATOR);
    	 dateBuilder.append(parseDate.substring(10,12));
    	 dateBuilder.append(COLONSEPARATOR);
    	 dateBuilder.append(parseDate.substring(12,14));
    	 
    	 return dateBuilder.toString();
    	
     }
     
     /**
      * 将如期转为 yyyy-MM-dd HH:mm:ss
      *
      * @param date
      * @return
      */
      public static Date dateStringToDate(String date,String Dateformat) {
          SimpleDateFormat sf = new SimpleDateFormat(Dateformat);
          try {
              return sf.parse(date);
          } catch (Exception e) {
              LOG.error("sf.parse sysetm error;param=" + date + ";message:" + e.getMessage(), e);
              throw new RuntimeException(e);
          }
      }
      
     
  	
     

}
