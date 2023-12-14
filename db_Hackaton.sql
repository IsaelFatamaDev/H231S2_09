CREATE DATABASE hackaton;

USE hackaton;

CREATE TABLE
          estado (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    estMant VARCHAR(50)
          );

drop table equipos;

CREATE TABLE
          equipos (
                    id int AUTO_INCREMENT PRIMARY KEY,
                    nombre varchar(30),
                    serie varchar(30),
                    marca varchar(50),
                    modelo VARCHAR(50) NOT NULL,
                    espTecni TEXT,
                    fkestMant int,
                    precio DECIMAL(10, 2),
                    cantidad INT,
                    descripcion TEXT,
                    terminos CHAR(2),
                    estado CHAR(1),
                    FOREIGN KEY (fkestMant) REFERENCES estado (id)
          );

INSERT INTO
          estado (id, estMant)
VALUES
          (null, 'En funcionamiento'),
          (null, 'Necesita mantenimiento'),
          (null, 'Fuera de Servicio');

INSERT INTO
          equipos (
                    serie,
                    nombre,
                    marca,
                    modelo,
                    espTecni,
                    fkestMant,
                    precio,
                    cantidad,
                    descripcion,
                    terminos,
                    estado
          )
VALUES
          (
                    'XYZ789',
                    'Procesaor',
                    'Dell',
                    'Latitude',
                    '16GB RAM, 512GB SSD',
                    2,
                    900.50,
                    3,
                    'Procesador marca Dell, de quinta generacion',
                    'Si',
                    'A'
          );

select
          *
from
          equipos;

UPDATE equipos
set
          estado = 'A'
where
          id = 1;

UPDATE equipos
SET
          fkestMant = 1
where
          id = 1;

SELECT
          equipos.id,
          equipos.serie,
          equipos.nombre,
          equipos.marca,
          equipos.modelo,
          equipos.espTecni,
          estado.estMant,
          equipos.precio,
          equipos.cantidad,
          equipos.descripcion,
FROM
          equipos
          INNER JOIN estado ON equipos.fkestMant = estado.id;

SELECT
          estado.id
from
          estado
where
          estado.estMant = "En funcionamiento";