DROP DATABASE IF EXISTS antiCovid;
CREATE DATABASE antiCovid;
USE antiCovid;

CREATE TABLE User (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    login TEXT(20) NOT NULL UNIQUE,
    password TEXT(50) NOT NULL,
    firstName TEXT(20) NOT NULL,
    lastName TEXT(20) NOT NULL,
    birthDate DATE NOT NULL,
    isInfected BOOLEAN NOT NULL DEFAULT 0,
    isContact BOOLEAN NOT NULL DEFAULT 0,
    isAdmin BOOLEAN NOT NULL DEFAULT 0
);

CREATE TABLE Location (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name TEXT(100) NOT NULL,
    address TEXT(100) NOT NULL,
    gps TEXT(100) NOT NULL
);

CREATE TABLE Activity (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    title TEXT(100) NOT NULL,
    startDate DATE NOT NULL DEFAULT NOW(),
    endDate DATE NOT NULL DEFAULT NOW(),
    location INT NOT NULL,
    user INT NOT NULL,
    CHECK (startDate <= endDate),
    FOREIGN KEY (location) REFERENCES Location(id),
    FOREIGN KEY (user) REFERENCES User(id)
);

CREATE TABLE Friends (
    user INT NOT NULL,
    friend INT NOT NULL CHECK ( friend <> user ),
    FOREIGN KEY (user) REFERENCES User(id),
    FOREIGN KEY (friend) REFERENCES User(id),
    PRIMARY KEY (user, friend)
);

CREATE TABLE Notification (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    text TEXT(255) NOT NULL DEFAULT 'Pas de texte',
    receivedDate DATE NOT NULL DEFAULT NOW(),
    user INT NOT NULL,
    FOREIGN KEY (user) REFERENCES User(id)
)