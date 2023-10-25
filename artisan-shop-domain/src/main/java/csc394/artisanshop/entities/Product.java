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
public class Product {
    private Long id;
    private String productName;
    private String productDetails;
    private String productBrand;
    private Double productPrice;
    private Category productCategory;
    private Integer quantity;
    private List<Image> images;
    private Seller seller;
}