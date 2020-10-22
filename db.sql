CREATE TABLE mentors
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    nick_name VARCHAR(50) NOT NULL,
	phone_number VARCHAR(30),
    email VARCHAR(100),
    city VARCHAR(50) NOT NULL,
    favourite_number INT
);


CREATE TABLE applicants
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(30),
    email VARCHAR(100),
    application_code INT
);