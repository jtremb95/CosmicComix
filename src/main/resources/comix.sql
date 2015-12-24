-- Set up the database
CREATE DATABASE cosmic;
USE cosmic;

-- Set up a User to log in with
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(16) NOT NULL,
    `password` varchar(40) NOT NULL,
    PRIMARY KEY(`id`),
    INDEX(`username`),
    CONSTRAINT `uc_username` UNIQUE (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1;
INSERT INTO users VALUES (1, 'joe', 'tester');

-- Create Comics Table with a few comics
DROP TABLE IF EXISTS `comics`;
CREATE TABLE `comics` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `series` VARCHAR(64) NOT NULL,
    `title` VARCHAR(64) NOT NULL,
    `info` VARCHAR(256),
    PRIMARY KEY(`id`),
    INDEX(`series`, `title`)
) ENGINE=InnoDB AUTO_INCREMENT=1;
-- TODO: populate

-- Create Favorites table
DROP TABLE IF EXISTS `favorites`;
CREATE TABLE `favorites` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(16) NOT NULL,
    `series` VARCHAR(64) NOT NULL,
    `title` VARCHAR(64) NOT NULL,
    PRIMARY KEY(`id`),
    INDEX(`username`),
    INDEX(`series`, `title`),
    CONSTRAINT FOREIGN KEY (`username`)
        REFERENCES users(`username`)
        ON DELETE CASCADE ON UPDATE CASCADE, 
    CONSTRAINT FOREIGN KEY (`series`, `title`)
        REFERENCES comics(`series`, `title`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1;

-- Create Wall
DROP TABLE IF EXISTS `wall`;
CREATE TABLE `wall` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `receiver` VARCHAR(16) NOT NULL,
    `sender` VARCHAR(16) NOT NULL,
    `message` VARCHAR(512) NOT NULL,
    PRIMARY KEY(`id`),
    INDEX(`receiver`),
    INDEX(`sender`),
    CONSTRAINT FOREIGN KEY (`receiver`)
        REFERENCES users(`username`)
        ON DELETE CASCADE ON UPDATE CASCADE, 
    CONSTRAINT FOREIGN KEY (`sender`)
        REFERENCES users(`username`)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1;

-- Give the webapp a means to access the database
CREATE USER 'miss'@'localhost' IDENTIFIED BY 'information';
GRANT SELECT ON cosmic.users TO 'miss'@'localhost' IDENTIFIED BY 'information';
GRANT SELECT ON cosmic.comics TO 'miss'@'localhost' IDENTIFIED BY 'information';
GRANT SELECT, INSERT, DELETE ON cosmic.favorites TO 'miss'@'localhost' IDENTIFIED BY 'information';
GRANT SELECT, INSERT ON cosmic.wall TO 'miss'@'localhost' IDENTIFIED BY 'information';