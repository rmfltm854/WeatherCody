package com.example.weathercody.DTO;

import com.example.weathercody.JPA.ProductEntity;
import com.example.weathercody.JPA.UserJPA;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private int idx;


    private String image;


    private String name;


    private int likeCount;

    private String ProductImageURL;

    public static ProductDTO toDTO(ProductEntity entity) {
        return ProductDTO.builder()
                .image(entity.getImage())
                .name(entity.getName())
                .likeCount(entity.getLikeCount())
                .ProductImageURL(entity.getProductImageURL())
                .build();
    }
}
