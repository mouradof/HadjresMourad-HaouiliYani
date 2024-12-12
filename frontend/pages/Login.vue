<template>
  <div class="login-container">
    <h1>Connexion</h1>
    <form @submit.prevent="handleLogin">
      <InputField
        v-model="form.username"
        label="Nom d'utilisateur"
        placeholder="Entrez votre nom d'utilisateur"
        type="text"
      />
      <InputField
        v-model="form.password"
        label="Mot de passe"
        placeholder="Entrez votre mot de passe"
        type="password"
      />
      <Button :isLoading="loading" label="Se connecter" />
    </form>
    <p v-if="error" class="error-message">{{ error }}</p>
  </div>
</template>

<script>
import InputField from '@/components/InputField.vue';
import Button from '@/components/Button.vue';
import axios from 'axios';

export default {
  components: { InputField, Button },
  data() {
    return {
      form: {
        username: '',
        password: '',
      },
      loading: false,
      error: null,
    };
  },
  methods: {
    async handleLogin() {
      console.log('Tentative de connexion avec:', this.form);
      this.loading = true;
      this.error = null;
      try {
        const response = await axios.post('http://localhost:8080/auth/login', this.form);

        if (response.data) {
          const token = response.data; // Assurez-vous que le serveur retourne bien le token
          console.log('Connexion réussie, token reçu:', token);

          // Stocke le token dans localStorage
          localStorage.setItem('token', token);

          console.log('Redirection vers la page d’accueil...');
          this.$router.push('/');
        } else {
          throw new Error('Aucun token reçu');
        }
      } catch (err) {
        console.error('Erreur lors de la connexion:', err);
        this.error = err.response?.data?.message || 'Échec de la connexion';
      } finally {
        this.loading = false;
      }
    },
  },
};
</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: auto;
  padding: 20px;
  text-align: center;
}
.error-message {
  color: red;
  margin-top: 10px;
}
</style>
