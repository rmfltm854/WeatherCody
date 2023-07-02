package com.example.weathercody.Repository;

import com.example.weathercody.JPA.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductsRepository extends JpaRepository<ProductsEntity,Long> {
    List<ProductsEntity> findAllByProductIdx(int idx);
}
