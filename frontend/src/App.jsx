import CoverPage from './components/ui/CoverPage';
import { useState, useEffect } from 'react';
import Login from './components/ui/Login';
import Dashboard from './components/ui/Dashboard';

function App() {
  const [showLogin, setShowLogin] = useState(false);
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [user, setUser] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem('token');
    const storedUser = localStorage.getItem('user');

    if (token && storedUser) {
      try {
        // Verificamos que no sea la palabra "undefined"
        if (storedUser !== "undefined") {
          const parsedUser = JSON.parse(storedUser);
          setIsAuthenticated(true);
          setUser(parsedUser);
        }
      } catch (error) {
        console.error("Error al leer usuario:", error);
        // Si hay error, limpiamos para que no se bloquee la app
        localStorage.removeItem('user');
        localStorage.removeItem('token');
      }
    }
  }, []);

  const handleLoginClick = () => {
    setShowLogin(true);
  };

  // FUNCIÓN PARA MANEJAR EL ÉXITO
  // Como Login.jsx ya guardó en localStorage, aquí solo actualizamos el estado
  const handleLoginSuccess = () => {
    const storedUser = localStorage.getItem('user');
    if (storedUser) {
        const parsedUser = JSON.parse(storedUser);
        setUser(parsedUser);
        setIsAuthenticated(true);
        setShowLogin(false); // Ocultamos el login
    }
  };

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    setIsAuthenticated(false);
    setUser(null);
    setShowLogin(false);
  };

  // RENDERIZADO CONDICIONAL
  if (isAuthenticated && user) {
    return <Dashboard user={user} onLogout={handleLogout} />;
  }

  return (
    <div style={{margin: 0, padding: 0}}>
      {!showLogin ? (
        <CoverPage
          onLoginClick={handleLoginClick}
          showAuthbuttons={true}
        />
      ) : (
        <Login 

          onBack={() => setShowLogin(false)} 
          onLoginSuccess={handleLoginSuccess} 
        />
      )}
    </div>
  );
}

export default App;