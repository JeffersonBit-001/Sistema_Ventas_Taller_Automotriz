-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-12-2023 a las 21:55:38
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyecto_poo`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id_cliente` int(11) NOT NULL,
  `cod_empresa` int(11) NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `apellido` varchar(200) NOT NULL,
  `dni` int(11) NOT NULL,
  `direccion` varchar(200) NOT NULL,
  `celular` int(11) NOT NULL,
  `tipo` varchar(5) NOT NULL,
  `fecha` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id_cliente`, `cod_empresa`, `nombre`, `apellido`, `dni`, `direccion`, `celular`, `tipo`, `fecha`) VALUES
(1, 4530, 'Pedro', 'Castillo', 11111111, 'Carabayllo', 981100988, 'NE', '2023-09-18'),
(2, 4530, 'Lucas', 'Hernández', 12300015, 'Lima Norte', 987623133, 'NE', '2023-09-18'),
(3, 4530, 'José', 'Martial', 21312318, 'Comas', 900001230, 'NE', '2023-09-18'),
(4, 4530, 'Miguel', 'Monte', 23000127, 'Lima Centro', 900123123, 'NE', '2023-09-20'),
(5, 4530, 'María', 'López', 78781233, 'Comas', 971212002, 'NE', '2023-09-20'),
(6, 4530, 'Roberto', 'López', 72281234, 'Comas', 971212000, 'NE', '2023-10-14'),
(7, 4530, 'José', 'Martial', 21312313, 'Comas', 900001231, 'NE', '2023-10-14'),
(21, 4530, 'Martín', 'Quispe', 12912002, 'Comas', 900001108, 'NE', '2023-11-14'),
(29, 4530, 'Pedro', 'Romax', 12312343, 'Comas', 923123123, 'NE', '2023-11-23'),
(30, 4530, 'Alberto', 'Merino', 70986701, 'Comas', 987654321, 'NE', '2023-11-24'),
(31, 4530, 'Iñaki', 'Peña', 79621562, 'Callao', 963852741, 'NE', '2023-12-05');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_factura`
--

