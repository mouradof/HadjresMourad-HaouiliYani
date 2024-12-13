export default defineNuxtRouteMiddleware((to, from) => {
  const isLoggedIn = !!localStorage.getItem('loggedIn');
  const protectedRoutes = ['/', '/events', '/events/create', '/events/edit'];
  const guestRoutes = ['/login', '/register'];

  const requiresAuth = protectedRoutes.some(route => to.path.startsWith(route));
  const isGuestRoute = guestRoutes.includes(to.path);

  if (requiresAuth && !isLoggedIn) {
    return navigateTo('/login');
  }

  if (isGuestRoute && isLoggedIn) {
    return navigateTo('/');
  }
});