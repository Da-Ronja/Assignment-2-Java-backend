DROP TABLE IF EXISTS power;

CREATE TABLE power (
    power_id serial PRIMARY KEY,
    power_name varchar(50) NOT NULL,
    description varchar(50)
);

DROP TABLE IF EXISTS assistant;

CREATE TABLE assistant (
    assistant_id serial PRIMARY KEY,
    assistant_name varchar(50) NOT NULL
);

DROP TABLE IF EXISTS superhero;

CREATE TABLE superhero (
    superhero_id serial PRIMARY KEY,
    superhero_name varchar(50) NOT NULL,
	superhero_alias varchar(50) NOT NULL,
	origin_alias varchar(50) NOT NULL

);
