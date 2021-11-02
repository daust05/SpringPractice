package com.example.demo.dto;

import com.example.demo.domain.User;
import lombok.Data;

@Data
public class UserInfoDto {
    Long id;
    String loginId;
    String username;

    public UserInfoDto(User user){
        this.id = user.getId();
        this.loginId = user.getLoginId();
        this.username = user.getUsername();
    }
}
