package com.example.weathercody.JPA;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Products")
@Table(name = "Products")
public class ProductsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @Column(name ="clothsImage",nullable = false)
    private String clothsImage;

    @Column(name ="clothsLink",nullable = false)
    private String clothsLink;

    @OneToOne
    @JoinColumn(name = "product_idx",foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), referencedColumnName = "idx",unique = true)
    @ToString.Include
    private ProductEntity product;
}
