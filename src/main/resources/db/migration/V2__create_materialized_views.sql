-- V2__create_materialized_views.sql

-- Vue matérialisée pour les événements futurs
CREATE MATERIALIZED VIEW mv_future_events AS
SELECT
    e.id AS event_id,
    e.name AS event_name,
    e.date AS event_date,
    e.location AS event_location,
    u.username AS organizer_name
FROM events e
         JOIN users u ON e.organizer_id = u.id
WHERE e.date > CURRENT_DATE;

-- Vue matérialisée pour les joueurs par match et compétences moyennes
CREATE MATERIALIZED VIEW mv_match_players AS
SELECT
    m.id AS match_id,
    m.type AS match_type,
    COUNT(p.id) AS player_count,
    AVG(p.skill_level) AS avg_skill_level
FROM match m
         JOIN player p ON p.match_id = m.id
GROUP BY m.id, m.type;

-- Vue matérialisée pour les utilisateurs et leurs préférences
CREATE MATERIALIZED VIEW mv_users_preferences AS
SELECT
    u.id AS user_id,
    u.username,
    u.skill_level,
    up.preference_key,
    up.preference_value
FROM users u
         JOIN user_preferences up ON u.id = up.user_id;
