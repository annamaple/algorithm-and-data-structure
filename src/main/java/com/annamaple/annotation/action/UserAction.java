package com.annamaple.annotation.action;

/**
 * @author xionglei
 * @create 2022-04-26 11:23
 */

@Action("/")
public class UserAction {
    
    public String getInfo() {
        return "userInfo";
    }
}
