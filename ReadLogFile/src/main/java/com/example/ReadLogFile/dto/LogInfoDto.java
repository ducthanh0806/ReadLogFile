package com.example.ReadLogFile.dto;

public record LogInfoDto (Long id, int lineNo, String logName, String message) {
}
