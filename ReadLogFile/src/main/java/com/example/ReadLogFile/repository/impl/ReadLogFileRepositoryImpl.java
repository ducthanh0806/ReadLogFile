package com.example.ReadLogFile.repository.impl;

import java.util.List;

import com.example.ReadLogFile.model.LogInfo;
import com.example.ReadLogFile.repository.ReadLogFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReadLogFileRepositoryImpl implements ReadLogFileRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public int save(LogInfo loginfo) {
    return jdbcTemplate.update("",
        new Object[] { });
  }

  @Override
  public int update(LogInfo loginfo) {
    return jdbcTemplate.update("",
        new Object[] { });
  }

  @Override
  public LogInfo findById(Long id) {
    try {
      LogInfo loginfo = jdbcTemplate.queryForObject("SELECT * FROM tutorials WHERE id=?",
          BeanPropertyRowMapper.newInstance(LogInfo.class), id);

      return loginfo;
    } catch (IncorrectResultSizeDataAccessException e) {
      return null;
    }
  }

  @Override
  public int deleteById(Long id) {
    return jdbcTemplate.update("DELETE FROM tutorials WHERE id=?", id);
  }

  @Override
  public List<LogInfo> findAll() {
    return jdbcTemplate.query("SELECT * from loginfo", BeanPropertyRowMapper.newInstance(LogInfo.class));
  }

  @Override
  public int deleteAll() {
    return jdbcTemplate.update("DELETE from loginfo");
  }
}