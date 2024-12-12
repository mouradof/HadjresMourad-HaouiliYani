<template>
    <button
      :type="type"
      class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline w-full"
    >
      Valider
    </button>
  </template>
  
  <script setup>
  defineProps(['text', 'type']);
  </script>
  