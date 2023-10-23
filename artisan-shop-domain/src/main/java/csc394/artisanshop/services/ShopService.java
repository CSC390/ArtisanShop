package csc394.artisanshop.services;

import csc394.artisanshop.dto.ItemDto;
import csc394.artisanshop.entities.Item;
import csc394.artisanshop.entities.Seller;

import java.util.List;
import java.util.Optional;

public interface ShopService {

    Seller setupShop(Seller seller);

    Optional<Seller> findByShopName(String shopName);

    Seller updateShop(Long id, Seller seller);

    Item addItem(Long sellerId, Item item);

    void removeItem(Long sellerId, Long itemId);

    List<Item> getSellerItems(Long sellerId);

    ItemDto updateItem(Long itemId, Item item);
}
