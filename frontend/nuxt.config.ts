export default defineNuxtConfig({
  devtools: { enabled: true },
  modules: [],
  router: {
    middleware: ['auth'],
  },
  css: ['@/assets/css/main.css'],
  build: {
    postcss: {
      plugins: {
        tailwindcss: {},
        autoprefixer: {},
      },
    },
  },
});