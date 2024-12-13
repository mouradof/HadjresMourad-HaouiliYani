package com.example.project_party.controller;

import com.example.project_party.model.Player;
import com.example.project_party.model.Users;
import com.example.project_party.service.PlayerService;
import com.example.project_party.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlayerService playerService;

    @PostMapping("/create")
    public ResponseEntity<Player> createPlayer(@RequestBody Player playerRequest) {
        Users user = userService.findById(playerRequest.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Player player = new Player();
        player.setUsername(playerRequest.getUsername());
        player.setUser(user);

        playerService.savePlayer(player);

        return ResponseEntity.ok(player);
    }
}
