import { createApp } from 'vue';
import App from './App.vue';
import router from './router';

const app = createApp(App);

app.use(router);

app.mount('#app');

// Vérification lors du chargement initial
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token');
  if (to.meta.requiresAuth && !token) {
    console.log('Accès refusé, redirection vers /login...');
    next('/login');
  } else {
    next();
  }
});

// Vérifie l'état de connexion sur navigation arrière/avant
window.addEventListener('popstate', () => {
  const token = localStorage.getItem('token');
  if (!token) {
    console.log('Navigation arrière détectée sans token, redirection vers /login');
    router.push('/login');
  }
});
