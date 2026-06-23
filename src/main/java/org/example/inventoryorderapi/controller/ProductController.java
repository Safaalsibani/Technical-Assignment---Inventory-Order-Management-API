package org.example.inventoryorderapi.controller;

import org.example.inventoryorderapi.entity.Product;
import org.example.inventoryorderapi.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/low-stock")
    public List<Product> getLowStockProducts(@RequestParam int threshold) {
        return productService.getLowStockProducts(threshold);
    }

    @PostMapping("/{productId}/stock-adjustment")
    public Product adjustStock(@PathVariable Long productId,
                               @RequestParam int quantity) {
        return productService.adjustStock(productId, quantity);
    }
}