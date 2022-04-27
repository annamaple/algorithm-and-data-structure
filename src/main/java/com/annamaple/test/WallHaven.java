package com.annamaple.test;

import cn.hutool.http.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class WallHaven {

    private int pageSize = 1;
    private String urlTopListPrefix = "https://wallhaven.cc/search?categories=110&purity=100&resolutions=1920x1080&ratios=16x9&topRange=1M&sorting=toplist&order=desc&page=";

    private List<String> imgUrlList = new LinkedList<>();

    public void getPic() {
        String html = HttpUtil.get(urlTopListPrefix + pageSize);
        //6.Jsoup解析html
        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByClass("thumb-general");
        for (Element element : elements) {
            Element img = element.select("img").get(0);
            String imgUrl = img.attr("data-src");
            addImgUrl(imgUrl);
        }
    }


    /**
     * 添加imgUrl到imgUrlList
     *
     * @param imgUrl url
     */
    private void addImgUrl(String imgUrl) {
        // https://th.wallhaven.cc/small/x8/x8ye3z.jpg
        String temp = imgUrl.replace("th", "w").replace("small", "full");
        String url = temp.substring(0, temp.lastIndexOf("/") + 1) + "wallhaven-" + temp.substring(temp.lastIndexOf("/") + 1);
        imgUrlList.add(url);
    }


    public void writeToJs() throws IOException {
        String path = "D:\\workspace\\algorithm-and-data-structure\\src\\main\\java\\com\\annamaple\\test\\pic.js";
        File file = new File(path);
        if (file.exists()) {
            if (file.createNewFile()) {
                throw new IOException("创建文件失败啊，请检查权限");
            }
        }
        try (FileOutputStream outputStream = new FileOutputStream(path, false);
             OutputStreamWriter writer = new OutputStreamWriter(outputStream);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write("const imageUrlArr = [");
            bufferedWriter.newLine();
            for (String imgUrl : imgUrlList) {
                bufferedWriter.write(String.format("'%s',", imgUrl));
                bufferedWriter.newLine();
            }
            bufferedWriter.write("];");
        }
    }

    public static void main(String[] args) throws IOException {
        WallHaven wallHaven = new WallHaven();
        for (int i = 0; i < 10; i++) {
            wallHaven.pageSize = i;
            wallHaven.getPic();
        }
        wallHaven.writeToJs();
    }


}
