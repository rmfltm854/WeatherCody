package com.example.weathercody.JPA;

import com.example.weathercody.DTO.UserDTO;
import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "UserInfo")
@Entity
public class UserJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
    private int idx;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 200, nullable = false)
    private int age;

    @Column(length = 200, nullable = false,name = "Email")
    private String Email;

    @Column(length = 200, nullable = false)
    private String PW;

    @Column(length = 200, nullable = false)
    private String Tel;

}
