package csc394.artisanshop.services;

import csc394.artisanshop.entities.Item;
import csc394.artisanshop.entities.Seller;

import java.util.List;
import java.util.Optional;

public interface ShopService {

    /**
     * Sets up shop.
     *
     * @param seller The seller entity to be registered.
     * @return The registered user entity.
     */
    Seller setupShop(Seller seller);

    /**
     * Finds a shop by their shop itemNameEnt.
     *
     * @param shopName The username of the user.
     * @return An Optional of the user if found.
     */
    Optional<Seller> findByShopName(String shopName);

    /**
     * Updates a seller's shop.
     *
     * @param id
     * @param seller The user entity with updated details.
     * @return The updated user entity.
     */
    Seller updateShop(Long id, Seller seller);

    Item addItem(Long sellerId, Item item);
    Item updateItemPrice(Long itemId, Double newPrice);

    List<Item> getAllItems();

    void deleteSeller(Long id);

    void removeItem(Long sellerId, Long itemId);

    List<Item> getSellerItems(Long sellerId);
}
