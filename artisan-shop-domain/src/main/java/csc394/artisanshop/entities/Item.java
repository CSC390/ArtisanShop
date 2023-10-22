package csc394.artisanshop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Item {
    private Long id;
    private String itemName;
    private String description;
    private Double price;
    private ItemCategory itemCategory;
    private Integer quantity;
    private List<Image> imageUrl;
    private Seller seller;
}