package com.example.demo;

public class Customer {

    int customer_id;
     public String first_name;
    public String last_name;
    public String country;
    public String postal_code;
    public String phone;
    public String email;

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", country='" + country + '\'' +
                ", postal_code='" + postal_code + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Customer(int customer_id, String first_name, String last_name, String country, String postal_code, String phone, String email) {
        this.customer_id = customer_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.country = country;
        this.postal_code = postal_code;
        this.phone = phone;
        this.email = email;
    }

    public Customer(String first_name, String last_name, String country, String postal_code, String phone, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.country = country;
        this.postal_code = postal_code;
        this.phone = phone;
        this.email = email;
    }
}
