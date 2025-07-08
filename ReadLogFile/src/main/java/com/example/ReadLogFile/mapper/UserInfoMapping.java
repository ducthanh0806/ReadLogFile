package com.example.ReadLogFile.mapper;

import com.example.ReadLogFile.dto.UserInfoDto;
import com.example.ReadLogFile.model.UserInfo;

public class UserInfoMapping {

    public static UserInfoDto toUserInfoDto(UserInfo userInfo)
    {
        return new UserInfoDto(userInfo.getUsername(), userInfo.getPassword(), userInfo.getEmail(),
                userInfo.getRoles());
    }

    public static UserInfo toUserInfo(UserInfoDto userInfoDto)
    {
        return new UserInfo(userInfoDto.username(), userInfoDto.password(), userInfoDto.email(),
                userInfoDto.roles());
    }
}