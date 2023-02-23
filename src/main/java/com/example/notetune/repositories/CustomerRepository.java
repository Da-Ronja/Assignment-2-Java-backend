package com.example.notetune.repositories;

import com.example.notetune.Models.Customer;
import com.example.notetune.Models.CustomerCountry;
import com.example.notetune.Models.CustomerGenre;
import com.example.notetune.Models.CustomerSpender;

import java.sql.SQLException;
import java.util.List;

public interface CustomerRepository extends CRUDRepository<Customer, Integer> {

    /**
     * Retrieves a list of customers whose first or last name contains the specified string.
     *
     * @param name the string to search for in the first and last names of the customers.
     * @return a list of Customer objects whose first or last name contains the specified string.
     * @throws SQLException if a database access error occurs.
     */
    List<Customer> getCustomerByName(String name) throws SQLException;

    /**
     * Retrieves the most popular genre of a customer based on the number of times they purchased tracks from each genre.
     * @param id the ID of the customer to get the most popular genre for.
     * @return a list of CustomerGenre objects representing the most popular genre of the given customer.
     * @throws SQLException if a database access error occurs.
     */
    List<CustomerGenre> getCustomerGenrePopularity(Integer id) throws SQLException;

    /**
     * Retrieves the country with the most costumers from the database.
     * @return a CustomerCountry object representing the country and the respective amount
     * @throws SQLException if a database access error occurs.
     */
    CustomerCountry getCountryWithMostCustomers() throws SQLException;

    /**
     * Retrieves customer with the highest total invoice amount from database.
     * @return a CustomerSpender object representing the customer who has the highest total invoice amount and the respective amount.
     * @throws SQLException if a database access error occurs.
     */
    CustomerSpender highestSpender() throws SQLException;

}
