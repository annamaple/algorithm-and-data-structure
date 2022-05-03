package com.annamaple.leetcode;

/**
 * 704. 二分查找 https://leetcode-cn.com/problems/binary-search/
 * <br><br>
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 * <br>
 * 示例 1:<br>
 * <br>
 * 输入: nums = [-1,0,3,5,9,12], target = 9<br>
 * 输出: 4<br>
 * 解释: 9 出现在 nums 中并且下标为 4<br>
 * 示例 2:<br>
 * <br>
 * 输入: nums = [-1,0,3,5,9,12], target = 2<br>
 * 输出: -1<br>
 * 解释: 2 不存在 nums 中因此返回 -1<br>
 *  <br>
 * <br>
 * 提示：<br>
 * <br>
 * 你可以假设 nums 中的所有元素是不重复的。<br>
 * n 将在 [1, 10000]之间。<br>
 * nums 的每个元素都将在 [-9999, 9999]之间。<br>
 */
public class LeetCode704 {

    public static void main(String[] args) {

        System.out.println("syntax error, pos 1, json : <!DOCTYPE html>\\n<html>\\n<head>\\n<meta id=\\\"viewport\\\" name=\\\"viewport\\\" content=\\\"width=device-width, initial-scale=1.0, maximum-scale=1.0\\\" />\\n<meta http-equiv=\\\"Content-Type\\\" content=\\\"text/html; charset=utf-8\\\" />\\n</head>\\n<body>\\n<script type=\\\"text/javascript\\\">\\n//获取url参数\\nfunction getUrlParam(name) {\\n    var reg = new RegExp(\\\"(^|&)\\\" + name + \\\"=([^&]*)(&|$)\\\", \\\"i\\\"),\\n        r = decodeURIComponent(window.location.search).substr(1).match(reg);\\n    return r!=null ? decodeURI(r[2]) : null;\\n}\\n\\n(function(){\\n\\tvar ratio = window.devicePixelRatio || 1;\\n\\tvar UA = String(navigator.userAgent).toLowerCase();\\n\\t//alert(navigator.userAgent)\\n\\t//var hasTouch = document.documentElement.ontouchstart !== undefined;\\n\\tvar isMobile = UA.indexOf('mobile') > -1;\\n\\t//var isAndroid = UA.indexOf('android') > -1;\\n\\tvar isIPad = UA.indexOf('ipad') > -1;\\n\\t//var isIPhone = UA.indexOf('iphone') > -1;\\n\\t//此方法误判很严重\\n\\t//var isBigScreen = Math.min(screen.height/ratio,screen.width/ratio) > 600;\\n\\t//针对点一点扫一扫，跳转结果界面\\n\\tvar g_type = getUrlParam(\\\"type\\\");\\n\\tvar g_tips = getUrlParam(\\\"tips\\\");\\n\\tvar tabs = getUrlParam(\\\"tabs\\\");\\n\\tvar sms = getUrlParam(\\\"sms\\\");\\n\\t\\n\\t//检测平台\\n\\tvar p = String(navigator.platform.toLowerCase()),\\n\\t\\tisWin = p.indexOf(\\\"win\\\") == 0,  \\n\\t\\tisMac = p.indexOf(\\\"mac\\\") == 0, \\n\\t\\tisX11 = (p == \\\"x11\\\") || (p.indexOf(\\\"linux\\\") == 0);  \\n\\t\\t\\n\\tvar template = getUrlParam(\\\"template\\\") || \\\"default\\\";\\n\\tvar $href = '';\\n\\t//1、not mobile //pc\\n\\t//2、mobile but ipad\\n\\t//3、big screen //android pad\\n\\t//4、检测平台\\n\\tif((isMobile && !isIPad)||!(isWin||isMac||isX11||isIPad)){\\n\\t\\t$href = \\\"mobile.html\\\";\\n\\t\\tif(tabs == \\\"wx\\\"){\\n\\t\\t\\tif (sms != 1){\\n\\t\\t\\t\\tif(g_type==\\\"logout\\\") {\\n\\t\\t\\t\\t\\tlocation.href = template+\\\"/result.html?type=success\\\";\\n\\t\\t\\t\\t\\treturn;\\n\\t\\t\\t\\t} else if(g_type==\\\"authfailed\\\") {\\n\\t\\t\\t\\t\\tlocation.href = template+\\\"/result.html?type=failure&g_tips=\\\"+g_tips;\\n\\t\\t\\t\\t\\treturn;\\n\\t\\t\\t\\t} else if(g_type==\\\"authalready\\\") {\\n\\t\\t\\t\\t\\tlocation.href = template+\\\"/result.html?type=warn&g_tips=\\\"+g_tips;\\n\\t\\t\\t\\t\\treturn;\\n\\t\\t\\t\\t}\\n\\t\\t\\t}\\n\\t\\t\\telse\\n\\t\\t\\t{\\n\\t\\t\\t\\t$href = \\\"mobile_sms.html\\\";\\n\\t\\t\\t\\tlocation.href = template+\\\"/\\\"+$href+location.search;\\n\\t\\t\\t\\treturn;\\n\\t\\t\\t}\\n\\t\\t}\\t\\t\\n\\t}else{\\n\\t\\t$href = \\\"pc.html\\\";\\n\\t}\\t\\n\\tlocation.href = template+\\\"/\\\"+$href+location.search;\\n})();\\n</script>\\n</body>\\n</html>\\n\\n");
    }

    private class Solution {
        public int search(int[] nums, int target) {
            if (nums == null) {
                return -1;
            }
            int left = 0;
            int right = nums.length - 1;
            int mid;
            while (left <= right) {
                mid = left + (right - left) >> 1;
                if (target < nums[mid]) {
                    right = mid - 1;
                    left++;
                } else if (target > nums[mid]) {
                    left = right + 1;
                    right--;
                } else {
                    return mid;
                }
            }
            return -1;
        }
    }


}
