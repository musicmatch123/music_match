DROP SCHEMA IF EXISTS beats_match;

CREATE SCHEMA beats_match;

USE beats_match;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
    user_id int AUTO_INCREMENT,
    username VARCHAR(32) NOT NULL,
    date_of_birth VARCHAR(128),

    PRIMARY KEY(user_id)
);


INSERT INTO users (username , date_of_birth)
VALUES 
('erin', '05.20.1997'),
('reen','08.12.1998');