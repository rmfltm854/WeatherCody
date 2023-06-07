package com.example.weathercody.JPA;
import jakarta.persistence.*;
import lombok.*;

@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ImageURL")
@Entity
@Getter
@Setter
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
    private long idx;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 1000, nullable = false)
    private String URL;

    @Column(length = 1000, nullable = false)
    private int Price;

    public ImageEntity(long idx, String name, String URL, int Price){
        this.idx = idx;
        this.name = name;
        this.URL = URL;
        this.Price = Price;
    }
}
