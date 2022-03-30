package com.annamaple.utils.test;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xionglei
 * @create 2022-03-07 10:47
 */
public class Test20220307 {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = ", fd, ";
        String[] strArr = str.split(",");
        System.out.println(strArr.length + "----" + Arrays.toString(strArr));
        List<String> stringList = new LinkedList<>();
        Arrays.stream(strArr).map(String::trim).filter(s -> !s.isEmpty()).forEach(stringList::add);
        System.out.println("stringList: " + stringList);
        System.out.println(System.getProperty("java.io.tmpdir") + File.separator);

        System.out.println("=====");
        System.out.println(URLDecoder.decode("%cd%f5%b7%f2", "GBK"));
        System.out.println(new String(URLDecoder.decode("%cd%f5%b7%f2", "GBK").getBytes("UTF-8"), "UTF-8"));
    }
}
