package csc394.artisanshop.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonBackReference
    private List<ImageDto> images = new ArrayList<>();

    @Column
    private String productName;

    @Column
    private String productDetails;

    @Column
    private String productBrand;

    @Column
    private Double productPrice;

    @Column
    private Integer quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category")
    @JsonBackReference
    private CategoryDto categoryDto;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private SellerDto sellerDto;

    public void setImages(List<ImageDto> images) {
        if (images != null) {
            this.images = images;
        }
    }
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderDto order;
}
