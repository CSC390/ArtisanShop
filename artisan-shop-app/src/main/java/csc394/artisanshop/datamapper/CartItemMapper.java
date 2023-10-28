package csc394.artisanshop.datamapper;

import csc394.artisanshop.dto.CartItemDto;
import csc394.artisanshop.entities.CartItem;

public class CartItemMapper {
    public static CartItemDto toCartItemDto(CartItem cartItem) {
        if (cartItem == null) {
            return null;
        }
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProduct(ProductMapper.toProductDto(cartItem.getProduct()));
        cartItemDto.setQuantity(cartItem.getQuantity());
        return cartItemDto;
    }

//    public static Product toProduct(CartItemDto cartItemDto) {
//        if (cartItemDto == null) {
//            return null;
//        }
//        return ProductMapper.toProduct(cartItemDto.getProduct());
//    }

    public static CartItem toProduct(CartItemDto cartItemDto) {
        if (cartItemDto == null) {
            return null;
        }
        CartItem cartItem = new CartItem();
        cartItem.setProduct(ProductMapper.toProduct(cartItemDto.getProduct()));
        cartItem.setQuantity(cartItemDto.getQuantity());
        return cartItem;
    }
}
