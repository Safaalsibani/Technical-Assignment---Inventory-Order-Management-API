package org.example.inventoryorderapi.service;

import org.example.inventoryorderapi.entity.Order;
import org.example.inventoryorderapi.entity.OrderItem;
import org.example.inventoryorderapi.entity.Product;
import org.example.inventoryorderapi.repository.OrderItemRepository;
import org.example.inventoryorderapi.repository.OrderRepository;
import org.example.inventoryorderapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository,
                        ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    public Order save(Order order) {
        if (order.getStatus() == null) {
            order.setStatus("DRAFT");
        }
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order confirmOrder(Long orderId) {
        Order order = getOrderById(orderId);

        List<OrderItem> items = orderItemRepository.findByOrderId(orderId);

        double total = 0;

        for (OrderItem item : items) {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getStockQuantity() < item.getQuantity()) {
                throw new RuntimeException("Not enough stock for product: " + product.getName());
            }

            total += product.getPrice() * item.getQuantity();
        }

        for (OrderItem item : items) {
            Product product = productRepository.findById(item.getProduct().getId()).get();
            product.setStockQuantity(product.getStockQuantity() - item.getQuantity());
            productRepository.save(product);
        }

        order.setTotalAmount(total);
        order.setStatus("CONFIRMED");
        return orderRepository.save(order);
    }

    public Order updateStatus(Long id, String status) {
        Order order = getOrderById(id);

        if (status.equals("CANCELLED") && order.getStatus().equals("CONFIRMED")) {
            List<OrderItem> items = orderItemRepository.findByOrderId(id);

            for (OrderItem item : items) {
                Product product = productRepository.findById(item.getProduct().getId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));

                product.setStockQuantity(product.getStockQuantity() + item.getQuantity());
                productRepository.save(product);
            }
        }

        order.setStatus(status);
        return orderRepository.save(order);
    }
}