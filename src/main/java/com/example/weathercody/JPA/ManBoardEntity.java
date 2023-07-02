package com.example.weathercody.JPA;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "manBoard")
@Table(name = "manBoard")
public class ManBoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
    private long idx;

    @Column(length = 400, nullable = false)
    private String review;


    @Column(name ="product_idx",nullable = false)
    private int product_idx;

}
