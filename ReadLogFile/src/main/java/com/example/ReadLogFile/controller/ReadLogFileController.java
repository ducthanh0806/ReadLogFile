package com.example.ReadLogFile.controller;

import com.example.ReadLogFile.model.LogInfo;
import com.example.ReadLogFile.model.UserInfo;
import com.example.ReadLogFile.repository.UserInfoRepository;
import com.example.ReadLogFile.service.ReadLogFileService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("logs")
public class ReadLogFileController {
    @Autowired
    ReadLogFileService readLogFileService;

    @Autowired
    private UserInfoRepository userRepo;

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

    @GetMapping("/logs")
    public ResponseEntity<List<LogInfo>> getAllLogInfo() {
        try {
            List<LogInfo> listLog = readLogFileService.getAllLogInfo();

            if (listLog.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(listLog, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/logs/{id}")
    public ResponseEntity<LogInfo> getLogInfoById(@PathVariable("id") long id) {
        LogInfo loginfo = readLogFileService.getLogInfoById(id);

        if (loginfo != null) {
            return new ResponseEntity<>(loginfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/logs")
    public ResponseEntity<String> createLogInfo(@RequestBody LogInfo loginfo) {
        try {
            readLogFileService.createLogInfo(loginfo);
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
            int result = readLogFileService.deleteLogInfoById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find Tutorial with id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Tutorial was deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete tutorial.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/logs")
    public ResponseEntity<String> deleteAllLogInfo() {
        try {
            int numRows = readLogFileService.deleteAllLogInfo();
            return new ResponseEntity<>("Deleted " + numRows + " Tutorial(s) successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete tutorials.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<UserInfo> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
   }
}