package com.annamaple.december.day1213;

/**
 * 兴业数金2021校招题目:
 * 输入一段字符串, 输出二进制表达与16进制表达,中间用","分隔。输入数字最大不能超过两个字节表示的二进制数。
 * 数字超限返回"NUM_ERROR", 输入的不是数字返回"INPUT_ERROR"
 * <p>
 * 提示:
 * 输入: 16
 * 输出: 0000000000010000,0010
 */
public class Day1213 {


    public static void main(String[] args) {
        String number = "-1";
        System.out.println(new Solution().getBinaryAndHexadecimal(number));
    }

}
