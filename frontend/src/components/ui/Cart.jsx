import { useState } from 'react';
import { ShoppingCart, Trash2 } from 'lucide-react';

function Cart({ cartItems, onRemoveItem, onGoToCheckout }) {
  const [showCart, setShowCart] = useState(false);

  const cartTotal = cartItems.reduce((total, item) => total + item.price, 0);

  return (
    <div 
        className="cart-container"
        onMouseEnter={() => setShowCart(true)}
        onMouseLeave={() => setShowCart(false)}
    >
        <div className="cart-icon-wrapper">
            <ShoppingCart size={24} color="#333" />
            {cartItems.length > 0 && (
                <span className="cart-badge">{cartItems.length}</span>
            )}
        </div>

        {showCart && (
            <div className="cart-dropdown">
                <h4>Tu Pedido</h4>
                {cartItems.length === 0 ? (
                    <p className="empty-cart-msg">El carrito está vacío</p>
                ) : (
                    <>
                        <div className="cart-items-list">
                            {cartItems.map((item, index) => (
                                <div key={index} className="cart-item-row">
                                    <img src={item.image} alt="thumb" className="cart-thumb"/>
                                    <div className="cart-item-info">
                                        <span className="cart-item-name">{item.title}</span>
                                        <span className="cart-item-price">${item.price}</span>
                                    </div>
                                    <button 
                                        onClick={(e) => { e.stopPropagation(); onRemoveItem(index); }} 
                                        className="btn-remove-item"
                                    >
                                        <Trash2 size={16} />
                                    </button>
                                </div>
                            ))}
                        </div>
                        
                        <div className="cart-footer">
                            <div className="cart-total">
                                <span>Total:</span>
                                <span>${cartTotal}.00</span>
                            </div>
                            <button 
                                onClick={() => {
                                    setShowCart(false); // Cerramos el menú
                                    onGoToCheckout();   // ¡Navegamos a la pagina nueva!
                                }} 
                                className="btn-checkout-mini"
                            >
                                Comprar Ahora
                            </button>
                        </div>
                    </>
                )}
            </div>
        )}
    </div>
  );
}

export default Cart;