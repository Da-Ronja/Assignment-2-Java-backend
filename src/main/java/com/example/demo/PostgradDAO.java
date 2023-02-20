package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
        try(Connection conn = DriverManager.getConnection(url, username,password);) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public void getCustomerByID(int id) {
        ArrayList customers = new ArrayList<>();

        String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email  " +
                "FROM customer WHERE customer_id= ?";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            // Execute statement
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }

    public void getCustomerByName(String name) {
        String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email  " +
                "FROM customer WHERE first_name LIKE '%?%' or last_name LIKE '%?%'";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(String, 1);
            // Execute statement
            ResultSet result = statement.executeQuery();


            while(result.next())
            {
                System.out.println(result.getInt(1));
                System.out.println(result.getString(2));
                System.out.println(result.getString(3));
                System.out.println(result.getString(4));
                System.out.println(result.getString(5));
                System.out.println(result.getString(6));
                System.out.println(result.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

