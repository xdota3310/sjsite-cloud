package com.lr.search.service.impl;

import com.lr.search.model.Item;
import com.lr.search.service.HTMLService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 请填写类注释
 *
 * @author shijie.xu
 * @since 2020年06月27日
 */
@Service
public class HTMLServiceImpl implements HTMLService {

    @Override
    public List<Item> getGoodsFromJD() {
        String url = "https://search.jd.com/Search?keyword=java&enc=utf-8&wq=java";
        try {
            Document document = Jsoup.parse(new URL(url), 3000);
            Element element = document.getElementById("J_goodsList");
            Elements elements = element.getElementsByTag("li");
            List<Item> itemList = new ArrayList<>();
            elements.forEach(e -> {
                Item item = new Item();
                item.setImg(e.getElementsByTag("img").eq(0).attr("src"))
                    .setPrice(e.getElementsByClass("p-price").eq(0).text())
                    .setTitle(e.getElementsByClass("p-name").eq(0).text());

                itemList.add(item);


            });
            return itemList;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        new HTMLServiceImpl().getGoodsFromJD();
    }

    @Override
    public void getGoodsFromTB() {

    }
}
