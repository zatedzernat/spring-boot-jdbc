CREATE TABLE students
(
    id         SERIAL PRIMARY KEY,
    first_name varchar(30)        NOT NULL,
    last_name  varchar(30)        NOT NULL,
    email      varchar(30) UNIQUE NOT NULL,
    age        integer            NOT NULL
);