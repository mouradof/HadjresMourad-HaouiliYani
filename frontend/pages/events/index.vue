<template>
  <div>
    <h1>Liste des Événements</h1>
    <button @click="goToCreate">Créer un Nouvel Événement</button>
    <div v-if="loading">Chargement...</div>
    <ul v-else>
      <li v-for="event in events" :key="event.id">
        <h3>{{ event.name }}</h3>
        <p>{{ event.description }}</p>
        <button @click="editEvent(event.id)">Modifier</button>
        <button @click="deleteEvent(event.id)">Supprimer</button>
      </li>
    </ul>
  </div>
</template>

<script>
import api from '@/services/api';

export default {
  data() {
    return {
      events: [],
      loading: false,
    };
  },
  methods: {
    async fetchEvents() {
      this.loading = true;
      try {
        const response = await api.get('/events');
        this.events = response.data;
      } catch (error) {
        console.error('Erreur lors de la récupération des événements:', error);
      } finally {
        this.loading = false;
      }
    },
    goToCreate() {
      this.$router.push('/events/create');
    },
    editEvent(id) {
      this.$router.push(`/events/edit/${id}`);
    },
    async deleteEvent(id) {
      try {
        await api.delete(`/events/${id}`);
        this.events = this.events.filter(event => event.id !== id);
      } catch (error) {
        console.error('Erreur lors de la suppression:', error);
      }
    },
  },
  mounted() {
    this.fetchEvents();
  },
};
</script>
