package com.example.demo.service;

import com.example.demo.domain.Monster;
import com.example.demo.domain.User;
import com.example.demo.domain.UserCharacter;
import com.example.demo.dto.BattleInfoDto;
import com.example.demo.dto.CharacterBattleDto;
import com.example.demo.dto.CharacterNewDto;
import com.example.demo.repository.MonsterRepository;
import com.example.demo.repository.UserCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserCharacterService {
    @Autowired
    UserCharacterRepository characterRepository;
    @Autowired
    MonsterRepository monsterRepository;

    public UserCharacter findOne(Long id){
        return characterRepository.findById(id).get();
    }

    @Transactional
    public BattleInfoDto battle(CharacterBattleDto battleDto){
        UserCharacter character = characterRepository.findById(battleDto.getCharacterId()).get();
        Monster monster = monsterRepository.findById(battleDto.getMonsterId()).get();

        character.reduceHp(battleDto.getLostHp());
        character.reduceMp(battleDto.getLostMp());

        Long exp = character.incExp(monster.getExp());
        if(exp >= Math.pow(2,character.getLevel())){
            character.levelUp();
        }
        return new BattleInfoDto(character);
    }
}
