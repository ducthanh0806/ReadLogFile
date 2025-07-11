package com.example.ReadLogFile.service;

import com.example.ReadLogFile.dto.UserInfoDto;
import org.springframework.stereotype.Component;

public interface UserInfoService {
    UserInfoDto login(UserInfoDto userInfoDto);
}
