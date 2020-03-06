CREATE SCHEMA mundo_bancario;

CREATE TABLE clientes (
	id INT PRIMARY KEY AUTO_INCREMENT, 
    usuario VARCHAR(50) UNIQUE,
    pass VARCHAR(50),
    nombre VARCHAR(50),
    email VARCHAR(50)
);
	
CREATE TABLE cuentas (
	num_cuenta INT PRIMARY KEY AUTO_INCREMENT, 
    alias VARCHAR(50),
    saldo INT,
	cliente INT,
    FOREIGN KEY (cliente) REFERENCES clientes (id)
);
    
CREATE TABLE tipos_movimiento (
	id INT PRIMARY KEY AUTO_INCREMENT, 
    tipo VARCHAR(50)
);	
	
CREATE TABLE movimientos (
	id INT PRIMARY KEY AUTO_INCREMENT, 
    descripcion VARCHAR(200),
    fecha DATE,
	importe FLOAT,
    cuenta INT,
    tipo_movimiento INT, 
    FOREIGN KEY (cuenta) REFERENCES cuentas (num_cuenta),
    FOREIGN KEY (tipo_movimiento) REFERENCES tipos_movimiento (id)
);
	
CREATE TABLE prestamos (
	id INT PRIMARY KEY AUTO_INCREMENT, 
    descripcion VARCHAR(200),
    fecha DATE,
	importe FLOAT,
    plazos INT,
    cuenta INT,
    pagado VARCHAR,
    FOREIGN KEY (cuenta) REFERENCES cuentas (num_cuenta)
);
		
CREATE TABLE amortizaciones (
	id INT PRIMARY KEY AUTO_INCREMENT, 
    fecha DATE,
	importe FLOAT,
    prestamo INT,
    pagado VARCHAR,
    FOREIGN KEY (prestamo) REFERENCES prestamos (id)
);	