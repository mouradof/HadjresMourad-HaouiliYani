import Vue from 'vue';
import Router from 'vue-router';
import Login from '@/pages/Login.vue';
import Register from '@/pages/Register.vue';
import Home from '@/pages/index.vue';
import Events from '@/pages/events/index.vue';
import CreateEvent from '@/pages/events/CreateEvent.vue';
import EditEvent from '@/pages/events/EditEvent.vue';

Vue.use(Router);

const router = new Router({
  mode: 'history',
  routes: [
    { path: '/login', name: 'Login', component: Login, meta: { guest: true } },
    { path: '/register', name: 'Register', component: Register, meta: { guest: true } },
    { path: '/', name: 'Home', component: Home, meta: { requiresAuth: true } },
    { path: '/events', name: 'Events', component: Events, meta: { requiresAuth: true } },
    { path: '/events/create', name: 'CreateEvent', component: CreateEvent, meta: { requiresAuth: true } },
    { path: '/events/edit/:id', name: 'EditEvent', component: EditEvent, props: true, meta: { requiresAuth: true } },
  ],
});

router.beforeEach((to, from, next) => {
  const isLoggedIn = !!localStorage.getItem('loggedIn');

  if (to.matched.some(record => record.meta.requiresAuth) && !isLoggedIn) {
    next('/login');
  } else if (to.matched.some(record => record.meta.guest) && isLoggedIn) {
    next('/');
  } else {
    next();
  }
});

export default router;