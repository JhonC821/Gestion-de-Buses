CREATE DATABASE IF NOT EXISTS db_gestion_buses;

USE db_gestion_buses;

CREATE TABLE personal(
    dpi_personal VARCHAR(13) PRIMARY KEY,
    nombre_completo VARCHAR(100) NOT NULL,
    apellido_completo VARCHAR(100) NOT NULL,
    telefono VARCHAR(15) UNIQUE,
    cargo VARCHAR(50) NOT NULL,
    usuario VARCHAR(50) UNIQUE NOT NULL,
    contrasenia VARCHAR(100) NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE
);


CREATE TABLE sucursal(
    codigo_sucursal VARCHAR(50) PRIMARY KEY NOT NULL,
    nombre_sucursal VARCHAR(100) NOT NULL,
    direccion VARCHAR(200) NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE chofer(
    dpi_personal VARCHAR(13) PRIMARY KEY NOT NULL,
    no_licencia VARCHAR(20) NOT NULL,
    tipo_licencia VARCHAR(10) NOT NULL,
    fecha_vencimiento DATE NOT NULL,
    sucursal_asignada VARCHAR(50) NOT NULL,
    salario_base DECIMAL(10,2) NOT NULL,
    estado_operativo VARCHAR(20) NOT NULL DEFAULT "LIBRE",
    FOREIGN KEY (dpi_personal) REFERENCES personal(dpi_personal),
    FOREIGN KEY (sucursal_asignada) REFERENCES sucursal(codigo_sucursal)
);

CREATE TABLE asignacion_admin_sucursal(
    id_asignacion INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    dpi_admin VARCHAR(13) NOT NULL,
    codigo_sucursal VARCHAR(50) NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (dpi_admin) REFERENCES personal(dpi_personal),
    FOREIGN KEY (codigo_sucursal) REFERENCES sucursal(codigo_sucursal)
);


CREATE TABLE bus(
    no_placa VARCHAR(10) PRIMARY KEY NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    marca VARCHAR(50) NOT NULL,
    codigo_sucursal VARCHAR(50) NOT NULL,
    sucursal_actual VARCHAR(50) NOT NULL,
    anio_fabricacion YEAR NOT NULL,
    capacidad INT NOT NULL,
    foto MEDIUMBLOB,
    kilometraje_actual DECIMAL(10,2) NOT NULL, 
    estado_operativo VARCHAR(20) NOT NULL DEFAULT "LIBRE",
    estado BOOLEAN NOT NULL DEFAULT TRUE,
    foreign key (codigo_sucursal) references sucursal(codigo_sucursal),
    foreign key (sucursal_actual) references sucursal(codigo_sucursal)
);

CREATE TABLE gasto_taller(
    id_gasto INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    no_placa VARCHAR(10) NOT NULL,
    fecha_gasto DATE NOT NULL,
    descripcion VARCHAR(200) NOT NULL,
    monto_repuesto DECIMAL(10,2) NOT NULL,
    monto_mano_obra DECIMAL(10,2) NOT NULL,
    monto_total DECIMAL(10,2) NOT NULL DEFAULT (monto_repuesto + monto_mano_obra),
    FOREIGN KEY (no_placa) REFERENCES bus(no_placa)
);

CREATE TABLE asiento(
    id_asiento INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    no_placa VARCHAR(10) NOT NULL,
    numero_asiento INT NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT "LIBRE",
    FOREIGN KEY (no_placa) REFERENCES bus(no_placa)
);

CREATE TABLE asignacion_chofer_bus(
    id_asignacion INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    no_placa VARCHAR(10) NOT NULL,
    dpi_personal VARCHAR(13) NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (no_placa) REFERENCES bus(no_placa),
    FOREIGN KEY (dpi_personal) REFERENCES chofer(dpi_personal)
);


CREATE TABLE ruta(
    codigo_ruta VARCHAR(50) PRIMARY KEY NOT NULL,
    sucursal_origen VARCHAR(50) NOT NULL,
    sucursal_destino VARCHAR(50) NOT NULL,
    distancia_km DECIMAL(10,2) NOT NULL,
    precio_boleto DECIMAL(10,2) NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (sucursal_origen) REFERENCES sucursal(codigo_sucursal),
    FOREIGN KEY (sucursal_destino) REFERENCES sucursal(codigo_sucursal)
);

CREATE TABLE viaje(
    id_viaje INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    no_placa VARCHAR(10) NOT NULL,
    dpi_personal VARCHAR(13) NOT NULL,
    codigo_ruta VARCHAR(50) NOT NULL,
    fecha_hora_salida DATETIME NOT NULL,
    fecha_hora_llegada DATETIME,
    kilometraje_inicial_bus DECIMAL(10,2) NOT NULL,
    kilometraje_final_bus DECIMAL(10,2),
    combustible_consumido DECIMAL(10,2),
    depreciacion_bus DECIMAL(10,2),
    monto_total DECIMAL(10,2),
    estado VARCHAR(20) NOT NULL DEFAULT "PROGRAMADO",
    FOREIGN KEY (no_placa) REFERENCES bus(no_placa),
    FOREIGN KEY (dpi_personal) REFERENCES chofer(dpi_personal),
    FOREIGN KEY (no_ruta) REFERENCES ruta(no_ruta)
);

CREATE TABLE cliente(
    dpi_cliente VARCHAR(13) PRIMARY KEY NOT NULL,
    nit_cliente VARCHAR(20) UNIQUE,
    nombre_completo VARCHAR(100) NOT NULL,
    apellido_completo VARCHAR(100) NOT NULL,
    telefono VARCHAR(15) UNIQUE,
    saldo DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    usuario VARCHAR(50) UNIQUE NOT NULL,
    contrasenia VARCHAR(100) NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE compra(
    id_compra INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    dpi_cliente VARCHAR(13) NOT NULL,
    tipo_viaje VARCHAR(20) NOT NULL DEFAULT "VIAJE_REGULAR",
    fecha_hora_compra DATETIME NOT NULL,
    monto_total DECIMAL(10,2) NOT NULL,
    estado_compra VARCHAR(20) NOT NULL DEFAULT "ABIERTA",
    FOREIGN KEY (dpi_cliente) REFERENCES cliente(dpi_cliente)
);

CREATE TABLE boleto(
    id_boleto INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_compra INT NOT NULL,
    id_viaje INT NOT NULL,
    id_asiento INT NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (id_viaje) REFERENCES viaje(id_viaje) ON DELETE CASCADE,
    FOREIGN KEY (id_asiento) REFERENCES asiento(id_asiento),
    FOREIGN KEY (id_compra) REFERENCES compra(id_compra)
);

CREATE TABLE alquiler_bus(
    id_alquiler INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_compra INT NOT NULL,
    id_viaje INT NOT NULL,
    origen VARCHAR(200) NOT NULL,
    destino VARCHAR(200) NOT NULL,
    kilometros DECIMAL(10,2) NOT NULL,
    fecha_hora_Salida DATETIME NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT "PENDIENTE",
    FOREIGN KEY (id_viaje) REFERENCES viaje(id_viaje) ON DELETE CASCADE,
    FOREIGN KEY (id_compra) REFERENCES compra(id_compra)
);

CREATE TABLE configuracion_sistema(
    nombreConfiguracion VARCHAR(100) PRIMARY KEY,
    valorConfiguracion DECIMAL(10,2) NOT NULL
);








