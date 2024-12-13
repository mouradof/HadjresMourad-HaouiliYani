<template>
  <div>
    <h1>Modifier le Match</h1>
    <form @submit.prevent="updateMatch">
      <input v-model="form.type" placeholder="Type de match" />
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
        type: '',
      },
    };
  },
  methods: {
    async fetchMatch() {
      const id = this.$route.params.id;
      try {
        const response = await api.get(`/matches/${id}`);
        this.form = response.data;
      } catch (error) {
        console.error('Erreur lors de la récupération:', error);
      }
    },
    async updateMatch() {
      const id = this.$route.params.id;
      try {
        await api.put(`/matches/${id}`, this.form);
        this.$router.push('/matches');
      } catch (error) {
        console.error('Erreur lors de la mise à jour:', error);
      }
    },
  },
  mounted() {
    this.fetchMatch();
  },
};
</script>
