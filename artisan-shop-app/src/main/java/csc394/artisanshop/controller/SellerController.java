package csc394.artisanshop.controller;

import csc394.artisanshop.entities.Seller;
import csc394.artisanshop.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {
    private final ShopService shopService;

    @Autowired
    public SellerController(final ShopService shopService) {
        this.shopService = shopService;
    }
    @PostMapping("/register")
    public ResponseEntity<Seller> setupShop(@RequestBody Seller seller) {
        Seller setupShop = shopService.setupShop(seller);
        return new ResponseEntity<>(setupShop, HttpStatus.CREATED);
    }
}