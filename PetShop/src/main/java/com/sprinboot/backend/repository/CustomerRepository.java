package com.sprinboot.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprinboot.backend.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
