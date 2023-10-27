package csc394.artisanshop.impl;

import csc394.artisanshop.datamapper.ShoppingCartMapper;
import csc394.artisanshop.dto.ProductDto;
import csc394.artisanshop.dto.ShoppingCartDto;
import csc394.artisanshop.dto.UserDto;
import csc394.artisanshop.entities.Order;
import csc394.artisanshop.entities.ShoppingCart;
import csc394.artisanshop.repositories.ProductDtoRepository;
import csc394.artisanshop.repositories.ShoppingCartRepository;
import csc394.artisanshop.services.ShoppingCartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public ShoppingCart addCartItem(final Long userId, final Long itemId) {
        Optional<ShoppingCartDto> cartDtoOptional = shoppingCartRepository.findByUser_UserId(userId);
        ShoppingCartDto cartDto = cartDtoOptional.orElse(new ShoppingCartDto()); // create a new cart if not found
        Optional<ProductDto> itemDtoOptional = productDtoRepository.findById(itemId);

        if (itemDtoOptional.isPresent()) {
            cartDto.getItems().add(itemDtoOptional.get());
            cartDto = shoppingCartRepository.save(cartDto);
            return ShoppingCartMapper.toShoppingCart(cartDto);
        }
        throw new IllegalArgumentException("Item with ID: " + itemId + " not found");
    }

    @Override
    public void removeCartItem(final Long userId, final Long itemId) {
        Optional<ShoppingCartDto> cartDtoOptional = shoppingCartRepository.findByUser_UserId(userId);
        if (cartDtoOptional.isPresent()) {
            ShoppingCartDto cartDto = cartDtoOptional.get();
            cartDto.getItems().removeIf(item -> item.getId().equals(itemId));
            shoppingCartRepository.save(cartDto);
        } else {
            throw new IllegalArgumentException("Cart not found for user ID: " + userId);
        }
    }
    @Transactional
    @Override
    public ShoppingCart getCart(final Long userId) {
        Optional<ShoppingCartDto> cartDtoOptional = shoppingCartRepository.findByUser_UserId(userId);
        if (cartDtoOptional.isPresent()) {
            return ShoppingCartMapper.toShoppingCart(cartDtoOptional.get());
        }
        ShoppingCartDto newCartDto = new ShoppingCartDto();
        UserDto userDto = new UserDto();
        userDto.setUserId(userId);
        newCartDto.setUser(userDto);
        ShoppingCart newCart = ShoppingCartMapper.toShoppingCart(newCartDto);
        newCartDto = shoppingCartRepository.save(newCartDto);
        return ShoppingCartMapper.toShoppingCart(newCartDto);
    }

    @Override
    public Order checkout(Long userId) {
        return null;
    }
}
