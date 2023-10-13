package csc394.artisanshop.service.product;

import csc394.artisanshop.dto.createDto.ProductCreateDto;
import csc394.artisanshop.dto.viewDto.ProductViewDto;
// import csc394.artisanshop.model.Cart;
import csc394.artisanshop.model.ConfirmedOrder;
// import csc394.artisanshop.model.CreditCard;
import csc394.artisanshop.model.Product;
//import csc394.artisanshop.request.ConfirmCartRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {
    List<Product> getAll();

    Product getById(int id);

    ProductCreateDto add(ProductCreateDto productCreateDto);

    List<Product> getByproductName(String productName);

    List<Product> getByproductBrand(String productBrand);

    void deleteById(int id);

    void updateByProductDetails(int productId, String productDetails);

    List<Product> slice(Pageable pageable);

    List<ProductViewDto> getDto();

    // void removeFromCart(int id);

    // ConfirmedOrder confirmCart(ConfirmCartRequest confirmCartRequest);

    List<ConfirmedOrder> getAllConfirmedOrder();

    ConfirmedOrder getConfirmedOrderById(int id);

    ConfirmedOrder getConfirmedOrderByOrderNumber(Long orderNumber);

    Map<Integer, Object> searchByProduct(String productName);

    void addFavorite(int productId);

    int getNumberOfFavorite(int productId);

    void removeFromFavorites(int productId);
}
