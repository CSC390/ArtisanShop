package csc394.artisanshop.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import csc394.artisanshop.entities.Order;
import csc394.artisanshop.entities.Product;
import csc394.artisanshop.entities.ShoppingCart;
import csc394.artisanshop.entities.User;
import csc394.artisanshop.services.OrderService;
import csc394.artisanshop.services.ShoppingCartService;
import csc394.artisanshop.services.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public UserController(final UserService userService,
                          final OrderService orderService,
                          final ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.orderService = orderService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.register(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @GetMapping("/profile/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username) {
        return userService.findByUserName(username)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/profile/{username}")
    public ResponseEntity<User> updateProfile(@PathVariable String username, @RequestBody User user) {
        if (!username.equals(user.getUsername())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User updatedUser = userService.updateProfile(user);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Getter
    public class ProductWrapper {
        private List<Product> items;


        // Getters and setters
    }
    @PostMapping("/placeOrder")
    public ResponseEntity<Order> placeOrder(@RequestParam Long userId,
                                            @RequestParam Long sellerId,
                                            @RequestBody  ProductWrapper productWrapper) {
        List<Product> items = productWrapper.getItems();
        Order placedOrder = orderService.placeOrder(userId, sellerId, items);
        if (placedOrder != null) {
            return new ResponseEntity<>(placedOrder, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static class CartActionRequest {
        @JsonProperty("userId")
        private Long userId;
        @JsonProperty("itemId")
        private Long itemId;
    }

    @PostMapping("/addItemToCart")
    public ResponseEntity<ShoppingCart> addItemToCart(@RequestBody CartActionRequest cartRequest) {
    
        ShoppingCart updatedCart = shoppingCartService.addCartItem(cartRequest.userId, cartRequest.itemId);
        if (updatedCart != null) {
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{userId}/removeItemFromCart/{productId}")
    public ResponseEntity<Void> removeItemFromCart(@RequestBody CartActionRequest cartRequest) {
        shoppingCartService.removeCartItem(cartRequest.userId, cartRequest.itemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{userId}/viewCart")
    public ResponseEntity<ShoppingCart> viewCart(@PathVariable Long userId) {
        ShoppingCart cart = shoppingCartService.getCart(userId);
        if (cart != null) {
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
