<template>
  <div>
    <h1>Modifier l'Événement</h1>
    <form @submit.prevent="updateEvent">
      <input v-model="form.name" placeholder="Nom de l'événement" />
      <textarea v-model="form.description" placeholder="Description"></textarea>
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
        name: '',
        description: '',
      },
    };
  },
  methods: {
    async fetchEvent() {
      const id = this.$route.params.id;
      try {
        const response = await api.get(`/events/${id}`);
        this.form = response.data;
      } catch (error) {
        console.error('Erreur lors de la récupération:', error);
      }
    },
    async updateEvent() {
      const id = this.$route.params.id;
      try {
        await api.put(`/events/${id}`, this.form);
        this.$router.push('/events');
      } catch (error) {
        console.error('Erreur lors de la mise à jour:', error);
      }
    },
  },
  mounted() {
    this.fetchEvent();
  },
};
</script>
