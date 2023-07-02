package com.example.weathercody.Repository;

import com.example.weathercody.JPA.ManBoardEntity;
import com.example.weathercody.JPA.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ManBoardRepository extends JpaRepository<ManBoardEntity,Long> {

    @Query(value = "select * from man_board where product_idx=?;",nativeQuery = true)
    List<ManBoardEntity> GetReview(int product_idx);
}
