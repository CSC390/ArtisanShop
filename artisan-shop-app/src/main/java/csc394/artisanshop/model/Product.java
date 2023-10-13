package csc394.artisanshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_brand")
    private String productBrand;

    @Column(name = "product_details")
    private String productDetails;

    @Min(value = 1, message = "{csc394.artisanshop.Min.price.message}")
    @Column(name = "product_price")
    private double productPrice;

    @Min(value = 1, message = "{csc394.artisanshop.Min.stock.message}")
    @Column(name = "stock")
    private int stock;

    @Column(name = "thumbnail")
    private String productImageUrl;

    @ElementCollection
    @Column(name = "image_url")
    private List<String> images;

    @Column(name = "favoriteNumber")
    @Min(value = 0)
    private int favoriteNumber = 0;

    @ManyToOne
    private Seller seller;

    @OneToMany
    private List<ProductComment> productComment;

    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private List<Category> categories;

    public Product(String productName, String productBrand, String productDetails, double productPrice, int stock,
            String productImageUrl, List<String> images) {
        this.productName = productName;
        this.productBrand = productBrand;
        this.productDetails = productDetails;
        this.productPrice = productPrice;
        this.stock = stock;
        this.productImageUrl = productImageUrl;
        this.images = images;
    }

}
