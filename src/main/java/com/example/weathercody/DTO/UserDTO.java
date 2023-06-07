package com.example.weathercody.DTO;

import com.example.weathercody.JPA.UserJPA;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int idx;

    private String email;

    private String pw;

    private String tel;

    private int age;

    private String name;

    public static UserDTO toDTO(UserJPA entity) {
        return UserDTO.builder()
                .idx(entity.getIdx())
                .name(entity.getName())
                .age(entity.getAge())
                .email(entity.getEmail())
                .pw(entity.getPW())
                .tel(entity.getTel())
                .build();
    }
}
