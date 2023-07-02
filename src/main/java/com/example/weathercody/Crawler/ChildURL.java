package com.example.weathercody.Crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChildURL {

    public List<HashMap<String,String>> getProductDetail(List<String> url) throws IOException {
        HashMap<String,String> map = new HashMap<String,String>();
        List<HashMap<String,String>> relist = new ArrayList<HashMap<String,String>>();
        for(int i = 0;i<url.size();i++){
            String childURL = url.get(i);
            Document Musinsa = Jsoup.connect(childURL).get();
            Elements productDetail = Musinsa.select(".styling_list > .swiper-slide > .box-img > .styling_img > img");
            Elements productDetailURL = Musinsa.select(".styling_list > .swiper-slide > .box-img > .styling_img");
            for(int j = 0;j<productDetail.size();j++){
                map.put("detailImage",productDetail.get(j).attr("src"));
                map.put("detailLink",productDetailURL.get(j).attr("href"));
                relist.add(map);
            }
            map = new HashMap<String,String>();
        }
        return relist;
    }

    public List<HashMap<String,String>> getProductDetail2(String url) throws IOException {
        HashMap<String,String> map = new HashMap<String,String>();
        List<HashMap<String,String>> relist = new ArrayList<HashMap<String,String>>();

        String childURL = url;
        Document Musinsa = Jsoup.connect(childURL).get();
        Elements productDetail = Musinsa.select(".styling_list > .swiper-slide > .box-img > .styling_img > img");
        Elements productDetailURL = Musinsa.select(".styling_list > .swiper-slide > .box-img > .styling_img");
        for(int j = 0;j<productDetail.size();j++){
            map.put("detailImage",productDetail.get(j).attr("src"));
            map.put("detailLink",productDetailURL.get(j).attr("href"));
            relist.add(map);
            map = new HashMap<String,String>();
        }
            System.out.println("크롤러에서 갯수" + relist.size());
            return relist;
        }

}
