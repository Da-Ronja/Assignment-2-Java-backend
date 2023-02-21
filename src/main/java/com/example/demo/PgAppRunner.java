package com.example.demo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class PgAppRunner implements ApplicationRunner {

    private final PostgradDAO postgradDAO;

    public PgAppRunner(PostgradDAO postgradDAO) {
        this.postgradDAO = postgradDAO;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        postgradDAO.getAllCustomers(); // TODO return or System.out.println();
        postgradDAO.getCustomerByID(12); //TODO return or System.out.println();
         postgradDAO.getCustomerByName("Luis");
        // postgradDAO.addCustomer(new Customer("Ronja", "Von Stroll", "SomeWhere", "12345", "0102224533", "ronja.von.stroll@hotmail.com"));
        postgradDAO.getCountryWithMostCustomers();
        postgradDAO.getCustomerGenrePopularity(12);

    }
}
