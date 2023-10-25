package csc394.artisanshop.services;

import csc394.artisanshop.entities.Item;
import csc394.artisanshop.entities.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    /**
     * Places an order.
     *
     * @param userId the ID of the user placing the order.
     * @param shopId the ID of the shop from which the items are bought.
     * @param items the items to be ordered.
     * @return the placed order, or null if the order could not be placed.
     */
    Order placeOrder(UUID userId, Long shopId, List<Item> items);

    /**
     * Fetches orders by user ID.
     *
     * @param userId the ID of the user.
     * @return a list of orders for the specified user.
     */
    List<Order> getOrdersByUserId(Long userId);
}
