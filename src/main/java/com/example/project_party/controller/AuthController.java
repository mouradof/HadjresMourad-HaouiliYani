package com.example.project_party.controller;

import com.example.project_party.dto.RegisterRequest;
import com.example.project_party.model.Users;
import com.example.project_party.security.JwtUtil;
import com.example.project_party.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        try {
            Map<String, String> preferences = request.getPreferences();
            userService.registerUser(request.getUsername(), request.getPassword(), request.getSkillLevel(),
                    preferences);
            return ResponseEntity.ok("User registered successfully");
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user) {
        String token = userService.authenticate(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(token);
    }
}
