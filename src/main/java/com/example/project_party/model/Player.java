package com.example.project_party.model;

import com.fasterxml.jackson.core.JsonParser;
import jakarta.persistence.*;
import java.util.Map;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column
    private int skillLevel;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "player_preferences", joinColumns = @JoinColumn(name = "player_id"))
    @MapKeyColumn(name = "preference_key")
    @Column(name = "preference_value")
    private Map<String, String> preferences;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    public JsonParser getPreferences() {
        return (JsonParser) preferences;
    }

    public void setPreferences(Map<String, String> preferences) {
        this.preferences = preferences;
    }
}
