package com.annamaple.aviator;

import cn.hutool.core.lang.Console;
import com.googlecode.aviator.AviatorEvaluator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xionglei
 * @create 2022-06-14 14:26
 */
public class TestExpression {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("资产到期", 10);
        map.put("资产到期标准一", 10);
        Object res = AviatorEvaluator.compile("资产到期*资产到期标准一").execute(map);
        Console.log("res: {}", res);
    }
}
