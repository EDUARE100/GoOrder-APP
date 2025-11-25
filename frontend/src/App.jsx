import CoverPage from './components/ui/CoverPage';
import { useState } from 'react';
import Login from './components/ui/Login';

function App() {

  const [showLogin, setShowLogin] = useState(false);

  const handleLogin = () => {
    setShowLogin(true);
  };
return (
  <div style={{margin: 0, padding: 0}}>
      {!showLogin ? (
        <CoverPage
          onLoginClick={handleLogin}
          showAuthbuttons={true}
        />
      ) : (
        <Login onBack={() => setShowLogin(false)}/>
      )}
    </div>
  )
}


export default App
