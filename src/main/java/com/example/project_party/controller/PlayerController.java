package com.example.project_party.controller;

import com.example.project_party.model.Player;
import com.example.project_party.model.Users;
import com.example.project_party.service.PlayerService;
import com.example.project_party.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlayerService playerService;

    @PostMapping("/create")
    public ResponseEntity<List<Player>> createPlayers(@RequestBody List<Player> players) {
        System.out.println("Received players: " + players);
        List<Player> savedPlayers = players.stream()
                .map(playerService::save)
                .toList();
        return ResponseEntity.ok(savedPlayers);
    }

}
