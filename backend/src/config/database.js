
const mysql = require('mysql2');
require('dotenv').config();

//Creamos el pool para las conexiones directamente traidas desde nuestro .env
const pool = mysql.createPool({
    host: process.env.DB_HOST,
    port: process.env.DB_PORT,
    user: process.env.DB_USER,
    password: process.env.DB_PASSWORD,
    database: process.env.DB_NAME,
    waitForConnections: true,
    connectionLimit: 10,
    queueLimit: 0
});

// Promisificar = Convertir una función asíncrona que usa Callbacks en una función que devuelve una Promesa de JS, para usar async/await
const promisepool = pool.promise();

pool.getConnection((err,connection) => {
    if(err){
        console.error("Error conectando a la base de datos: ",err);
        return;
    }
    console.log("Conexión exitosa a la BD");
    connection.release();
})

module.exports = promisepool;