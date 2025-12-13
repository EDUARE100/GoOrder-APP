import { useState, useEffect } from 'react';
import { ArrowLeft, Plus, MapPin, CreditCard } from 'lucide-react';
import '../../styles/Dashboard.css'; 
import Cart from './Cart';

const getBaseUrl = () => {
    const currentHost = window.location.hostname; 
    return (currentHost === 'localhost' || currentHost === '127.0.0.1') 
        ? 'http://localhost:3000' 
        : 'http://192.168.100.63:3000';
};

const BASE_URL = getBaseUrl(); 
const API_URL = BASE_URL + '/api/products';
const ORDERS_URL = BASE_URL + '/api/orders';

const CATEGORIES = ['Todos', 'Entradas', 'Platos Fuertes', 'Bebidas', 'Postres'];

function Dashboard({ user, onLogout }) {
  const [products, setProducts] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState('Todos');
  const [loading, setLoading] = useState(true);

  const [cartItems, setCartItems] = useState([]);
  const [currentView, setCurrentView] = useState('catalog'); // 'catalog', 'detail', 'checkout'
  const [selectedProduct, setSelectedProduct] = useState(null);

  // Estado para el formulario de pago
  const [shippingData, setShippingData] = useState({
      address: '', card: '', expiry: '', cvv: ''
  });

  // Carga de productos
  useEffect(() => {
    setLoading(true);
    let url = API_URL;
    if (selectedCategory && selectedCategory !== 'Todos') url += `?category=${selectedCategory}`; 
    fetch(url)
      .then(res => res.ok ? res.json() : [])
      .then(data => { setProducts(data); setLoading(false); })
      .catch(err => { console.error(err); setLoading(false); });
  }, [selectedCategory]); 

  // Carrito y Navegación
  const handleAddToCart = () => {
      setCartItems([...cartItems, selectedProduct]);
      setSelectedProduct(null);
      setCurrentView('catalog');
  };

  const handleRemoveFromCart = (index) => setCartItems(cartItems.filter((_, i) => i !== index));
  const handleProductClick = (p) => { setSelectedProduct(p); setCurrentView('detail'); };
  const handleBackToMenu = () => { setSelectedProduct(null); setCurrentView('catalog'); };
  
  const handleGoToCheckout = () => {
      if(cartItems.length === 0) { alert("Tu carrito está vacío"); return; }
      
      // Aquí concatenamos los 3 campos del usuario para el envío
      // Si el usuario tiene dirección guardada, la usamos por defecto
      let defaultAddress = '';
      if (user && user.calle) {
          defaultAddress = `${user.calle} #${user.numero_exterior}, Col. ${user.colonia}`;
      }

      // Actualizamos el formulario con esa dirección
      setShippingData(prev => ({
          ...prev, 
          address: defaultAddress 
      }));

      setCurrentView('checkout');
  };

  const handleInputChange = (e) => setShippingData({...shippingData, [e.target.name]: e.target.value});

  // PROCESAR PAGO
  const handlePlaceOrder = async (e) => {
      e.preventDefault();
      const total = cartItems.reduce((acc, item) => acc + item.price, 0);

      try {
          const response = await fetch(ORDERS_URL, {
              method: 'POST',
              headers: { 'Content-Type': 'application/json' },
              body: JSON.stringify({
                  userId: user.id,
                  items: cartItems,
                  total: total,
                  // Se envía la dirección que esté en el input (ya sea la automática o si el usuario la editó)
                  address: shippingData.address 
              })
          });

          const data = await response.json();
          if (response.ok) {
              alert(`¡Pedido Exitoso! ID: ${data.orderId}`);
              setCartItems([]);
              setShippingData({ address: '', card: '', expiry: '', cvv: '' });
              setCurrentView('catalog');
          } else {
              alert('Error: ' + data.message);
          }
      } catch (error) {
          console.error("Error pago:", error);
          alert("Error de conexión");
      }
  };

  if (loading) return <div className="dashboard-container"><p>Cargando...</p></div>;
  
  return (
    <div className="dashboard-container">
      <header className="dashboard-header">
        <div>
            <h1 className="dashboard-title">Menú GoOrder</h1>
            <p className="dashboard-subtitle">Hola, {user.nombre}</p>
        </div>
        <div className="user-actions">
            <Cart 
                cartItems={cartItems} 
                onRemoveItem={handleRemoveFromCart}
                onGoToCheckout={handleGoToCheckout} 
            />
            <button onClick={onLogout} className="btn-logout">Salir</button>
        </div>
      </header>

      <main>
        {/* VISTA 1: DETALLE DE PRODUCTO */}
        {currentView === 'detail' && selectedProduct && (
          <div className="detail-view">
            <button onClick={handleBackToMenu} className="btn-back"><ArrowLeft size={20}/> Volver</button>
            <div className="detail-layout">
                <img src={selectedProduct.image} alt={selectedProduct.title} className="detail-image"/>
                <div className="detail-info">
                    <span className="detail-category">{selectedProduct.category}</span> 
                    <h2>{selectedProduct.title}</h2>
                    <div className="detail-price-main">${selectedProduct.price}.00</div>
                    <p>{selectedProduct.fullDescription}</p>
                    <button onClick={handleAddToCart} className="btn-add-order"><Plus size={24}/> Agregar</button>
                </div>
            </div>
          </div>
        )}

        {/* VISTA 2: CHECKOUT (PAGINA NUEVA) */}
        {currentView === 'checkout' && (
            <div className="checkout-view">
                <button onClick={handleBackToMenu} className="btn-back">
                    <ArrowLeft size={20} /> Seguir comprando
                </button>
                <h2 className="section-title">Finalizar Compra</h2>
                
                <div className="checkout-grid">
                    {/* COLUMNA IZQUIERDA: RESUMEN */}
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
                            <strong>${cartItems.reduce((acc, item) => acc + item.price, 0)}.00</strong>
                        </div>
                    </div>

                    {/* COLUMNA DERECHA: FORMULARIO */}
                    <div className="checkout-form-container">
                        <h3>Datos de Envío y Pago</h3>
                        <form className="checkout-form" onSubmit={handlePlaceOrder}>
                            <div className="form-group">
                                <label><MapPin size={16}/> Dirección de entrega</label>
                                {/* Este input aparecerá pre-llenado con los datos del perfil */}
                                <input 
                                    name="address" 
                                    placeholder="Calle, Número, Colonia" 
                                    required 
                                    value={shippingData.address} 
                                    onChange={handleInputChange}
                                />
                            </div>
                            <div className="form-group">
                                <label><CreditCard size={16}/> Número de Tarjeta</label>
                                <input name="card" placeholder="0000 0000 0000 0000" maxLength="16" required 
                                    value={shippingData.card} onChange={handleInputChange}/>
                            </div>
                            <div className="form-row">
                                <div className="form-group">
                                    <label>Expira (MM/AA)</label>
                                    <input name="expiry" placeholder="12/25" maxLength="5" required 
                                        value={shippingData.expiry} onChange={handleInputChange}/>
                                </div>
                                <div className="form-group">
                                    <label>CVV</label>
                                    <input name="cvv" type="password" placeholder="123" maxLength="3" required 
                                        value={shippingData.cvv} onChange={handleInputChange}/>
                                </div>
                            </div>
                            <button type="submit" className="btn-pay-now">
                                Pagar Pedido
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        )}

        {/* VISTA 3: CATÁLOGO (DEFAULT) */}
        {currentView === 'catalog' && (
          <div className="catalog-view">
            <div className="filters-scroll">
                {CATEGORIES.map(cat => (
                    <button key={cat} className={`filter-btn ${selectedCategory === cat ? 'active' : ''}`} onClick={() => setSelectedCategory(cat)}>
                        {cat}
                    </button>
                ))}
            </div>
            <div className="products-grid">
              {products.map(p => (
                <div key={p.id} className="product-card" onClick={() => handleProductClick(p)}>
                  <div className="card-image-container"><img src={p.image} alt={p.title} className="card-image"/></div>
                  <div className="card-content">
                    <div className="card-header"><h3>{p.title}</h3><span>${p.price}</span></div>
                    <p className="card-desc">{p.description}</p>
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