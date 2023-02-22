package com.example.demo.repositories;

import com.example.demo.Customer;
import com.example.demo.Models.CustomerCountry;
import com.example.demo.Models.CustomerGenre;
import com.example.demo.Models.CustomerSpender;

import java.util.List;

public interface CustomerRepository extends CRUDRepository<Customer, Integer> {

    List<CustomerGenre> getCustomerGenrePopularity(Integer id);
    CustomerCountry getCountryWithMostCustomers();
    CustomerSpender highestSpender();

}
