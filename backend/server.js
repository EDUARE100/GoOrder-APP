const express = require('express');
const cors = require('cors');
const bcrypt = require('bcryptjs'); 
const jwt = require('jsonwebtoken'); 
const dotenv = require('dotenv');

// Importamos tu conexión a la BD existente
const db = require('./src/config/database'); 

dotenv.config();

const app = express();

// --- CONFIGURACIÓN ---
app.use(cors()); // Permite conexiones desde cualquier origen
app.use(express.json());


// 1. RUTA DE REGISTRO

app.post('/api/auth/register', async (req, res) => {
    // Recibimos los datos exactos que envía tu Login.jsx
    const { nombre, email, password, telefono, calle, numero_exterior, colonia } = req.body;

    // Validación básica
    if (!nombre || !email || !password || !telefono) {
        return res.status(400).json({ message: 'Faltan datos obligatorios' });
    }

    try {
        // 1. Verificar si ya existe el correo en tb_usuario_web
        const [userExists] = await db.query('SELECT email FROM tb_usuario_web WHERE email = ?', [email]);
        
        if (userExists.length > 0) {
            return res.status(400).json({ message: 'El correo ya está registrado' });
        }

        // Encriptar contraseña con bcrypt
        const salt = await bcrypt.genSalt(10);
        const hashedPassword = await bcrypt.hash(password, salt);

        // 3. Insertar en la tabla CORRECTA (tb_usuario_web) con la dirección
        await db.query(
            `INSERT INTO tb_usuario_web 
            (nombre, email, contrasena, telefono, calle, numero_exterior, colonia) 
            VALUES (?, ?, ?, ?, ?, ?, ?)`,
            [nombre, email, hashedPassword, telefono, calle, numero_exterior, colonia]
        );

        res.status(201).json({ message: 'Usuario registrado exitosamente' });

    } catch (error) {
        console.error('Error en registro:', error);
        res.status(500).json({ message: 'Error en el servidor al registrar' });
    }
});


// 2. RUTA DE LOGIN 

app.post('/api/auth/login', async (req, res) => {
    const { email, password } = req.body;

    try {
        // Buscamos el usuario
        const [users] = await db.query('SELECT * FROM tb_usuario_web WHERE email = ?', [email]);

        if (users.length === 0) {
            return res.status(400).json({ message: 'Usuario no encontrado' });
        }

        const user = users[0];

        // Comparamos contraseñas
        const validPassword = await bcrypt.compare(password, user.contrasena);
        if (!validPassword) {
            return res.status(400).json({ message: 'Contraseña incorrecta' });
        }

        // Generamos Token (Opcional, pero recomendado)
        const token = jwt.sign({ id: user.idClienteweb }, process.env.JWT_SECRET || 'secret', { expiresIn: '1d' });

        //Enviamos el objeto 'user' formateado como lo espera el Frontend
        // El Dashboard busca "user.id", así que mapeamos idClienteweb -> id
        res.json({
            message: 'Login exitoso',
            token,
            user: {
                id: user.idClienteweb,
                nombre: user.nombre,
                email: user.email,
                telefono: user.telefono,
                calle: user.calle,
                numero_exterior: user.numero_exterior,
                colonia: user.colonia
            }
        });

    } catch (error) {
        console.error('Error en login:', error);
        res.status(500).json({ message: 'Error en el servidor' });
    }
});


// 3. RUTA DE PRODUCTOS (Para el Catálogo)

app.get('/api/products', async (req, res) => {
    const category = req.query.category;
    try {
        let sql = `
            SELECT p.idProducto as id, p.nombre as title, p.precio as price, 
                   p.descripcion as description, p.url_imagen as image, c.descripcion as category
            FROM tb_producto p
            JOIN tb_categoria c ON p.idCategoria = c.idCategoria
            WHERE p.estado = 1
        `;
        
        const params = [];
        if (category && category !== 'Todos') {
            sql += ' AND c.descripcion = ?';
            params.push(category);
        }

        const [products] = await db.query(sql, params);
        res.json(products);
    } catch (error) {
        console.error(error);
        res.status(500).json({ message: 'Error al obtener productos' });
    }
});


// 4. RUTA DE PEDIDOS

app.post('/api/orders', async (req, res) => {
    // Recibimos userId, items, total y address desde el Dashboard.jsx
    const { userId, items, total, address } = req.body;

    // Validación
    if (!userId || !items || items.length === 0) {
        return res.status(400).json({ message: 'Datos de pedido incompletos' });
    }

    let connection;
    try {
        // Usamos una conexión dedicada para poder hacer rollback si algo falla (Transacción)
        connection = await db.getConnection();
        await connection.beginTransaction();

        // 1. Insertar en tb_pedido_web
        const [orderResult] = await connection.query(
            `INSERT INTO tb_pedido_web (idClienteWeb, total_pagar, direccion_entrega, estado) 
             VALUES (?, ?, ?, 'PENDIENTE')`,
            [userId, total, address]
        );
        
        const orderId = orderResult.insertId;

        // 2. Insertar cada producto en tb_detalle_pedido_web
        for (const item of items) {
            await connection.query(
                `INSERT INTO tb_detalle_pedido_web 
                (idPedidoWeb, idProducto, cantidad, precio_unitario, subtotal) 
                VALUES (?, ?, ?, ?, ?)`,
                [orderId, item.id, 1, item.price, item.price] // Asumimos cantidad 1 por item según tu carrito actual
            );
        }

        // Si todo sale bien, confirmamos los cambios
        await connection.commit();
        
        console.log(`Pedido #${orderId} creado exitosamente.`);
        res.status(201).json({ message: 'Pedido guardado', orderId });

    } catch (error) {
        // Si hay error, deshacemos todo para no dejar pedidos a medias
        if (connection) await connection.rollback();
        console.error('Error al guardar pedido:', error);
        res.status(500).json({ message: 'Error al procesar el pedido en base de datos' });
    } finally {
        if (connection) connection.release();
    }
});

// Iniciar servidor
const PORT = process.env.PORT || 3000;
app.listen(PORT, '0.0.0.0', () => { //0.0.0.0. Para que acepte todos los puertos o conexiones
    console.log(`Servidor corriendo en puerto ${PORT}`);
});