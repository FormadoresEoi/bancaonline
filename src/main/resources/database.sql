CREATE TABLE `clientes`
(
    `id`      int auto_increment,
    `usuario` varchar(255) not null,
    `pass`    varchar(255) not null,
    `nombre`  varchar(255) not null,
    `email`   varchar(255) not null,

    primary key (`id`),
    unique index (`usuario`)
) DEFAULT CHARACTER SET 'utf8';

CREATE TABLE `cuentas`
(
    `num_cuenta`  varchar(255)  not null,
    `clientes_id` int           not null,
    `alias`       varchar(255)  not null,
    `saldo`       decimal(9, 2) not null default '0.00',

    primary key (`num_cuenta`)
) DEFAULT CHARACTER SET 'utf8';

CREATE TABLE `movimientos`
(
    `id`                 int auto_increment,
    `cuentas_num_cuenta` varchar(255)  not null,
    `movimientos_id`     int           not null,
    `descripcion`        varchar(255)  not null,
    `fecha`              timestamp     not null default current_timestamp,
    `importe`            decimal(9, 2) not null,

    primary key (`id`)
) DEFAULT CHARACTER SET 'utf8';

CREATE TABLE `prestamos`
(
    `id`                 int auto_increment,
    `cuentas_num_cuenta` varchar(255)  not null,
    `descripcion`        varchar(255)  not null,
    `fecha`              timestamp     not null default current_timestamp,
    `importe`            decimal(9, 2) not null,
    `plazos`             int           not null,

    primary key (`id`)
) DEFAULT CHARACTER SET 'utf8';

CREATE TABLE `tipos_movimiento`
(
    `id`   int auto_increment,
    `tipo` varchar(255) not null,

    primary key (`id`)
) DEFAULT CHARACTER SET 'utf8';

CREATE TABLE `amortizaciones`
(
    `id`           int auto_increment,
    `prestamos_id` int           not null,
    `fecha`        timestamp     not null default current_timestamp,
    `importe`      decimal(9, 2) not null,

    primary key (`id`)
) DEFAULT CHARACTER SET 'utf8';

ALTER TABLE `cuentas`
    ADD CONSTRAINT `cuentas_clientes`
        FOREIGN KEY (`clientes_id`) REFERENCES `clientes` (`id`);

ALTER TABLE `movimientos`
    ADD CONSTRAINT `movimientos_cuentas`
        FOREIGN KEY (`cuentas_num_cuenta`) REFERENCES `cuentas` (`num_cuenta`);

ALTER TABLE `prestamos`
    ADD CONSTRAINT `prestamos_cuentas`
        FOREIGN KEY (`cuentas_num_cuenta`) REFERENCES `cuentas` (`num_cuenta`);

ALTER TABLE `movimientos`
    ADD CONSTRAINT `movimientos_tipos_movimiento`
        FOREIGN KEY (`movimientos_id`) REFERENCES `movimientos` (`id`);

ALTER TABLE `amortizaciones`
    ADD CONSTRAINT `amortizaciones_prestamos`
        FOREIGN KEY (`prestamos_id`) REFERENCES `prestamos` (`id`);