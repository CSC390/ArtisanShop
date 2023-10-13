package csc394.artisanshop.services;

import csc394.artisanshop.entities.User;
import java.util.Optional;

public interface UserService {

    /**
     * Registers a new user.
     *
     * @param user The user entity to be registered.
     * @return The registered user entity.
     */
    User register(User user);

    /**
     * Finds a user by their username.
     *
     * @param username The username of the user.
     * @return An Optional of the user if found.
     */
    Optional<User> findByUserName(String username);

    /**
     * Updates a user's profile.
     *
     * @param user The user entity with updated details.
     * @return The updated user entity.
     */
    User updateProfile(User user);

    // Additional methods related to user activities would be added here.
    // login, placeOrder, addItemToCart, removeItemFromCart, etc.
}

