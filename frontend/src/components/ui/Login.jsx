import { useState } from 'react';
import { UtensilsCrossed, Mail, Lock, User, Phone, MapPin } from 'lucide-react';
import '../../styles/Login.css';

const getBaseUrl = () => {
    const { hostname } = window.location;
    return `http://${hostname}:3000`;
};

const BASE_API_URL = `${getBaseUrl()}/api/auth`;

function Login({ onBack, onLoginSuccess }) { 
    const [isLogin, setIsLogin] = useState(true);
    
    // Estados existentes
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [name, setname] = useState("");
    const [telefono, settelefono] = useState("");
    const [confirmpassword, setconfirmpassword] = useState("");
    
    // 2. NUEVOS ESTADOS para la dirección
    const [calle, setCalle] = useState("");
    const [numero, setNumero] = useState("");
    const [colonia, setColonia] = useState("");
    
    const [error, setError] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError("");

        if (!isLogin && password !== confirmpassword) {
            setError("Las contraseñas no coinciden");
            return;
        }

        const endpoint = isLogin ? `${BASE_API_URL}/login` : `${BASE_API_URL}/register`;

        // ACTUALIZAMOS EL BODY DEL REGISTRO
        // Enviamos la dirección desglosada para que se guarde en la BD
        const bodyData = isLogin 
            ? { email, password }
            : { 
                nombre: name, 
                email, 
                password, 
                telefono,
                calle,
                numero_exterior: numero,
                colonia
              };

        try {
            const response = await fetch(endpoint, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(bodyData),
            });

            const data = await response.json();

            if (!response.ok) {
                throw new Error(data.message || 'Error en la solicitud');
            }

            if (isLogin) {
                localStorage.setItem('token', data.token);
                localStorage.setItem('user', JSON.stringify(data.user));
                
                if (onLoginSuccess) {
                    onLoginSuccess();
                } else {
                    console.error("Error: onLoginSuccess no fue pasado a Login");
                }
            } else {
                alert("Registro exitoso. Ahora inicia sesión.");
                // Limpiamos formulario y cambiamos a Login
                setIsLogin(true);
                setPassword("");
                setconfirmpassword("");
            }

        } catch (err) {
            console.error(err);
            setError(err.message || "Error al conectar con el servidor");
        }
    };

    return (
        <div className="login-container">
            <div className="login-card">
                <div className="login-header">
                    <div className="login-icon-wrapper">
                        <UtensilsCrossed className="login-icon" />
                    </div>
                    <h1 className="login-title">GoOrder</h1>
                    <p className="login-subtitle">Tu comida favorita a un clic</p>
                </div>

                <div className="login-tabs">
                    <button
                        className={`login-tab ${isLogin ? 'active' : ''}`}
                        onClick={() => setIsLogin(true)}
                    >
                        Iniciar Sesión
                    </button>
                    <button
                        className={`login-tab ${!isLogin ? 'active' : ''}`}
                        onClick={() => setIsLogin(false)}
                    >
                        Registrarse
                    </button>
                </div>

                <form className="login-form" onSubmit={handleSubmit}>
                    
                    {/* SECCIÓN DE REGISTRO */}
                    {!isLogin && (
                        <>
                            <div className="form-group">
                                <label className="form-label">Nombre Completo</label>
                                <div className="input-wrapper">
                                    <User className="input-icon" />
                                    <input
                                        type="text"
                                        placeholder="nombre_completo"
                                        className="form-input"
                                        value={name}
                                        onChange={(e) => setname(e.target.value)}
                                        required
                                    />
                                </div>
                            </div>

                            <div className="form-group">
                                <label className="form-label">Teléfono</label>
                                <div className="input-wrapper">
                                    <Phone className="input-icon" />
                                    <input
                                        type="tel"
                                        placeholder="Ej. 229 123 4567"
                                        className="form-input"
                                        value={telefono}
                                        onChange={(e) => settelefono(e.target.value)}
                                        required
                                    />
                                </div>
                            </div>

                            {/* 4. NUEVOS CAMPOS DE DIRECCIÓN */}
                            <div className="form-group">
                                <label className="form-label">Calle</label>
                                <div className="input-wrapper">
                                    <MapPin className="input-icon" />
                                    <input
                                        type="text"
                                        placeholder="Av. 20 de Noviembre"
                                        className="form-input"
                                        value={calle}
                                        onChange={(e) => setCalle(e.target.value)}
                                        required
                                    />
                                </div>
                            </div>

                            <div style={{ display: 'flex', gap: '10px' }}>
                                <div className="form-group" style={{ flex: 1 }}>
                                    <label className="form-label">Número Ext.</label>
                                    <div className="input-wrapper">
                                        <MapPin className="input-icon" />
                                        <input
                                            type="text"
                                            placeholder="452"
                                            className="form-input"
                                            value={numero}
                                            onChange={(e) => setNumero(e.target.value)}
                                            required
                                        />
                                    </div>
                                </div>
                                <div className="form-group" style={{ flex: 1 }}>
                                    <label className="form-label">Colonia</label>
                                    <div className="input-wrapper">
                                        <MapPin className="input-icon" />
                                        <input
                                            type="text"
                                            placeholder="Centro"
                                            className="form-input"
                                            value={colonia}
                                            onChange={(e) => setColonia(e.target.value)}
                                            required
                                        />
                                    </div>
                                </div>
                            </div>
                        </>
                    )}

                    {/* CAMPOS COMUNES (Email y Pass) */}
                    <div className="form-group">
                        <label className="form-label">Correo Electrónico</label>
                        <div className="input-wrapper">
                            <Mail className="input-icon" />
                            <input
                                type="email"
                                placeholder="tu@email.com"
                                className="form-input"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                                required
                            />
                        </div>
                    </div>

                    <div className="form-group">
                        <label className="form-label">Contraseña</label>
                        <div className="input-wrapper">
                            <Lock className="input-icon" />
                            <input
                                type="password"
                                placeholder="••••••••"
                                className="form-input"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                required
                            />
                        </div>
                    </div>

                    {!isLogin && (
                        <div className="form-group">
                            <label className="form-label">Confirmar Contraseña</label>
                            <div className="input-wrapper">
                                <Lock className="input-icon" />
                                <input
                                    type="password"
                                    placeholder="••••••••"
                                    className="form-input"
                                    value={confirmpassword}
                                    onChange={(e) => setconfirmpassword(e.target.value)}
                                    required
                                />
                            </div>
                        </div>
                    )}

                    {error && <p className="error-message" style={{color: 'red', textAlign: 'center'}}>{error}</p>}

                    <button type="submit" className="login-button">
                        {isLogin ? 'Iniciar Sesión' : 'Registrarse'}
                    </button>
                </form>

                <p className="login-footer">
                    Al continuar, aceptas nuestros términos y condiciones
                </p>

                {onBack && (
                    <button onClick={onBack} className="back-button">
                        ← Volver al inicio
                    </button>
                )}
            </div>
        </div>
    );
}

export default Login;