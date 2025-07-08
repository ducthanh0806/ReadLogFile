package com.example.ReadLogFile.service.impl;

import com.example.ReadLogFile.dto.UserInfoDto;
import com.example.ReadLogFile.model.UserInfo;
import com.example.ReadLogFile.repository.UserInfoRepository;
import com.example.ReadLogFile.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.ReadLogFile.mapper.UserInfoMapping;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String login(UserInfoDto userInfoDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userInfoDto.username(), userInfoDto.password()
        ));
        if (authentication.isAuthenticated())
            return "Success";
        return "Fail";
    }
}
