<template>
  <div class="home-container">
    <h1>Bienvenue, {{ username }}!</h1>
    <div class="buttons-container">
      <button @click="goToEvents" class="events-button">Voir les Événements</button>
      <button @click="logout" class="logout-button">Se déconnecter</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      username: '',
    };
  },
  methods: {
    goToEvents() {
      this.$router.push('/events');
    },
    logout() {
      console.log('Déconnexion en cours...');
      localStorage.removeItem('loggedIn');
      localStorage.removeItem('username');
      console.log('Redirection vers la page de connexion...');
      this.$router.push('/login');
    },
  },
  mounted() {
    this.username = localStorage.getItem('username') || 'Utilisateur';
  },
};
</script>

<style scoped>
.home-container {
  max-width: 600px;
  margin: 50px auto;
  text-align: center;
}

h1 {
  font-size: 28px;
  margin-bottom: 30px;
  color: #333;
}

.buttons-container {
  display: flex;
  justify-content: center;
  gap: 20px;
}

button {
  background-color: #3498db;
  color: white;
  border: none;
  padding: 12px 20px;
  font-size: 16px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #2980b9;
}

.logout-button {
  background-color: #e74c3c;
}

.logout-button:hover {
  background-color: #c0392b;
}

.events-button {
  background-color: #f39c12;
}

.events-button:hover {
  background-color: #e67e22;
}
</style>
