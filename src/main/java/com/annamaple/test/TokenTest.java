package com.annamaple.test;

import cn.hutool.core.lang.Console;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.Date;
import java.util.Scanner;

/**
 * @author xionglei
 * @create 2022-04-18 11:38
 */
public class TokenTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        Scanner scanner = new Scanner(System.in);
        String ip = scanner.next();
        while (!ip.equals("0")) {
            char a = 'a';
            char A = 'A';
            String base = new String(Base64.getEncoder().encode(ip.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
            Console.log("ip: {}, base64: {}", ip, base);
            String base2 = new String(Base64.getEncoder().encode(base.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
            Console.log("src: {}, base64: {}", base, base2);
            LocalDateTime time = LocalDateTime.now();
            Date date = Date.from(time.toInstant(ZoneOffset.ofHours(8)));
            int mod = time.getSecond() % 26;
            int cc = (time.getSecond() & 1) == 1 ? A : a;
            char c = (char) (cc + mod);
            Console.log("time.getSecond(): {}, src: {}, replace: {}", time.getSecond(), base2, 
                    base2.replaceAll("=", "") + mod + c);
            Console.log();
            ip = scanner.next();
        }
        scanner.close();
    }
}
