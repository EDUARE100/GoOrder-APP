
import { useState, useEffect } from 'react'; //Agregamos useEffect
import {  ArrowLeft, Plus, CreditCard, MapPin } from 'lucide-react';
import '../../styles/Dashboard.css'; 
import Cart from './Cart';

const getBaseUrl = () => {
    const currentHost = window.location.hostname; 
    
    // Si estamos en localhost
    if (currentHost === 'localhost' || currentHost === '127.0.0.1') {
        return 'http://localhost:3000';
    } 
    // Si estamos en cualquier otra IP (celular en misma red)
    else {
        return 'http://192.168.100.63:3000';
    }
};

const BASE_URL = getBaseUrl(); 
const API_URL = BASE_URL + '/api/products';
// ==========================================

// Lista de categorías que tenemos en la BD
const CATEGORIES = ['Todos', 'Entradas', 'Platos Fuertes', 'Bebidas', 'Postres'];

function Dashboard({ user, onLogout }) {
  // Estado para guardar todos los productos cargados de la BD
  const [products, setProducts] = useState([]);
  // Estado para saber qué categoría está seleccionada (para el filtro)
  const [selectedCategory, setSelectedCategory] = useState('Todos');
  const [loading, setLoading] = useState(true); // Nuevo estado para la carga

  const [cartItems, setCartItems] = useState([]); // Array de productos en carrito
  const [currentView, setCurrentView] = useState('catalog'); // 'catalog', 'detail', 'checkout'
  const [selectedProduct, setSelectedProduct] = useState(null);

  // LÓGICA DE CARGA DE DATOS (useEffect) - HU-05
  useEffect(() => {
    setLoading(true);
    let url = API_URL;

    // 1. Lógica para construir la URL con el filtro
    if (selectedCategory && selectedCategory !== 'Todos') {
      // Si hay filtro, agregamos el parámetro a la URL
      url += `?category=${selectedCategory}`; 
    }

    // 2. Ejecutamos la petición al Backend
    fetch(url)
      .then(response => {
        if (!response.ok) {
          throw new Error('Error al obtener el menú: ' + response.status);
        }
        return response.json();
      })
      .then(data => {
        setProducts(data); // Guardamos los datos REALES de la BD
        setLoading(false);
      })
      .catch(error => {
        console.error("Error al cargar productos:", error);
        setLoading(false);
        // Podrías mostrar un mensaje de error en pantalla si quieres
      });

  // 3. El [selectedCategory] en el array de dependencias 
  // hace que esta función se ejecute CADA VEZ que el usuario cambia de filtro.
  }, [selectedCategory]); 

  // Función de Agregar al Carrito (se queda igual por ahora)
      // 1. Agregar producto al array del carrito
    const handleAddToCart = () => {
      setCartItems([...cartItems, selectedProduct]);
      alert(`¡${selectedProduct.title} se agregó a tu orden!`);
      setSelectedProduct(null);
      setCurrentView('catalog'); // Regresa al catálogo
    };

    // 2. Eliminar producto específico (por índice para permitir duplicados)
    const handleRemoveFromCart = (indexToRemove) => {
      setCartItems(cartItems.filter((_, index) => index !== indexToRemove));
    };

    // 3. Calcular total, este es visual y para pasarlo al checkout, el carttotal lo calcula también en Cart.jsx
    const cartTotal = cartItems.reduce((total, item) => total + item.price, 0);

    // 4. Ir a pagar
    const handleGoToCheckout = () => {
      setShowCart(false); // Ocultar el dropdown
      setCurrentView('checkout'); // Cambiar vista principal
      setSelectedProduct(null); // Asegurar que no se vea el detalle
    };

    // 5. Manejo de selección de producto
    const handleProductClick = (product) => {
      setSelectedProduct(product);
      setCurrentView('detail');
    };

    // 6. Volver al menú
    const handleBackToMenu = () => {
      setSelectedProduct(null);
      setCurrentView('catalog');
  };


  if (loading) {
    return <div className="dashboard-container"><p>Cargando menú...</p></div>;
  }
  
  return (
    <div className="dashboard-container">
      
      {/* HEADER */}
      <header className="dashboard-header">
        <div>
            <h1 className="dashboard-title">Menú GoOrder</h1>
            <p className="dashboard-subtitle">Hola, ¿qué se te antoja hoy?</p>
        </div>
        
        <div className="user-actions">
            
            {/* AQUÍ USAMOS TU NUEVO COMPONENTE CART */}
            <Cart 
                cartItems={cartItems} 
                onRemoveItem={handleRemoveFromCart}
                onGoToCheckout={handleGoToCheckout}
            />
            
            <button onClick={onLogout} className="btn-logout">
                Cerrar Sesión
            </button>
        </div>
      </header>

      <main>
        {/* VISTA DE DETALLE */}
        {currentView === 'detail' && selectedProduct && (
          <div className="detail-view">
            <button onClick={handleBackToMenu} className="btn-back">
                <ArrowLeft size={20} /> Regresar al menú
            </button>
            <div className="detail-layout">
                <img src={selectedProduct.image} alt={selectedProduct.title} className="detail-image"/>
                <div className="detail-info">
                    <span className="detail-category">{selectedProduct.category}</span> 
                    <h2 className="detail-title-main">{selectedProduct.title}</h2>
                    <div className="detail-price-main">${selectedProduct.price}.00</div>
                    <p className="detail-description-main">{selectedProduct.fullDescription}</p>
                    <button onClick={handleAddToCart} className="btn-add-order">
                        <Plus size={24} /> Agregar al Pedido
                    </button>
                </div>
            </div>
          </div>
        )}

        {/* VISTA DE CHECKOUT (PAGO) */}
        {currentView === 'checkout' && (
            <div className="checkout-view">
                <button onClick={handleBackToMenu} className="btn-back">
                    <ArrowLeft size={20} /> Seguir comprando
                </button>
                <h2 className="section-title">Finalizar Compra</h2>
                <div className="checkout-grid">
                    <div className="checkout-summary">
                        <h3>Resumen del Pedido</h3>
                        {cartItems.map((item, idx) => (
                            <div key={idx} className="checkout-item">
                                <span>{item.title}</span>
                                <span>${item.price}</span>
                            </div>
                        ))}
                        <div className="checkout-total-row">
                            <strong>Total a pagar:</strong>
                            <strong>${cartTotal}.00</strong>
                        </div>
                    </div>
                    <div className="checkout-form-container">
                        <h3>Datos de Envío y Pago</h3>
                        <form className="checkout-form" onSubmit={(e) => { e.preventDefault(); alert("¡Compra realizada!"); }}>
                            <div className="form-group">
                                <label><MapPin size={16}/> Dirección de entrega</label>
                                <input type="text" placeholder="Calle, Número, Colonia" required />
                            </div>
                            <div className="form-group">
                                <label><CreditCard size={16}/> Número de Tarjeta</label>
                                <input type="text" placeholder="0000 0000 0000 0000" maxLength="16" required />
                            </div>
                            <div className="form-row">
                                <div className="form-group">
                                    <label>Expira (MM/AA)</label>
                                    <input type="text" placeholder="12/25" maxLength="5" required />
                                </div>
                                <div className="form-group">
                                    <label>CVV</label>
                                    <input type="password" placeholder="123" maxLength="3" required />
                                </div>
                            </div>
                            <button type="submit" className="btn-pay-now">
                                Pagar ${cartTotal}.00
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        )}

        {/* VISTA DE CATÁLOGO */}
        {currentView === 'catalog' && (
          <div className="catalog-view">
            <div className="filters-scroll">
                {CATEGORIES.map((cat) => (
                    <button 
                        key={cat} 
                        className={`filter-btn ${selectedCategory === cat ? 'active' : ''}`}
                        onClick={() => setSelectedCategory(cat)} 
                    >
                        {cat}
                    </button>
                ))}
            </div>
            
            {products.length === 0 && (
                <p className="no-products-msg">No se encontraron productos.</p>
            )}

            <div className="products-grid">
              {products.map((product) => (
                <div key={product.id} className="product-card" onClick={() => handleProductClick(product)}>
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