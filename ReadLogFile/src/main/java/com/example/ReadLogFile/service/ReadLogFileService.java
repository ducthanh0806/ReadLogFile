package com.example.ReadLogFile.service;

import com.example.ReadLogFile.dto.LogInfoDto;
import com.example.ReadLogFile.model.LogInfo;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface ReadLogFileService {
    ArrayList<LogInfoDto> readLogFile(File file) throws IOException;

    List<LogInfoDto> getAllLogInfo();

    LogInfoDto getLogInfoById(long id);

    void createLogInfo(LogInfo loginfo);

    void updateLogInfo(long id, LogInfo loginfo);

    int deleteLogInfoById(long id);

    int deleteAllLogInfo();
}
