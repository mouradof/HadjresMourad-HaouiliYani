package com.example.project_party.service;

import com.example.project_party.model.Users;
import com.example.project_party.repository.UserRepository;
import com.example.project_party.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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

    public void registerUser(String username, String password, Integer skillLevel, Map<String, String> preferences) {
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // N'oubliez pas d'encoder le mot de passe
        user.setSkillLevel(skillLevel);
        user.setPreferences(preferences);

        System.out.println("Saving user: " + user.getUsername());

        userRepository.save(user);
    }

    public String authenticate(String username, String password) {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));
        if (passwordEncoder.matches(password, user.getPassword())) {
            return jwtUtil.generateToken(username);
        }
        throw new RuntimeException("Invalid username or password");
    }

    public Optional<Users> findById(Long id) {
        return userRepository.findById(id);
    }

}
