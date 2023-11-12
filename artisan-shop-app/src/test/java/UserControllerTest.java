import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import csc394.artisanshop.ArtisanShopApplication;
import csc394.artisanshop.controller.UserController;
import csc394.artisanshop.entities.Order;
import csc394.artisanshop.entities.Product;
import csc394.artisanshop.entities.User;
import csc394.artisanshop.services.UserService;
import csc394.artisanshop.services.OrderService;
import csc394.artisanshop.services.ShoppingCartService;

@SpringBootTest(classes = ArtisanShopApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private OrderService orderService;

    @MockBean
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    public void whenRegisterUser_thenUserCreated() throws Exception {

        User newUser = new User(); // new user
        newUser.setUsername("Halmann");
        newUser.setFirstName("Hassan");
        newUser.setLastName("Al-Mannai");
        newUser.setEmail("halmann@1.com");


        User registeredUser = new User(); // The expected registered user info
        registeredUser.setUsername(newUser.getUsername());
        registeredUser.setFirstName(newUser.getFirstName());
        registeredUser.setLastName(newUser.getLastName());
        registeredUser.setEmail(newUser.getEmail());


        given(userService.register(any(User.class))).willReturn(registeredUser);

        mockMvc.perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value(registeredUser.getUsername()))
                .andExpect(jsonPath("$.firstName").value(registeredUser.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(registeredUser.getLastName()))
                .andExpect(jsonPath("$.email").value(registeredUser.getEmail()));
    }



    @Test
    public void whenFindByUsername_thenUserReturned() throws Exception {

        String username = "existingUser";
        User user = new User(); // The user object to be returned
        user.setUsername(username);
        user.setFirstName("Existing");
        user.setLastName("User");
        user.setEmail("exists@reality.com");


        given(userService.findByUserName(username)).willReturn(Optional.of(user));


        mockMvc.perform(get("/users/profile/{username}", username))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username").value(user.getUsername()))
            .andExpect(jsonPath("$.firstName").value(user.getFirstName()))
            .andExpect(jsonPath("$.lastName").value(user.getLastName()))
            .andExpect(jsonPath("$.email").value(user.getEmail()));


    }


    @Test
    public void whenUpdateProfile_thenProfileUpdated() throws Exception {

        String username = "existingUser";
        User originalUser = new User(); // Original user info
        originalUser.setUsername(username);
        originalUser.setFirstName("existing");
        originalUser.setLastName("user");
        originalUser.setEmail("existing@reality.com");

        User updatedUser = new User(); // Updated user info
        updatedUser.setUsername(username);
        updatedUser.setFirstName("updated"); 
        updatedUser.setLastName("user");
        updatedUser.setEmail("1@dpu.com");    

        given(userService.updateProfile(any(User.class))).willReturn(updatedUser);

        mockMvc.perform(put("/users/profile/{username}", username)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedUser)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username").value(updatedUser.getUsername()))
            .andExpect(jsonPath("$.firstName").value(updatedUser.getFirstName()));

    }

    // @Test
    // @Disabled("To be completed")
    // public void whenPlaceOrder_thenOrderPlaced() throws Exception {
   
    //     Long userId = 1L;
    //     Long sellerId = 2L;
    //     List<Product> products = new ArrayList<>();
    //     // Assuming you've set up some products to include in the order
    //     products.add(new Product("Oryx Necklace", 15.99, "Necklace"));
    //     products.add(new Product("Candle", 8.99, "Candle"));

    //     UserController.ProductWrapper productWrapper = new UserController.ProductWrapper();
    //     productWrapper.setItems(products);

    //     Order placedOrder = new Order();
    //     placedOrder.setUser(new User()); // Set user details
    //     placedOrder.setProducts(products); // Set the list of products for the order

    //     given(orderService.placeOrder(eq(userId), eq(sellerId), anyList())).willReturn(placedOrder);


    //     mockMvc.perform(post("/users/placeOrder")
    //             .param("userId", String.valueOf(userId))
    //             .param("sellerId", String.valueOf(sellerId))
    //             .contentType(MediaType.APPLICATION_JSON)
    //             .content(objectMapper.writeValueAsString(productWrapper)))
    //             .andExpect(status().isCreated())
    //             .andExpect(jsonPath("$.orderId").value(placedOrder.getOrderId()));
    // }

}


