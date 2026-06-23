package org.example.inventoryorderapi.repository;

import org.example.inventoryorderapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}