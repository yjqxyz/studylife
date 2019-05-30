package com.yujunquan.studylife.util;

public class StringUtils  extends org.apache.commons.lang3.StringUtils {
    //org.apache.commons.lang3.StringUtils
    //org.springframework.util.StringUtils
    public static void main(String[] args) {
        String str = "11010719750110245X";
        String birthdayByIdCard = getBirthdayByIdCard(str);
        System.out.println(birthdayByIdCard);
    }
    public void test1(){


    }

    /**
     * 根据身份证号获取出生日期
     * @param idCard
     * @return
     */
    public static   String getBirthdayByIdCard(String idCard) {
        if(idCard == null || "".equals(idCard)){
            return null;
        }
        String idCardNumber = idCard.trim();
        int idCardLength = idCardNumber.length();
        String birthday = null;
        if (idCardNumber == null || "".equals(idCardNumber)) {
            return null;
        }
        if (idCardLength == 18) {
            birthday = idCardNumber.substring(6, 10) + "-"
                    + idCardNumber.substring(10, 12) + "-"
                    + idCardNumber.substring(12, 14);
        }
        if (idCardLength == 15) {
            birthday = "19" + idCardNumber.substring(6,8) + "-"
                    + idCardNumber.substring(8, 10) + "-"
                    + idCardNumber.substring(10, 12);
        }
        return birthday;
    }
}
