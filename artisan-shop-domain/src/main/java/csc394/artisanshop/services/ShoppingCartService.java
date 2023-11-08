package csc394.artisanshop.services;

import csc394.artisanshop.entities.Order;
import csc394.artisanshop.entities.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCart addCartItem(Long userId, Long itemId);
    void removeCartItem(Long userId, Long itemId);
    ShoppingCart getCart(Long userId);
    Order checkout(Long userId); // This will handle the order placement
}


