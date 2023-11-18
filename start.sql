-- Tabla cliente
CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nombres VARCHAR(50),
    apellidos VARCHAR(50),
    direccion VARCHAR(100),
    email VARCHAR(50),
    telefono VARCHAR(20),
    documento varchar(20),
    ruc varchar(20)
);

-- Tabla producto_categoria
CREATE TABLE producto_categoria (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50)
);

-- Tabla producto
CREATE TABLE producto (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50),
    descripcion TEXT,
    precio float8,
    cantidad_disponible INT,
    producto_categoria_id INT REFERENCES producto_categoria(id)
);

-- Tabla pedido_estado
CREATE TABLE pedido_estado (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50)
);

-- Tabla pedido
CREATE TABLE pedido (
    id SERIAL PRIMARY KEY,
    fecha_pedido timestamptz,
    fecha_entregado timestamptz,
    cliente_id INT REFERENCES cliente(id),
    estado_id INT REFERENCES pedido_estado(id),
    total float8
);

-- Tabla detalle_pedido
CREATE TABLE pedido_detalle (
    id SERIAL PRIMARY KEY,
    pedido_id INT REFERENCES pedido(id),
    producto_id INT REFERENCES producto(id),
    cantidad INT,
    total float8
);

-- Tabla usuario
CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    usuario VARCHAR(50),
    pass VARCHAR(50)
);

-- Datos iniciales
INSERT INTO public.pedido_estado
(id, nombre)
VALUES(nextval('pedido_estado_id_seq'::regclass), 'Aprobado');

INSERT INTO public.pedido_estado
(id, nombre)
VALUES(nextval('pedido_estado_id_seq'::regclass), 'Rechazado');

INSERT INTO public.pedido_estado
(id, nombre)
VALUES(nextval('pedido_estado_id_seq'::regclass), 'Entregado');

INSERT INTO public.pedido_estado
(id, nombre)
VALUES(nextval('pedido_estado_id_seq'::regclass), 'Pendiente de Entrega');

INSERT INTO public.pedido_estado
(id, nombre)
VALUES(nextval('pedido_estado_id_seq'::regclass), 'Pendiente de Pago');

INSERT INTO public.usuario
(id, usuario, pass)
VALUES(nextval('usuario_id_seq'::regclass), 'admin', 'admin');

INSERT INTO public.cliente (nombres, apellidos, direccion, email, telefono, documento, ruc)
VALUES ('Juan', 'Pérez', 'Calle Falsa 123', 'juan.perez@example.com', '123456789', '12345678', '12345678901');

INSERT INTO public.cliente (nombres, apellidos, direccion, email, telefono, documento, ruc)
VALUES ('Ana', 'García', 'Avenida Siempre Viva 456', 'ana.garcia@example.com', '987654321', '87654321', '10987654321');

INSERT INTO public.cliente (nombres, apellidos, direccion, email, telefono, documento, ruc)
VALUES ('Luis', 'Rodríguez', 'Calle Inventada 789', 'luis.rodriguez@example.com', '555555555', '55555555', '20555555551');

INSERT INTO public.producto_categoria (nombre)
VALUES ('Smartphones');

INSERT INTO public.producto_categoria (nombre)
VALUES ('Computadoras Portátiles');

INSERT INTO public.producto_categoria (nombre)
VALUES ('Componentes de PC');

INSERT INTO public.producto_categoria (nombre)
VALUES ('Tabletas');

INSERT INTO public.producto_categoria (nombre)
VALUES ('Monitores y Pantallas');

INSERT INTO public.producto_categoria (nombre)
VALUES ('Accesorios de Computadora');

INSERT INTO public.producto_categoria (nombre)
VALUES ('Dispositivos de Almacenamiento');

INSERT INTO public.producto_categoria (nombre)
VALUES ('Cámaras y Fotografía');

INSERT INTO public.producto_categoria (nombre)
VALUES ('Audio y Video');

INSERT INTO public.producto_categoria (nombre)
VALUES ('Gadgets y Otros Electrónicos');


INSERT INTO public.producto (nombre, descripcion, precio, cantidad_disponible, producto_categoria_id)
VALUES ('Smartphone XYZ', 'Smartphone de última generación con pantalla de 6.5 pulgadas', 299.99, 50, 1);

INSERT INTO public.producto (nombre, descripcion, precio, cantidad_disponible, producto_categoria_id)
VALUES ('Laptop ABC', 'Laptop potente para trabajo y entretenimiento, 16GB RAM, SSD 512GB', 899.99, 30, 2);

INSERT INTO public.producto (nombre, descripcion, precio, cantidad_disponible, producto_categoria_id)
VALUES ('Memoria RAM 8GB', 'Memoria RAM DDR4 de 8GB para actualización de PC', 79.99, 100, 3);

INSERT INTO public.producto (nombre, descripcion, precio, cantidad_disponible, producto_categoria_id)
VALUES ('Tablet Pro 10"', 'Tablet de 10 pulgadas, ideal para lectura y navegación en internet', 499.99, 40, 4);

INSERT INTO public.producto (nombre, descripcion, precio, cantidad_disponible, producto_categoria_id)
VALUES ('Monitor 4K UHD', 'Monitor de 27 pulgadas con resolución 4K UHD, ideal para diseño gráfico', 349.99, 25, 5);