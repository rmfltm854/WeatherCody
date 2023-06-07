package com.example.weathercody.Repository;

import com.example.weathercody.JPA.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<ImageEntity,Long> {

    @Query(value = "select *, Rank() over(order by idx desc)as ranking from imageurl limit 6",nativeQuery = true)
    List<ImageEntity> getRanking();

    @Query(value = "select * from imageurl limit ?,12",nativeQuery = true)
    List<ImageEntity> ProductRender(int pageNo);

}
