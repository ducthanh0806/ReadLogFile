package com.example.ReadLogFile.controller;

import com.example.ReadLogFile.model.LogInfo;
import com.example.ReadLogFile.service.ReadLogFileService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("logs")
public class ReadLogFIleController {
    @Autowired
    ReadLogFileService readLogFileService;

    @GetMapping("")
    public ResponseEntity<String> readLogFIle() throws IOException {
        try {
            List<LogInfo> listLog = readLogFileService.readLogFile(new File("C:/Users/thanh/SpringBoot/kernel.log"));
            // Create a Gson instance
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // Convert the list to JSON
            String json = gson.toJson(listLog);
            return new ResponseEntity<>(json, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error when reading log file", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
