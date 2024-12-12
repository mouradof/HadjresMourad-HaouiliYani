<template>
    <div class="register-container">
      <h1>Créer un compte</h1>
      <form @submit.prevent="handleRegister">
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
        <InputField
          v-model="form.skillLevel"
          label="Niveau de compétence"
          placeholder="Entrez votre niveau de compétence"
          type="number"
        />
        <InputField
          v-model="form.preferences.maxDistance"
          label="Distance maximale"
          placeholder="Entrez la distance maximale"
          type="text"
        />
        <InputField
          v-model="form.preferences.desiredSkillLevel"
          label="Niveau de compétence souhaité"
          placeholder="Entrez le niveau de compétence souhaité"
          type="number"
        />
        <Button :isLoading="loading" label="S'inscrire" />
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
          skillLevel: 1,
          preferences: {
            maxDistance: '',
            desiredSkillLevel: '',
          },
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
          const response = await axios.post('http://localhost:8080/auth/register', this.form);
          console.log('Success:', response.data);
          this.$router.push('/login');
        } catch (err) {
          console.error('Error:', err);
          this.error = err.response?.data?.message || 'Une erreur s\'est produite';
        } finally {
          this.loading = false;
        }
      },
    },
  };
  </script>
  
  <style scoped>
  .register-container {
    max-width: 500px;
    margin: 50px auto;
    padding: 20px;
    border-radius: 10px;
    background-color: #f9f9f9;
    box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
  }
  
  h1 {
    text-align: center;
    font-size: 24px;
    margin-bottom: 20px;
    color: #333;
  }
  
  form {
    display: flex;
    flex-direction: column;
    gap: 15px;
  }
  
  .error-message {
    color: #e74c3c;
    font-size: 14px;
    text-align: center;
  }
  
  input {
    border: 1px solid #ddd;
    border-radius: 5px;
    padding: 10px;
    font-size: 14px;
    outline: none;
    transition: all 0.3s ease;
  }
  
  input:focus {
    border-color: #3498db;
    box-shadow: 0 0 5px rgba(52, 152, 219, 0.5);
  }
  
  button {
    background-color: #3498db;
    color: white;
    border: none;
    padding: 10px 15px;
    font-size: 16px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  
  button:hover {
    background-color: #2980b9;
  }
  </style>
  