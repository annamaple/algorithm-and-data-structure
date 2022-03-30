package com.annamaple.utils.file;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) throws IOException {
//        testListFileZip();
//        testSrcPathZip();
        testSrcPathZip2();

    }

    public static void testListFileZip() throws IOException {
        String outPath = "C:\\Users\\lei\\Desktop\\MultiDir.zip";
        long start = System.currentTimeMillis();
        List<File> fileList = new LinkedList<>();
        String srcPath1 = "C:\\Users\\lei\\Desktop\\TODO";
        String srcPath2 = "C:\\Users\\lei\\Desktop\\bak";
        String srcPath3 = "C:\\Users\\lei\\Desktop\\TODO\\突击班面经";
        fileList.add(new File(srcPath1));
        fileList.add(new File(srcPath2));
        fileList.add(new File(srcPath3));

        FileUtils.toZip(fileList, outPath);
        long end = System.currentTimeMillis();
        System.out.println("压缩完成, 耗时: " + (end - start) + " ms");
    }

    public static void testSrcPathZip() throws IOException {

        String srcPath = "C:\\Users\\lei\\Desktop\\eeee";
        String outPath = "C:\\Users\\lei\\Desktop\\todZipFIle-Todo.zip";
        File file = new File(outPath);
        file.deleteOnExit();

        long start = System.currentTimeMillis();
        FileUtils.toZip(srcPath, outPath);
        long end = System.currentTimeMillis();
        System.out.println("压缩完成, 耗时: " + (end - start) + " ms");

        try {
            TimeUnit.SECONDS.sleep(7);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        file.deleteOnExit();
    }

    public static void testSrcPathZip2() throws IOException {
        String outPath = "C:\\Users\\lei\\Desktop\\todZipFIle-Todo.zip";
        try (OutputStream outputStream = new FileOutputStream(outPath);
             ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
            zipOutputStream.putNextEntry(new ZipEntry("xyz/ll/"));
            zipOutputStream.closeEntry();
            zipOutputStream.putNextEntry(new ZipEntry("xyz/fdfds/"));
            zipOutputStream.closeEntry();
            zipOutputStream.putNextEntry(new ZipEntry("xyz/ll/write.lock"));
            try (FileInputStream inputStream = new FileInputStream("C:\\Users\\lei\\Desktop\\eeee\\index\\FILE_MGRfd\\write.lock");
                 BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {
                int bufferSize = 4096;
                byte[] buffer = new byte[bufferSize];
                int len;
                while ((len = bufferedInputStream.read(buffer)) != -1) {
                    zipOutputStream.write(buffer, 0, len);
                    zipOutputStream.closeEntry();
                }
            }
        }
    }
}
