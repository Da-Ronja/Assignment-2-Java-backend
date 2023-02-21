package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;


@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(PostgradDAO.class, args);
        PostgradDAO hej = new PostgradDAO();
        System.out.println(hej.getCustomerByID(40));
        ArrayList<Customer> opo = hej.getCustomerByName("Ma");
        for (Customer c : opo) {
            System.out.println(c);
        }


        hej.addCustomer(new Customer("Hanna", "Andersson", "Sweden", "12133",
                "0102224533", "hanna,andersson@hotmail.com"));

        System.out.println(hej.getCountryWithMostCustomers());

        System.out.println(hej.getCustomerGenrePopularity(12));
    }


}


