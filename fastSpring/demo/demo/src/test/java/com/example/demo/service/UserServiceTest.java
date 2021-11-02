package com.example.demo.service;

import com.example.demo.domain.UserCharacter;
import com.example.demo.domain.User;
import com.example.demo.dto.UserLoginDto;
import com.example.demo.repository.UserCharacterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserCharacterRepository characterRepository;


    @Test
    public void loginTest(){
        User one = userService.findOne(1L);
        Optional<User> login = userService.login(new UserLoginDto("id1","pw1"));
        assertThat(one).isEqualTo(login.get());
        Optional<User> login2 = userService.login(new UserLoginDto("id1","pw12"));
        assertThat(login2).isEqualTo(Optional.ofNullable(null));
    }

    @Test
    public void makeCharacter(){
        Optional<UserCharacter> c1 = userService.makeCharacter(1L,"c1");
        Optional<UserCharacter> findCh = characterRepository.findById(c1.get().getId());
        assertThat(c1).isEqualTo(findCh);
    }
}