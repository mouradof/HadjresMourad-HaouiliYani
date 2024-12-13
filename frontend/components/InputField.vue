<template>
  <div class="input-container">
    <label :for="label" class="input-label">{{ label }}</label>
    <template v-if="type === 'textarea'">
      <textarea
        :id="label"
        :placeholder="placeholder"
        :value="modelValue"
        @input="$emit('update:modelValue', $event.target.value)"
        class="input-field textarea"
      ></textarea>
    </template>
    <template v-else>
      <input
        :type="type"
        :id="label"
        :placeholder="placeholder"
        :value="modelValue"
        @input="$emit('update:modelValue', $event.target.value)"
        class="input-field"
      />
    </template>
  </div>
</template>

<script setup>
const props = defineProps({
  label: {
    type: String,
    required: true,
  },
  placeholder: {
    type: String,
    default: '',
  },
  type: {
    type: String,
    default: 'text', // Peut être 'text', 'password', 'number', 'textarea', etc.
  },
  modelValue: {
    type: [String, Number],
    default: '',
  },
});
const emit = defineEmits(['update:modelValue']);
</script>

<style scoped>
.input-container {
  margin-bottom: 20px;
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.input-label {
  font-size: 0.9rem;
  font-weight: bold;
  color: #333333;
  margin-bottom: 5px;
}

.input-field {
  padding: 10px 15px;
  font-size: 1rem;
  border: 2px solid #ddd;
  border-radius: 8px;
  transition: border-color 0.3s, box-shadow 0.3s;
}

.input-field:focus {
  border-color: #2575fc;
  box-shadow: 0 0 8px rgba(37, 117, 252, 0.3);
  outline: none;
}

.input-field::placeholder {
  color: #aaa;
  font-style: italic;
}

.textarea {
  resize: vertical;
  min-height: 100px;
}
</style>
