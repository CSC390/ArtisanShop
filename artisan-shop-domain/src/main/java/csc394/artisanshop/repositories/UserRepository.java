package csc394.artisanshop.repositories;

import csc394.artisanshop.entities.User;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public interface UserRepository {

    // For registration
    User create(User user);
    User update(User user);

    // Find by username (used for viewing profile)
    Optional<User> findByUsername(String username);

    //Login can be handled via Spring Security
    // Place order, add items to cart, and delete items from cart
    // will involve other entities and corresponding repository methods.
}
