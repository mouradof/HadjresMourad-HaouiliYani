<template>
  <div class="home-container">
    <h1>Bienvenue sur la page d’accueil</h1>
    <button @click="logout">Se déconnecter</button>
  </div>
</template>

<script>
export default {
  mounted() {
    const token = localStorage.getItem('token');
    if (!token) {
      console.log('Aucun token détecté, redirection vers la page de connexion...');
      this.$router.push('/login');
    }
  },
  methods: {
    logout() {
      console.log('Déconnexion en cours...');
      localStorage.removeItem('token');
      console.log('Redirection vers la page de connexion...');
      this.$router.push('/login');
    },
  },
};
</script>

<style scoped>
.home-container {
  max-width: 400px;
  margin: auto;
  text-align: center;
}
</style>
