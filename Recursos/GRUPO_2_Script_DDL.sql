drop table if exists AMORTIZACIONES;
drop table if exists MOVIMIENTOS;
drop table if exists TIPOS_MOVIMIENTO;
drop table if exists PRESTAMOS;
drop table if exists CUENTAS;
drop table if exists CLIENTES;
create table TIPOS_MOVIMIENTO(
	ID int,
    TIPO varchar(20),
    primary key(ID)
);
create table CLIENTES(
	ID int,
    USUARIO varchar(20),
    PASS varchar(20),
    NOMBRE varchar(20),
    EMAIL varchar(50),
    primary key(ID)
);
create table CUENTAS(
	NUM_CUENTA int,
    ALIAS varchar(20),
    SALDO double,
    ID_CLIENTE int,
    primary key(NUM_CUENTA),
    foreign key (ID_CLIENTE) references CLIENTES (ID)
);
create table PRESTAMOS(
	ID int,
    DESCRIPCION text,
    FECHA date,
    IMPORTE double,
    PLAZOS int,
    NUM_CUENTA int,
    primary key(ID),
    foreign key (NUM_CUENTA) references CUENTAS (NUM_CUENTA)
);
create table AMORTIZACIONES(
	ID int,
    ID_PRESTAMO int,
    FECHA date,
    IMPORTE double,
    foreign key (ID_PRESTAMO) references PRESTAMOS (ID),
    primary key(ID)
);
create table MOVIMIENTOS(
	ID int,
    DESCRIPCION text,
    FECHA date,
    IMPORTE double,
    ID_TIPO_MOVIMIENTO int,
    NUM_CUENTA int,
    primary key(ID),
    foreign key (ID_TIPO_MOVIMIENTO) references TIPOS_MOVIMIENTO (ID),
    foreign key (NUM_CUENTA) references CUENTAS (NUM_CUENTA)
);