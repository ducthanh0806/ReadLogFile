package com.example.ReadLogFile.service;

import com.example.ReadLogFile.model.LogInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface ReadLogFileService {
    public ArrayList<LogInfo> readLogFile(File file) throws IOException;

    public List<LogInfo> getAllLogInfo();

    LogInfo getLogInfoById(long id);

    void createLogInfo(LogInfo loginfo);

    void updateLogInfo(long id, LogInfo loginfo);

    int deleteLogInfoById(long id);

    int deleteAllLogInfo();
}
