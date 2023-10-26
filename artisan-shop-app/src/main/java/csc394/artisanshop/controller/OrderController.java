package csc394.artisanshop.controller;

import csc394.artisanshop.entities.Order;
import csc394.artisanshop.entities.Product;
import csc394.artisanshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/orders")
@RestController
public class OrderController {

    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/buy")
    public ResponseEntity<Order> placeOrder(@RequestParam UUID userId, @RequestParam Long shopId, @RequestBody List<Product> products) {
        Order placedOrder = orderService.placeOrder(userId, shopId, products);
        if (placedOrder != null) {
            return new ResponseEntity<>(placedOrder, HttpStatus.CREATED);
        } else {
            // Return an appropriate error message if the order couldn't be placed
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}