package com.example.ReadLogFile.repository;

import java.util.List;

import com.bezkoder.spring.jdbc.oracle.model.LogInfo;

public interface ReadLogFileRepository {
  int save(LogInfo loginfo);

  int update(LogInfo loginfo);

  Tutorial findById(Long id);

  int deleteById(Long id);

  List<LogInfo> findAll();

  int deleteAll();
}