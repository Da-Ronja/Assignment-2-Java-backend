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
//        postgradDAO.getAllCustomers(); // TODO return or System.out.println();
//        postgradDAO.getCustomerByID(12);
//         postgradDAO.getCustomerByName("Luis");
         postgradDAO.getCustomersPage(10, 1); //Not fully functional, working on fix
        // postgradDAO.addCustomer(new Customer("Ronja", "Von Stroll", "SomeWhere", "12345", "0102224533", "ronja.von.stroll@hotmail.com"));

// 		Customer newCustomer = new Customer(1, "Milovan", "Glisovic",
//				"Serbia", "123456", "987654321", "Mil@gmail.com");
//      postgradDAO.updateCostumer(1, newCustomer);

        postgradDAO.getCountryWithMostCustomers();
        postgradDAO.highestSpender();
        postgradDAO.getCustomerGenrePopularity(12);

    }
}
