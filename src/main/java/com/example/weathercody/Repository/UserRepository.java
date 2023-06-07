package com.example.weathercody.Repository;

import com.example.weathercody.JPA.ImageEntity;
import com.example.weathercody.JPA.UserJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserJPA,Long> {

    @Query(value = "select * from user_info where email = ?",nativeQuery = true)
    UserJPA sinInUser(String Email);

}
