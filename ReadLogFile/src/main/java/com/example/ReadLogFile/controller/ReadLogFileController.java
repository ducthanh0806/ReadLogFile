package com.example.ReadLogFile.controller;

import com.example.ReadLogFile.dto.LogInfoDto;
import com.example.ReadLogFile.model.LogInfo;
import com.example.ReadLogFile.service.ReadLogFileService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/read")
public class ReadLogFileController {
    @Autowired
    ReadLogFileService readLogFileService;

    @GetMapping("")
    public ResponseEntity<String> readLogFIle() throws IOException {
        try {
            List<LogInfoDto> listLog = readLogFileService.readLogFile(new File("C:/Users/thanh/SpringBoot/kernel.log"));
            // Create a Gson instance
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // Convert the list to JSON
            String json = gson.toJson(listLog);
            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error when reading log file", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/logs")
    public ResponseEntity<List<LogInfoDto>> getAllLogInfo() {
        try {
            List<LogInfoDto> listLog = readLogFileService.getAllLogInfo();

            if (listLog.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(listLog, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/logs/{id}")
    public ResponseEntity<LogInfoDto> getLogInfoById(@PathVariable("id") long id) {
        LogInfoDto logInfoDto = readLogFileService.getLogInfoById(id);

        if (logInfoDto != null) {
            return new ResponseEntity<>(logInfoDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/logs")
    public ResponseEntity<String> createLogInfo() {
        try {
            File file = new File("C:/Users/thanh/SpringBoot/TestLog.log");
            readLogFileService.createLogInfo(file);
            return new ResponseEntity<>("Logs was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/logs/{id}")
    public ResponseEntity<String> updateLogInfo(@PathVariable("id") long id, @RequestBody LogInfo loginfo) {
        try {
            readLogFileService.updateLogInfo(id, loginfo);
            return new ResponseEntity<>("Logs was updated successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot find Logs with id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/logs/{id}")
    public ResponseEntity<String> deleteLogInfoById(@PathVariable("id") long id) {
        try {
            readLogFileService.deleteLogInfoById(id);
            return new ResponseEntity<>("Tutorial was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete this log.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/logs")
    public ResponseEntity<String> deleteAllLogInfo() {
        try {
            readLogFileService.deleteAllLogInfo();
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete logs.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}