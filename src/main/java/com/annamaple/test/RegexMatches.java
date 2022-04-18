package com.annamaple.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches {
    
    public static void main(String args[]) {
        String str = "10.0.0.123";
        String pattern = "((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());
    }

}