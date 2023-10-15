package csc394.artisanshop.api;

import csc394.artisanshop.service.category.CategoryService;
import csc394.artisanshop.service.product.ProductService;
import csc394.artisanshop.dto.viewDto.ProductViewDto;
import csc394.artisanshop.model.Category;
import csc394.artisanshop.repository.CategoryRepository;
import csc394.artisanshop.shared.ECommerceMessage;
import csc394.artisanshop.model.Product;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/categorys/")
@RequiredArgsConstructor
@CrossOrigin
public class CategoryController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final CategoryRepository categoryRepository;

    @GetMapping("getAll")
    public List<Category> getAll() {
        return this.categoryService.getAll();
    }

    @GetMapping("getCategories")
    public ResponseEntity<List<String>> getCategories() {
        List<String> categoryNames = categoryService.getCategories();

        return ResponseEntity.ok(categoryNames);
    }

    @GetMapping("getCategoriesIds")
    public List<Map<String, Object>> getCategoriesIds() {
        List<Object[]> categoryData = categoryService.getCategoriesIds();

        List<Map<String, Object>> response = new ArrayList<>();
        for (Object[] category : categoryData) {
            Map<String, Object> categoryMap = new HashMap<>();
            categoryMap.put("id", category[0]);
            categoryMap.put("categoryName", category[1]);
            response.add(categoryMap);
        }
        return response;
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody Category category) {
        Category savedCategory = categoryService.add(category);

        if (savedCategory != null) {
            return ResponseEntity.ok(ECommerceMessage.CATEGORY_SAVED);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add the category");
        }
    }

    @DeleteMapping("delete/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") int categoryId) {
        boolean deleted = categoryService.deleteCategoryById(categoryId);

        if (deleted) {
            return ResponseEntity.ok("Category deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }

    }

    @PutMapping("edit/{categoryId}")
    public ResponseEntity<Category> editCategory(@PathVariable("categoryId") int categoryId,
            @RequestBody Category updatedCategory) {
        // Check if the category with the given ID exists
        Category existingCategory = categoryService.getCategoryById(categoryId);

        if (existingCategory == null) {
            return ResponseEntity.notFound().build();
        }

        // Update the properties of the existing category with the data from the
        // updatedCategory
        existingCategory.setCategoryName(updatedCategory.getCategoryName());

        // Save the updated category
        Category savedCategory = categoryService.updateCategory(existingCategory);

        return ResponseEntity.ok(savedCategory);
    }

    @PostMapping("{categoryId}/addProduct/{productId}")
    public ResponseEntity<?> addProductToCategory(
            @PathVariable("categoryId") int categoryId,
            @PathVariable("productId") int productId) {
        Category category = categoryService.getCategoryById(categoryId);

        // Use the productService instance to get the product
        Product product = productService.getById(productId);

        if (category == null || product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category or Product not found");
        }

        // Add the product to the category
        category.getProducts().add(product);
        categoryRepository.save(category);

        return ResponseEntity.ok("Product added to the category successfully");
    }

    @DeleteMapping("{categoryId}/removeProduct/{productId}")
    public ResponseEntity<?> removeProductFromCategory(
            @PathVariable("categoryId") int categoryId,
            @PathVariable("productId") int productId) {
        Category category = categoryService.getCategoryById(categoryId);
        Product product = productService.getById(productId);

        if (category == null || product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category or Product not found");
        }

        // Remove the product from the category
        category.getProducts().remove(product);
        categoryRepository.save(category);

        return ResponseEntity.ok("Product removed from the category successfully");
    }

    @GetMapping("findByCategoryName/{categoryName}")
    public ResponseEntity<List<ProductViewDto>> findByCategoryName(@PathVariable String categoryName) {
        List<Product> products = productService.getProductsByCategoryName(categoryName);

        if (products.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        List<ProductViewDto> productDtos = products.stream()
                .map(ProductViewDto::of)
                .collect(Collectors.toList());

        return ResponseEntity.ok(productDtos);
    }
}
