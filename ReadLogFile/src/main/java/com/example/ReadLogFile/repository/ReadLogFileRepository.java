package com.example.ReadLogFile.repository;

import com.example.ReadLogFile.model.LogInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ReadLogFileRepository {
  int save(LogInfo loginfo);

  int update(LogInfo loginfo);

  LogInfo findById(Long id);

  int deleteById(Long id);

  List<LogInfo> findAll();

  int deleteAll();
}