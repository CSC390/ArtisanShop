package csc394.artisanshop.dto.viewDto;

import csc394.artisanshop.model.Category;
import csc394.artisanshop.model.Product;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.Getter;

@Getter
public class ProductViewDto {

    private final Integer id;
    private final String productName;
    private final String productBrand;
    private final String productDetails;
    private final double productPrice;
    private final String productImageUrl;
    private final List<String> images;
    private final List<CategoryInfo> categories;

    private ProductViewDto(Integer id, String productName, String productBrand, String productDetails,
            double productPrice,
            String productImageUrl, List<String> images, List<CategoryInfo> categories) {
        this.id = id;
        this.productName = productName;
        this.productBrand = productBrand;
        this.productDetails = productDetails;
        this.productPrice = productPrice;
        this.productImageUrl = productImageUrl;
        this.images = images;
        this.categories = categories;
    }

    public static ProductViewDto of(Product product) {
        List<CategoryInfo> categoryInfoList = new ArrayList<>();
        for (Category category : product.getCategories()) {
            CategoryInfo categoryInfo = new CategoryInfo(category.getId(), category.getCategoryName());
            categoryInfoList.add(categoryInfo);
        }

        return new ProductViewDto(product.getId(), product.getProductName(), product.getProductBrand(),
                product.getProductDetails(), product.getProductPrice(), product.getProductImageUrl(),
                product.getImages(), categoryInfoList);
    }

    @Data
    public static class CategoryInfo {
        private final Integer categoryId;
        private final String categoryName;

        public CategoryInfo(Integer categoryId, String categoryName) {
            this.categoryId = categoryId;
            this.categoryName = categoryName;
        }
    }

}
