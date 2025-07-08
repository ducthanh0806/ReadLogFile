package com.example.ReadLogFile.mapper;

import com.example.ReadLogFile.dto.LogInfoDto;
import com.example.ReadLogFile.model.LogInfo;

public class LogInfoMapping {
    public static LogInfoDto toLogInfoDto(LogInfo logInfo) {
        return new LogInfoDto(logInfo.getId(), logInfo.getLineNo(),
                logInfo.getLogName(), logInfo.getMessage());
    }

    public static LogInfo toBook(LogInfoDto LogInfoDto) {
        return new LogInfo(LogInfoDto.id(), LogInfoDto.lineNo(),
                LogInfoDto.logName(), LogInfoDto.message());
    }
}
