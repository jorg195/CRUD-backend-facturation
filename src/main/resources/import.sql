/* Populate tabla Clientes */

INSERT INTO regiones (id, nombre) VALUES (1,'Sudam�rica');
INSERT INTO regiones (id, nombre) VALUES (2,'Centroam�rica');
INSERT INTO regiones (id, nombre) VALUES (3,'Norteam�rica');
INSERT INTO regiones (id, nombre) VALUES (4,'Europa');
INSERT INTO regiones (id, nombre) VALUES (5,'Asia');
INSERT INTO regiones (id, nombre) VALUES (6,'Africa');
INSERT INTO regiones (id, nombre) VALUES (7,'Ocean�a');
INSERT INTO regiones (id, nombre) VALUES (8,'Ant�rtida');


INSERT INTO clientes (region_id,nombre,apellido,email,create_at) VALUES (3,'Jorge','Morales','jorgealejandro699@gmail.com','2020-03-25');
INSERT INTO clientes (region_id,nombre,apellido,email,create_at) VALUES (2,'Mr. John','Doe','john.doe@gmail.com','2020-03-25');
INSERT INTO clientes (region_id,nombre,apellido,email,create_at) VALUES (4,'Linus','Torvalds','linus.torvalds@gmail.com','2020-03-25');
INSERT INTO clientes (region_id,nombre,apellido,email,create_at) VALUES (4,'Rasmus','Lerdorf','rasmus.lerdorf@gmail.com','2020-03-25');
INSERT INTO clientes (region_id,nombre,apellido,email,create_at) VALUES (4,'Erich','Gamma','erich.gamma@gmail.com','2020-03-25');
INSERT INTO clientes (region_id,nombre,apellido,email,create_at) VALUES (3,'Richard','Helm','richard.helm@gmail.com','2020-03-25');
INSERT INTO clientes (region_id,nombre,apellido,email,create_at) VALUES (3,'Ralph','Johnson','ralph.johnson@gmail.com','2020-03-25');
INSERT INTO clientes (region_id,nombre,apellido,email,create_at) VALUES (3,'John','Vlissides','john.vlissides@gmail.com','2020-03-25');
INSERT INTO clientes (region_id,nombre,apellido,email,create_at) VALUES (3,'Dr. James','Gosgling','james.gosgling@gmail.com','2020-03-25');
INSERT INTO clientes (region_id,nombre,apellido,email,create_at) VALUES (5,'Magma','Lee','magma.lee@gmail.com','2020-03-25');
INSERT INTO clientes (region_id,nombre,apellido,email,create_at) VALUES (6,'Tornado','Roe','tornado.roe@gmail.com','2020-03-25');
INSERT INTO clientes (region_id,nombre,apellido,email,create_at) VALUES (7,'Jade','Doe','jane.doe@gmail.com','2020-03-25');

INSERT INTO `usuarios` (username,password,enabled,nombre,apellido,email) VALUES ('jorge','$2a$10$bAHRuFY4zOtMs3ukABjkFuoMW/5MzrJs7XHayKWVlWa0pvBd9anFS',1,'Jorge Alejandro','Morales','jorgealejandro699@gmail.com');
INSERT INTO `usuarios` (username,password,enabled,nombre,apellido,email) VALUES ('admin','$2a$10$IZmjxCyh9TWSDgRUx6FgquzpLwrSYNckkUr/3BLqpMhqqgxISdaNW',1,'Admin','McAdmin','admin.mcdallas@gmail.com');
/**/
INSERT INTO `roles` (nombre) VALUES ('ROLE_USER');
INSERT INTO `roles` (nombre) VALUES ('ROLE_ADMIN');
 
INSERT INTO `usuario_roles` (usuario_id, role_id) VALUES (1,1);
INSERT INTO `usuario_roles` (usuario_id, role_id) VALUES (2,2);
INSERT INTO `usuario_roles` (usuario_id, role_id) VALUES (2,1);

/* Populate tabla productos */
INSERT INTO productos (nombre, precio, create_at) VALUES('Gabinete AORUS AC300W LITE con Ventana RGB',2529, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Tarjeta de Video Gigabyte NVIDIA GeForce RTX 2070 WINDFORCE 2X',11699, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Mousepad Gamer HyperX FURY S Pro L',493 , NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Tarjeta Madre AORUS B450 AORUS PRO WIFI',3289, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Memoria RAM HyperX FURY Black RGB DDR4',1349, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Fuente de Poder EVGA 700 GD 80 PLUS Gold',2129, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('HyperX Audífonos Gamer Cloud Stinger Gaming para PC y Consolas, Alámbrico',1139, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Mouse Gamer Logitech Óptico G502 Hero RGB, Alámbrico',709, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Corsair Hydro H100x Enfriamiento Liquido para CPU',2039, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('HyperX Audífonos Gamer Cloud Stinger Gaming para PC y Consolas',299990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('SSD Gigabyte NVMe, 256GB',1489, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Procesador AMD Ryzen 7 3700X',7539, NOW());

/* Creamos algunas facturas */
INSERT INTO facturas(descripcion, observacion, cliente_id, create_at) VALUES ('Acessorios Gamer', 'Mouse, Audifonos Gamer, etc', 1, NOW());

INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES (1,1,3);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES (2,1,5);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES (1,1,7);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES (1,1,8);

INSERT INTO facturas(descripcion, observacion, cliente_id, create_at) VALUES('Tarjeta de v�deo', 'Para jugar mejor xd', 1, NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id) VALUES (1,2,2);
