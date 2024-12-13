package com.example.project_party.repository;

import com.example.project_party.model.Player;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @EntityGraph(attributePaths = {"user"})
    List<Player> findBySkillLevelGreaterThanEqual(int skillLevel);
}
