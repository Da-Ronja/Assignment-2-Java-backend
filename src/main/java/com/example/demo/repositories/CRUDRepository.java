package com.example.demo.repositories;

import com.example.demo.Customer;

import java.util.List;

public interface CRUDRepository<T, U> {
    List<T> getAllCustomers();

    List<T> getCustomersPage(U idx, U idy);

    T getCustomerByID(U id);

    List<Customer> getCustomerByName(String name);

    void addCustomer(T object);

    void updateCostumer(U id, T customer);

}