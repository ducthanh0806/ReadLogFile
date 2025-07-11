package com.example.ReadLogFile.repository;
 
import com.example.ReadLogFile.model.UserInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
//    @Query("SELECT u FROM User u WHERE u.username = ?1")
//    Optional<UserInfo> findByUserName(String username);
}