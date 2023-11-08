//package csc394.artisanshop.controller;
//
//import csc394.artisanshop.entities.ShoppingCart;
//import csc394.artisanshop.services.ShoppingCartService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/cart")
//public class ShoppingCartController {
//    private final ShoppingCartService shoppingCartService;
//    @Autowired
//    public ShoppingCartController(final ShoppingCartService shoppingCartService) {
//        this.shoppingCartService = shoppingCartService;
//    }
//
//    public static class CartActionRequest {
//        private Long userId;
//        private Long itemId;
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<ShoppingCart> addItemToCart(@RequestBody CartActionRequest cartRequest) {
//        ShoppingCart updatedCart = shoppingCartService.addCartItem(cartRequest.userId, cartRequest.itemId);
//        if (updatedCart != null) {
//            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @DeleteMapping("/remove")
//    public ResponseEntity<Void> removeItemFromCart(@RequestBody CartActionRequest cartRequest) {
//        shoppingCartService.removeCartItem(cartRequest.userId, cartRequest.itemId);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @GetMapping("/{userId}")
//    public ResponseEntity<ShoppingCart> viewCart(@PathVariable Long userId) {
//        ShoppingCart cart = shoppingCartService.getCart(userId);
//        if (cart != null) {
//            return new ResponseEntity<>(cart, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }
//}
