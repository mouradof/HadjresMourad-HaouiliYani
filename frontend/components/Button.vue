<template>
  <button
    :type="type"
    class="button"
    :disabled="isLoading"
    @click="$emit('click')"
  >
    <span v-if="isLoading" class="spinner"></span>
    {{ label }}
  </button>
</template>

<script setup>
const props = defineProps({
  label: {
    type: String,
    required: true,
  },
  type: {
    type: String,
    default: 'button', // Peut être 'button', 'submit', ou 'reset'
  },
  isLoading: {
    type: Boolean,
    default: false,
  },
});
const emit = defineEmits(['click']);
</script>

<style scoped>
.button {
  background-color: #3498db;
  color: white;
  border: none;
  padding: 10px 15px;
  font-size: 16px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  position: relative;
}

.button:hover {
  background-color: #2980b9;
}

.button:disabled {
  background-color: #95a5a6;
  cursor: not-allowed;
}

.spinner {
  border: 2px solid #f3f3f3;
  border-top: 2px solid #fff;
  border-radius: 50%;
  width: 14px;
  height: 14px;
  animation: spin 1s linear infinite;
  display: inline-block;
  margin-right: 8px;
  vertical-align: middle;
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
</style>
