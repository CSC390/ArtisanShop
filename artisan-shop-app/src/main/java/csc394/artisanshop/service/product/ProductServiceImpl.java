package csc394.artisanshop.service.product;

import csc394.artisanshop.exception.NotFoundException;
import csc394.artisanshop.model.*;
import csc394.artisanshop.repository.*;
import csc394.artisanshop.dto.createDto.ProductCreateDto;
import csc394.artisanshop.dto.viewDto.ProductViewDto;
// import csc394.artisanshop.request.ConfirmCartRequest;
// import csc394.artisanshop.service.cart.CartService;
// import csc394.artisanshop.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    // private final CartService cartService;

    // private final CreditCardService creditCardService;

    private final ConfirmedOrderRepository confirmedOrderRepository;

    // private final PromoCodeRepository promoCodeRepository;

    @Override
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getById(int id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("product couldn't be found by following id: " + id));
        return product;
    }

    @Override
    public ProductCreateDto add(ProductCreateDto productCreateDto) {
        this.productRepository.save(new Product(productCreateDto.getProductName(), productCreateDto.getProductBrand(),
                productCreateDto.getProductDetails(), productCreateDto.getProductPrice(), productCreateDto.getStock(),
                productCreateDto.getProductImageUrl(), productCreateDto.getImages()));
        return productCreateDto;
    }

    @Override
    public List<Product> getByproductName(String productName) {
        return this.productRepository.getByproductName(productName);
    }

    @Override
    public List<Product> getByproductBrand(String productBrand) {
        return this.productRepository.getByproductBrand(productBrand);
    }

    @Override
    public void deleteById(int id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public void updateByProductDetails(int productId, String productDetails) {
        Optional<Product> product = productRepository.findById(productId);

        product.ifPresent(value -> value.setProductDetails(productDetails));
        productRepository.save(product.get());
    }

    @Override
    public List<Product> slice(Pageable pageable) {
        final List<Product> products = this.productRepository.findAll(pageable).stream().collect(Collectors.toList());
        return products;
    }

    @Override
    public List<ProductViewDto> getDto() {
        final List<ProductViewDto> products = this.productRepository.findAll().stream().map(ProductViewDto::of)
                .collect(Collectors.toList());
        return products;
    }

    @Override
    public List<ConfirmedOrder> getAllConfirmedOrder() {
        return confirmedOrderRepository.findAll();
    }

    @Override
    public ConfirmedOrder getConfirmedOrderById(int id) {
    return confirmedOrderRepository.findById(id)
    .orElseThrow(() -> new NotFoundException("confirmed order couldn't be found
    by following id: " + id));
    }

    @Override
    public ConfirmedOrder getConfirmedOrderByOrderNumber(Long orderNumber) {
        return confirmedOrderRepository.findConfirmedOrderByOrderNumber(orderNumber);
    }

    @Override
    public Map<Integer, Object> searchByProduct(String productName) {
        Map<Integer, Object> searchResult = new HashMap<>();
        List<Product> products = new ArrayList<>();

        for (Product product : productRepository.findAll()) {
            if (product.getProductName().contains(productName)) {
                products.add(product);
                searchResult.put(products.size(), products);
                return searchResult;
            }
        }
        return null;
    }

    @Override
    public void addFavorite(int productId) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent()) {
            product.get().setFavoriteNumber(product.get().getFavoriteNumber() + 1);
            productRepository.save(product.get());
        } else {
            throw new NotFoundException("product couldn't be found by following id: " + productId);
        }

    }

    @Override
    public int getNumberOfFavorite(int productId) {
        return productRepository.findById(productId).get().getFavoriteNumber();
    }

    @Override
    public void removeFromFavorites(int productId) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent()) {
            product.get().setFavoriteNumber(product.get().getFavoriteNumber() - 1);
            productRepository.save(product.get());
        } else {
            throw new NotFoundException("product couldn't be found by following id: " + productId);
        }
    }

    public List<Product> getProductsByCategoryName(String categoryName) {
        return productRepository.findProductsByCategoryName(categoryName);
    }
}
