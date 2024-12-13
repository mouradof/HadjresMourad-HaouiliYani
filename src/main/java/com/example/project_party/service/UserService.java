package com.example.project_party.service;

import com.example.project_party.model.Users;
import com.example.project_party.repository.UserRepository;
import com.example.project_party.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @CachePut(value = "usersCache", key = "#username")
    public void registerUser(String username, String password, Integer skillLevel, Map<String, String> preferences) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username '" + username + "' already exists");
        }

        Users user = new Users();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setSkillLevel(skillLevel);
        user.setPreferences(preferences);

        userRepository.save(user);
    }

    @Cacheable(value = "usersCache", key = "#username")
    public String authenticate(String username, String password) {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));
        if (passwordEncoder.matches(password, user.getPassword())) {
            return jwtUtil.generateToken(username);
        }
        throw new RuntimeException("Invalid username or password");
    }

    @Cacheable(value = "usersCache", key = "#id")
    public Optional<Users> findById(Long id) {
        return userRepository.findById(id);
    }

    @Cacheable(value = "usersCache")
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @CacheEvict(value = "usersCache", key = "#id")
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @CacheEvict(value = "usersCache", key = "#id")
    public Page<Users> getAllUsers(Pageable pageable) {
        return userRepository.findAll((org.springframework.data.domain.Pageable) pageable);
    }
}
