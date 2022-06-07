package com.annamaple.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.EvalMode;
import com.googlecode.aviator.Options;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 4.0 之前， aviator 的求值器是一个全局共享的对象 AviatorEvaluator，使用起来很方便，
 * <p>
 * 但是无法在同一个进程内提供不同配置选项或者自定义函数的求值器，
 * <p>
 * 因此，从 4.0 开始，aviator 引入了 AviatorEvaluatorInstance，
 * <p>
 * 表示一个单独的求值器实例，原有的 AviatorEvaluator 只是默认提供的一个全局单例。
 * <p>
 * 链接：https://juejin.cn/post/7002235897873694757
 *
 * @author xionglei
 * @create 2022-06-07 14:00
 */
public class AviatorInstanceTest {

    public static void main(String[] args) {
        AviatorEvaluatorInstance instance = AviatorEvaluator.newInstance();
        // 设置打印计算过程
        instance.setOption(Options.TRACE_EVAL, true);
        // 手动设置在计算时把浮点类型转为BigDecimal类型计算
        // instance.setOption(Options.ALWAYS_PARSE_FLOATING_POINT_NUMBER_INTO_DECIMAL, true);
        // 手动设置在计算时把整形型转为BigDecimal类型计算
         instance.setOption(Options.ALWAYS_PARSE_INTEGRAL_NUMBER_INTO_DECIMAL, true);
        // 12M 会被解析成BigDecimal类型
        System.out.println(instance.execute("12 + 12M"));
        // 多种类型参与的运算，按照下列顺序： long -> bigint -> decimal -> double  自动提升
        System.out.println(instance.execute("12.0 + 12M"));
        System.out.println(instance.execute("'1 + 2 =#{1 + 2}'"));
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("x1fdsfsdfsdfsdfsfdds", new BigDecimal("10.12"));
        paramMap.put("x2fdsfsdfsdfsdfsw22", 20);
        instance.execute("x1fdsfsdfsdfsdfsfdds + x2fdsfsdfsdfsdfsw22", paramMap);
    }
}
