import CoverPage from './components/ui/CoverPage';
import { useState, useEffect } from 'react';
import Login from './components/ui/Login';
import Dashboard from './components/ui/Dashboard';

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
      <Dashboard onLogout={handleLogout} />
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
