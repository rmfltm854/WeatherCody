package com.example.weathercody.JPA;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "WomanProducts")
@Table(name = "WomanProducts")
public class WomanProductsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @Column(name ="WomanclothsImage",nullable = false)
    private String clothsImage;

    @Column(name ="WomanclothsLink",nullable = false)
    private String clothsLink;

    @OneToOne
    @JoinColumn(name = "product_idx",foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT), referencedColumnName = "idx",unique = true)
    @ToString.Include
    private WomanProductEntity product;
}
