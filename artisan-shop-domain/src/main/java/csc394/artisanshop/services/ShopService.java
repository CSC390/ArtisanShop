package csc394.artisanshop.services;

import csc394.artisanshop.entities.Product;
import csc394.artisanshop.entities.Seller;

import java.util.List;
import java.util.Optional;

public interface ShopService {

    Seller setupShop(Seller seller);

    Optional<Seller> findByShopName(String shopName);

    Seller updateShop(Long id, Seller seller);

    Product addItem(Long sellerId, Product product);

    void removeItem(Long sellerId, Long itemId);

    List<Product> getSellerItems(Long sellerId);

    Product updateItem(Long itemId, Product product);
}
