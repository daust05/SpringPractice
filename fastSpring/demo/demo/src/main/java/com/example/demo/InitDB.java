package com.example.demo;

import com.example.demo.domain.Monster;
import com.example.demo.domain.User;
import com.example.demo.domain.UserCharacter;
import com.example.demo.repository.MonsterRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;

@Component
@Transactional
public class InitDB {
    @Autowired
    UserRepository userRepository;
    @Autowired
    MonsterRepository monsterRepository;
    @Autowired
    UserService userService;

    @PostConstruct
    public void init(){
        User user = new User("id1","pw1","user1");
        userRepository.save(user);

        Monster monster = new Monster("mon",1L);
        monsterRepository.save(monster);

        UserCharacter c1c1 = userService.makeCharacter(1L, "c1c1").get();
    }
}
