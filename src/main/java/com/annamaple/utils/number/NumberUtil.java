package com.annamaple.utils.number;

/**
 * 数字格式化
 *
 * @author xionglei
 * @create 2022-02-19 14:16
 */
public class NumberUtil {

    /**
     * 数字字符串格式化为小数点后两位
     *
     * @param numStr 数字字符串
     * @return 格式化后的数字字符串
     */
    public static String formatToPercentile(String numStr) {
        if (numStr == null || numStr.equals("")) {
            return "";
        }
        try {
            return String.format("%.2f", Double.parseDouble(numStr));
        } catch (NumberFormatException e) {
            return "";
        }
    }


    public static void main(String[] args) {
        String numStr = "123";
        System.out.println(formatToPercentile(numStr));
    }
}
