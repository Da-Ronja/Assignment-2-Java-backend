# Data Access with JDBC and SQL
This is the the second backend assignment provided by Noroff.

## Description
This assignment is divided in two parts. First part focused on creating a database and using it manipulating SQL data in Postgres. The second part focused on building a Spring Boot application using Java and manipulating SQL data through JDBC. Both parts are intended to  interact with the database and implementing functionality to read, add, update, and delete data in the database, as well as perform various queries on the data.

The assignment is divided into two parts:

- Appendix A: SQL scripts to create the Superheroes database
- Appendix B: Reading data with JDBC from the Chinook database

### Appendix A: SQL scripts to create database
Contains several SQL scripts to create a database, set up tables, adding relationships and populate them with data. The theme of the database is superheroes, and it contains tables for Superhero, Assistant, and Power. 
The scripts should be run in order from 1 to 8 in order to create this functional database.

The following scripts features are available:

- Table relationships
- Inserting data
- Updating data
- Deleting data

### Appendix B: Reading data with JDBC 
A Spring Boot application that contains functionality for interacting with the Chinook using the JDBC with the PostgreSQL driver. It includes CRUD operations for customer management and reporting. 

The following features are available:

- Read all customers in the database
- Read a specific customer by ID
- Read a specific customer by name
- Return a page of customers
- Add a new customer to the database
- Update an existing customer
- Return the country with the most customers
- Return the customer who is the highest spender
- For a given customer, their most popular genre

## Tools and Requirments
- IntelliJ Ultimate JDK 
- Spring Boot
- Postgres 
- PgAdmin
- Postgres SQL driver dependency
- Maven


## Files and directories
The project structure is organized as follows:

- main/: Code for the application
- test/: 
- pom.xml: Build file for Spring boot and Maven
- SuperheroesDb/: SQL scripts
- README.md: This file
