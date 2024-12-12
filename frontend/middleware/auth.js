export default function ({ redirect }) {
  const token = localStorage.getItem('authToken');
  console.log('Vérification du token:', token);

  if (!token) {
    console.log('Aucun token trouvé. Redirection vers la page de connexion...');
    return redirect('/login');
  }

  console.log('Token valide. Accès autorisé.');
}
