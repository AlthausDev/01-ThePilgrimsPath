DROP DATABASE IF EXISTS bdperegrinos_samuelAlthaus;

CREATE DATABASE bdperegrinos_samuelAlthaus CHARSET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE bdperegrinos_samuelAlthaus;

CREATE TABLE Tcredenciales(	
	pkId INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	cNombre VARCHAR (50) NOT NULL UNIQUE,
	cPerfil CHECK IN ("PEREGRINO", "ADMIN_PARADA", "ADMIN"),
	cPassword VARCHAR (50) NOT NULL
);


CREATE TABLE Tperegrino (
    pkfkIdPeregrino INT UNSIGNED PRIMARY KEY,
    cNombrePer VARCHAR(50) NOT NULL,
    cNacionalidad VARCHAR (50) NOT NULL,
    FOREIGN KEY (pkfkIdPeregrino)
    	REFERENCES Tcredenciales (pkID)
    
);

CREATE TABLE Tparada (
    pkfkIdParada INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    cNombrePar VARCHAR(50) NOT NULL,
    cRegion CHAR (1) NOT NULL,
    FOREIGN KEY (pkfkIdParada)
    	REFERENCES Tcredenciales (pkID)
);

CREATE TABLE Testancia (
	pkIdEstancia INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    dFecha DATE,
    bVip TINYINT(1) DEFAULT 0,    
    fkIdPeregrino INT UNSIGNED,
    fkIdParada INT UNSIGNED,
	FOREIGN KEY (fkIdPeregrino)
		REFERENCES Tperegrino(pkIdPeregrino),
	FOREIGN KEY (fkIdParada)
		REFERENCES Tparada(pkIdParada)
);

CREATE TABLE Tcarnet (	
    dFechaExp DATE,
    nDistancia DOUBLE,
    nVips INT,
    
    fkIdPeregrino INT UNSIGNED PRIMARY KEY,
    fkIdParada INT UNSIGNED,    
    FOREIGN KEY (fkIdPeregrino)
		REFERENCES Tperegrino(pkIdPeregrino),
	FOREIGN KEY (fkIdParada)
		REFERENCES Tparada(pkIdParada),
    UNIQUE (fkIdPeregrino)
);


CREATE TABLE Tperegrino_parada(
	fkIdPeregrino INT UNSIGNED,
    fkIdParada INT UNSIGNED,
    
    PRIMARY KEY (fkIdPeregrino, fkIdParada),
	FOREIGN KEY (fkIdPeregrino)
		REFERENCES Tperegrino(pkIdPeregrino),
	FOREIGN KEY (fkIdParada)
		REFERENCES Tparada(pkIdParada)
);
            
