# Inventory & Order Management API

## How to Run

1. Open the project in IntelliJ IDEA.
2. Make sure Maven dependencies are downloaded.
3. Run InventoryOrderApiApplication.
4. The application starts on:

http://localhost:8080

## Entities

- Category
- Product
- Customer
- Order
- OrderItem

## Business Rules

1. Products belong to a category.
2. Customers can create orders.
3. Orders contain order items.
4. Order items reference products.
5. Confirming an order reduces product stock.
6. Cancelling an order does not restore stock.
7. Low stock products can be retrieved using the low-stock endpoint.

## Assumptions

1. Product stock is reduced when an order is confirmed.
2. Stock is not restored after order cancellation.
3. One order can contain multiple order items.
4. Each order item references one product.

## Tested Endpoints

POST /categories

POST /products

POST /customers

POST /orders

POST /orderitems

POST /orders/{id}/confirm

POST /orders/{id}/status

GET /products/{id}

GET /products

GET /products/low-stock?threshold=20

1. Create Category  
   Method: POST  
   URL: http://localhost:8080/categories  
   Purpose: Creates a new product category.

2. Create Product  
   Method: POST  
   URL: http://localhost:8080/products  
   Purpose: Creates a new product under a category with price and initial stock quantity.

3. Create Customer  
   Method: POST  
   URL: http://localhost:8080/customers  
   Purpose: Creates a new customer.

4. Create Draft Order  
   Method: POST  
   URL: http://localhost:8080/orders  
   Purpose: Creates a new draft order for a customer.

5. Add Product Line to Order  
   Method: POST  
   URL: http://localhost:8080/orderitems  
   Purpose: Adds a product and quantity to an order.

6. Confirm Order  
   Method: POST  
   URL: http://localhost:8080/orders/1/confirm  
   Purpose: Confirms the order, calculates total amount, and reduces product stock.

7. Check Product After Confirmation  
   Method: GET  
   URL: http://localhost:8080/products/1  
   Purpose: Checks product stock after order confirmation.

8. Low Stock Report  
   Method: GET  
   URL: http://localhost:8080/products/low-stock?threshold=20  
   Purpose: Shows all products below the selected stock threshold.

9. Cancel Order  
   Method: POST  
   URL: http://localhost:8080/orders/1/status?status=CANCELLED  
   Purpose: Cancels a confirmed order and restores the product stock.

10. Check Product After Cancellation  
    Method: GET  
    URL: http://localhost:8080/products/1  
    Purpose: Checks that stock was restored after cancellation.

11. Manual Stock Adjustment  
    Method: POST  
    URL: http://localhost:8080/products/1/stock-adjustment?quantity=5  
    Purpose: Manually increases or decreases product stock.

12. Get Order Total  
    Method: GET  
    URL: http://localhost:8080/orders/1  
    Purpose: Gets order details including total amount.

13. Update Order Item Quantity  
    Method: PATCH  
    URL: http://localhost:8080/orderitems/1?quantity=5  
    Purpose: Updates the quantity of an order item.

14. Delete Order Item  
    Method: DELETE  
    URL: http://localhost:8080/orderitems/1  
    Purpose: Removes an item from an order.