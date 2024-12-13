package com.example.project_party.controller;

import com.example.project_party.model.Player;
import com.example.project_party.model.Users;
import com.example.project_party.service.PlayerService;
import com.example.project_party.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = userService.findAll();
        users.forEach(user -> user.setPassword(null));
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        Optional<Users> user = userService.findById(id);
        if (user.isPresent()) {
            Users foundUser = user.get();
            foundUser.setPassword(null);
            return ResponseEntity.ok(foundUser);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public ResponseEntity<List<Player>> createPlayers(@RequestBody List<Player> players) {
        List<Player> savedPlayers = players.stream()
                .map(playerService::save)
                .toList();
        return ResponseEntity.ok(savedPlayers);
    }

}
