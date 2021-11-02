package com.example.demo.dto;

import com.example.demo.domain.UserCharacter;
import lombok.Data;

@Data
public class BattleInfoDto {
    Long id;
    Long level;
    Long attackDamage;
    Long hp;
    Long mp;
    Long exp;

    public BattleInfoDto(UserCharacter character){
        this.id = character.getId();
        this.level = character.getLevel();
        this.attackDamage = character.getAttackDamage();
        this.hp = character.getHp();
        this.mp = character.getMp();
        this.exp = character.getExp();
    }
}
