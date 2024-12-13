// A. Login Page (pages/Login.vue)
<template>
  <div class="login-container">
    <h1>Connexion</h1>
    <form @submit.prevent="handleLogin">
      <InputField v-model="form.username" label="Nom d'utilisateur" placeholder="Entrez votre nom d'utilisateur" type="text" />
      <InputField v-model="form.password" label="Mot de passe" placeholder="Entrez votre mot de passe" type="password" />
      <Button :isLoading="loading" label="Se connecter" type="submit" />
    </form>
    <p v-if="error" class="error-message">{{ error }}</p>
    <p class="redirect-message">
      Pas encore de compte ? <nuxt-link to="/register">Inscrivez-vous ici</nuxt-link>.
    </p>
  </div>
</template>

<script>
import InputField from '@/components/InputField.vue';
import Button from '@/components/Button.vue';
import api from '@/services/api';

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
      this.loading = true;
      this.error = null;
      try {
        const response = await api.post('/auth/login', this.form);
        localStorage.setItem('jwt', response.data);
        localStorage.setItem('loggedIn', 'true');
        this.$router.push('/');
      } catch (err) {
        this.error = 'Échec de la connexion.';
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
  margin: 50px auto;
  padding: 20px;
  border-radius: 10px;
  background-color: #f9f9f9;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
  text-align: center;
}
</style>