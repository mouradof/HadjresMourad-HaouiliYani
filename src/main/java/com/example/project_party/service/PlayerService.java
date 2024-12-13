package com.example.project_party.service;

import com.example.project_party.model.Player;
import com.example.project_party.model.Users;
import com.example.project_party.repository.PlayerRepository;
import com.example.project_party.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private UserRepository userRepository;

    public Player createPlayer(String username, Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Player player = new Player();
        player.setUsername(username);
        player.setUser(user);

        return playerRepository.save(player);
    }

    public Player save(Player player) {
        return playerRepository.save(player);
    }

    public List<Player> getAllAvailablePlayers() {
        return playerRepository.findAll();
    }
}
