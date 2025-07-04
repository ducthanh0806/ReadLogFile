package com.example.ReadLogFile.service.impl;

import com.example.ReadLogFile.model.LogInfo;
import com.example.ReadLogFile.service.ReadLogFileService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class ReadLogServiceImpl implements ReadLogFileService {
    @Override
    public ArrayList<LogInfo> readLogFile(File file) throws IOException {
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

        return logInfos;
    }
}
