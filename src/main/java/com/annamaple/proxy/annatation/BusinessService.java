package com.annamaple.proxy.annatation;

/**
 * 测试用接口
 *
 * @author xionglei
 * @create 2022-04-19 10:02
 */
public interface BusinessService {
    
    @Permission()
    void method01();
    
    void method02();
}
