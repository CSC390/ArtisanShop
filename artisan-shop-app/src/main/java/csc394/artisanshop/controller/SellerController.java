package csc394.artisanshop.controller;

import csc394.artisanshop.entities.Product;
import csc394.artisanshop.entities.Seller;
import csc394.artisanshop.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PutMapping("/update/{id}")
    public ResponseEntity<Seller> updateSeller(@PathVariable Long id, @RequestBody Seller seller) {
        Seller updatedSeller = shopService.updateShop(id, seller);
        return new ResponseEntity<>(updatedSeller, HttpStatus.OK);
    }

    @PostMapping("/{sellerId}/addItem")
    public ResponseEntity<Product> addItem(@PathVariable Long sellerId, @RequestBody Product product) {
        product.setImages(product.getImages());
        Product addedProduct = shopService.addItem(sellerId, product);
        return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("/{sellerId}/removeItem/{itemId}")
    public ResponseEntity<Void> removeItem(@PathVariable Long sellerId, @PathVariable Long itemId) {
        shopService.removeItem(sellerId, itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{sellerId}/items")
    public ResponseEntity<List<Product>> getSellerItems(@PathVariable Long sellerId) {
        List<Product> products = shopService.getSellerItems(sellerId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/seller/{shopName}")
    public ResponseEntity<Seller> getSellerByShopName(@PathVariable String shopName) {
        Optional<Seller> sellerOpt = shopService.findByShopName(shopName);
        if (sellerOpt.isPresent()) {
            return new ResponseEntity<>(sellerOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable String id) {
        Optional<Seller> shopName = shopService.findByShopName(id);
        if(shopName.isPresent()) {
            return new ResponseEntity<>(shopName.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}