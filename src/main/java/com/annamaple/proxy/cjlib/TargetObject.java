package com.annamaple.proxy.cjlib;

import com.annamaple.proxy.annatation.Permission;

/**
 * 需要代理的类，且此类没有实现任何接口
 *
 * @author xionglei
 * @create 2022-04-19 11:30
 */
public class TargetObject {

    @Permission
    public String method1(String paramName) {
        return paramName;
    }
    
    public int method2(int count) {
        return count;
    }

    public int method3(int count) {
        return count;
    }

    @Override
    public String toString() {
        return "TargetObject []" + getClass();
    }
}
