CREATE DATABASE IF NOT EXISTS informacion_personas;

USE informacion_personas;


CREATE TABLE IF NOT EXISTS Provincia (
    Id_prov INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS oSexual (
    Id_sexual INT AUTO_INCREMENT PRIMARY KEY,
    Descripcion VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS aPolitica (
    Id_poli INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS nAcademica (
    Id_acad INT AUTO_INCREMENT PRIMARY KEY,
    Descripcion VARCHAR(100) NOT NULL UNIQUE
);


CREATE TABLE IF NOT EXISTS Persona (
    Id_perso INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Apell1 VARCHAR(100) NOT NULL,
    Apell2 VARCHAR(100) NOT NULL,
    Sexo CHAR(1) NOT NULL CHECK (Sexo IN ('F', 'M', 'O')),
    eCivil CHAR(1) NOT NULL CHECK (eCivil IN ('S', 'C', 'D', 'V', 'U')),
    Nacido DATE NOT NULL,
    Id_prov INT NOT NULL,
    Id_sexual INT NOT NULL,
    Id_poli INT NOT NULL,
    Id_acad INT NOT NULL,
    Salario INT NOT NULL CHECK (Salario >= 0),
    FOREIGN KEY (Id_prov) REFERENCES Provincia(Id_prov),
    FOREIGN KEY (Id_sexual) REFERENCES oSexual(Id_sexual),
    FOREIGN KEY (Id_poli) REFERENCES aPolitica(Id_poli),
    FOREIGN KEY (Id_acad) REFERENCES nAcademica(Id_acad)
);

CREATE INDEX idx_persona_nombre ON Persona(Nombre, Apell1, Apell2);
CREATE INDEX idx_persona_nacido ON Persona(Nacido);
CREATE INDEX idx_persona_salario ON Persona(Salario);

