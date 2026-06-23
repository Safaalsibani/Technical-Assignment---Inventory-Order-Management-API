package org.example.inventoryorderapi.service;

import org.example.inventoryorderapi.entity.OrderItem;
import org.example.inventoryorderapi.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public OrderItem updateQuantity(Long id, int quantity) {

        OrderItem item = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order item not found"));

        item.setQuantity(quantity);

        return orderItemRepository.save(item);
    }

    public void delete(Long id) {
        orderItemRepository.deleteById(id);
    }
}