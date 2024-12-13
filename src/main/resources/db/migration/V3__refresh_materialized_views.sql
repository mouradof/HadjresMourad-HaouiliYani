-- V3__refresh_materialized_views.sql

-- Rafraîchissement des vues matérialisées
REFRESH MATERIALIZED VIEW mv_future_events;
REFRESH MATERIALIZED VIEW mv_match_players;
REFRESH MATERIALIZED VIEW mv_users_preferences;
