package com.annamaple.test;

import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class WallHaven {

    private int pageSize = 1;
//    private String urlTopListPrefix = "https://wallhaven.cc/search?categories=110&purity=100&resolutions=1920x1080&ratios=16x9&topRange=1M&sorting=toplist&order=desc&page=";
    private String urlTopListPrefix = "https://wallhaven.cc/search?q=id%3A37&categories=110&purity=100&atleast=1920x1080&sorting=toplist&order=desc&page=";

    private List<String> imgUrlList = new LinkedList<>();

    public void getPic() {
        String html = HttpUtil.get(urlTopListPrefix + pageSize);
        //6.Jsoup解析html
        Document document = Jsoup.parse(html);
        Element thumbs = document.getElementById("thumbs");
        Element ul = thumbs.selectFirst("ul");
        Elements liArr = ul.children();
        for (Element li : liArr) {
            Element img = li.select("img").get(0);
            String imgUrl = img.attr("src");
            if (StringUtil.isBlank(imgUrl)) {
                imgUrl = img.attr("data-src");
            }
            String picSux = "jpg";
            Elements pngs = li.getElementsByClass("png");
            if (pngs.size() > 0) {
                Element png =  pngs.get(0);
                picSux = png.child(0).text().toLowerCase(Locale.ROOT);
            }
            addImgUrl(imgUrl, picSux);
        }
    }


    /**
     * 添加imgUrl到imgUrlList
     *
     * @param imgUrl url
     */
    private void addImgUrl(String imgUrl, String style) {
        // https://th.wallhaven.cc/small/x8/x8ye3z.jpg
        String temp = imgUrl.replace("th", "w").replace("small", "full");
        String url = temp.substring(0, temp.lastIndexOf("/") + 1) + "wallhaven-" + temp.substring(temp.lastIndexOf("/") + 1);
        url = url.substring(0, url.lastIndexOf(".") + 1) + style;
        imgUrlList.add(url);
    }


    public void writeToJs(String fileName) throws IOException {
//        String fileName = "pic.js";
        File file = TestFileUtil.createNewFile(fileName);
        if (file.exists()) {
            if (file.createNewFile()) {
                throw new IOException("创建文件失败啊，请检查权限");
            }
        }
        try (FileOutputStream outputStream = new FileOutputStream(file, false);
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
        Console.log("writeToJs success path: [{}]", file.getAbsolutePath());
    }

    public static void main(String[] args) throws IOException {
        WallHaven wallHaven = new WallHaven();
//        for (int i = 1; i <= 10; i++) {
//            wallHaven.pageSize = i;
//            Console.log("pageSize = {}", i);
//            wallHaven.getPic();
//        }
//        wallHaven.writeToJs();
//        wallHaven.download();
        wallHaven.imgUrlList = DownloadPicFactory.getImgName("D:\\workspace\\algorithm-and-data-structure\\out\\production\\classes\\com\\annamaple\\test\\pic.js");
        wallHaven.writeToJs("picFileName.js");
    }

    public void download() throws FileNotFoundException {
        String savePath = "C:\\Users\\lei\\Pictures\\wallhaven\\blog\\";
        String picJsPath = "D:\\workspace\\algorithm-and-data-structure\\out\\production\\classes\\com\\annamaple\\test\\pic.js";
        DownloadPicFactory.Downloader downloader = DownloadPicFactory.getDownloader(savePath, picJsPath);
        downloader.download();
    }


}
