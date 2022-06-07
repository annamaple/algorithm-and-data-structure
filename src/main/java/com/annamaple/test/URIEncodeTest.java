package com.annamaple.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author xionglei
 * @create 2022-05-30 11:28
 */
public class URIEncodeTest {

    public static void main(String[] args) {
        try {
            String str = URLDecoder.decode("%E3%80%90%E8%87%AA%E7%84%B6%E8%B5%84%E6%BA%90%E9%83%A8%E8%B4%A2%E5%8A%A1%E5%AE%A1%E8%AE%A1%E7%9B%91%E7%9D%A3%E7%AE%A1%E7%90%86%E5%B9%B3%E5%8F%B0%E3%80%91%0A%E6%82%A8%E4%BA%8E2022-05-30+11%3A13%3A15%E6%94%B6%E5%88%B0%E9%80%9A%E7%9F%A5%E5%85%AC%E5%91%8A%E3%80%8A%E6%B5%8B%E8%AF%95%E7%9F%AD%E4%BF%A1%E9%80%9A%E7%9F%A5%E3%80%8B%EF%BC%8C%E8%AF%B7%E7%99%BB%E5%BD%95%E7%9B%91%E7%AE%A1%E5%B9%B3%E5%8F%B0%E6%9F%A5%E7%9C%8B"
                    , "UTF-8");
            System.out.println(str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
