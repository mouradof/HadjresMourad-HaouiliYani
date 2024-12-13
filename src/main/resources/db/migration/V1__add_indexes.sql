-- V1__add_indexes.sql

-- Index sur la table 'events'
CREATE INDEX idx_events_name ON events(name);
CREATE INDEX idx_events_date ON events(date);
CREATE INDEX idx_events_location ON events(location);
CREATE INDEX idx_events_organizer ON events(organizer_id);

-- Index sur la table 'match'
CREATE INDEX idx_match_type ON match(type);
CREATE INDEX idx_match_skill_level ON match(required_skill_level);
CREATE INDEX idx_match_distance ON match(max_distance);

-- Index sur la table 'player'
CREATE INDEX idx_player_username ON player(username);
CREATE INDEX idx_player_user ON player(user_id);
CREATE INDEX idx_player_skill_level ON player(skill_level);

-- Index composite pour optimiser les requêtes fréquentes
CREATE INDEX idx_match_players ON player(match_id);

-- Index sur la table 'users'
CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_users_skill_level ON users(skill_level);
