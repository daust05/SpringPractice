package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.dto.UserInfoDto;
import com.example.demo.dto.UserLoginDto;
import com.example.demo.dto.UserNewDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/new")
    public ResponseEntity<UserInfoDto> joinUser(@RequestBody UserNewDto userNewDto){
        User saveUser = userNewDto.makeEntity();
        userService.join(saveUser);
        return new ResponseEntity<UserInfoDto>(new UserInfoDto(saveUser), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<UserInfoDto> loginUser(@RequestBody UserLoginDto loginDto){
        Optional<User> user = userService.login(loginDto);
        if(user.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(new UserInfoDto(user.get()),HttpStatus.OK);
    }
}
