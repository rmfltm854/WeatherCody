package com.example.weathercody;

import com.example.weathercody.JPA.ImageEntity;
import com.example.weathercody.Repository.ImageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.stream.IntStream;

@SpringBootTest
class WeatherCodyApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    ImageRepository repo;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            ImageEntity jpa = ImageEntity.builder()
                    .name("남자옷")
                    .URL("/Users/jominsu/Desktop/ManCody(Munsinsa)/MusinSaM1_1.jpg")
                    .Price(15000)
                    .build();

            repo.save(jpa);
        });
    }


}
