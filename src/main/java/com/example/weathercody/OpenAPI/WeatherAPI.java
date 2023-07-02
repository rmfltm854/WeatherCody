package com.example.weathercody.OpenAPI;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class WeatherAPI {
    public String returnApi(){
        String resultStr = "";
        try{
            HashMap<String, Object> result = new HashMap<String, Object>();
            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
            factory.setConnectTimeout(5000); //타임아웃 설정 5초
            RestTemplate restTemplate = new RestTemplate(factory);
            HttpHeaders header = new HttpHeaders();
            HttpEntity<?> entity = new HttpEntity<>(header);
            String url = "http://api.openweathermap.org/data/2.5/weather?id=1842943&appid=af9aa407e73b6f45d5e9093b00631f32";
            UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();
            //이 한줄의 코드로 API를 호출해 MAP타입으로 전달 받는다.
            ResponseEntity<String> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, String.class);
            JSONObject object = new JSONObject(resultMap.getBody());
            JSONArray array = (JSONArray) object.get("weather");
            JSONObject object2 = (JSONObject) array.get(0);
            resultStr = object2.get("main").toString();
            return resultStr;
        }catch (Exception e){
            e.printStackTrace();
            return "Exception!!(WeatherAPI)";
        }
    }
}
