package com.example.demo.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Monster {
    @Id @GeneratedValue
    @Column(name="monster_id")
    Long id;

    String name;

    Long level;
    Long hp;
    Long attackDamage;
    Long exp;

    public Monster(String name, Long level){
        this.name = name;
        this.level = level;
        hp = level*10;
        attackDamage = level;
        exp = level;
    }
}
