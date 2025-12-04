import { useState } from 'react';
import { UtensilsCrossed, Mail, Lock, User, Phone } from 'lucide-react';
import '../../styles/Login.css';

function Login({ onBack, onLoginSuccess }) { 
    const [isLogin, setIsLogin] = useState(true);
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [name, setname] = useState("");
    const [telefono, settelefono] = useState("");
    const [confirmpassword, setconfirmpassword] = useState("");
    const [error, setError] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError("");

        if (!isLogin && password !== confirmpassword) {
            setError("Las contraseñas no coinciden");
            return;
        }

        const baseUrl = 'http://localhost:3000/api/auth';
        const endpoint = isLogin ? `${baseUrl}/login` : `${baseUrl}/register`;

        const bodyData = isLogin 
            ? { email, password }
            : { nombre: name, email, password, telefono };

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
                
                // AHORA SÍ FUNCIONARÁ PORQUE LO RECIBIMOS ARRIBA
                if (onLoginSuccess) {
                    onLoginSuccess();
                } else {
                    console.error("Error: onLoginSuccess no fue pasado a Login");
                }
            } else {
                alert("Registro exitoso. Ahora inicia sesión.");
                setIsLogin(true);
                setPassword("");
                setconfirmpassword("");
            }

        } catch (err) {
            console.error(err);
            setError(err.message || "Error al conectar con el servidor");
        }
    };
    //Creamos todas nuestras clases mediante la importación del archivo css
    return (
        <div className="login-container">
            <div className="login-card">
                {/* Header naranja */}
                <div className="login-header">
                    <div className="login-icon-wrapper">
                        <UtensilsCrossed className="login-icon" />
                    </div>
                    <h1 className="login-title">GoOrder</h1>
                    <p className="login-subtitle">Tu comida favorita a un clic</p>
                </div>

                {/* Tabs */}
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

                {/* Formulario */}
                <form className="login-form" onSubmit={handleSubmit}>
                    {/* Campos de REGISTRO si es falso que isLogin se accede al campo de registro */}
                    {!isLogin && (
                        <>
                            <div className="form-group">
                                <label className="form-label">Nombre Completo</label>
                                <div className="input-wrapper">
                                    <User className="input-icon" />
                                    <input
                                        type="text"
                                        placeholder="Tunombrecompleto"
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
                                        placeholder="+52 123 456 7890"
                                        className="form-input"
                                        value={telefono}
                                        onChange={(e) => settelefono(e.target.value)}
                                        required
                                    />
                                </div>
                            </div>
                        </>
                    )}

                    {/* Campos COMUNES (Email) */}
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

                    {/* Campos COMUNES (Contraseña) */}
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

                    {/* Campo adicional para REGISTRO (Confirmar contraseña) */}
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

                    <button type="submit" className="login-button">
                        {isLogin ? 'Iniciar Sesión' : 'Registrarse'}
                    </button>
                </form>

                {/* Footer */}
                <p className="login-footer">
                    Al continuar, aceptas nuestros términos y condiciones
                </p>

                {/* Botón para volver */}
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