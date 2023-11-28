USE bdperegrinos_samuelAlthaus;

CREATE TABLE Tcredenciales (
    pkId INT UNSIGNED AUTO_INCREMENT UNIQUE,
    cNombre VARCHAR(50) NOT NULL PRIMARY KEY,
    cPerfil ENUM('PEREGRINO', 'ADMIN_PARADA', 'ADMIN') NOT NULL,
    cPassword VARCHAR(50) NOT NULL
);

CREATE TABLE Tperegrino (
    pkfkIdPeregrino INT UNSIGNED PRIMARY KEY,
    cNombrePer VARCHAR(50) NOT NULL,
    cNacionalidad VARCHAR(50) NOT NULL,
    FOREIGN KEY (pkfkIdPeregrino)
        REFERENCES Tcredenciales(pkId)
);

CREATE TABLE Tparada (
    pkfkIdParada INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    cNombre VARCHAR(50) NOT NULL,
    cRegion CHAR(1) NOT NULL,
    fkIdAdminParada INT UNSIGNED UNIQUE,
    FOREIGN KEY (pkfkIdParada)
        REFERENCES Tcredenciales(pkId),
    FOREIGN KEY (fkIdAdminParada)
        REFERENCES Tadmin_parada(pkfkIdAdminParada)
);

CREATE TABLE Tadmin_parada (
    pkfkIdAdminParada INT UNSIGNED PRIMARY KEY,
    cNombre VARCHAR(50) NOT NULL,
    FOREIGN KEY (pkfkIdAdminParada)
        REFERENCES Tcredenciales(pkId)
);

CREATE TABLE Testancia (
    pkIdEstancia INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    dFecha DATE,
    bVip TINYINT(1) DEFAULT 0,
    fkIdPeregrino INT UNSIGNED,
    fkIdParada INT UNSIGNED,
    FOREIGN KEY (fkIdPeregrino)
        REFERENCES Tperegrino(pkfkIdPeregrino),
    FOREIGN KEY (fkIdParada)
        REFERENCES Tparada(pkfkIdParada)
);

CREATE TABLE Tcarnet (
    dFechaExp DATE,
    nDistancia DOUBLE,
    nVips INT,
    fkIdPeregrino INT UNSIGNED PRIMARY KEY,
    fkIdParada INT UNSIGNED,
    FOREIGN KEY (fkIdPeregrino)
        REFERENCES Tperegrino(pkfkIdPeregrino),
    FOREIGN KEY (fkIdParada)
        REFERENCES Tparada(pkfkIdParada),
);

CREATE TABLE Tperegrino_parada (
    fkIdPeregrino INT UNSIGNED,
    fkIdParada INT UNSIGNED,
    PRIMARY KEY (fkIdPeregrino, fkIdParada),
    FOREIGN KEY (fkIdPeregrino)
        REFERENCES Tperegrino(pkfkIdPeregrino),
    FOREIGN KEY (fkIdParada)
        REFERENCES Tparada(pkfkIdParada)
);


INSERT INTO Tcredenciales (cNombre, cPerfil, cPassword) VALUES
 ('admim', 'ADMIN', 'admim'),
 ('Juan', 'PEREGRINO', '123'),
 ('Maria', 'PEREGRINO', '543'),
 ('Alejandro', 'PEREGRINO', 'awd2'),
 ('Laura', 'PEREGRINO', 'asz3'),
 ('Javier', 'PEREGRINO', 'jyt5'),
 ('John', 'ADMIN_PARADA', 'admin123'),
 ('Claude', 'ADMIN_PARADA', 'admin456'),
 ('Patricia', 'ADMIN_PARADA', 'admin789'),
 ('Kumar', 'ADMIN_PARADA', 'admin010'),
 ('Sofia', 'ADMIN_PARADA', 'admin011');

 INSERT INTO Tadmin_parada (cNombreAdminParada) VALUES
 ('John'),
 ('Claude'),
 ('Patricia'),
 ('Kumar'),
 ('Sofia');

 INSERT INTO Tparada (cNombrePar, cRegion, fkIdAdminParada) VALUES
 ('Avilés', 'A', 7),
 ('Lugo', 'L', 8),
 ('Ponferrada', 'P', 9),
 ('Burgos', 'B', 10),
 ('Logroño', 'L', 11);

 INSERT INTO Tperegrino (pkfkIdPeregrino, cNombrePer, cNacionalidad) VALUES
 (2, 'Juan', 'Española'),
 (3, 'Maria', 'Austria'),
 (4, 'Alejandro', 'Italia'),
 (5, 'Laura', 'Noruega'),
 (6, 'Javier', 'Alemania');

INSERT INTO Tcarnet (dFechaExp, nDistancia, nVips, fkIdPeregrino, fkIdParada) VALUES
('2023-12-01', 960.3, 2, 2, 1),
('2023-12-01', 200.5, 1, 3, 5),
('2023-12-01', 80.5, 0, 4, 2),
('2023-12-01', 0, 0, 5, 3),
('2023-12-01', 0, 0, 6, 1);

INSERT INTO Tperegrino_parada (fkIdPeregrino, fkIdParada) VALUES
(2, 1), (2, 2), (2, 3), (2, 4), (2, 5),
(3, 1), (3, 2), (3, 3), (3, 4), (3, 5),
(4, 2). (4,5),
(5, 3),
(6, 1);

INSERT INTO Testancia (dFecha, bVip, fkIdPeregrino, fkIdParada) VALUES
('2023-11-24', 1, 2, 1),
('2023-11-25', 0, 2, 2),
('2023-11-26', 1, 2, 3),
('2023-11-27', 0, 2, 4),
('2023-11-28', 1, 2, 5);

('2023-11-24', 0, 3, 1),
('2023-11-25', 1, 3, 2);





