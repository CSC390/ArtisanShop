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
    private String brand;
    private Double price;
    private ItemCategory itemCategory;
    private Integer quantity;
    private List<Image> imageUrls;
    private Seller seller;
    private Order order;
}
