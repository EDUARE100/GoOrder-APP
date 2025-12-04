import CoverPage from './components/ui/CoverPage';
import { useState, useEffect } from 'react';
import Login from './components/ui/Login';

function App() {

  const [showLogin, setShowLogin] = useState(false);
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  // Verificar si ya existe sesión al cargar la página
  useEffect(() => {
    const token = localStorage.getItem('token');
    if (token) {
      setIsAuthenticated(true);
    }
  }, []);

  const handleLoginClick = () => {
    setShowLogin(true);
  };

  const handleLoginSuccess = () => {
    setIsAuthenticated(true);
    setShowLogin(false);
  };

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    setIsAuthenticated(false);
    setShowLogin(false);
  };

if (isAuthenticated) {
    return (
      <div style={{ padding: '20px' }}>
        <h1>Bienvenido a GoOrder</h1>
        {/* Aquí cargarás tu componente MENU o DASHBOARD */}
        <p>Has iniciado sesión correctamente.</p>
        <button onClick={handleLogout} style={{ marginTop: '20px' }}>
          Cerrar Sesión
        </button>
      </div>
    );
  }


return (
  <div style={{margin: 0, padding: 0}}>
      {!showLogin ? (
        <CoverPage
          onLoginClick={handleLoginClick}
          showAuthbuttons={true}
        />
      ) : (
        <Login onBack={() => setShowLogin(false)}
          onLoginSuccess={handleLoginSuccess}
        />
      )}
    </div>
  )
}


export default App
