package com.annamaple.aviator;

import cn.hutool.core.lang.Console;
import com.googlecode.aviator.AviatorEvaluator;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xionglei
 * @create 2022-05-25 11:14
 */
public class Test {

    @SneakyThrows
    public static void main(String[] args) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("学生数量", 20);
        dataMap.put("每生每月补贴", 10);
        dataMap.put("一共补贴多少月", 11);
        dataMap.put("额外总补助", 10000);
        dataMap.put("预科生数量", 10);
        dataMap.put("extra1", 20);
        dataMap.put("et", 0);
        dataMap.put("x", 1);
        dataMap.put("y", 2);
        dataMap.put("z", 3);

        // 计算：dataMap从数据库中查询而来, 即Record中的字段名; 因此公式中的变量也应该为Record中的字段名;不能为中文描述;
        // 因此; 公式存储有两个(一个为中文表意存储; 一个为逻辑存储)
        // 解析到列表时：需要解析出公式中对应的列出来;通过逻辑公式字符串, 解析到所使用的指标(相同项目不同学校不一样)和标准(相同项目不同学校一样);
        // 10000 + (20 + 10) * 10 * 11 =
        Long res = (Long) AviatorEvaluator.compile("T=额外总补助+(学生数量+预科生数量)*每生每月补贴*一共补贴多少月").execute(dataMap);
        Console.log("res: {}", res);

        // (1 + 2) * 1 + (1 + 3) * 2 = 3 + 8 = 11
        String expression = "(x + y) * x + (x + z) * y + 3.9";
        Console.log("{} = {}", expression, AviatorEvaluator.execute(expression, dataMap));
    }
}
