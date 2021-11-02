package com.example.demo.repository;

import com.example.demo.domain.UserCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCharacterRepository extends JpaRepository<UserCharacter,Long> {

}
