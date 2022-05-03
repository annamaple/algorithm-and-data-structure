package com.annamaple.test;

import cn.hutool.core.io.StreamProgress;
import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpDownloader;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class DownloadPicFactory {

    /**
     * 获取wallHaven图片下载器
     *
     * @param savePath  下载完成的图片啊保存路径文件夹
     * @param picJsPath 存放wallHaven图片url的js文件路径
     */
    public static Downloader getDownloader(String savePath, String picJsPath) {
        if (savePath == null || picJsPath == null) {
            throw new IllegalArgumentException("path is null");
        }
        List<String> urlList = new LinkedList<>();
        try (FileInputStream inputStream = new FileInputStream(picJsPath);
             Reader reader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String url;
            while ((url = bufferedReader.readLine()) != null) {
                if (url.startsWith("'https")) {
                    urlList.add(url.substring(1, url.lastIndexOf("'")));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Downloader(savePath, urlList);
    }

    public static class Downloader {
        private Executor executor = Executors.newFixedThreadPool(10);

        public Downloader(String savePath, List<String> imgUrlList) {
            File file = new File(savePath);
            if (!file.exists()) {
                if (!file.mkdirs()) {
                    throw new IllegalArgumentException("create file failed, please check access");
                }
            }
            this.savePath = savePath;
            this.imgUrlList = imgUrlList;
        }

        // 存放图片的目录
        private String savePath;
        private List<String> imgUrlList;

        public void download() throws FileNotFoundException {
            Console.log("一共有{}图片", imgUrlList.size());
            if (savePath == null || savePath.trim().length() == 0) {
                throw new IllegalArgumentException("save picture path is null");
            }
            int i = 1;
            for (String url : imgUrlList) {
                int suffix = i;
                executor.execute(() -> {
                    if (url != null && url.trim().length() > 0) {
                        try {
                            // 记住文件名生成规则
                            File file = createPicFile(String.format("%04d-wallhaven%s", suffix, url.substring(url.lastIndexOf("."))));
                            try {
                                FileOutputStream outputStream = new FileOutputStream(file);
                                HttpDownloader.download(url, outputStream, true, new PicStreamProgress(suffix));
                                // 等待5s
                                try {
                                    TimeUnit.SECONDS.sleep(1);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        } catch (Exception e) {
                            Console.log(e.getMessage());
                        }
                    }
                });
                i++;
            }
        }


        /**
         * 创建文件
         *
         * @param fileName 文件名带文件后缀
         * @return 文件File
         */
        public File createPicFile(String fileName) throws Exception {
            File file = new File(savePath + File.separator + fileName);
            try {
                if (!file.exists()) {
                    if (!file.createNewFile()) {
                        throw new IllegalArgumentException("create new file failed, please check access");
                    }
                } else {
                    throw new Exception("该图片已下载");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return file;
        }
    }


    private static class PicStreamProgress implements StreamProgress {

        public PicStreamProgress(int suffix) {
            this.suffix = suffix;
        }

        private int suffix;

        @Override
        public void start() {
            Console.log("第{}张图片正在开始下载", suffix);
        }

        @Override
        public void progress(long progressSize) {

        }

        @Override
        public void finish() {
            Console.log("第{}张图片正在下载成功", suffix);
        }
    }
}
