package com.example.project_party.repository;

import com.example.project_party.model.Match;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    @EntityGraph(attributePaths = {"players"})
    List<Match> findByRequiredSkillLevelLessThanEqual(Integer skillLevel);
}
