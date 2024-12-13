package com.example.project_party.service;

import com.example.project_party.model.Match;
import com.example.project_party.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @CachePut(value = "matchesCache", key = "#match.id")
    public Match createMatch(Match match) {
        return matchRepository.save(match);
    }

    @Cacheable(value = "matchesCache", key = "#id")
    public Match getMatch(Long id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found"));
    }

    @CacheEvict(value = "matchesCache", key = "#id")
    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }
}
