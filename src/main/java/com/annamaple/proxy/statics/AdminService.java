package com.annamaple.proxy.statics;

/**
 * 静态代理接口-定义提供哪些服务
 *
 * @author xionglei
 * @create 2022-04-14 20:14
 */
public interface AdminService {

    void update(Integer num);

    Object find();
}
