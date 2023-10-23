package csc394.artisanshop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Seller {
    private Long sellerId;
    private String sellerName;
    private String sellerEmail;
    private String firstName;
    private String lastName;
//    private List<Item> itemsForSale;
}