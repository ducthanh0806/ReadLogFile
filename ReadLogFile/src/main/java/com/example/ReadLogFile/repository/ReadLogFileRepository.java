package com.example.ReadLogFile.repository;

import com.example.ReadLogFile.model.LogInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadLogFileRepository {
  int save(LogInfo loginfo);

  int update(LogInfo loginfo);

  LogInfo findById(Long id);

  int deleteById(Long id);

  List<LogInfo> findAll();

  int deleteAll();
}