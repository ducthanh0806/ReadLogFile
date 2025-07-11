package com.example.ReadLogFile.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

@Setter
@Getter
@Entity
@Table(name = "LOG_INFO")
public class LogInfo {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "LINE_NO")
    private int lineNo;

    @Column(name = "LOG_NAME")
    private String logName;

    @Column(name = "MESSAGE")
    private String message;

    public LogInfo() {
    }

    public LogInfo(Long id, int lineNo, String logName, String message) {
    }
}
