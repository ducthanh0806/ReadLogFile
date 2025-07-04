package com.example.ReadLogFile.service;

import com.example.ReadLogFile.model.LogInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface ReadLogFileService {
    public ArrayList<LogInfo> readLogFile(File file) throws IOException;
}
