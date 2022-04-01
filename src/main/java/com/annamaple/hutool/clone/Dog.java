package com.annamaple.hutool.clone;

import cn.hutool.core.clone.CloneSupport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * 继承泛型克隆类,点到内部不难发现，CloneSupport<T>其实就是实现hutool的Cloneable<T>接口
 * @author xionglei
 * @create 2022-03-23 12:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Dog extends CloneSupport<Dog> {
    private String dogName;
    private int dogAge;
}
