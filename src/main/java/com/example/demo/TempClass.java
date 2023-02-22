package com.example.demo;

import java.sql.*;
import java.util.ArrayList;

public class TempClass {
    private String url = "jdbc:postgresql://localhost:5432/Chinook";
    private String username = "postgres";
    private String password = "postgres";

    public TempClass() {
    }

    public ArrayList<Customer> getCustomersPage(int x, int y) {
    ArrayList customers = new ArrayList<>();
    String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email  " +
            "FROM customer ORDER BY customer_id LIMIT ? OFFSET ?";
        try (
    Connection conn = DriverManager.getConnection(url, username, password)) {
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, x);
        statement.setInt(2, y);
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


    public void updateCostumer(int id, Customer newcustomer) {
        String sql = "UPDATE customer SET first_name = ?, last_name = ?, country = ?, postal_code = ?, phone = ?, email = ? " +
                "WHERE customer_id = ?";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, newcustomer.first_name);
            statement.setString(2, newcustomer.last_name);
            statement.setString(3, newcustomer.country);
            statement.setString(4, newcustomer.postal_code);
            statement.setString(5, newcustomer.phone);
            statement.setString(6, newcustomer.email);

            statement.setInt(7, id);


            statement.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();

        }

    }



    }