package com.annamaple.proxy.annatation;

/**
 * @author xionglei
 * @create 2022-04-19 10:15
 */
public class Test {
    
    // 一般是spring注入
    private static BusinessService service = new BusinessServiceImpl();

    public static void main(String[] args) {
        
        User user1 = new User(1);
        User user10 = new User(10);
        User user11 = new User(11);
        ServiceFactory<BusinessService> factory = new ServiceFactory<>(service);
        BusinessService service = factory.createProxy();
        // 默认operator user id = 1 可以操作
        service.method01();
        // method2 没加无影响
        service.method02();
        // 改为11 权限判断无权限
        Operator.setUser(new User(11));
        service.method01();
        service.method02();
    }
}
