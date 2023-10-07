package csc394.artisanshop.api;

import csc394.artisanshop.request.CampaignCreateRequest;
import csc394.artisanshop.request.PriceIncreaseRequest;
import csc394.artisanshop.request.ProductDetailsUpdateRequest;
import csc394.artisanshop.service.product.ProductService;
import csc394.artisanshop.dto.createDto.ProductCreateDto;
import csc394.artisanshop.dto.viewDto.ProductViewDto;
import csc394.artisanshop.model.Product;
import csc394.artisanshop.service.product.UpdateProductPriceService;
import csc394.artisanshop.shared.ECommerceMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products/")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {

    private final ProductService productService;

    private final UpdateProductPriceService updateProductPriceService;

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody @Valid ProductCreateDto productCreateDto) {
        this.productService.add(productCreateDto);
        return ResponseEntity.ok(ECommerceMessage.PRODUCT_SAVED);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping("getAll")
    public ResponseEntity<?> getAll() {
        final List<Product> products = productService.getAll();
        if (products.size() <= 0) {
            return ResponseEntity.ok(ECommerceMessage.PRODUCT_NOT_FOUND);
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("getByProductName/{productName}")
    public ResponseEntity<?> getByproductName(@PathVariable String productName) {
        String productsName = "";
        List<Product> products = this.productService.getByproductName(productName);
        for (Product product : products) {
            productsName = product.getProductName();
        }
        if (!productName.equals(productsName)) {
            return ResponseEntity.ok(ECommerceMessage.NOT_FOUND_THIS_NAME);
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("getByProductBrand/{productBrand}")
    public List<Product> getByproductBrand(@PathVariable String productBrand) {
        return this.productService.getByproductBrand(productBrand);
    }

    @GetMapping("slice")
    public ResponseEntity<List<Product>> slice(Pageable pageable) {
        final List<Product> products = this.productService.slice(pageable);
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<?> deleteByid(@PathVariable int id) {
        this.productService.deleteById(id);
        return ResponseEntity.ok(ECommerceMessage.PRODUCT_DELETED);
    }

    @GetMapping("getDto")
    public ResponseEntity<?> getDto() {
        List<ProductViewDto> products = this.productService.getDto();
        if (products.size() <= 0) {
            return ResponseEntity.ok(ECommerceMessage.PRODUCT_NOT_FOUND);
        } else {
            return ResponseEntity.ok(products);
        }
    }

    @GetMapping("searchByProduct/{productName}")
    public ResponseEntity<?> searchByProduct(@PathVariable String productName) {
        Map<Integer, Object> result = productService.searchByProduct(productName);
        return ResponseEntity.ok(result);
    }

    // @GetMapping("getConfirmedOrderById/{id}")
    // public ResponseEntity<?> getConfirmedOrderById(@PathVariable int id) {
    // return ResponseEntity.ok(productService.getConfirmedOrderById(id));
    // }

    // @GetMapping("getConfirmedOrderByOrderNumber/{orderNumber}")
    // public ResponseEntity<?> getConfirmedOrderByOrderNumber(@PathVariable Long
    // orderNumber) {
    // return
    // ResponseEntity.ok(productService.getConfirmedOrderByOrderNumber(orderNumber));
    // }

    // @PutMapping("createCampaign")
    // public ResponseEntity<?> createCampaign(@RequestBody CampaignCreateRequest
    // campaignCreateRequest) {
    // updateProductPriceService.createCampaign(campaignCreateRequest);
    // return ResponseEntity.ok("success");
    // }

    // @PutMapping("priceIncrease")
    // public ResponseEntity<?> priceIncrease(@RequestBody PriceIncreaseRequest
    // priceIncreaseRequest) {
    // updateProductPriceService.priceIncrease(priceIncreaseRequest);
    // return ResponseEntity.ok("success");
    // }

    @PutMapping("update-product-details")
    public ResponseEntity<?> updateByProductDetails(@RequestBody ProductDetailsUpdateRequest updateRequest) {
        productService.updateByProductDetails(updateRequest.getProductId(), updateRequest.getProductDetails());
        return ResponseEntity.ok("success");
    }
}
