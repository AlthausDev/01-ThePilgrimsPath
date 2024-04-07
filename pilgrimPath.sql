-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-03-2024 a las 14:49:31
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `pilgrimPath`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `admin_parada`
--

CREATE TABLE IF NOT EXISTS `admin_parada` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `perfil` enum('PEREGRINO','ADMIN_PARADA','ADMIN_GENERAL','INVITADO') NOT NULL,
  `parada_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKgeowllxicarssfc0gu7c5346x` (`parada_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `admin_parada`:
--   `parada_id`
--       `parada` -> `id`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carnet`
--

CREATE TABLE IF NOT EXISTS `carnet` (
  `id` bigint(20) NOT NULL,
  `distancia` double NOT NULL,
  `fecha_exp` date NOT NULL,
  `nvips` int(11) NOT NULL,
  `parada_inicial_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe9buwv591b23rb6ylb0yecef1` (`parada_inicial_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `carnet`:
--   `parada_inicial_id`
--       `parada` -> `id`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `credenciales`
--

CREATE TABLE IF NOT EXISTS `credenciales` (
  `id` bigint(20) NOT NULL,
  `password_test` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `perfil` enum('PEREGRINO','ADMIN_PARADA','ADMIN_GENERAL','INVITADO') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `credenciales`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estancia`
--

CREATE TABLE IF NOT EXISTS `estancia` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `vip` bit(1) DEFAULT NULL,
  `parada_id` bigint(20) DEFAULT NULL,
  `peregrino_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKns33vu4hrun13fbl2wbgsnw49` (`parada_id`),
  KEY `FKa6a2yum4fscmfw9y3237cbkuh` (`peregrino_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `estancia`:
--   `peregrino_id`
--       `peregrino` -> `id`
--   `parada_id`
--       `parada` -> `id`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `parada`
--

CREATE TABLE IF NOT EXISTS `parada` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `region` char(1) NOT NULL,
  `admin_parada` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gdoo9g7vn525wmmgxqthjydqk` (`admin_parada`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `parada`:
--   `admin_parada`
--       `admin_parada` -> `id`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peregrino`
--

CREATE TABLE IF NOT EXISTS `peregrino` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `perfil` enum('PEREGRINO','ADMIN_PARADA','ADMIN_GENERAL','INVITADO') NOT NULL,
  `nacionalidad` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `peregrino`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peregrino_paradas`
--

CREATE TABLE IF NOT EXISTS `peregrino_paradas` (
  `peregrino_id` bigint(20) NOT NULL,
  `parada_id` bigint(20) NOT NULL,
  KEY `FKof56ret1283u7i4m3jp7vdydd` (`parada_id`),
  KEY `FKdii63fmwteawls48dukq4oxm8` (`peregrino_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELACIONES PARA LA TABLA `peregrino_paradas`:
--   `peregrino_id`
--       `peregrino` -> `id`
--   `parada_id`
--       `parada` -> `id`
--

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `admin_parada`
--
ALTER TABLE `admin_parada`
  ADD CONSTRAINT `FKqjomo1o0e5olbjrmctijqdt2i` FOREIGN KEY (`parada_id`) REFERENCES `parada` (`id`);

--
-- Filtros para la tabla `carnet`
--
ALTER TABLE `carnet`
  ADD CONSTRAINT `FKe9buwv591b23rb6ylb0yecef1` FOREIGN KEY (`parada_inicial_id`) REFERENCES `parada` (`id`);

--
-- Filtros para la tabla `estancia`
--
ALTER TABLE `estancia`
  ADD CONSTRAINT `FKa6a2yum4fscmfw9y3237cbkuh` FOREIGN KEY (`peregrino_id`) REFERENCES `peregrino` (`id`),
  ADD CONSTRAINT `FKns33vu4hrun13fbl2wbgsnw49` FOREIGN KEY (`parada_id`) REFERENCES `parada` (`id`);

--
-- Filtros para la tabla `parada`
--
ALTER TABLE `parada`
  ADD CONSTRAINT `FKhr9t32wwrign16vmwmjaemx2d` FOREIGN KEY (`admin_parada`) REFERENCES `admin_parada` (`id`);

--
-- Filtros para la tabla `peregrino_paradas`
--
ALTER TABLE `peregrino_paradas`
  ADD CONSTRAINT `FKdii63fmwteawls48dukq4oxm8` FOREIGN KEY (`peregrino_id`) REFERENCES `peregrino` (`id`),
  ADD CONSTRAINT `FKof56ret1283u7i4m3jp7vdydd` FOREIGN KEY (`parada_id`) REFERENCES `parada` (`id`);
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
