package com.example.ReadLogFile.controller;

import com.example.ReadLogFile.dto.UserInfoDto;
import com.example.ReadLogFile.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserInfoDto userInfoDtoBody)
    {
        UserInfoDto userInfoDto = userInfoService.login(userInfoDtoBody);
        return new ResponseEntity<>("User " + userInfoDto.username() +
                " is login", HttpStatus.ACCEPTED);
    }
}
