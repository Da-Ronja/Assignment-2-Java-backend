package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(PostgradDAO.class, args);
		PostgradDAO hej = new PostgradDAO();
		hej.getCustomerByID(2);
		ArrayList<Customer> opo = hej.getAllCustomers();
		for(Customer c : opo){
			System.out.println(c);
		}

	}




}


