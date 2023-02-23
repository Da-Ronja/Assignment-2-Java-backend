package com.example.demo.repositories;

import com.example.demo.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CRUDRepository<T, U> {
    /**
     * Retrieves a list of all customers.
     *
     * @return a list of T Customer objects.
     * @throws SQLException if a database access error occurs.
     */
    List<T> getAllCustomers() throws SQLException;

    /**
     * Retrieves a page of customers, with a specified limit and offset.
     *
     * @param limit the maximum of customer to retrieve.
     * @param offset the index of the first customers to retrieve.
     * @return a list of T Customer objects representing the requested subset of customer.
     * @throws SQLException if a database access error occurs.
     */
    List<T> getCustomersPage(U limit, U offset) throws SQLException;

    /**
     * Retrieves a customer by ID.
     *
     * @param id the ID of the customer to retrieve.
     * @return the T Customer object with the specified ID, or null if no such customer exists.
     * @throws SQLException if a database access error occurs.
     */
    T getCustomerByID(U id) throws SQLException;

    /**
     * Retrieves a list of customers whose first or last name contains the specified string.
     *
     * @param name the string to search for in the first and last names of the customers.
     * @return a list of Customer objects whose first or last name contains the specified string.
     * @throws SQLException if a database access error occurs.
     */
    List<T> getCustomerByName(String name) throws SQLException; // TODO Should This Live here? Look at the patterns!

    /**
     * Adds a customer to the database.
     *
     * @param object the Customer object to add to the database.
     * @return the number of rows affected by the INSERT statement (1 if successful, 0 if unsuccessful).
     * @throws SQLException if a database access error occurs.
     */
    int addCustomer(T object) throws SQLException;

    /**
     * Updates a customer in the database.
     *
     * @param id the ID of the customer to update.
     * @param customer the updated Customer object.
     * @return the number of rows affected by the UPDATE statement (1 if successful, 0 if unsuccessful).
     * @throws SQLException if a database access error occurs.
     */
    int updateCostumer(U id, T customer) throws SQLException;

}