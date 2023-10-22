package csc394.artisanshop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Item {
    private Long id;
    private String itemNameEnt;
    private String description;
    private Double price;
    private ItemCategory itemCategory;
    private Integer quantity;
    private String imageUrl;
    private Seller seller;
}
