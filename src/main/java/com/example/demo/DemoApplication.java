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
		System.out.println(hej.getCustomerByID(40));
		ArrayList<Customer> opo = hej.getCustomerByName("Ma");
		for(Customer c : opo){
			System.out.println(c);
		}
	}




}


