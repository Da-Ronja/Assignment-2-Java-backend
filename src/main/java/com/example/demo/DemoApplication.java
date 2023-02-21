package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

//		SpringApplication.run(PostgradDAO.class, args);
		PostgradDAO hej = new PostgradDAO();
//		System.out.println(hej.getCustomerByID(40));
//		ArrayList<Customer> opo = hej.getCustomerByName("Dan");
//		for(Customer c : opo){
//			System.out.println(c);

		TempClass hello = new TempClass();
//		ArrayList<Customer> opo = hello.getCustomersPage(10, 0);
//		for(Customer c : opo){
//			System.out.println(c);


		Customer newCustomer = new Customer(1, "Greger", "Glisovic",
				"Serbia", "123456", "987654321", "Mil@gmail.com");

		System.out.println(hej.getCustomerByID(1));

		hello.updateCostumer(1, newCustomer);

		System.out.println(hej.getCustomerByID(1));






	}
	}







