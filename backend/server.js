const express = require('express');
const cors = require('cors');
const bcrypt = require('bcryptjs'); 
const jwt = require('jsonwebtoken'); 
require('dotenv').config(); 

const db = require('./src/config/database');

const app = express();

// --- MIDDLEWARES ---
app.use(cors({
    origin: ['http://localhost:5173', 'http://192.168.100.63:5173', 'http://192.168.100.63:3000' , 'http://172.20.10.2:3000'],
    credentials: true
}));
app.use(express.json());

// 1. RUTA DE REGISTRO (POST) - ACTUALIZADA
// React envía: { nombre, email, password, telefono, calle, numero_exterior, colonia }
app.post('/api/auth/register', async (req, res) => {
    // 1. Recibimos los nuevos campos de dirección
    const { nombre, email, password, telefono, calle, numero_exterior, colonia } = req.body;

    // Validamos ue no falten datos importantes
    if (!nombre || !email || !password || !telefono || !calle || !numero_exterior || !colonia) {
        return res.status(400).json({ message: 'Todos los campos son obligatorios' });
    }

    try {
        const [userExists] = await db.query('SELECT email FROM tb_usuario_web WHERE email = ?', [email]);
        
        if (userExists.length > 0) {
            return res.status(409).json({ message: 'El correo electrónico ya está registrado' });
        }

        const salt = await bcrypt.genSalt(10);
        const hashedPassword = await bcrypt.hash(password, salt);

        // 2. Insertamos la dirección en la BD
        await db.query(
            'INSERT INTO tb_usuario_web (nombre, email, contrasena, telefono, calle, numero_exterior, colonia) VALUES (?, ?, ?, ?, ?, ?, ?)',
            [nombre, email, hashedPassword, telefono, calle, numero_exterior, colonia]
        );

        res.status(201).json({ message: 'Usuario registrado exitosamente' });

    } catch (error) {
        console.error('Error en registro:', error);
        res.status(500).json({ message: 'Error en el servidor al intentar registrarse' });
    }
});

// 2. RUTA DE LOGIN (POST)
app.post('/api/auth/login', async (req, res) => {
    const { email, password } = req.body;

    if (!email || !password) {
        return res.status(400).json({ message: 'Correo y contraseña son obligatorios' });
    }

    try {
        // Seleccionamos TODOS los campos (*) para obtener también la dirección
        const [users] = await db.query('SELECT * FROM tb_usuario_web WHERE email = ?', [email]);

        if (users.length === 0) {
            return res.status(401).json({ message: 'Credenciales inválidas (Usuario no encontrado)' });
        }

        const user = users[0];

        const validPassword = await bcrypt.compare(password, user.contrasena);

        if (!validPassword) {
            return res.status(401).json({ message: 'Credenciales inválidas (Contraseña incorrecta)' });
        }

        const token = jwt.sign(
            { id: user.idClienteWeb, email: user.email }, 
            process.env.JWT_SECRET || 'secreto_super_seguro', 
            { expiresIn: '24h' }
        );

        // 3. Enviamos los datos de dirección al frontend
        res.json({
            message: 'Login exitoso',
            token: token,
            user: {
                id: user.idClienteWeb,
                nombre: user.nombre,
                email: user.email,
                telefono: user.telefono,
                // Agregamos la dirección al objeto usuario
                calle: user.calle,
                numero_exterior: user.numero_exterior,
                colonia: user.colonia
            }
        });

    } catch (error) {
        console.error('Error en login:', error);
        res.status(500).json({ message: 'Error en el servidor al iniciar sesión' });
    }
});

// 3. OBTENER MENÚ CON FILTROS
app.get('/api/products', async (req, res) => {
    const categoryName = req.query.category; 
    
    try {
        let query = `
            SELECT 
                p.idProducto AS id, 
                p.nombre AS title, 
                p.descripcion AS description, 
                p.descripcion AS fullDescription,  
                p.precio AS price, 
                p.url_imagen AS image, 
                c.descripcion AS category 
            FROM tb_producto p
            JOIN tb_categoria c ON p.idCategoria = c.idCategoria
            WHERE p.estado = 1 
        `;
        
        const queryParams = [];

        if (categoryName && categoryName !== 'Todos') {
            query += ` AND c.descripcion = ?`; 
            queryParams.push(categoryName);
        }
        
        const [results] = await db.query(query, queryParams);
        
        res.json(results);

    } catch (error) {
        console.error('Error obteniendo productos con filtro:', error); 
        res.status(500).send('Error al obtener el menú');
    }
});

// 4. CREAR PEDIDO
app.post('/api/orders', async (req, res) => {
    // Recibimos userId, productos, total y dirección
    const { userId, items, total, address } = req.body;

    if (!userId || !items || items.length === 0 || !address) {
        return res.status(400).json({ message: 'Faltan datos del pedido' });
    }

    try {
        const [orderResult] = await db.query(
            'INSERT INTO tb_pedido_web (idClienteWeb, total_pagar, direccion_entrega, estado) VALUES (?, ?, ?, ?)',
            [userId, total, address, 'PENDIENTE']
        );
        const orderId = orderResult.insertId;

        for (const item of items) {
            await db.query(
                'INSERT INTO tb_detalle_pedido_web (idPedidoWeb, idProducto, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)',
                [orderId, item.id, 1, item.price, item.price]
            );
        }

        res.status(201).json({ message: 'Pedido creado', orderId });

    } catch (error) {
        console.error('Error creando pedido:', error);
        res.status(500).json({ message: 'Error al procesar el pedido' });
    }
});

//Inicialización del servidor
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
    console.log(`Servidor backend corriendo en http://localhost:${PORT}`);
});