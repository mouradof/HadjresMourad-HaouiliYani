<template>
  <div>
    <h1>Créer un Joueur</h1>
    <form @submit.prevent="createPlayer">
      <input v-model="form.username" placeholder="Nom du joueur" />
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
        username: '',
      },
    };
  },
  methods: {
    async createPlayer() {
      try {
        await api.post('/players/create', this.form);
        this.$router.push('/players');
      } catch (error) {
        console.error('Erreur lors de la création:', error);
      }
    },
  },
};
</script>
