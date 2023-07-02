package com.example.weathercody.DTO;

import com.example.weathercody.JPA.ProductEntity;
import com.example.weathercody.JPA.ProductsEntity;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductsDTO {
    private int idx;


    private String clothsImage;


    private String clothsLink;

    public static ProductsDTO toDTO(ProductsEntity entity) {
        return ProductsDTO.builder()
                .idx(entity.getIdx())
                .clothsImage(entity.getClothsImage())
                .clothsLink(entity.getClothsLink())
                .build();
    }
}
