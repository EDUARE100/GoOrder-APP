const express = require('express');
const cors = require('cors');
const bcrypt = require('bcryptjs'); // Para encriptar contraseñas
const jwt = require('jsonwebtoken'); // Para crear tokens de sesión
require('dotenv').config(); // Para leer el archivo .env

// Importamos la conexion de la base de datos
const db = require('./src/config/database');

const app = express();

// --- MIDDLEWARES ---
// Esto permite que el frontend  se comunique con el backend
app.use(cors()); 
// Esto permite que el backend entienda los datos JSON que se envian
app.use(express.json());

// 1. RUTA DE REGISTRO (POST)
// React envía: { nombre, email, password, telefono }
app.post('/api/auth/register', async (req, res) => {
    const { nombre, email, password, telefono } = req.body;

    // Validación: Que no falten datos
    if (!nombre || !email || !password || !telefono) {
        return res.status(400).json({ message: 'Todos los campos son obligatorios' });
    }

    try {
        // A. Verificar si el correo ya existe
        const [userExists] = await db.query('SELECT email FROM tb_usuario_web WHERE email = ?', [email]);
        
        if (userExists.length > 0) {
            return res.status(409).json({ message: 'El correo electrónico ya está registrado' });
        }

        // B. Encriptar la contraseña (Nunca la guardes en texto plano)
        const salt = await bcrypt.genSalt(10);
        const hashedPassword = await bcrypt.hash(password, salt);

        // C. Insertar el nuevo cliente en la BD
        await db.query(
            'INSERT INTO tb_usuario_web (nombre, email, contrasena, telefono) VALUES (?, ?, ?, ?)',
            [nombre, email, hashedPassword, telefono]
        );

        res.status(201).json({ message: 'Usuario registrado exitosamente' });

    } catch (error) {
        console.error('Error en registro:', error);
        res.status(500).json({ message: 'Error en el servidor al intentar registrarse' });
    }
});

// 2. RUTA DE LOGIN (POST)
// React envía: { email, password }
app.post('/api/auth/login', async (req, res) => {
    const { email, password } = req.body;

    // Validación básica
    if (!email || !password) {
        return res.status(400).json({ message: 'Correo y contraseña son obligatorios' });
    }

    try {
        // A. Buscar al usuario por su email
        const [users] = await db.query('SELECT * FROM tb_usuario_web WHERE email = ?', [email]);

        // Si no se encuentra el usuario
        if (users.length === 0) {
            return res.status(401).json({ message: 'Credenciales inválidas (Usuario no encontrado)' });
        }

        const user = users[0];

        // B. Comparar la contraseña ingresada con la encriptada
        const validPassword = await bcrypt.compare(password, user.contrasena);

        if (!validPassword) {
            return res.status(401).json({ message: 'Credenciales inválidas (Contraseña incorrecta)' });
        }

        // C. Generar el Token (JWT)
        // Esto es como un "pase VIP" que React guardará para recordar que el usuario inició sesión
        const token = jwt.sign(
            { id: user.idClienteWeb, email: user.email }, 
            process.env.JWT_SECRET || 'secreto_super_seguro', 
            { expiresIn: '24h' }
        );

        // D. Responder con éxito y enviar datos del usuario
        res.json({
            message: 'Login exitoso',
            token: token,
            user: {
                id: user.idClienteWeb,
                nombre: user.nombre,
                email: user.email,
                telefono: user.telefono
            }
        });

    } catch (error) {
        console.error('Error en login:', error);
        res.status(500).json({ message: 'Error en el servidor al iniciar sesión' });
    }
});

// 3. OBTENER MENÚ CON FILTROS
app.get('/api/products', async (req, res) => {
    // 🚨 Capturamos el filtro que viene del frontend (ej: Platos Fuertes)
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

        // Filtramos por categorias y dependiendo de la descrupcion del producto
        if (categoryName && categoryName !== 'Todos') {
            // Agregamos la condición WHERE para filtrar por la descripción de la categoría
            query += ` AND c.descripcion = ?`; 
            // Agregamos el nombre de la categoría a los parámetros que el driver MySQL maneja
            queryParams.push(categoryName);
        }
        
        // El driver MySQL usa los queryParams para sustituir el '?' de forma segura
        const [results] = await db.query(query, queryParams);
        
        res.json(results);

    } catch (error) {
        // Si hay un error SQL, lo mostramos en la consola del backend para diagnóstico
        console.error('Error obteniendo productos con filtro:', error); 
        // 🚨 Devolvemos el 500 al frontend
        res.status(500).send('Error al obtener el menú');
    }
});

//Inicialización del servidor
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
    console.log(`Servidor backend corriendo en http://localhost:${PORT}`);
});