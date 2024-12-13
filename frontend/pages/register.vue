<template>
  <div class="register-container">
    <h1>Créer un Compte</h1>
    <form @submit.prevent="handleRegister">
      <InputField v-model="form.username" label="Nom d'utilisateur" placeholder="Entrez votre nom d'utilisateur" type="text" />
      <InputField v-model="form.password" label="Mot de passe" placeholder="Entrez votre mot de passe" type="password" />
      <Button :isLoading="loading" label="S'inscrire" type="submit" />
    </form>
    <p v-if="error" class="error-message">{{ error }}</p>
    <p class="redirect-message">
      Déjà inscrit ? <nuxt-link to="/login">Connectez-vous ici</nuxt-link>.
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
    async handleRegister() {
      this.loading = true;
      this.error = null;
      try {
        await api.post('/auth/register', this.form);
        this.$router.push('/login');
      } catch (err) {
        this.error = 'Erreur lors de l inscription.';
      } finally {
        this.loading = false;
      }
    },
  },
};
</script>

<style scoped>
.register-container {
  max-width: 400px;
  margin: 50px auto;
  padding: 20px;
  border-radius: 10px;
  background-color: #f9f9f9;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
  text-align: center;
}
</style>