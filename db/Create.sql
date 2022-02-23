USE db_university;

CREATE TABLE user (
    ID int NOT NULL,
    username varchar(255) NOT NULL,
    password varchar(255),
    full_name varchar(255),
    email varchar(255),
    date_created date,
    PRIMARY KEY (ID)
);