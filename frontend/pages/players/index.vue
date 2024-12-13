<template>
  <div>
    <h1>Liste des Joueurs</h1>
    <div v-if="loading">Chargement des joueurs...</div>
    <ul v-else>
      <li v-for="player in players" :key="player.id">
        <h3>{{ player.username }}</h3>
        <p>ID: {{ player.id }}</p>
      </li>
    </ul>
  </div>
</template>

<script>
import api from '@/services/api';

export default {
  data() {
    return {
      players: [],
      loading: false,
    };
  },
  methods: {
    async fetchPlayers() {
      this.loading = true;
      try {
        const response = await api.get('/players');
        this.players = response.data;
      } catch (error) {
        console.error('Erreur lors de la récupération des joueurs:', error);
      } finally {
        this.loading = false;
      }
    },
  },
  mounted() {
    this.fetchPlayers();
  },
};
</script>
