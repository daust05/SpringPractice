package com.example.demo.dto;

import com.example.demo.domain.Monster;
import lombok.Data;

@Data
public class MonsterInfoDto {
    Long id;
    String name;
    Long level;
    Long hp;
    Long attackDamage;
    Long exp;
    public MonsterInfoDto(Monster monster){
        id = monster.getId();
        name = monster.getName();
        level = monster.getLevel();
        hp = monster.getHp();
        attackDamage = monster.getAttackDamage();
        exp = monster.getExp();
    }
}
