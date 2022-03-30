package com.annamaple.utils.webapi;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author xionglei
 * @create 2021-09-07 9:31
 */
public class Test {

    public static void main(String[] args) {
        String getTokenUrl = "http://localhost:9000/sso/getToken";
        String getUserInfo = "http://localhost:9000/sso/getUser";

        Map<String, String> map = new HashMap<>();
        map.put("code", UUID.randomUUID().toString());
        map.put("redirect", getTokenUrl);
        String result = WebApiUtilsRec.sendGet(getTokenUrl, map);
        System.out.println("result: " + result);

        map.clear();
        map.put("token", result);
        String userInfo = WebApiUtilsRec.sendPost(getUserInfo, map);
        System.out.println("userInfo: " + userInfo);


    }
}
