package com.annamaple.json;

import org.json.JSONObject;

/**
 * @author xionglei
 * @create 2022-05-26 18:10
 */
public class OrgJson {

    public static void main(String[] args) {
        BiUser user = new BiUser();
        user.setOrgName("ming");
        user.setUserTitle("绫濑遥");
        user.setOrgTitle("海街日记");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", new JSONObject(user));
        System.out.println(jsonObject);
    }
}
