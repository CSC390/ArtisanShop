package csc394.artisanshop.repositories;

import csc394.artisanshop.entities.Seller;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface SellerRepository {

    Seller setupShop(Seller seller);

    Seller updateShop(Seller seller);

    Optional<Seller> findByShopName(String shopName);
}