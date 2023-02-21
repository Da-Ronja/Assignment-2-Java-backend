package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements ApplicationRunner {

    @Autowired
    PostgradDAO postgradDAO;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        postgradDAO.getAllCustomers(); // TODO return or sout
        postgradDAO.getCustomerByID(12); //TODO return or sout
      //  postgradDAO.getCustomerByName("Luis");
        // postgradDAO.addCustomer(new Customer("Ronja", "Von Stroll", "SomeWhere", "12345", "0102224533", "ronja.von.stroll@hotmail.com"));
        postgradDAO.getCountryWithMostCustomers();
        postgradDAO.getCustomerGenrePopularity(12);
    }
}


