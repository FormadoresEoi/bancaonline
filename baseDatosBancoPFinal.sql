create database banca;
use banca;
drop table if exists Movimientos;
drop table if exists Amortizaciones;
drop table if exists Prestamos;
drop table if exists Tipos_movimiento;
drop table if exists Cuentas;
drop table if exists Clientes;

CREATE TABLE Clientes(
	id int NOT NULL AUTO_INCREMENT,
    nombre varchar(40),
    usuario varchar(40) UNIQUE,
	pass varchar(40),
    email varchar(40),
    PRIMARY KEY (id)
);

CREATE TABLE Cuentas(
	num_cuenta int NOT NULL AUTO_INCREMENT,
    alias varchar(40),
    saldo double,
    id_cliente int,
    FOREIGN KEY (id_cliente) REFERENCES Clientes(id),
    PRIMARY KEY (num_cuenta)
);

CREATE TABLE Tipos_movimiento(
	id int NOT NULL AUTO_INCREMENT,
    tipo varchar(40),
    PRIMARY KEY (id)    
);

CREATE TABLE Movimientos(
	id int NOT NULL AUTO_INCREMENT,
    descripcion varchar(40),
    fecha timestamp,
    importe double,
    id_cuenta int,
    id_tipo int,
    FOREIGN KEY (id_cuenta) REFERENCES Cuentas(num_cuenta),
    FOREIGN KEY (id_tipo) REFERENCES Tipos_movimiento(id),
    PRIMARY KEY (id)
);

CREATE TABLE Prestamos(
	id int NOT NULL AUTO_INCREMENT,
	descripcion varchar(40),
    fecha timestamp,
    importe double,
    plazos int,
    id_cuenta int,
    FOREIGN KEY (id_cuenta) REFERENCES Cuentas(num_cuenta),
    PRIMARY KEY (id)
);

CREATE TABLE Amortizaciones(
	id int NOT NULL AUTO_INCREMENT,
    fecha timestamp,
    importe double,
    id_prestamo int,
    FOREIGN KEY (id_prestamo) REFERENCES Prestamos(id),
    PRIMARY KEY (id)
);

alter table prestamos add column estado varchar(20) after id_cuenta;
alter table amortizaciones add column estado varchar(20) after id_prestamo;

 INSERT INTO Clientes (usuario,pass,nombre,email) VALUES('user1','password','M Rajoy','@gmail.com');
 INSERT INTO Clientes (usuario,pass,nombre,email) VALUES('user2','password','bertin','@gmail.com');
 INSERT INTO Clientes (usuario,pass,nombre,email) VALUES('user3','password','agapito di sousa','@gmail.com');

 INSERT INTO Cuentas (alias,saldo) VALUES('CuentaUniversidad','5000');
 INSERT INTO Cuentas (alias,saldo) VALUES('CuentaViajes','10000');
 INSERT INTO Cuentas (alias,saldo) VALUES('CuentaSalario','7000');

 INSERT INTO Prestamos (descripcion,importe,plazos) VALUES('coche','4000','4');
 INSERT INTO Prestamos (descripcion,importe,plazos) VALUES('avion','8000','4');
 INSERT INTO Prestamos (descripcion,importe,plazos) VALUES('tren','90000','10');


 INSERT INTO Movimientos (descripcion,fecha,importe) VALUES('movimiento1','2017-07-23','5000');
 INSERT INTO Movimientos (descripcion,fecha,importe) VALUES('movimiento2','2017-07-20','8000');
 INSERT INTO Movimientos (descripcion,fecha,importe) VALUES('movimiento3', '2017-07-28' ,'1000');
 
INSERT INTO Tipos_movimiento (tipo) VALUES('Interes');