package com.example.demo.repositories;

import com.example.demo.Customer;
import com.example.demo.Models.CustomerCountry;
import com.example.demo.Models.CustomerGenre;
import com.example.demo.Models.CustomerSpender;

import java.sql.SQLException;
import java.util.List;

public interface CustomerRepository extends CRUDRepository<Customer, Integer> {

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
