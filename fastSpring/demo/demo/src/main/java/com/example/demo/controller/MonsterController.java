package com.example.demo.controller;

import com.example.demo.domain.Monster;
import com.example.demo.dto.MonsterInfoDto;
import com.example.demo.repository.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/monster")
public class MonsterController {

    @Autowired
    MonsterRepository monsterRepository;

    @GetMapping("/{id}")
    public ResponseEntity<MonsterInfoDto> getMonster(@PathVariable Long id){
        Optional<Monster> monster = monsterRepository.findById(id);
        MonsterInfoDto infoDto = new MonsterInfoDto(monster.get());
        return new ResponseEntity<>(infoDto, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<MonsterInfoDto> getMonsterByLevel(@RequestParam Long level){
        List<Monster> levelMonster = monsterRepository.findByLevelBetween(level - 1, level + 1);
        Monster monster = levelMonster.get((int) (Math.random() * levelMonster.size()));
        MonsterInfoDto infoDto = new MonsterInfoDto(monster);
        return new ResponseEntity<>(infoDto, HttpStatus.OK);
    }
}
