package csc394.artisanshop.datamapper;

import csc394.artisanshop.dto.ImageDto;
import csc394.artisanshop.dto.ProductDto;
import csc394.artisanshop.entities.Image;
import csc394.artisanshop.entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {
    public static ProductDto toProductDto(Product product) {
        if (product == null) {
            return null;
        }
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getProductId());
        productDto.setProductDetails(product.getProductDetails());
        productDto.setProductName(product.getProductName());
        productDto.setProductPrice(product.getProductPrice());
        productDto.setQuantity(product.getQuantity());
        productDto.setSellerDto(SellerMapper.toSellerDto(product.getSeller()));
        productDto.setProductBrand(product.getProductBrand());
        productDto.setCategoryDto(CategoryMapper.toCategoryDto(product.getProductCategory()));

        List<ImageDto> imageDtos = product.getImages() != null
                ? product.getImages().stream()
                .map(ImageMapper::toImageDto)
                .collect(Collectors.toList())
                : new ArrayList<>();
        productDto.setImages(imageDtos);

        return productDto;
    }

    public static Product toProduct(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }

        Product product = new Product();
        product.setProductId(productDto.getId());
        product.setProductName(productDto.getProductName());
        product.setProductDetails(productDto.getProductDetails());
        product.setProductPrice(productDto.getProductPrice());
        product.setQuantity(productDto.getQuantity());
        product.setSeller(SellerMapper.toSeller(productDto.getSellerDto()));
        product.setProductBrand(productDto.getProductBrand());
        product.setProductCategory(CategoryMapper.toCategory(productDto.getCategoryDto()));

        List<Image> images = productDto.getImages() != null
                ? productDto.getImages().stream()
                .map(ImageMapper::toImage)
                .collect(Collectors.toList())
                : new ArrayList<>();
        product.setImages(images);
        return product;
    }
}
