import {UtensilsCrossed, Clock, Shield, Star} from "lucide-react"
import StarBorder from "./Starborderbutton";
import '../../styles/CoverPage.css'
import alitasimg from '../../assets/main-header.png'
import newyork from '../../assets/newyork.png'
import cocteles from '../../assets/cocteles.png'

//Constante de tipo handle para el botón, servirá para redireccionarnos a alguna parte del código como el login.
//Faltan validaciones
const handleRegister = () => {
    console.log('Lol');
  };

function CoverPage() {
    return (
        <div className="cover-container">
            <div className="overlay">
                <div className="cover-content">
                    <div className="cover-icon-wrapper">
                        {/* Icono */}
                        <UtensilsCrossed className="cover-icon" />
                    </div>
                    
                    {/* Título y Subtítulo */}
                    <h1 className="cover-title">
                        Bienvenido a GoOrder
                    </h1>
                    <p className="cover-subtitle">
                        Tu restaurante favorito ahora más cerca que nunca. Ordena comida deliciosa
                        con solo unos clics y recíbela directo en tu puerta.
                    </p>

                    <StarBorder 
                            color="#F97316"
                            speed="5s"
                            thickness={1.5}
                            onClick={handleRegister}>
                        Comenzar a ordenar
                    </StarBorder>

                    <div className="cover-features">
                        <div className="cover-feature-item">
                            <Clock style={{width: '1.5rem', height: '1.5rem', marginBottom: '0.5rem'}} />
                            <span className="cover-feature-text">Entrega Rápida</span>
                        </div>
                        <div className="cover-feature-item">
                            <Shield style={{width: '1.5rem', height: '1.5rem', marginBottom: '0.5rem'}} />
                            <span className="cover-feature-text">Pago Seguro</span>
                        </div>
                        <div className="cover-feature-item">
                            <Star style={{width: '1.5rem', height: '1.5rem', marginBottom: '0.5rem'}} />
                            <span className="cover-feature-text">Calidad Top</span>
                        </div>
                    </div>
                </div>
                
            </div>

            <div className="features-section">
                <h2 className="features-title">¿Por qué elegir GoOrder?</h2>
                <div className="features-grid">
                    {/* Primer carta */}
                    <div className="feature-card">
                        <div className="feature-icon-wrapper">
                        <Clock className="feature-icon" />
                        </div>
                    
                    <h3 className="feature-card-title">Entrega rápida</h3>
                    <p className="feature-card-text">
                        Entregamos tu pedido en 30-45 minutos. Caliente y fresco, como debe ser.
                    </p>
                    </div>
                    {/* Segunda carta */}
                    <div className="feature-card">
                        <div className="feature-icon-wrapper">
                            <Star className="feature-icon" />
                        </div>
                        <h3 className="feature-card-title">Calidad Premium</h3>
                        <p className="feature-card-text">
                            Ingredientes frescos y recetas deliciosas preparadas por chefs expertos.
                        </p>
                    </div>
                    {/* Tercer carta */}
                    <div className="feature-card">
                        <div className="feature-icon-wrapper">
                            <Shield className="feature-icon" />
                        </div>
                        <h3 className="feature-card-title">Pago Seguro</h3>
                        <p className="feature-card-text">
                            Tus datos están protegidos con sistema de encriptación.
                        </p>
                    </div>

                </div>
            </div>

            <div className="platillos-section">
                <h2 className="platillos-title">Nuestros Platillos Más Populares</h2>
                <p className="platillos-subtitle">Descubre los favoritos de nuestros clientes</p>
                {/* Platillo 1*/}
                <div className="platillos-grid">
                    <div className="platillo-card">
                        <div className="platillo-image-wrapper">
                            <img 
                                src={alitasimg}
                                className="platillo-image"
                            />
                        </div>
                        <div className="platillo-content">
                            <h3 className="platillo-title" style={{textAlign:'center'}}>Alitas de pollo a la barbacoa</h3>
                            <p className="platillo-description" style={{textAlign:'center'}}>
                                Platillo tradicional que combina carne sazonada, horneada, bañadas en salsa barbacoa agridulce opcion perfecta para compartir.
                            </p>
                        </div>
                    </div>

                    {/* Platillo 2 */}
                    <div className="platillo-card">
                        <div className="platillo-image-wrapper">
                            <img 
                                src={newyork} 
                                className="platillo-image"
                            />
                        </div>
                        <div className="platillo-content">
                            <h3 className="platillo-title" style={{textAlign:'center'}}>Variedad de cortes</h3>
                            <p className="platillo-description" style={{textAlign:'center'}}>
                                Estilos de cortes únicos a escoger, al termino que guste y el sazón de la casa siempre presente para que deleite todos los sabores que brinda este platillo.
                            </p>
                        </div>
                    </div>

                    {/* Platillo 3 */}
                    <div className="platillo-card">
                        <div className="platillo-image-wrapper">
                            <img 
                                src={cocteles}  
                                className="platillo-image"
                            />
                        </div>
                        <div className="platillo-content">
                            <h3 className="platillo-title" style={{textAlign:'center'}}>Cócteles preparados</h3>
                            <p className="platillo-description" style={{textAlign:'center'}}>
                                Variedad de bebidas ideales para que la pases mucho mejor en esta experiencia y en acompañamiento con tus alimentos o simplemente para disfrutar.
                            </p>
                        </div>
                    </div>
                </div>
            </div>

            <footer className="footer">
                <div className="footer-content">
                    <div className="footer-logo-section">
                        <div className="footer-logo">
                            <UtensilsCrossed className="footer-logo-icon" />
                            <span className="footer-logo-text">GoOrder</span>
                        </div>
                        <p className="footer-tagline">La mejor comida, directo a tu puerta</p>
                    </div>
                    
                    <div className="footer-info">
                        <span className="footer-hours">Lun-Dom: 11:00 - 23:00</span>
                        <span className="footer-separator">•</span>
                        <span className="footer-phone">+1 (555) 123-4567</span>
                    </div>
                </div>
            </footer>
        </div>
    );
}

export default CoverPage;