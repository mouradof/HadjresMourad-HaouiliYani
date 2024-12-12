import Vue from 'vue';
import Router from 'vue-router';
import Login from '@/pages/Login.vue';
import Home from '@/pages/Home.vue';

Vue.use(Router);

const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login,
    },
    {
      path: '/',
      name: 'Home',
      component: Home,
      meta: { requiresAuth: true },
    },
  ],
});

router.beforeEach((to, from, next) => {
  const isAuthenticated = !!localStorage.getItem('token');
  if (to.matched.some((record) => record.meta.requiresAuth) && !isAuthenticated) {
    console.log('Accès refusé, redirection vers la page de connexion...');
    next('/login');
  } else {
    next();
  }
});

export default router;
