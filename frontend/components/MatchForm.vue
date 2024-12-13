<template>
    <form @submit.prevent="handleSubmit">
      <InputField
        v-model="form.name"
        label="Nom du Match"
        placeholder="Entrez le nom du match"
        type="text"
      />
      <InputField
        v-model="form.date"
        label="Date et Heure"
        placeholder="Sélectionnez la date et l'heure"
        type="datetime-local"
      />
      <InputField
        v-model="form.location"
        label="Lieu"
        placeholder="Entrez le lieu du match"
        type="text"
      />
      <InputField
        v-model="form.description"
        label="Description"
        placeholder="Entrez une description"
        type="textarea"
      />
      <Button :isLoading="loading" :label="buttonLabel" type="submit" />
    </form>
  </template>
  
  <script>
  import InputField from '@/components/InputField.vue';
  import Button from '@/components/Button.vue';
  
  export default {
    components: { InputField, Button },
    props: {
      buttonLabel: {
        type: String,
        default: 'Valider',
      },
      form: {
        type: Object,
        required: true,
      },
      loading: {
        type: Boolean,
        default: false,
      },
      onSubmit: {
        type: Function,
        required: true,
      },
    },
    methods: {
      handleSubmit() {
        this.onSubmit(this.form);
      },
    },
  };
  </script>
  
  <style scoped>
  /* Ajoutez des styles spécifiques ici si nécessaire */
  </style>
  