package com.annamaple.utils.file;

import java.io.*;
import java.util.Objects;

public class ReadFile {

    public static void main(String[] args) {
        String folderPath = "G:";
        String outFileNamePath = "C:\\Users\\lei\\Desktop\\";
        new ReadFile().readFileName(folderPath, outFileNamePath);
    }

    public void readFileName(String folderPath, String outFileNamePath) {
        if (folderPath == null || folderPath.isEmpty() ||
                outFileNamePath == null || outFileNamePath.isEmpty()) {
            throw new IllegalArgumentException("文件夹路径不能为空");
        }
        File folder = new File(folderPath);
        if (!folder.exists() || folder.isFile()) {
            throw new IllegalArgumentException("文件夹不纯在");
        }
        File outNameFile = new File(outFileNamePath);
        if (outNameFile.exists() && outNameFile.isFile()) {
            throw new IllegalArgumentException("输出文件名的路径不能是已经存在的文件");
        }
        if (!outNameFile.exists()) {
            try {
                if (outNameFile.createNewFile()) {
                    System.out.println("创建文件失败，请检查权限");
                }
            } catch (IOException e) {
                System.out.println("创建文件失败，请检查权限");
                e.printStackTrace();
            }
        }
        if (outNameFile.exists() && outNameFile.isDirectory()) {
            int i = 0;
            String tempFolderPath = outNameFile.getAbsolutePath();
            do {
                if (i == 0) {
                    outNameFile = new File(tempFolderPath + "/OUT_FILE_NAME.txt");
                } else {
                    String path = String.format(tempFolderPath + "/OUT_FILE_NAME_%s.txt", i);
                    outNameFile = new File(path);
                }
                ++i;
            } while (outNameFile.exists());

        }
        try (OutputStream outputStream = new FileOutputStream(outNameFile);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
             BufferedWriter writer = new BufferedWriter(outputStreamWriter)) {
            readFileName(folder, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void readFileName(File file, BufferedWriter writer) throws IOException {
        writer.write(file.getAbsolutePath());
        writer.newLine();
        if (file.isDirectory() && file.listFiles() != null && Objects.requireNonNull(file.listFiles()).length > 0) {
            for (File child: Objects.requireNonNull(file.listFiles())) {
                readFileName(child, writer);
            }
        }
    }
}