CREATE TABLE `detalle_factura` (
  `id_factura` int(11) NOT NULL,
  `cantidad_servicios` int(11) NOT NULL,
  `cant_prod_usados` int(11) NOT NULL,
  `total_servicio` double NOT NULL,
  `desc_igv` double NOT NULL,
  `pagocondesc` double NOT NULL,
  `tipo` varchar(5) NOT NULL,
  `tipo2` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalle_factura`
--

INSERT INTO `detalle_factura` (`id_factura`, `cantidad_servicios`, `cant_prod_usados`, `total_servicio`, `desc_igv`, `pagocondesc`, `tipo`, `tipo2`) VALUES
(1, 1, 10, 500, 90, 590, 'C', 'NE'),
(2, 1, 10, 500, 90, 590, 'C', 'NE'),
(3, 2, 8, 800, 144, 944, 'C', 'NE'),
(4, 2, 10, 800, 144, 944, 'C', 'NE'),
(5, 2, 10, 800, 144, 944, 'C', 'NE'),
(6, 0, 12, 0, 0, 0, 'S', 'NE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_factura2`
--

CREATE TABLE `detalle_factura2` (
  `id_factura` int(11) NOT NULL,
  `id_vehiculos` int(11) NOT NULL,
  `id_servicio` int(11) NOT NULL,
  `id_productos` int(11) NOT NULL,
  `cantidad_servicios` int(11) NOT NULL,
  `cant_prod_usados` int(11) NOT NULL,
  `total_servicio` double NOT NULL,
  `desc_igv` double NOT NULL,
  `pagocondesc` double NOT NULL,
  `tipo` varchar(2) NOT NULL,
  `tipo2` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalle_factura2`
--

INSERT INTO `detalle_factura2` (`id_factura`, `id_vehiculos`, `id_servicio`, `id_productos`, `cantidad_servicios`, `cant_prod_usados`, `total_servicio`, `desc_igv`, `pagocondesc`, `tipo`, `tipo2`) VALUES
(97, 42, 1, 1, 2, 1, 900, 162, 1062, 'C', 'NE'),
(97, 42, 4, 1, 2, 3, 900, 162, 1062, 'C', 'NE'),
(97, 42, 1, 4, 2, 1, 900, 162, 1062, 'C', 'NE'),
(97, 42, 4, 9, 2, 1, 900, 162, 1062, 'C', 'NE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_pago`
--

CREATE TABLE `detalle_pago` (
  `id_pagos` int(11) NOT NULL,
  `sueldo` double NOT NULL,
  `serv_adicional` int(11) NOT NULL,
  `precio_aum` double NOT NULL,
  `bonificacion` double NOT NULL,
  `total` double NOT NULL,
  `tipo` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalle_pago`
--

INSERT INTO `detalle_pago` (`id_pagos`, `sueldo`, `serv_adicional`, `precio_aum`, `bonificacion`, `total`, `tipo`) VALUES
(8, 1400, 2, 12, 24, 1424, 'NE'),
(11, 1400, 3, 10, 30, 1430, 'NE'),
(14, 1200, 2, 12, 24, 1224, 'NE'),
(17, 1200, 2, 13, 26, 1226, 'NE'),
(19, 1200, 5, 12, 60, 1260, 'NE'),
(40, 1200, 5, 17, 85, 1285, 'NE'),
(47, 1400, 2, 11, 22, 1422, 'NE'),
(48, 1200, 2, 13, 26, 1226, 'NE'),
(53, 1200, 12, 6, 72, 1272, 'NE'),
(56, 1200, 5, 12, 60, 1260, 'NE'),
(57, 1200, 2, 13, 26, 1226, 'NE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE `empresa` (
  `cod_empresa` int(11) NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `ruc` varchar(100) NOT NULL,
  `dueño` varchar(200) NOT NULL,
  `direccion` varchar(200) NOT NULL,
  `fecha` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empresa`
--

INSERT INTO `empresa` (`cod_empresa`, `nombre`, `ruc`, `dueño`, `direccion`, `fecha`) VALUES
(4530, 'SASEL AUTOMOTRIZ', '20609926989', 'Edwin Jesús Gamarra', 'JR. Santa Catalina NRO.153 URB. S.R.I.', '2023-09-18');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `id_factura` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_vehiculos` int(11) NOT NULL,
  `id_servicio` int(11) NOT NULL,
  `id_productos` int(11) NOT NULL,
  `tipo` varchar(5) NOT NULL,
  `tipo2` varchar(5) NOT NULL,
  `hora` time NOT NULL DEFAULT current_timestamp(),
  `fecha` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`id_factura`, `id_cliente`, `id_vehiculos`, `id_servicio`, `id_productos`, `tipo`, `tipo2`, `hora`, `fecha`) VALUES
(1, 1, 1, 1, 1, 'C', 'NE', '18:15:00', '2023-11-24'),
(2, 1, 1, 1, 2, 'C', 'NE', '18:15:18', '2023-11-24'),
(3, 30, 41, 15, 8, 'C', 'NE', '18:52:57', '2023-11-24'),
(4, 30, 41, 1, 1, 'C', 'NE', '18:53:33', '2023-11-24'),
(5, 30, 41, 1, 4, 'C', 'NE', '18:54:02', '2023-11-24'),
(6, 30, 41, 1, 4, 'S', 'NE', '12:06:02', '2023-11-30');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura2`
--

CREATE TABLE `factura2` (
  `id_factura` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_usuarios` int(11) NOT NULL,
  `tipo` varchar(2) NOT NULL,
  `tipo2` varchar(2) NOT NULL,
  `hora` time NOT NULL DEFAULT current_timestamp(),
  `fecha` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `factura2`
--

INSERT INTO `factura2` (`id_factura`, `id_cliente`, `id_usuarios`, `tipo`, `tipo2`, `hora`, `fecha`) VALUES
(97, 31, 3, 'C', 'NE', '15:31:10', '2023-12-05');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagos`
--

CREATE TABLE `pagos` (
  `id_pagos` int(11) NOT NULL,
  `id_usuarios` int(11) NOT NULL,
  `tipo` varchar(5) NOT NULL,
  `hora` time NOT NULL DEFAULT current_timestamp(),
  `fecha` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pagos`
--

INSERT INTO `pagos` (`id_pagos`, `id_usuarios`, `tipo`, `hora`, `fecha`) VALUES
(8, 3, 'NE', '14:51:59', '2023-10-07'),
(11, 1, 'NE', '02:31:15', '2023-10-09'),
(14, 4, 'NE', '03:15:41', '2023-10-09'),
(17, 8, 'NE', '19:30:45', '2023-10-09'),
(19, 13, 'NE', '20:26:53', '2023-10-14'),
(40, 22, 'NE', '19:09:55', '2023-11-04'),
(47, 3, 'NE', '20:46:20', '2023-11-19'),
(48, 4, 'NE', '17:48:22', '2023-11-21'),
(53, 35, 'NE', '21:00:48', '2023-11-23'),
(55, 36, 'NE', '15:42:09', '2023-12-05'),
(56, 13, 'NE', '15:42:22', '2023-12-05'),
(57, 8, 'NE', '15:44:23', '2023-12-05');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id_productos` int(11) NOT NULL,
  `cod_empresa` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(200) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio_unidad` double NOT NULL,
  `total` double NOT NULL,
  `tipo` varchar(5) NOT NULL,
  `hora` time NOT NULL DEFAULT current_timestamp(),
  `fecha` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id_productos`, `cod_empresa`, `nombre`, `descripcion`, `cantidad`, `precio_unidad`, `total`, `tipo`, `hora`, `fecha`) VALUES
(1, 4530, 'Clavos', 'Nueva marca', 805, 12, 13320, 'NE', '09:15:00', '2023-10-15'),
(2, 4530, 'Pernos', 'Comunes', 982, 14, 15680, 'NE', '19:27:11', '2023-10-15'),
(4, 4530, 'Engranajes', 'Mejor calidad', 816, 13, 13130, 'NE', '23:05:52', '2023-11-19'),
(7, 4530, 'Pegamento', 'Reforzar', 9, 21, 1050, 'NE', '18:46:48', '2023-11-21'),
(8, 4530, 'Cintas', 'Adherentes', 43, 120, 2400, 'NE', '18:52:08', '2023-11-24'),
(9, 4530, 'Pintura aerosol', 'Doble capa', 1, 125, 375, 'NE', '15:26:25', '2023-12-05');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicios`
--

CREATE TABLE `servicios` (
  `id_servicio` int(11) NOT NULL,
  `cod_empresa` int(11) NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `descripcion` varchar(200) NOT NULL,
  `precio` decimal(10,0) NOT NULL,
  `tipo` varchar(5) NOT NULL,
  `hora` time NOT NULL DEFAULT current_timestamp(),
  `fecha` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `servicios`
--

INSERT INTO `servicios` (`id_servicio`, `cod_empresa`, `nombre`, `descripcion`, `precio`, `tipo`, `hora`, `fecha`) VALUES
(1, 4530, 'Mantenimiento', 'Limpieza', 500, 'NE', '12:32:09', '2023-09-18'),
(2, 4530, 'Cambio de puerta', 'Fortalecer', 340, 'NE', '12:32:09', '2023-09-18'),
(3, 4530, 'Repuesto', 'Cambio de frenos', 120, 'NE', '12:32:09', '2023-09-18'),
(4, 4530, 'Lavado', 'Carros', 400, 'NE', '12:32:09', '2023-09-20'),
(5, 4530, 'Cambio', 'Parabrisas', 230, 'NE', '12:32:09', '2023-09-20'),
(6, 4530, 'Engranaje', 'Tuercas', 123, 'NE', '12:32:09', '2023-09-20'),
(7, 4530, 'Limpiador', 'Caja', 121, 'NE', '12:32:09', '2023-09-20'),
(8, 4530, 'Cambio de frenos', 'Pedales', 650, 'NE', '12:32:09', '2023-09-20'),
(12, 4530, 'Parchado', 'Asientos', 652, 'NE', '19:51:42', '2023-11-08'),
(13, 4530, 'Placas', 'Cambio', 124, 'NE', '15:25:26', '2023-11-21'),
(14, 4530, 'Soldadura', 'Tornillos', 125, 'NE', '18:46:03', '2023-11-21'),
(15, 4530, 'Empaquetar', 'Forrado', 300, 'NE', '18:50:49', '2023-11-24'),
(16, 4530, 'Pintado', 'Remasterizado', 500, 'NE', '15:21:30', '2023-12-05');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuarios` int(11) NOT NULL,
  `cod_empresa` int(11) NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `apellido` varchar(200) NOT NULL,
  `dni` int(11) NOT NULL,
  `correo` varchar(200) NOT NULL,
  `celular` varchar(200) NOT NULL,
  `usuario` varchar(200) NOT NULL,
  `pass` varchar(100) NOT NULL,
  `rol` varchar(25) NOT NULL,
  `fecha` date NOT NULL DEFAULT current_timestamp(),
  `tipo` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuarios`, `cod_empresa`, `nombre`, `apellido`, `dni`, `correo`, `celular`, `usuario`, `pass`, `rol`, `fecha`, `tipo`) VALUES
(1, 4530, 'Jefferson', 'Pernia', 99912390, 'jefferson1@gmail.com', '900012324', 'U22220722', 'jef23', 'Administrador', '2023-09-18', 'NE'),
(3, 4530, 'Yair', 'Aguero', 99911215, 'yairg@gmail.com', '900032114', 'U22219929', 'ya3', 'Empleado', '2023-09-18', 'NE'),
(4, 4530, 'Raúl', 'Socualaya', 34512399, 'raul2@gmail.com', '900000022', 'U09090915', 'raul123', 'Empleado', '2023-09-18', 'NE'),
(5, 4530, 'Kevin', 'Cañari', 44432112, 'kevin@gmail.com', '900002315', 'U22900123', '4x3', 'Empleado', '2023-09-18', 'NE'),
(8, 4530, 'Juan', 'Martin', 99191114, 'martins@gmail.com', '990000123', 'U00909011', '000', 'Empleado', '2023-10-01', 'NE'),
(13, 4530, 'Pedro', 'Lobaton', 89812397, 'pedro@gmail.com', '987000019', 'U07890119', '907', 'Empleado', '2023-10-01', 'NE'),
(22, 4530, 'Alessandro', 'Bustamante', 77712392, 'aless1@gmail.com', '988777126', 'U12300023', 'kevin', 'Empleado', '2023-10-06', 'NE'),
(35, 4530, 'Javier', 'Albino', 10234002, 'jav@gmail.com', '900012322', 'U20202211', '123', 'Empleado', '2023-11-23', 'NE'),
(36, 4530, 'Ernesto', 'Valverde', 99912399, 'er@gmail.com', '987654321', 'U22270882', 'er22', 'Empleado', '2023-11-24', 'NE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculos`
--

CREATE TABLE `vehiculos` (
  `id_vehiculos` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `placa` varchar(200) NOT NULL,
  `modelo` varchar(200) NOT NULL,
  `color` varchar(200) NOT NULL,
  `tipo` varchar(5) NOT NULL,
  `fecha` date NOT NULL DEFAULT current_timestamp(),
  `hora` time NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `vehiculos`
--

INSERT INTO `vehiculos` (`id_vehiculos`, `id_cliente`, `placa`, `modelo`, `color`, `tipo`, `fecha`, `hora`) VALUES
(1, 1, 'I-2317', 'Toyota', 'Celeste', 'NE', '2023-09-18', '18:26:37'),
(2, 2, 'U-0901', 'Lomes', 'Negro', 'NE', '2023-09-18', '18:27:55'),
(3, 3, 'O-9000', 'Supra', 'Negro', 'NE', '2023-09-18', '22:43:00'),
(4, 1, 'I-Z913', 'Mercedez-Benz', 'Negro', 'NE', '2023-09-20', '18:32:39'),
(7, 4, 'R-tx40', 'Toyota', 'Verde', 'NE', '2023-10-18', '19:50:56'),
(8, 7, 'L-Zx32', 'Toyota', 'Morado', 'NE', '2023-11-04', '21:51:47'),
(29, 7, 'L-ZT99', 'Toyota', 'Morado', 'NE', '2023-11-21', '15:33:34'),
(40, 29, 'Xs-102', 'Ferrari', 'Rojo', 'NE', '2023-11-23', '21:35:36'),
(41, 30, 'U-222', 'Toyota', 'Negro', 'NE', '2023-11-24', '18:49:53'),
(42, 31, 'A-205', 'Audi', 'Negro', 'NE', '2023-12-05', '15:17:25');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id_cliente`),
  ADD KEY `cod_empresa` (`cod_empresa`);

--
-- Indices de la tabla `detalle_factura`
--
ALTER TABLE `detalle_factura`
  ADD KEY `id_factura` (`id_factura`);

--
-- Indices de la tabla `detalle_factura2`
--
ALTER TABLE `detalle_factura2`
  ADD KEY `id_factura` (`id_factura`),
  ADD KEY `id_productos` (`id_productos`),
  ADD KEY `id_vehiculos` (`id_vehiculos`),
  ADD KEY `id_servicio` (`id_servicio`);

--
-- Indices de la tabla `detalle_pago`
--
ALTER TABLE `detalle_pago`
  ADD KEY `id_pagos` (`id_pagos`);

--
-- Indices de la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`cod_empresa`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`id_factura`),
  ADD KEY `id_cliente` (`id_cliente`);

--
-- Indices de la tabla `factura2`
--
ALTER TABLE `factura2`
  ADD PRIMARY KEY (`id_factura`),
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_usuarios` (`id_usuarios`);

--
-- Indices de la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD PRIMARY KEY (`id_pagos`),
  ADD KEY `id_usuarios` (`id_usuarios`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id_productos`),
  ADD KEY `cod_empresa` (`cod_empresa`);

--
-- Indices de la tabla `servicios`
--
ALTER TABLE `servicios`
  ADD PRIMARY KEY (`id_servicio`),
  ADD KEY `cod_empresa` (`cod_empresa`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuarios`),
  ADD KEY `cod_empresa` (`cod_empresa`);

--
-- Indices de la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  ADD PRIMARY KEY (`id_vehiculos`),
  ADD KEY `id_cliente` (`id_cliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `id_factura` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `factura2`
--
ALTER TABLE `factura2`
  MODIFY `id_factura` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=98;

--
-- AUTO_INCREMENT de la tabla `pagos`
--
ALTER TABLE `pagos`
  MODIFY `id_pagos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id_productos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `servicios`
--
ALTER TABLE `servicios`
  MODIFY `id_servicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuarios` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT de la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  MODIFY `id_vehiculos` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD CONSTRAINT `clientes_ibfk_1` FOREIGN KEY (`cod_empresa`) REFERENCES `empresa` (`cod_empresa`);

--
-- Filtros para la tabla `detalle_factura`
--
ALTER TABLE `detalle_factura`
  ADD CONSTRAINT `detalle_factura_ibfk_1` FOREIGN KEY (`id_factura`) REFERENCES `factura` (`id_factura`);

--
-- Filtros para la tabla `detalle_factura2`
--
ALTER TABLE `detalle_factura2`
  ADD CONSTRAINT `detalle_factura2_ibfk_1` FOREIGN KEY (`id_factura`) REFERENCES `factura2` (`id_factura`),
  ADD CONSTRAINT `detalle_factura2_ibfk_2` FOREIGN KEY (`id_productos`) REFERENCES `productos` (`id_productos`),
  ADD CONSTRAINT `detalle_factura2_ibfk_3` FOREIGN KEY (`id_vehiculos`) REFERENCES `vehiculos` (`id_vehiculos`),
  ADD CONSTRAINT `detalle_factura2_ibfk_4` FOREIGN KEY (`id_servicio`) REFERENCES `servicios` (`id_servicio`);

--
-- Filtros para la tabla `detalle_pago`
--
ALTER TABLE `detalle_pago`
  ADD CONSTRAINT `detalle_pago_ibfk_1` FOREIGN KEY (`id_pagos`) REFERENCES `pagos` (`id_pagos`);

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `factura_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`);

--
-- Filtros para la tabla `factura2`
--
ALTER TABLE `factura2`
  ADD CONSTRAINT `factura2_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`),
  ADD CONSTRAINT `factura2_ibfk_2` FOREIGN KEY (`id_usuarios`) REFERENCES `usuarios` (`id_usuarios`);

--
-- Filtros para la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD CONSTRAINT `pagos_ibfk_1` FOREIGN KEY (`id_usuarios`) REFERENCES `usuarios` (`id_usuarios`);

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`cod_empresa`) REFERENCES `empresa` (`cod_empresa`);

--
-- Filtros para la tabla `servicios`
--
ALTER TABLE `servicios`
  ADD CONSTRAINT `servicios_ibfk_1` FOREIGN KEY (`cod_empresa`) REFERENCES `empresa` (`cod_empresa`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`cod_empresa`) REFERENCES `empresa` (`cod_empresa`);

--
-- Filtros para la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  ADD CONSTRAINT `vehiculos_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
