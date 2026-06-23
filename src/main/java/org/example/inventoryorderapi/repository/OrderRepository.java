package org.example.inventoryorderapi.repository;

import org.example.inventoryorderapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
