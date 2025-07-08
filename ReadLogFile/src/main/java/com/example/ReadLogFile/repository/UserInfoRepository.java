package com.example.ReadLogFile.repository;
 
import com.example.ReadLogFile.model.UserInfo;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    Optional<UserInfo> findByUserName(String username);
}