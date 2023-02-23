package com.example.notetune.repositories;

import com.example.notetune.Models.Customer;
import com.example.notetune.Models.CustomerCountry;
import com.example.notetune.Models.CustomerGenre;
import com.example.notetune.Models.CustomerSpender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final String url;
    private final String username;
    private final String password;

    public CustomerRepositoryImpl(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Customer> findAll() {
        ArrayList<Customer> customers = new ArrayList<>();
        String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email  FROM customer";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Customer customer = new Customer(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(7));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public List<Customer> getPage(Integer limit, Integer offset) {
        ArrayList<Customer> customers = new ArrayList<>();
        String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email  " +
                "FROM customer ORDER BY customer_id LIMIT ? OFFSET ?";
        try (
                Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            ResultSet result = statement.executeQuery();

            while(result.next())
            {
                Customer customer = new Customer(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(7)
                );
                customers.add(customer);
            }

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Customer findById(Integer id) {
        Customer customer = null;
        String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email  " +
                "FROM customer WHERE customer_id= ?";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                customer = new Customer(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return customer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Customer> getCustomerByName(String name) {
        ArrayList<Customer> customers = new ArrayList<>();
        String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email  " +
                "FROM customer WHERE first_name LIKE ? OR last_name LIKE ?";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + name + "%");
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Customer customer = new Customer(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(7));
                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int insert(Customer customer) {
        String sql = "INSERT INTO " + "customer(first_name, last_name, country, postal_code, phone, email)" +
                "VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, customer.first_name);
            statement.setString(2, customer.last_name);
            statement.setString(3, customer.country);
            statement.setString(4, customer.postal_code);
            statement.setString(5, customer.phone);
            statement.setString(6, customer.email);

           return statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(Integer id, Customer customer) {
        String sql = "INSERT INTO " + "customer(first_name, last_name, country, postal_code, phone, email)" +
                "VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, customer.first_name);
            statement.setString(2, customer.last_name);
            statement.setString(3, customer.country);
            statement.setString(4, customer.postal_code);
            statement.setString(5, customer.phone);
            statement.setString(6, customer.email);

            return statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerGenre> getCustomerGenrePopularity(Integer customerId) {
        String sql = """
                SELECT i.customer_id, g.name as most_popular_genre
                FROM invoice as i
                         JOIN invoice_line as il on i.invoice_id = il.invoice_id
                         JOIN track as t on il.track_id = t.track_id
                         JOIN genre g on t.genre_id = g.genre_id
                WHERE i.customer_id = ?
                GROUP BY i.customer_id, g.name
                HAVING count(g.genre_id) = (
                    SELECT count(g.genre_id) as genre_popularity
                    FROM invoice as i
                             JOIN invoice_line as il on i.invoice_id = il.invoice_id
                             JOIN track as t on il.track_id = t.track_id
                             JOIN genre g on t.genre_id = g.genre_id
                    WHERE i.customer_id = ?
                    GROUP BY i.customer_id, g.genre_id
                    ORDER BY genre_popularity DESC
                    LIMIT 1)""";
        List<CustomerGenre> customerGenre = new ArrayList<>();

        try(Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, customerId);
            statement.setInt(2, customerId);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                customerGenre.add(new CustomerGenre(
                        result.getInt("customer_id"),
                        result.getString("most_popular_genre")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerGenre;
    }

    /**
     * @return country
     */
    @Override
    public CustomerCountry getCountryWithMostCustomers() {
        CustomerCountry country = null;
        String sql = "SELECT country, COUNT (*) as customer_amount FROM customer " +
                "GROUP BY country ORDER BY customer_amount desc limit 1";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                country = new CustomerCountry(
                        result.getString("country"),
                        result.getInt("customer_amount"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return country;
    }

    /**
     * @return customerSpender
     */
    @Override
    public CustomerSpender highestSpender() {
        CustomerSpender customerSpender = null;
        String sql = """
         SELECT customer.customer_id, customer.first_name, customer.last_name, SUM(invoice.total) AS highest_spender
                FROM invoice
        INNER JOIN customer ON customer.customer_id = invoice.customer_id
        GROUP BY customer.first_name, customer.last_name, customer.customer_id
        ORDER BY SUM(invoice.total) DESC
         LIMIT 1""";
        try(Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                customerSpender = new CustomerSpender(
                        result.getInt( "customer_id"),
                        result.getString("first_name"),
                        result.getString("last_name"),
                        result.getDouble("highest_spender")
                );
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return customerSpender;
    }
}
