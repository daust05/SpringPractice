package com.example.demo.dto;

import com.example.demo.domain.User;
import lombok.Data;

@Data
public class UserNewDto {
    String loginId;
    String loginPw;
    String username;

    public User makeEntity(){
        return new User(loginId,loginPw,username);
    }
}
