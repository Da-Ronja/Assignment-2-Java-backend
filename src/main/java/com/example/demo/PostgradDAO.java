package com.example.demo;

import java.sql.*;
import java.util.ArrayList;

public class PostgradDAO {
    // Default values that can be overridden
    private String url = "jdbc:postgresql://localhost:5432/Chinook";
    private String username = "postgres";
    private String password = "postgres";

    public PostgradDAO() {
    }

    public PostgradDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void test() {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connected to Postgres...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Customer> getAllCustomers() {
        ArrayList customers = new ArrayList<>();
        String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email  FROM customer";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            // Execute statement
            ResultSet result = statement.executeQuery();


            while (result.next()) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public Customer getCustomerByID(int id) {
        Customer customer = null;
        String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email  " +
                "FROM customer WHERE customer_id= ?";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            // Execute statement
            ResultSet result = statement.executeQuery();


            while (result.next()) {
                customer = new Customer(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(7)
                );

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return customer;
    }


    public ArrayList<Customer> getCustomerByName(String name) {
        ArrayList customers = new ArrayList<>();
        String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email  " +
                "FROM customer WHERE first_name LIKE ? OR last_name LIKE ?";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + name + "%");
            // Execute statement
            ResultSet result = statement.executeQuery();


            while (result.next()) {
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    // 5. Add a new customer to the database. You also need to add only the fields listed above (our customer object)

    public void create(Customer customer) {
        String sql = "INSERT INTO " +
                "customer(first_name, last_name, country, postal_code, phone, email)" +
                "VALUES(?, ?, ?, ?, ?, ?)";
        try(Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, customer.first_name);
            statement.setString(2, customer.last_name);
            statement.setString(3, customer.country);
            statement.setString(4, customer.postal_code);
            statement.setString(5, customer.phone);
            statement.setString(6, customer.email);

            int result = statement.executeUpdate();
            System.out.println("Result: " + result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}





