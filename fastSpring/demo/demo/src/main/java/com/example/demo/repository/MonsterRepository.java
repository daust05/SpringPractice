package com.example.demo.repository;

import com.example.demo.domain.Monster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, Long> {
    List<Monster> findByLevelBetween(Long start, Long end);
}
