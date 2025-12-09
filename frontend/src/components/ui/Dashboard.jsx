// src/components/ui/Dashboard.jsx
import { useState, useEffect } from 'react'; //Agregamos useEffect
import { ShoppingCart, ArrowLeft, Plus } from 'lucide-react';
import '../../styles/Dashboard.css'; 


const API_URL = 'http://localhost:3000/api/products'; 

// Lista de categorías que tenemos en la BD
const CATEGORIES = ['Todos', 'Entradas', 'Platos Fuertes', 'Bebidas', 'Postres'];

function Dashboard({ user, onLogout }) {
  // Estado para guardar todos los productos cargados de la BD
  const [products, setProducts] = useState([]);
  // Estado para saber qué categoría está seleccionada (para el filtro)
  const [selectedCategory, setSelectedCategory] = useState('Todos');
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [cartCount, setCartCount] = useState(0);
  const [loading, setLoading] = useState(true); // Nuevo estado para la carga

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
  const handleAddToCart = () => {
    setCartCount(prev => prev + 1);
    alert(`¡${selectedProduct.title} se agregó a tu orden!`);
    setSelectedProduct(null); 
  };


  if (loading) {
    return <div className="dashboard-container"><p>Cargando menú...</p></div>;
  }
  
  return (
    <div className="dashboard-container">
      
      <header className="dashboard-header">
        <div>
            <h1 className="dashboard-title">Menú GoOrder</h1>
            <p className="dashboard-subtitle">Hola, ¿qué se te antoja hoy?</p>
        </div>
        
        <div className="user-actions">
             {/* Icono de Carrito con contador (sigue igual) */}
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
          /* ================= VISTA DE DETALLE (HU-06) ================= */
          <div className="detail-view">
            {/* ... Tu código de detalle sigue igual, pero usando selectedProduct ... */}
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
                    {/* El backend te devuelve la categoría */}
                    <span className="detail-category">{selectedProduct.category}</span> 
                    <h2 className="detail-title-main">{selectedProduct.title}</h2>
                    <div className="detail-price-main">${selectedProduct.price}.00</div>
                    
                    <p className="detail-description-main">
                        {/* fullDescription viene de la BD */}
                        {selectedProduct.fullDescription} 
                    </p>

                    <button onClick={handleAddToCart} className="btn-add-order">
                        <Plus size={24} /> Agregar al Pedido
                    </button>
                </div>
            </div>
          </div>

        ) : (
          /* ================= VISTA DE CATÁLOGO (HU-05) ================= */
          <div className="catalog-view">
            {/* 1. BOTONES DE FILTRADO FUNCIONALES */}
            <div className="filters-scroll">
                {CATEGORIES.map((cat) => (
                    <button 
                        key={cat} 
                        className={`filter-btn ${selectedCategory === cat ? 'active' : ''}`}
                        // Al hacer clic, se dispara el useEffect y llama al API
                        onClick={() => setSelectedCategory(cat)} 
                    >
                        {cat}
                    </button>
                ))}
            </div>
            
            {/* Si no hay productos (Ej: el filtro no regresa nada) */}
            {products.length === 0 && (
                <p style={{ textAlign: 'center', marginTop: '50px', color: '#888' }}>
                    No se encontraron productos en la categoría "{selectedCategory}".
                </p>
            )}

            {/* 2. Grid de Productos (usamos el estado 'products') */}
            <div className="products-grid">
              {products.map((product) => (
                <div 
                    key={product.id} 
                    className="product-card"
                    // Al hacer click, guardamos el producto completo en selectedProduct
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