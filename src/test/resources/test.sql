-- Set up a User to log in with
CREATE TABLE Users (
    id BIGINT,
    username VARCHAR(16) NOT NULL,
    password varchar(40) NOT NULL,
);

-- Create Comics Table with a few comics
CREATE TABLE Comics (
    --id BIGINT NOT NULL AUTO_INCREMENT,
    series VARCHAR(64) NOT NULL,
    title VARCHAR(64) NOT NULL,
    info VARCHAR(256),
);

-- Create Favorites table
CREATE TABLE Favorites (
    --id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(16) NOT NULL,
    series VARCHAR(64) NOT NULL,
    title VARCHAR(64) NOT NULL,
);

-- Create Wall
CREATE TABLE Wall (
    --id BIGINT NOT NULL AUTO_INCREMENT,
    receiver VARCHAR(16) NOT NULL,
    sender VARCHAR(16) NOT NULL,
    message VARCHAR(512) NOT NULL,
);

INSERT INTO Users (username, password) VALUES ('joe', 'tester');
insert into comics (series, title, info) values ('Amazing Spiderman', 'Superman', 'This is not real');
insert into favorites (username, series, title) values ('joe', 'Amazing Spiderman', 'Superman');
insert into wall (receiver, sender, message) values ('joe', 'jason', 'I hate comics');