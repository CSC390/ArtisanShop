package csc394.artisanshop.datamapper;

import csc394.artisanshop.dto.CartItemDto;
import csc394.artisanshop.dto.ShoppingCartDto;
import csc394.artisanshop.entities.CartItem;
import csc394.artisanshop.entities.ShoppingCart;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartMapper {

    public static ShoppingCartDto toShoppingCartDto(ShoppingCart shoppingCart) {
        if (shoppingCart == null) {
            return null;
        }
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        shoppingCartDto.setId(shoppingCart.getId());
        shoppingCartDto.setUser(UserMapper.toUserDto(shoppingCart.getUser()));

        List<CartItemDto> cartItemDtos = shoppingCart.getItems().stream()
                .map(CartItemMapper::toCartItemDto)
                .collect(Collectors.toList());
        shoppingCartDto.setItems(cartItemDtos);

        return shoppingCartDto;
    }

    public static CartItemDto toCartItemDto(CartItem cartItem) {
        if (cartItem == null) {
            return null;
        }

        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setId(cartItem.getId());
        cartItemDto.setProduct(ProductMapper.toProductDto(cartItem.getProduct()));
        cartItemDto.setQuantity(cartItem.getQuantity());

        return cartItemDto;
    }

    public static ShoppingCart toShoppingCart(ShoppingCartDto shoppingCartDto) {
        if (shoppingCartDto == null) {
            return null;
        }

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(shoppingCartDto.getId());
        shoppingCart.setUser(UserMapper.toUser(shoppingCartDto.getUser()));

        List<CartItem> cartItems = shoppingCartDto.getItems().stream()
                .map(ShoppingCartMapper::toCartItem)
                .collect(Collectors.toList());
        shoppingCart.setItems(cartItems);

        return shoppingCart;
    }

    public static CartItem toCartItem(CartItemDto cartItemDto) {
        if (cartItemDto == null) {
            return null;
        }

        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemDto.getId());
        cartItem.setProduct(ProductMapper.toProduct(cartItemDto.getProduct()));
        cartItem.setQuantity(cartItemDto.getQuantity());

        return cartItem;
    }
}
