package csc394.artisanshop.services;

import csc394.artisanshop.entities.Order;
import csc394.artisanshop.entities.Product;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    Order placeOrder(UUID userId, Long shopId, List<Product> products);
}
