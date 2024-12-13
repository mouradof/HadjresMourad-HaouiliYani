# Optimisations Techniques

1. Optimisations des Requêtes

Réduction des requêtes N+1
Utilisation de fetch = FetchType.LAZY dans les relations JPA pour éviter de charger les entités inutiles par défaut.
Gestion explicite des jointures et chargements nécessaires dans les services pour un contrôle optimal.
Cache avec Spring
Ajout des annotations @Cacheable, @CacheEvict, et @CachePut pour optimiser les appels répétitifs aux méthodes critiques (exemple : récupération des utilisateurs et des événements).
Configuration d'un cache in-memory (par exemple, Caffeine ou Ehcache) pour stocker temporairement les résultats fréquemment demandés.
2. Optimisation de la Base de Données

Indexation
Création d’index sur les colonnes les plus fréquemment utilisées dans les requêtes :
events : name, date, location, organizer_id.
match : type, required_skill_level, max_distance.
player : username, user_id, skill_level.
users : username, skill_level.
Ajout d’index composites pour les relations courantes :
Relation match_id dans la table player.
Vues matérialisées
Création de vues matérialisées pour les cas d'utilisation complexes :

mv_future_events : Liste des événements futurs avec leurs organisateurs.
mv_match_players : Informations sur les matchs, le nombre de joueurs et les compétences moyennes.
mv_users_preferences : Liste des utilisateurs et leurs préférences sous forme normalisée.
Rafraîchissement des vues via un script SQL intégré.
3. Pagination

Implémentation de la pagination sur les endpoints API pour éviter de charger des volumes de données importants :
Endpoints concernés :
/events : Liste paginée des événements.
/players : Liste paginée des joueurs.
/matches : Liste paginée des matchs.
/users : Liste paginée des utilisateurs.
4. Structure des Migrations SQL

Les fichiers SQL pour les migrations et optimisations sont organisés dans le dossier src/main/resources/db/migration et gérés via Flyway :

V1__add_indexes.sql : Ajout des index pour les tables clés.
V2__create_materialized_views.sql : Création des vues matérialisées.
V3__refresh_materialized_views.sql : Scripts pour rafraîchir les vues.
5. Bonnes Pratiques de Développement

Relations JPA : Gestion fine des relations avec @ManyToOne, @OneToMany, et @ElementCollection.
Lazy Loading : Chargement différé pour les données volumineuses.
Logging : Activation des logs SQL pour monitorer et diagnostiquer les requêtes.
6. Configuration Globale

Configuration optimisée pour Spring JPA dans application.properties :
Hibernate DDL Auto : Gestion automatique du schéma (dev uniquement).
Logs SQL : Activation pour observer les performances.
Configuration du cache et de Flyway pour automatiser les migrations.