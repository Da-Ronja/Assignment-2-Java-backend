package com.example.notetune;

import com.example.notetune.Models.Customer;
import com.example.notetune.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class PgAppRunner implements ApplicationRunner {

    private final CustomerRepository customerRepository;

    @Autowired
    public PgAppRunner(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(customerRepository.findAll());
        System.out.println(customerRepository.findById(12));
        System.out.println(customerRepository.getPage(10, 10));

        int insert = customerRepository.insert(new Customer("Robin", "Hood", "Sherwood skogen",
                "12345", "0102224533", "robin.hood@hotmail.com"));
        System.out.println("Insert" + insert);

        Customer newCustomer = new Customer("Milovan", "Glisovic",
                "Serbia", "123456", "987654321", "Mil@gmail.com");
        System.out.println(customerRepository.update(1, newCustomer));

        System.out.println(customerRepository.getCustomerByName("Luis"));
        System.out.println(customerRepository.getCustomerGenrePopularity(12));
        System.out.println(customerRepository.getCountryWithMostCustomers());
        System.out.println(customerRepository.highestSpender());
    }
}
