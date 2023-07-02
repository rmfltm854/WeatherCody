package com.example.weathercody.JPA;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Product")
@Table(name = "Product")

public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @Column(name ="image",nullable = false)
    private String image;

    @Column(name ="name",nullable = false)
    private String name;

    @Column(name ="likeCount",nullable = false)
    private int likeCount;

    @Column(name ="ProductImageURL",nullable = false)
    private String ProductImageURL;

    @Column(name ="weather",nullable = false)
    private String Weather;


}
