package com.example.ReadLogFile.service.impl;

import com.example.ReadLogFile.dto.LogInfoDto;
import com.example.ReadLogFile.mapper.LogInfoMapping;
import com.example.ReadLogFile.model.LogInfo;
import com.example.ReadLogFile.repository.ReadLogFileRepository;
import com.example.ReadLogFile.service.ReadLogFileService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class ReadLogFileServiceImpl implements ReadLogFileService {
	@Autowired
    ReadLogFileRepository readLogFileRepository;
	
    @Override
    public ArrayList<LogInfoDto> readLogFile(File file) throws IOException {
        int numLines = 1000;
        ArrayList<LogInfo> logInfos = new ArrayList<>();

        Deque<String> deque = new ArrayDeque<>(numLines);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (deque.size() == numLines) {
                    deque.removeFirst(); // Remove oldest line
                }
                deque.addLast(line); // Add newest line
            }
        }
        ArrayList<String> logLines = new ArrayList<>(deque);

        for (int i = 0; i < numLines-1; i++) {
            String line = logLines.get(i);
            if (line.contains("Error:") && !line.contains("<entry")) {
                LogInfo logInfo = new LogInfo();
                logInfo.setId((long) i);
                logInfo.setLineNo(i);
                logInfo.setLogName(line.trim());
                logInfo.setMessage(logLines.get(i + 1).trim());
                logInfos.add(logInfo);
            } else if (line.contains("Exception:")) {
                LogInfo logInfo = new LogInfo();
                logInfo.setId((long) i);
                logInfo.setLineNo(i);
                logInfo.setLogName(line.substring(0, line.indexOf("Exception:") + 10).trim());
                logInfo.setMessage(line.substring(line.indexOf("Exception:") + 11).trim());
                logInfos.add(logInfo);
            }
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Convert the list to JSON
        String json = gson.toJson(logInfos);
        File jsonFIle = new File(file.getAbsolutePath().replace(".log", "") + ".json");
        // Specifying the output file path as a Path object
        Path outputFilePath = Path.of(jsonFIle.getPath());

        // Writing the JSON string to a file
        Files.write(outputFilePath, json.getBytes());

        ArrayList<LogInfoDto> listLogDto = new ArrayList<>();
        for (LogInfo log : logInfos) {
            listLogDto.add(LogInfoMapping.toLogInfoDto(log));
        }

        return listLogDto;
    }
    
    
    public ArrayList<LogInfoDto> getAllLogInfo() {

        ArrayList<LogInfo> listLog = new ArrayList<>(readLogFileRepository.findAll());
        if (listLog.isEmpty()) {
          return new ArrayList<>();
        }
        ArrayList<LogInfoDto> listLogDto = new ArrayList<>();
        for (LogInfo log : listLog) {
            listLogDto.add(LogInfoMapping.toLogInfoDto(log));
        }
        return listLogDto;
    }
    
    public LogInfoDto getLogInfoById (long id) {
        LogInfo logInfo = readLogFileRepository.findById(id);
        return LogInfoMapping.toLogInfoDto(logInfo);
    }
    
    public void createLogInfo (LogInfo loginfo) {
    	readLogFileRepository.save(new LogInfo(loginfo.getId(),
    			loginfo.getLineNo(), loginfo.getLogName(), loginfo.getMessage()));
    }
    
    public void updateLogInfo (long id, LogInfo loginfoUpdate) {
    	LogInfo loginfo = readLogFileRepository.findById(id);

        loginfo.setId(id);
        loginfo.setLineNo(loginfoUpdate.getLineNo());
        loginfo.setLogName(loginfoUpdate.getLogName());
        loginfo.setMessage(loginfoUpdate.getMessage());
        readLogFileRepository.update(loginfo);
    }
    
    public int deleteLogInfoById (long id) {
        return readLogFileRepository.deleteById(id);
    }
    
    public int deleteAllLogInfo () {
        return readLogFileRepository.deleteAll();
    }
}
