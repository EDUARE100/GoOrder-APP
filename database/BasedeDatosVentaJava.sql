-- Creacion de una base de datos

create database bd_sistema_ventas;
-- Para crear la base de datos e intanciarla darle al rayito con cursor para que ejecute 
-- linea a linea y en el rayito normal para ejecutar todo el codigo

-- Una vez ingresados los datos ejecutar el sistema_ventas
use bd_sistema_ventas;

-- Asi son los comentarios -- y espacio
-- Crear la base de datos
create table tb_usuario(

idUsuario int (11) auto_increment primary key,
nombre varchar(30) not null,
apellido varchar(30) not null,
usuario varchar(15) not null,
password varchar(15) not null,
telefono varchar(15) not null
 
);

create table tb_mesas (
    numero_mesa int(11) PRIMARY KEY,  -- Un identificador único para la mesa
    estado varchar(20) default 'libre'  -- Estado de la mesa (libre, ocupada, etc.)
);

CREATE TABLE tb_cliente (
    idCliente INT(11) AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    apellido VARCHAR(30) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    numero_mesa INT(11)  NOT NULL,  -- Número de la mesa asignada al cliente
    FOREIGN KEY (numero_mesa) REFERENCES tb_mesas(numero_mesa)  -- Relaciona numero_mesa con numero de mesa
    ON DELETE NO ACTION 
    ON UPDATE CASCADE
);

-- Crear tabla categoria
create table tb_categoria(

idCategoria int (11) auto_increment primary key,
descripcion varchar(200) not null,
estado int(1) not null
 
);

-- Crear tabla producto
create table tb_producto(

idProducto int (11) auto_increment primary key,
nombre varchar(100) not null,
cantidad int (11) not null,
precio double(10,2) not null,
descripcion varchar(200) not null,
porcentajeIva int(2) not null,
idCategoria int(11) not null,
estado int(1) not null 
);

ALTER TABLE tb_producto 
ADD COLUMN url_imagen VARCHAR(500) DEFAULT 'https://placehold.co/400'; -- Agregamos una nueva columna para que se carguen las imagenes mediante url y no se haga tan pesado la carga de la página

INSERT INTO tb_producto (nombre, cantidad, precio, descripcion, porcentajeIva, idCategoria, estado, url_imagen) VALUES
('Alitas de pollo a la barbacoa', 50, 180.00, 'Platillo tradicional bañado en salsa BBQ. Opción ideal para compartir.', 16, 1, 1, 'https://images.unsplash.com/photo-1567620832903-9fc6debc209f?auto=format&fit=crop&w=500&q=60'),
('Corte New York', 20, 350.00, 'Corte fino asado al carbón con guarnición de vegetales.', 16, 2, 1, 'https://images.unsplash.com/photo-1546069901-ba9599a7e63c?auto=format&fit=crop&w=500&q=60'),
('Hamburguesa Clásica', 40, 120.00, 'Carne de res 100%, lechuga, tomate y queso cheddar.', 16, 2, 1, 'https://images.unsplash.com/photo-1568901346375-23c9450c58cd'),
('Mixología de Autor', 100, 95.00, 'Selección de cocteles preparados con licores premium.', 16, 3, 1, 'https://images.unsplash.com/photo-1513558161293-cdaf765ed2fd?auto=format&fit=crop&w=500&q=60'),
('Tiramisú', 50, 65.00, 'Clásico postre italiano con café y queso mascarpone.', 16, 4, 1, 'https://images.unsplash.com/photo-1712262582493-01aa9ec5c7f8?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D');

UPDATE tb_producto
SET url_imagen = 'https://images.unsplash.com/photo-1712262582493-01aa9ec5c7f8?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D' 
WHERE idProducto = 5;


-- Crear tabla detalle de venta
create table tb_detalle_venta(
idDetalleVenta int (11) auto_increment primary key,
idCabeceraVenta int (11)not null,
idProducto int (11)not null,
cantidad int (11)not null,
precioUnitario double(10,2) not null,
subtotal double(10,2) not null,
descuento double(10,2) not null,
iva double(10,2) not null,
totalPagar double(10,2) not null,
estado int(1) not null 
);

-- Nuevas tablas creadas para el registro de usuarios y pedidos asi como el detalle de pedidos

create table tb_usuario_web(
	idClienteweb int(11) auto_increment primary key,
    nombre varchar(50) NOT NULL,
    email varchar(30) not null, -- Este campo será el usuario para logearse despues del registro
    contrasena varchar(255) not null, -- Longitud de 255 para soportar encriptación
    telefono varchar(20) not null,
    fecha_registro datetime default current_timestamp
);

CREATE TABLE tb_pedido_web (
    idPedidoWeb INT(11) AUTO_INCREMENT PRIMARY KEY,
    idClienteWeb INT(11) NOT NULL,
    fecha_pedido DATETIME DEFAULT CURRENT_TIMESTAMP,
    total_pagar DOUBLE(10,2) NOT NULL,
    direccion_entrega VARCHAR(200), -- Opcional, si agregas delivery
    estado VARCHAR(20) DEFAULT 'PENDIENTE', -- Estados: PENDIENTE, ACEPTADO, EN_CAMINO, FINALIZADO
    FOREIGN KEY (idClienteWeb) REFERENCES tb_usuario_web(idClienteweb)
);

CREATE TABLE tb_detalle_pedido_web (
    idDetalleWeb INT(11) AUTO_INCREMENT PRIMARY KEY,
    idPedidoWeb INT(11) NOT NULL,
    idProducto INT(11) NOT NULL, -- Relación con tu tabla de productos existente
    cantidad INT(11) NOT NULL,
    precio_unitario DOUBLE(10,2) NOT NULL,
    subtotal DOUBLE(10,2) NOT NULL,
    FOREIGN KEY (idPedidoWeb) REFERENCES tb_pedido_web(idPedidoWeb),
    FOREIGN KEY (idProducto) REFERENCES tb_producto(idProducto)
);

-- Se insertan todos los campos menos idUsuario ya que este se autoincrementa
insert into tb_usuario(nombre,apellido,usuario,password,telefono)
values("Eduardo","Arevalo","eduardo","120104","2299846742");

select usuario,password from tb_usuario where usuario = "eduardo" and password = "120104";

insert into tb_mesas(numero_mesa,estado) values 
(1,'libre'),
(2,'libre'),
(3,'libre'),
(4,'libre'),
(5,'libre'),
(6,'libre'),
(7,'libre'),
(8,'libre'),
(9,'libre');

