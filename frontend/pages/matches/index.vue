<template>
  <div>
    <h1>Liste des Matchs</h1>
    <button @click="goToCreate">Créer un Nouveau Match</button>
    <div v-if="loading">Chargement des matchs...</div>
    <ul v-else>
      <li v-for="match in matches" :key="match.id">
        <h3>Type: {{ match.type }}</h3>
        <p>ID: {{ match.id }}</p>
        <button @click="editMatch(match.id)">Modifier</button>
        <button @click="deleteMatch(match.id)">Supprimer</button>
      </li>
    </ul>
  </div>
</template>

<script>
import api from '@/services/api';

export default {
  data() {
    return {
      matches: [],
      loading: false,
    };
  },
  methods: {
    async fetchMatches() {
      this.loading = true;
      try {
        const response = await api.get('/matches');
        this.matches = response.data;
      } catch (error) {
        console.error('Erreur lors de la récupération des matchs:', error);
      } finally {
        this.loading = false;
      }
    },
    goToCreate() {
      this.$router.push('/matches/create');
    },
    editMatch(id) {
      this.$router.push(`/matches/edit/${id}`);
    },
    async deleteMatch(id) {
      try {
        await api.delete(`/matches/${id}`);
        this.matches = this.matches.filter(match => match.id !== id);
      } catch (error) {
        console.error('Erreur lors de la suppression:', error);
      }
    },
  },
  mounted() {
    this.fetchMatches();
  },
};
</script>
