package com.example.project_party.controller;

import com.example.project_party.model.Match;
import com.example.project_party.service.MatchmakingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchmakingService matchmakingService;

    @PostMapping("/create")
    public ResponseEntity<Match> createMatch(@RequestBody Match match) {
        Match createdMatch = matchmakingService.createMatch(match);
        return ResponseEntity.ok(createdMatch);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatch(@PathVariable Long id) {
        return ResponseEntity.ok(matchmakingService.getMatch(id));
    }
}
