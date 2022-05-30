package com.project.springbootecommerce.dao;

import com.project.springbootecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByEmail(String email);
}
