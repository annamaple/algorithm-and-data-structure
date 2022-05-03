package com.annamaple.test;

import cn.hutool.http.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author xionglei
 * @create 2022-04-28 12:08
 */
public class Poetry {
    
    private String demoUrl = "https://so.gushiwen.cn/mingjus/default.aspx?page=%s&tstr=&astr=&cstr=&xstr=";
    private int pageSize = 1;
    public void get() {
        String html = HttpUtil.get(String.format(demoUrl, pageSize));
        //6.Jsoup解析html
        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByClass("thumb-general");
        for (Element element : elements) {
            Element img = element.select("img").get(0);
            String imgUrl = img.attr("src");
            if (StringUtil.isBlank(imgUrl)) {
                imgUrl = img.attr("data-src");
            }
        }
    }

    public static void main(String[] args) {
        new Poetry().get();
    }
}
