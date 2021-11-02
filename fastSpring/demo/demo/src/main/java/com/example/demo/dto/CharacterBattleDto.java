package com.example.demo.dto;

import lombok.Data;

@Data
public class CharacterBattleDto {
    Long characterId;
    Long monsterId;
    Long lostHp;
    Long lostMp;
}
