package csc394.artisanshop.services;

import csc394.artisanshop.entities.Order;
import csc394.artisanshop.entities.Product;

import java.util.List;

public interface OrderService {
    Order placeOrder(Long userId, Long shopId, List<Product> products);
}
