package com.example.demo.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class UserCharacter {
    @Id
    @GeneratedValue
    @Column(name = "character_id")
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    String name;
    Long level;
    Long attackDamage;
    Long hp;
    Long mp;
    Long exp;

    public UserCharacter() {
        level = 1L;
        attackDamage = 5L;
        hp = 100L;
        mp = 10L;
        exp = 0L;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long incExp(Long expPoint){
        exp += expPoint;
        return exp;
    }

    public void levelUp(){
        exp -= (long) Math.pow(2,level);
        level += 1;
        hp += 10;
        mp += 1;
        attackDamage += 1;
    }

    public Long reduceHp(Long damage){
        hp -= damage;
        return hp;
    }
    public Long reduceMp(Long mana){
        mp -= mana;
        return mp;
    }
}
