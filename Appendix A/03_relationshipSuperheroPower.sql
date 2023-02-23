CREATE TABLE superhero_power (
	superhero_power_id SERIAL PRIMARY KEY,
	superhero_id int REFERENCES superhero,
	power_id int REFERENCES power
);

