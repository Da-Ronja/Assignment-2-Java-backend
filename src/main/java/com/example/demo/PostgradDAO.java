package com.example.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        ArrayList<Customer> customers = new ArrayList<>();
        String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email  FROM customer";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            // Execute statement
            // TODO extract method???
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
                        result.getString(7));

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return customer;
    }


    public ArrayList<Customer> getCustomerByName(String name) {
        ArrayList<Customer> customers = new ArrayList<>();
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
                        result.getString(7));
                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    // 5. Add a new customer to the database. You also need to add only the fields listed above (our customer object)

    public void addCustomer(Customer customer) {
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

            int result = statement.executeUpdate();
            System.out.println("Result: " + result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //
    // 7. Return the country with the most customers.
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

    // 9. For a given customer, their most popular genre (in the case of a tie, display both). Most popular in this
    // context means the genre that corresponds to the most tracks from invoices associated to that customer.
    public List<CustomerGenre> getCustomerGenrePopularity(int customerId) {
        List<CustomerGenre> customerGenre = new ArrayList<>();

        String sql = "SELECT i.customer_id, g.name as most_popular_genre\n" +
                "FROM invoice as i\n" +
                "         JOIN invoice_line as il on i.invoice_id = il.invoice_id\n" +
                "         JOIN track as t on il.track_id = t.track_id\n" +
                "         JOIN genre g on t.genre_id = g.genre_id\n" +
                "WHERE i.customer_id = ?\n" +
                "GROUP BY i.customer_id, g.name\n" +
                "HAVING count(g.genre_id) = (\n" +
                "    SELECT count(g.genre_id) as genre_popularity\n" +
                "    FROM invoice as i\n" +
                "             JOIN invoice_line as il on i.invoice_id = il.invoice_id\n" +
                "             JOIN track as t on il.track_id = t.track_id\n" +
                "             JOIN genre g on t.genre_id = g.genre_id\n" +
                "    WHERE i.customer_id = ?\n" +
                "    GROUP BY i.customer_id, g.genre_id\n" +
                "    ORDER BY genre_popularity DESC\n" +
                "    LIMIT 1)";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
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

}





