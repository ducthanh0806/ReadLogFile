package com.example.ReadLogFile.service.impl;
 
import com.example.ReadLogFile.mapper.UserDetailMapping;
import com.example.ReadLogFile.model.UserInfo;
import com.example.ReadLogFile.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@EnableJpaRepositories
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userInfoRepository.findByUserName(username);

        return userInfo.map(UserDetailMapping::new)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " Not Found"));
    }
}