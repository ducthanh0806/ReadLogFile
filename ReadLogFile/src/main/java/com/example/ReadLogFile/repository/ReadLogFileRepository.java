package com.example.ReadLogFile.repository;

import com.example.ReadLogFile.model.LogInfo;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ReadLogFileRepository extends JpaRepository<LogInfo, String> {
  @Query("SELECT u FROM LOG_INFO u WHERE u.id = 1")
  LogInfo findLogInfoById(Long id);
}