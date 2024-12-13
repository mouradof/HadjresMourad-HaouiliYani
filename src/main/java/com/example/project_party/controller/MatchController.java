package com.example.project_party.controller;

import com.example.project_party.model.Match;
import com.example.project_party.service.MatchmakingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchmakingService matchmakingService;

    @GetMapping
    public ResponseEntity<Page<Match>> getAllMatches(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Match> matches = matchmakingService.getAllMatches((Pageable) PageRequest.of(page, size));
        return ResponseEntity.ok(matches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatch(@PathVariable Long id) {
        return ResponseEntity.ok(matchmakingService.getMatch(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Match> createMatch(@RequestBody Match match) {
        Match createdMatch = matchmakingService.createMatch(match);
        return ResponseEntity.ok(createdMatch);
    }
}
