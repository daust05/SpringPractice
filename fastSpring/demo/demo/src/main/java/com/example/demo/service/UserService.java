package com.example.demo.service;

import com.example.demo.domain.UserCharacter;
import com.example.demo.domain.User;
import com.example.demo.dto.UserLoginDto;
import com.example.demo.repository.UserCharacterRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserCharacterRepository characterRepository;

    @Transactional
    public Long join(User user){
        User saveUser = userRepository.save(user);
        return saveUser.getId();
    }

    public User findOne(Long id){
        Optional<User> findUser = userRepository.findById(id);
        return findUser.get();
    }
    public Optional<User> login(UserLoginDto loginDto){
        return userRepository.findByLoginIdAndLoginPw(loginDto.getLoginId(), loginDto.getLoginPw());
    }

    @Transactional
    public Optional<UserCharacter> makeCharacter(Long id, String name){
        User user = findOne(id);
        UserCharacter character = user.makeCharacter(name);
        characterRepository.save(character);
        return Optional.ofNullable(character);
    }
}

