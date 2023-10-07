package csc394.artisanshop.dto.viewDto;

import csc394.artisanshop.model.Product;
import java.util.List;
import lombok.Getter;

@Getter
public class ProductViewDto {

    private final String productName;
    private final String productBrand;
    private final String productDetails;
    private final double productPrice;
    private final String productImageUrl;
    private final List<String> images;

    private ProductViewDto(String productName, String productBrand, String productDetails, double productPrice,
            String productImageUrl, List<String> images) {
        this.productName = productName;
        this.productBrand = productBrand;
        this.productDetails = productDetails;
        this.productPrice = productPrice;
        this.productImageUrl = productImageUrl;
        this.images = images;
    }

    public static ProductViewDto of(Product product) {
        return new ProductViewDto(product.getProductName(), product.getProductBrand(),
                product.getProductDetails(), product.getProductPrice(), product.getProductImageUrl(),
                product.getImages());
    }

}
