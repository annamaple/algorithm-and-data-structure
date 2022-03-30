package com.annamaple.utils.netutil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xionglei
 * @create 2022-03-15 15:05
 */
public class HttpUtil {
    
    public static void main(String[] args) {
        System.out.println(new HttpUtil().verifyPhone("17528122215"));

    }

    /**
     * 校验手机号
     * @param phone
     * @return
     */
    private boolean verifyPhone(String phone) {
        if(phone == null || phone.trim().isEmpty()) {
            return false;
        }
        // 手机号正则表达式
        String regex = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        return m.matches();
    }
}
