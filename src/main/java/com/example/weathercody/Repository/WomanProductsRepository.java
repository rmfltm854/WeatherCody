package com.example.weathercody.Repository;

import com.example.weathercody.JPA.ProductsEntity;
import com.example.weathercody.JPA.WomanProductEntity;
import com.example.weathercody.JPA.WomanProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WomanProductsRepository extends JpaRepository<WomanProductsEntity,Long> {

    List<WomanProductsEntity> findAllByProductIdx(int idx);
}
