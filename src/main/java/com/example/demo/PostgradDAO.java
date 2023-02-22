package com.example.demo;

import com.example.demo.Models.CustomerCountry;
import com.example.demo.Models.CustomerGenre;
import com.example.demo.Models.CustomerSpender;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PostgradDAO {
    // Default values that can be overridden
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    public PostgradDAO() {
    }

    public PostgradDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    // 1. Read all the customers in the database, this should display their: Id, first name, last name, country, postal code,
    //phone number and email
    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email  FROM customer";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            // Execute statement
            // TODO extract method???
            resultStatementCustomer(customers, statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    // 2. Read a specific customer from the database (by Id), should display everything listed in the above point.
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
            System.out.println(customer);
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return customer;
    }

    // 3. Read a specific customer by name. HINT: LIKE keyword can help for partial matches.
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
            resultStatementCustomer(customers, statement);
            System.out.println(customers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    // 4. Return a page of customers from the database. This should take in limit and offset as parameters and make use
    //of the SQL limit and offset keywords to get a subset of the customer data. The customer model from above
    //should be reused
    public ArrayList<Customer> getCustomersPage(int x, int y) {
        ArrayList<Customer> customers = new ArrayList<>();
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
                System.out.println(customer);
            }

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    // Vad är detta :O
    private void resultStatementCustomer(ArrayList<Customer> customers, PreparedStatement statement) throws SQLException {
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

// 6. Update an existing customer.
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

            System.out.println(country);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return country;
    }

    // 8. Customer who is the highest spender (total in invoice table is the largest)
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
            System.out.println(customerSpender);
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return customerSpender;
    }

    // 9. For a given customer, their most popular genre (in the case of a tie, display both). Most popular in this
    // context means the genre that corresponds to the most tracks from invoices associated to that customer.
    public List<CustomerGenre> getCustomerGenrePopularity(int customerId) {
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
            // Write statement
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, customerId);
            statement.setInt(2, customerId);
            // Execute statement
            ResultSet result = statement.executeQuery();
            // Handle result
            while (result.next()) {
                customerGenre.add(new CustomerGenre(
                        result.getInt("customer_id"),
                        result.getString("most_popular_genre")
                ));
            }

            System.out.println(customerGenre);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerGenre;
    }

}





