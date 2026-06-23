package org.example.inventoryorderapi.controller;

import org.example.inventoryorderapi.entity.OrderItem;
import org.example.inventoryorderapi.service.OrderItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderitems")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping
    public OrderItem createOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemService.save(orderItem);
    }

    @PatchMapping("/{id}")
    public OrderItem updateQuantity(@PathVariable Long id,
                                    @RequestParam int quantity) {
        return orderItemService.updateQuantity(id, quantity);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderItem(@PathVariable Long id) {
        orderItemService.delete(id);
    }
}