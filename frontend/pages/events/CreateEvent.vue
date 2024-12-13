<template>
  <div>
    <h1>Créer un Événement</h1>
    <form @submit.prevent="createEvent">
      <input v-model="form.name" placeholder="Nom de l'événement" />
      <textarea v-model="form.description" placeholder="Description"></textarea>
      <button type="submit">Créer</button>
    </form>
  </div>
</template>

<script>
import api from '@/services/api';

export default {
  data() {
    return {
      form: {
        name: '',
        description: '',
      },
    };
  },
  methods: {
    async createEvent() {
      try {
        await api.post('/events', this.form);
        this.$router.push('/events');
      } catch (error) {
        console.error('Erreur lors de la création:', error);
      }
    },
  },
};
</script>
