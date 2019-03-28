package com.teste.vetta.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.vetta.models.Customer;

public interface CustomerService extends JpaRepository<Customer, Integer> {
	
}
