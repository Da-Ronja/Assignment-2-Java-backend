package com.example.notetune.repositories;

import com.example.notetune.Customer;
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

    CustomerCountry getCountryWithMostCustomers() throws SQLException;
    CustomerSpender highestSpender() throws SQLException;

}
