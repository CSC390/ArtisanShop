package csc394.artisanshop.controller;

import csc394.artisanshop.dto.ItemDto;
import csc394.artisanshop.entities.Item;
import csc394.artisanshop.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ShopService shopService;

    @PutMapping("/{itemId}")
    public ResponseEntity<ItemDto> updateItem(@PathVariable Long itemId, @RequestBody Item item) {
        ItemDto updatedItem = shopService.updateItem(itemId, item);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }
}
