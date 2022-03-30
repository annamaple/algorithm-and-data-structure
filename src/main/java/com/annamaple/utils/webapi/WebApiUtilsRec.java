package com.annamaple.utils.webapi;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * 发送webApi请求工具类
 *
 * @author xionglei
 */
public class WebApiUtilsRec {

    /**
     * 发送Get请求
     *
     * @param urlStr     请求url的字符串
     * @param parameters 请求的参数
     * @return 返回的结果
     */
    public static String sendGet(String urlStr, Map<String, String> parameters) {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;// 响应输入流
        String params = getParams(parameters);// 编码之后的参数
        try {
            String fullUrl = urlStr + "?" + params;
            // 输出GET请求完整的URL
            // System.out.println(fullUrl);
            // 创建url对象
            URL url = new URL(fullUrl);
            // 打开url连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置通用属性
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            // 建立实际连接
            connection.connect();
            // 响应头部获取
            Map<String, List<String>> headers = connection.getHeaderFields();
            // 遍历并打印所有的响应头字段
            // for (String key : headers.keySet()) {
            //     System.out.println(key + "\t：\t" + headers.get(key));
            // }
            // 定义BufferedReader输入流来读取URL的响应,并设置编码方式
            in = new BufferedReader(new InputStreamReader(connection
                    .getInputStream(), StandardCharsets.UTF_8));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result.toString();
    }

    /**
     * 发送POST请求
     *
     * @param url        目的地址
     * @param parameters 请求参数，Map类型。
     * @return 远程响应结果
     */
    public static String sendPost(String url, Map<String, String> parameters) {
        StringBuilder result = new StringBuilder();// 返回的结果
        BufferedReader in = null;// 读取响应输入流
        PrintWriter out = null;
        String params = getParams(parameters);// 编码之后的参数
        try {
            // 创建URL对象
            URL connURL = new URL(url);
            // 打开URL连接
            HttpURLConnection httpConn = (HttpURLConnection) connURL
                    .openConnection();
            // 设置通用属性
            httpConn.setRequestProperty("Accept", "*/*");
            httpConn.setRequestProperty("Connection", "Keep-Alive");
            httpConn.setRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
            // 设置请求参数为JSON格式
            // httpConn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            // 设置POST方式
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            // 获取HttpURLConnection对象对应的输出流
            out = new PrintWriter(httpConn.getOutputStream());
            // 发送请求参数
            out.write(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应，设置编码方式
            in = new BufferedReader(new InputStreamReader(httpConn
                    .getInputStream(), StandardCharsets.UTF_8));
            String line;
            // 读取返回的内容
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();
    }


    /**
     * 返回编码之后的参数
     *
     * @param map 参数
     * @return 编码之后的参数
     */
    public static String getParams(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();// 处理请求参数
        String params = "";
        // 编码请求参数
        try {
            if (map.size() == 1) {
                for (String name : map.keySet()) {
                    sb.append(name).append("=").append(
                            java.net.URLEncoder.encode(map.get(name),
                                    "UTF-8"));
                }
                params = sb.toString();
            } else if (map.size() > 1) {
                for (String name : map.keySet()) {
                    sb.append(name).append("=").append(
                            java.net.URLEncoder.encode(map.get(name),
                                    "UTF-8")).append("&");
                }
                String temp_params = sb.toString();
                params = temp_params.substring(0, temp_params.length() - 1);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return params;
    }
}
