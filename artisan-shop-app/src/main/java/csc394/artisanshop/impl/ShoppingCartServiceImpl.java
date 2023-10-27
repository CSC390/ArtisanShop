package csc394.artisanshop.impl;

import csc394.artisanshop.entities.Order;
import csc394.artisanshop.entities.ShoppingCart;
import csc394.artisanshop.repositories.ProductDtoRepository;
import csc394.artisanshop.repositories.ShoppingCartRepository;
import csc394.artisanshop.services.ShoppingCartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductDtoRepository productDtoRepository;

    @Autowired
    public ShoppingCartServiceImpl(final ShoppingCartRepository shoppingCartRepository,
                                   final ProductDtoRepository productDtoRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productDtoRepository = productDtoRepository;
    }


    @Transactional
    @Override
    public ShoppingCart addCartItem(Long userId, Long itemId) {
        return null;
    }

    @Transactional
    @Override
    public void removeCartItem(Long userId, Long itemId) {
        // Your logic to remove item from cart
        // Similar to addCartItem but removing instead
    }

    @Transactional
    @Override
    public ShoppingCart getCart(Long userId) {
        return null;
    }

    @Override
    public Order checkout(Long userId) {
        return null;
    }
}
