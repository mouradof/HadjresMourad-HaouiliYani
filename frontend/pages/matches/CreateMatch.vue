<template>
  <div>
    <h1>Créer un Match</h1>
    <form @submit.prevent="createMatch">
      <input v-model="form.type" placeholder="Type de match (ex: 1v1, 5v5)" />
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
        type: '',
      },
    };
  },
  methods: {
    async createMatch() {
      try {
        await api.post('/matches', this.form);
        this.$router.push('/matches');
      } catch (error) {
        console.error('Erreur lors de la création:', error);
      }
    },
  },
};
</script>
