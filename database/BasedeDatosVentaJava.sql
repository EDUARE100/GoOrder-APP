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

