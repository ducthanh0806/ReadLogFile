package com.example.ReadLogFile.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "logInfos")
public class LogInfo {
    @Id
    private Long id;
    private int lineNo;
    private String logName;
    private String message;

    public LogInfo() {

    }

}
