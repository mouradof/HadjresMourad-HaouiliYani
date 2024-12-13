<template>
  <div>
    <h1>Modifier le Joueur</h1>
    <form @submit.prevent="updatePlayer">
      <input v-model="form.username" placeholder="Nom du joueur" />
      <button type="submit">Mettre à jour</button>
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
    async fetchPlayer() {
      const id = this.$route.params.id;
      try {
        const response = await api.get(`/players/${id}`);
        this.form = response.data;
      } catch (error) {
        console.error('Erreur lors de la récupération:', error);
      }
    },
    async updatePlayer() {
      const id = this.$route.params.id;
      try {
        await api.put(`/players/${id}`, this.form);
        this.$router.push('/players');
      } catch (error) {
        console.error('Erreur lors de la mise à jour:', error);
      }
    },
  },
  mounted() {
    this.fetchPlayer();
  },
};
</script>
