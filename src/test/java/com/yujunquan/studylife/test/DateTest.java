package com.yujunquan.studylife.test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateTest {
    private static double getDaysCount(Date startDate, Date endDate) {

        // 根据起始日期计算起始的毫秒
        long startTime = startDate.getTime();
        BigDecimal startTimeB = new BigDecimal(startTime);
        // 根据终止日期计算终止的毫秒
        long endTime = endDate.getTime();
        BigDecimal endTimeB = new BigDecimal(endTime);

        BigDecimal oneDayMillis = new BigDecimal(24 * 60 * 60 * 1000);
        BigDecimal result = endTimeB.subtract(startTimeB).divide(oneDayMillis, 1, BigDecimal.ROUND_FLOOR);

        double v = result.doubleValue();
        return v;
    }

    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {

            Date date2 = sdf.parse("2019-08-03");

            System.out.println(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main12(String[] args) {
        System.out.println(24 * 60 * 60 * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date1 = sdf.parse("2019-08-05 00:00:00");
            Date date2 = sdf.parse("2019-08-03 23:59:59");
            double daysCount = getDaysCount(date1, date2);
            System.out.println(daysCount);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main11(String[] args) {
        SimpleDateFormat sdfTmp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(0L);
        System.out.println(sdfTmp.format(date));
        //String dateStr = sdfTmp.format(new Date());
        try {
//            Date date1 = sdfTmp.parse("2019-08-01 00:00:00");
//            Date date2 = sdfTmp.parse("2019-08-02 01:00:00");
//            long daysCount = getDaysCount(date1, date2);
//            System.out.println(daysCount);
//            System.out.println("***********");
            Date date3 = sdfTmp.parse("1970-01-01 08:00:00");
            System.out.println(date3);
            System.out.println(date3.getTime());
            String format = sdfTmp.format(date3);
            System.out.println(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public static void main9(String[] args) {
        SimpleDateFormat sdfTmp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String dateStr = sdfTmp.format(new Date());
        try {
            Date date1 = sdfTmp.parse("2019-11-11 11:09:55");
            Date date2 = sdfTmp.parse("2019-11-02 11:09:55");

            int i = date1.compareTo(date2);
            System.out.println(i);
            System.out.println(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //System.out.println(dateStr.length());

    }

    public static void main8(String[] args) {
    System.out.println(new Date().getTime());
    System.out.println(System.currentTimeMillis());
    System.out.println(new Date(1562567906855L));
  }

    public void test1(){

        new Boolean("");
        System.out.println("JUnit test");
    }

    public static void main7(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = sdf.parse("2019-11-11 24:9:55");//解析异常
            System.out.println(parse);
            System.out.println(sdf.format(parse));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main6(String[] args) {
        Date date = new Date(null);// 解析异常
        System.out.println(date);
    }

    public static void main5(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = "2019-01-01 0:11:00";
        try {
            Date parse = sdf.parse(strDate);
            System.out.println(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static void main2(String[] args) {
        Date date = new Date();

        Date date1 = new Date();
        System.out.println(date.getTime());
        System.out.println(date1.getTime());
        if(date.equals(date1)){
            if(date.after(date1)){

            }
            System.out.println(date.getTime());
            System.out.println(date1.getTime());
            System.out.println("相等");

        }
        System.out.println("test");
    }
}
