package com.example.weathercody.Repository;

import com.example.weathercody.JPA.ManBoardEntity;
import com.example.weathercody.JPA.WomanBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WomanBoardRepository extends JpaRepository<WomanBoardEntity,Long> {

    @Query(value = "select * from man_board where product_idx=?;",nativeQuery = true)
    List<WomanBoardEntity> GetReview(int product_idx);
}
