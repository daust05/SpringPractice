package com.example.demo.controller;

import com.example.demo.domain.UserCharacter;
import com.example.demo.dto.BattleInfoDto;
import com.example.demo.dto.CharacterBattleDto;
import com.example.demo.dto.CharacterNewDto;
import com.example.demo.service.UserCharacterService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/character")
public class CharacterController {

    @Autowired
    UserCharacterService characterService;
    @Autowired
    UserService userService;


    @PostMapping("/new")
    public ResponseEntity<Long> makeCharacter(@RequestBody CharacterNewDto newDto){
        Optional<UserCharacter> character = userService.makeCharacter(newDto.getUserId(), newDto.getName());
        return new ResponseEntity<Long>(character.get().getId(), HttpStatus.OK);
    }

    @PostMapping("/hunt")
    public ResponseEntity<BattleInfoDto> huntMonster(@RequestBody CharacterBattleDto battleDto){
        BattleInfoDto infoDto = characterService.battle(battleDto);
        return new ResponseEntity<>(infoDto,HttpStatus.OK);
    }
}
