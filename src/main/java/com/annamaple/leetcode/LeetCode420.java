package com.annamaple.leetcode;

/**
 * 强密码检验器
 * <br>
 * 如果一个密码满足下述所有条件，则认为这个密码是强密码：<br>
 * 由至少 6 个，至多 20 个字符组成。<br>
 * 至少包含 一个小写 字母，一个大写 字母，和 一个数字 。<br>
 * 同一字符 不能 连续出现三次 (比如 "...aaa..." 是不允许的, 但是"...aa...a..." 如果满足其他条件也可以算是强密码)。<br>
 * 给你一个字符串 password ，返回将 password 修改到满足强密码条件需要的最少修改步数。<br>
 * 如果 password 已经是强密码，则返回 0 。
 * <br>
 * 在一步修改操作中，你可以：
 * <br>
 * 插入一个字符到 password ，从 password 中删除一个字符，或用另一个字符来替换 password 中的某个字符。
 * <br>
 * <br>
 * 示例 1：<br>
 * 输入：password = "a"<br>
 * 输出：5<br>
 * 示例 2：<br>
 * 输入：password = "aA1"<br>
 * 输出：3<br>
 * 示例 3：<br>
 * 输入：password = "1337C0d3"<br>
 * 输出：0<br>
 * <br>
 * 提示：<br>
 * 1 <= password.length <= 50<br>
 * password 由字母、数字、点 '.' 或者感叹号 '!'<br>
 * 
 * @author xionglei
 * @link https://leetcode-cn.com/problems/strong-password-checker
 * @create 2022-04-02 11:22
 */
public class LeetCode420 {

    private class Solution {
        public int strongPasswordChecker(String password) {
            return 0;
        }
    }
}
