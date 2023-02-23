package com.example.notetune;

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
//        customerRepository.getAllCustomers();
//         customerRepository.getCustomerByName("Luis");
//        customerRepository.getCustomersPage(10, 10);
//
//        System.out.println(      customerRepository.getCustomerByID(12)
//);
//
//
//
//
//        customerRepository.addCustomer(new Customer("Hej", "A", "SomeWhere", "12345", "0102224533", "ronja.von.stroll@hotmail.com"));
//
// 		Customer newCustomer = new Customer( "Milovan", "Glisovic",
//				"Serbia", "123456", "987654321", "Mil@gmail.com");
//
//        System.out.println(customerRepository.updateCostumer(1, newCustomer));
//        customerRepository.getCountryWithMostCustomers();
//        customerRepository.highestSpender();
//        customerRepository.getCustomerGenrePopularity(12);

    }
}
