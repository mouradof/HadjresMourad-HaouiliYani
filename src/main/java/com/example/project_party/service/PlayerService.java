package com.example.project_party.service;

import com.example.project_party.model.Player;
import com.example.project_party.model.Users;
import com.example.project_party.repository.PlayerRepository;
import com.example.project_party.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = "playersCache", key = "#userId")
    public Player createPlayer(String username, Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Player player = new Player();
        player.setUsername(username);
        player.setUser(user);

        return playerRepository.save(player);
    }

    @Cacheable(value = "playersCache", key = "#player.id")
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    @Cacheable(value = "playersCache")
    public List<Player> getAllAvailablePlayers() {
        return playerRepository.findAll();
    }

    public Page<Player> getAllPlayers(Pageable pageable) {
        return playerRepository.findAll((org.springframework.data.domain.Pageable) pageable);
    }
}
