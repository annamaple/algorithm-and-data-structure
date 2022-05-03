package com.annamaple.test;

import cn.hutool.core.lang.Console;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author xionglei
 * @create 2022-04-27 12:03
 */
public class CodeTest {

    static CountDownLatch countDownLatch = new CountDownLatch(2);
    
    @SneakyThrows
    public static void main(String[] args) {
        JSONArray jsonArray = readFile("index_2020__level_5.json");
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            initMap(jsonArray.getJSONObject(i), map);    
        }
        // writeFile(map, "inputAll.json");
        Console.log("init success");
        AnnaTaskFileMergeEntityServiceImpl annaTaskFileMergeEntityService = new AnnaTaskFileMergeEntityServiceImpl();
        long start = System.nanoTime();
        Map<String, List<String>> mergeMap = annaTaskFileMergeEntityService.getMergeEntityList("", "", "", map);
        long end = System.nanoTime();
        Console.log("调用getMergeEntityList方法完毕，用时【{}】ms", (end - start) / 1_000_000.0);
        // writeFile(mergeMap, "outAll.json");
        // countDownLatch.await();
        Console.log("程序执行结束");
    }

    
    private static JSONArray readFile(String fileName) {
        try (InputStream inputStream = CodeTest.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                Console.log("inputStream = null");
                return null;
            }
            try (Reader reader = new InputStreamReader(inputStream);
                 BufferedReader bufferedReader = new BufferedReader(reader)) {
                StringBuilder sb = new StringBuilder();
                String s;
                while ((s = bufferedReader.readLine()) != null) {
                    sb.append(s);
                }
                return new JSONArray(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 合并结果写入文件
     *
     * @param mergeMap 合并结果map
     */
    private static void writeFile(Map<String, ?> mergeMap, String fileName) {
        new Thread(() -> {
            Console.log("{},开始生成{}文件", Thread.currentThread().getName(), fileName);
            File file = TestFileUtil.createNewFile(fileName);
            try (FileOutputStream outputStream = new FileOutputStream(file);
                 Writer writer = new OutputStreamWriter(outputStream);
                 BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                bufferedWriter.write(new JSONObject(mergeMap).toString());
                countDownLatch.countDown();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, "writeFile线程").start();
    }


    private static void initMap(JSONObject parent, Map<String, String> map) {
        if (parent == null) {
            return;
        }
        try {
            JSONArray jsonArray = parent.getJSONArray("children");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject child = jsonArray.getJSONObject(i);
                map.put(child.getString("value"), parent.getString("value"));
                initMap(child, map);
            }
        } catch (JSONException e) {
        }
    }
}
