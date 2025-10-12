CREATE TABLE `tecnologia` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(60) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `capacidad_tecnologias` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_tecnologia` bigint DEFAULT NULL,
  `id_capacidad` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_tecnologia_capacidad` (`id_tecnologia`,`id_capacidad`),
  KEY `id_tecnologia_tecnologia_idx` (`id_tecnologia`),
  CONSTRAINT `id_tecnologia_tecnologia` FOREIGN KEY (`id_tecnologia`) REFERENCES `tecnologia` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci