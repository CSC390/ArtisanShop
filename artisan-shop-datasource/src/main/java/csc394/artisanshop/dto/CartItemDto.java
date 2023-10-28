package csc394.artisanshop.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart_item")
@Inheritance(strategy = InheritanceType.JOINED)
public class CartItemDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "product_id")
//    private ProductDto product;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private ShoppingCartDto shoppingCart;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductDto product;
}
