package com.example.demo.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id @GeneratedValue
    @Column(name ="user_id")
    Long id;
    String loginId;
    String loginPw;
    String username;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<UserCharacter> characters = new ArrayList<>();

    public User(String loginId, String loginPw, String username){
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.username = username;
    }

    public UserCharacter makeCharacter(String name){
        UserCharacter character = new UserCharacter();
        character.setName(name);
        character.setUser(this);
        this.characters.add(character);
        return character;
    }
}
