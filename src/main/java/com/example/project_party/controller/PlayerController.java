package com.example.project_party.controller;

import com.example.project_party.model.Player;
import com.example.project_party.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public ResponseEntity<Page<Player>> getAllPlayers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Player> players = playerService.getAllPlayers((Pageable) PageRequest.of(page, size));
        return ResponseEntity.ok(players);
    }

    @PostMapping("/create")
    public ResponseEntity<List<Player>> createPlayers(@RequestBody List<Player> players) {
        List<Player> savedPlayers = players.stream()
                .map(playerService::save)
                .toList();
        return ResponseEntity.ok(savedPlayers);
    }
}
