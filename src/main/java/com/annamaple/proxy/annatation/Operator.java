package com.annamaple.proxy.annatation;


/**
 * @author xionglei
 * @create 2022-04-19 10:24
 */

public class Operator {
    
    private static User user = new User(1);
    
    public static User operator() {
        return user;
    }
    
    public static void setUser(User user) {
        Operator.user = user;
    }
}
