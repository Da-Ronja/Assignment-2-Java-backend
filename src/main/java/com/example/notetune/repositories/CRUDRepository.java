package com.example.notetune.repositories;

import java.sql.SQLException;
import java.util.List;

public interface CRUDRepository<T, U> {
    /**
     * Retrieves a list of all objects.
     *
     * @return a list of T objects.
     * @throws SQLException if a database access error occurs.
     */
    List<T> findAll() throws SQLException;

    /**
     * Retrieves a customer by ID.
     *
     * @param id the ID of the object to retrieve.
     * @return the T object with the specified ID, or null if no such object exists.
     * @throws SQLException if a database access error occurs.
     */
    T findById(U id) throws SQLException;

    /**
     * Retrieves a page of object, with a specified limit and offset.
     *
     * @param limit the maximum of object to retrieve.
     * @param offset the index of the first objects to retrieve.
     * @return a list of T objects representing the requested subset of object.
     * @throws SQLException if a database access error occurs.
     */
    List<T> getPage(U limit, U offset) throws SQLException;

    /**
     * Adds an object to the database.
     *
     * @param object the T object to add to the database.
     * @return the number of rows affected by the INSERT statement (1 if successful, 0 if unsuccessful).
     * @throws SQLException if a database access error occurs.
     */
    int insert(T object) throws SQLException;

    /**
     * Updates an object in the database.
     *
     * @param id the ID of the object to update.
     * @param object the updated T object.
     * @return the number of rows affected by the UPDATE statement (1 if successful, 0 if unsuccessful).
     * @throws SQLException if a database access error occurs.
     */
    int update(U id, T object) throws SQLException;

}