package csc394.artisanshop.controller;

import csc394.artisanshop.entities.Item;
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) {
        shopService.deleteSeller(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable String id) {
        // Assuming the ShopService has a method called findByShopName
        Optional<Seller> shopName = shopService.findByShopName(id);

        if(shopName.isPresent()) {
            return new ResponseEntity<>(shopName.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{sellerId}/addItem")
    public ResponseEntity<Item> addItem(@PathVariable Long sellerId, @RequestBody Item item) {
        Item addedItem = shopService.addItem(sellerId, item);
        return new ResponseEntity<>(addedItem, HttpStatus.CREATED);
    }

    @PutMapping("/{sellerId}/updateItem/{itemId}")
    public ResponseEntity<Item> updateItemPrice(@PathVariable Long sellerId, @PathVariable Long itemId, @RequestBody Double newPrice) {
        Item updatedItem = shopService.updateItemPrice(itemId, newPrice);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    @DeleteMapping("/{sellerId}/removeItem/{itemId}")
    public ResponseEntity<Void> removeItem(@PathVariable Long sellerId, @PathVariable Long itemId) {
        shopService.removeItem(sellerId, itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{sellerId}/items")
    public ResponseEntity<List<Item>> getSellerItems(@PathVariable Long sellerId) {
        List<Item> items = shopService.getSellerItems(sellerId);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}