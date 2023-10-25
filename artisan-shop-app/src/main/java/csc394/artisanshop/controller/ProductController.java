package csc394.artisanshop.controller;

import csc394.artisanshop.entities.Product;
import csc394.artisanshop.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ShopService shopService;

    @Autowired
    public ProductController(final ShopService shopService) {
        this.shopService = shopService;
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long productId,
            @RequestBody Product updatedProduct) {

        Product updatedProductDto = shopService.updateItem(productId, updatedProduct);

        return new ResponseEntity<>(updatedProductDto, HttpStatus.OK);
    }
}
