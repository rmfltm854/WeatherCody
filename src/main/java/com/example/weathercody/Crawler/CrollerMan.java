package com.example.weathercody.Crawler;

import java.io.FileOutputStream;
import java.net.URL;

import com.example.weathercody.JPA.ImageEntity;
import com.example.weathercody.Repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
@RequiredArgsConstructor
public class CrollerMan {
    public List<String> setData() {
        int pageNo = 1;
        List<String> list = new ArrayList<>();
        //String HowPage = "https://www.musinsa.com/search/musinsa/coordi?q=%EB%82%A8%EC%9E%90+%EB%B4%84+%EC%BD%94%EB%94%94&list_kind=small&sortCode=term_date&sub_sort=&page=1&display_cnt=0&saleGoods=&includeSoldOut=&setupGoods=&popular=&category1DepthCode=&category2DepthCodes=&category3DepthCodes=&selectedFilters=&category1DepthName=&category2DepthName=&brandIds=&price=&colorCodes=&contentType=&styleTypes=&includeKeywords=&excludeKeywords=&originalYn=N&tags=&campaignId=&serviceType=&eventType=&type=&season=&measure=&openFilterLayout=N&selectedOrderMeasure=&shoeSizeOption=&groupSale=&d_cat_cd=&attribute=";
        String HowPage = "https://www.musinsa.com/search/musinsa/coordi?q=%EB%82%A8%EC%9E%90%EC%BD%94%EB%94%94&list_kind=small&sortCode=term_date&sub_sort=&page=1&display_cnt=0&saleGoods=&includeSoldOut=&setupGoods=&popular=&category1DepthCode=&category2DepthCodes=&category3DepthCodes=&selectedFilters=&category1DepthName=&category2DepthName=&brandIds=&price=&colorCodes=&contentType=&styleTypes=&includeKeywords=&excludeKeywords=&originalYn=N&tags=&campaignId=&serviceType=&eventType=&type=&season=&measure=&openFilterLayout=N&selectedOrderMeasure=&shoeSizeOption=&groupSale=&d_cat_cd=&attribute=&plusDeliveryYn=";
        try {
            Document page = Jsoup.connect(HowPage).get();
            Elements totalNum = page.select(".totalPagingNum");
            pageNo = Integer.parseInt(totalNum.get(0).text());
            System.out.println(pageNo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 1; i <= 2; i++) {
            String wtUrl = "https://www.musinsa.com/search/musinsa/coordi?q=%EB%82%A8%EC%9E%90%EC%BD%94%EB%94%94&list_kind=small&sortCode=term_date&sub_sort=&page=" + i + "&display_cnt=0&saleGoods=&includeSoldOut=&setupGoods=&popular=&category1DepthCode=&category2DepthCodes=&category3DepthCodes=&selectedFilters=&category1DepthName=&category2DepthName=&brandIds=&price=&colorCodes=&contentType=&styleTypes=&includeKeywords=&excludeKeywords=&originalYn=N&tags=&campaignId=&serviceType=&eventType=&type=&season=&measure=&openFilterLayout=N&selectedOrderMeasure=&shoeSizeOption=&groupSale=&d_cat_cd=&attribute=&plusDeliveryYn=";
            try {
                System.out.println(wtUrl);
                Document Musinsa = Jsoup.connect(wtUrl).get();

                Elements imgUrl = Musinsa.select(".style-list-thumbnail > img");

                System.out.println(i);

                for (int j = 0; j < imgUrl.size(); j++) {
                    System.out.println(imgUrl.get(j).attr("data-original"));
                    String src = imgUrl.get(j).attr("data-original");

                    URL url = new URL(src);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setRequestProperty("Referer", src);
                    BufferedImage img = ImageIO.read(conn.getInputStream());

                    FileOutputStream out = new FileOutputStream("/Users/jominsu/spring-framework-learning/Topic 4 Thymeleaf/WeatherCody/src/main/resources/static/CodyImage/ManCody(Musinsa)/MusinSaM" + i + "_" + (j + 1) + ".jpg");
                    ImageIO.write(img, "jpg", out);


                    list.add("/CodyImage/ManCody(Musinsa)/MusinSaM" + i + "_" + (j + 1) + ".jpg");

                }
                pageNo++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}