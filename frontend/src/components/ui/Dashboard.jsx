// src/components/ui/Dashboard.jsx
import { useState } from 'react';
import { ShoppingCart, ArrowLeft, Plus } from 'lucide-react';
import '../../styles/Dashboard.css'; // <--- IMPORTACIÓN DEL NUEVO CSS

// (Tus imports de imágenes se quedan igual...)
import alitasimg from '../../assets/main-header.png';
import newyork from '../../assets/newyork.png';
import cocteles from '../../assets/cocteles.png';

// (Tus mock data se quedan igual...)
const productsData = [
    // ... tus productos ...
    {
    id: 1,
    title: "Alitas de pollo a la barbacoa",
    description: "Platillo tradicional bañado en salsa BBQ.",
    fullDescription: "Deliciosas alitas marinadas por 24 horas y bañadas en nuestra salsa secreta BBQ. Acompañadas de bastones de apio y aderezo ranch.",
    price: 180.00,
    category: "Entradas",
    image: alitasimg
  },
  {
    id: 2,
    title: "Corte New York",
    description: "Corte fino asado al carbón.",
    fullDescription: "300g de corte New York calidad Prime, asado al término de tu elección. Incluye puré de papa y espárragos salteados.",
    price: 350.00,
    category: "Platos Fuertes",
    image: newyork
  },
  {
    id: 3,
    title: "Mixología de Autor",
    description: "Bebidas refrescantes con licor premium.",
    fullDescription: "Nuestra selección especial de cocteles preparados al momento con frutas naturales y licores importados.",
    price: 95.00,
    category: "Bebidas",
    image: cocteles
  },
];

function Dashboard({ user, onLogout }) {
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [cartCount, setCartCount] = useState(0);

  const handleAddToCart = () => {
    setCartCount(prev => prev + 1);
    alert(`¡${selectedProduct.title} se agregó a tu orden!`);
    setSelectedProduct(null); 
  };

  return (
    <div className="dashboard-container">
      
      {/* --- HEADER --- */}
      <header className="dashboard-header">
        <div>
            <h1 className="dashboard-title">Menú GoOrder</h1>
            <p className="dashboard-subtitle">Hola, ¿qué se te antoja hoy?</p>
        </div>
        
        <div className="user-actions">
            {/* Carrito */}
            <div className="cart-icon-wrapper">
                <ShoppingCart size={24} color="#333" />
                {cartCount > 0 && (
                    <span className="cart-badge">{cartCount}</span>
                )}
            </div>
            
            <button onClick={onLogout} className="btn-logout">
                Cerrar Sesión
            </button>
        </div>
      </header>

      <main>
        {selectedProduct ? (
          /* ================= VISTA DE DETALLE ================= */
          <div className="detail-view">
            <button onClick={() => setSelectedProduct(null)} className="btn-back">
                <ArrowLeft size={20} /> Regresar al menú
            </button>

            <div className="detail-layout">
                <img 
                    src={selectedProduct.image} 
                    alt={selectedProduct.title} 
                    className="detail-image"
                />
                
                <div className="detail-info">
                    <span className="detail-category">{selectedProduct.category}</span>
                    <h2 className="detail-title-main">{selectedProduct.title}</h2>
                    <div className="detail-price-main">${selectedProduct.price}.00</div>
                    
                    <p className="detail-description-main">
                        {selectedProduct.fullDescription}
                    </p>

                    <button onClick={handleAddToCart} className="btn-add-order">
                        <Plus size={24} /> Agregar al Pedido
                    </button>
                </div>
            </div>
          </div>

        ) : (
          /* ================= VISTA DE CATÁLOGO ================= */
          <div className="catalog-view">
            {/* Filtros */}
            <div className="filters-scroll">
                {['Todos', 'Entradas', 'Platos Fuertes', 'Bebidas', 'Postres'].map((cat, index) => (
                    <button key={cat} className={`filter-btn ${index === 0 ? 'active' : ''}`}>
                        {cat}
                    </button>
                ))}
            </div>

            {/* Grid de Productos */}
            <div className="products-grid">
              {productsData.map((product) => (
                <div 
                    key={product.id} 
                    className="product-card"
                    onClick={() => setSelectedProduct(product)}
                >
                  <div className="card-image-container">
                    <img src={product.image} alt={product.title} className="card-image" />
                  </div>
                  
                  <div className="card-content">
                    <div className="card-header">
                        <h3 className="card-title">{product.title}</h3>
                        <span className="card-price">${product.price}</span>
                    </div>
                    <p className="card-desc">{product.description}</p>
                  </div>
                </div>
              ))}
            </div>
          </div>
        )}
      </main>
    </div>
  );
}

export default Dashboard;