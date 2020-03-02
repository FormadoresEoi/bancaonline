CREATE SCHEMA `mundo_bancario` ;

CREATE TABLE clientes (
	id INT PRIMARY KEY AUTO_INCREMENT, 
    usuario VARCHAR(20) UNIQUE,
    pass VARCHAR(20),
    nombre VARCHAR(20),
    email VARCHAR(20)
    );
	
CREATE TABLE cuentas (
	num_cuenta INT PRIMARY KEY AUTO_INCREMENT, 
    alias VARCHAR(20),
    saldo INT,
	id_cliente INT,
    FOREIGN KEY (id_cliente) REFERENCES clientes (id)
    );
    
CREATE TABLE tipos_movimiento (
	id INT PRIMARY KEY AUTO_INCREMENT, 
    tipo VARCHAR(20)
    );	
	
CREATE TABLE movimientos (
	id INT PRIMARY KEY AUTO_INCREMENT, 
    descripcion VARCHAR(20),
    fecha DATE,
	importe INT,
    num_cuenta INT,
    id_tipo_movimiento INT, 
    FOREIGN KEY (num_cuenta) REFERENCES cuentas (num_cuenta),
    FOREIGN KEY (id_tipo_movimiento) REFERENCES tipos_movimiento (id)
    );
	
CREATE TABLE prestamos (
	id INT PRIMARY KEY AUTO_INCREMENT, 
    descripcion VARCHAR(20),
    fecha DATE,
	importe INT,
    plazos INT,
    num_cuenta INT,
    FOREIGN KEY (num_cuenta) REFERENCES cuentas (num_cuenta)
    );
		
CREATE TABLE amortizaciones (
	id INT PRIMARY KEY AUTO_INCREMENT, 
    fecha DATE,
	importe INT,
    id_prestamo INT, 
    FOREIGN KEY (id_prestamo) REFERENCES prestamos (id)
    );	