package csc394.artisanshop.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import csc394.artisanshop.dto.CategoryDto;
import csc394.artisanshop.services.CategoryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categorys")
@RequiredArgsConstructor
@CrossOrigin
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/getAll")
    public List<CategoryDto> getAll() {
        return (List<CategoryDto>) categoryService.getAll();
    }

    @GetMapping("/getCategoriesList")
    public ResponseEntity<List<String>> getCategories() {
        List<String> categoryNames = categoryService.getCategorieslst();

        return ResponseEntity.ok(categoryNames);
    }

    @GetMapping("/getCategoriesIds")
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

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CategoryDto categoryDto) {
        CategoryDto savedCategory = categoryService.add(categoryDto);

        if (savedCategory != null) {
            return ResponseEntity.ok("Category saved successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add the category");
        }
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        boolean deleted = categoryService.deleteCategoryById(categoryId);

        if (deleted) {
            return ResponseEntity.ok("Category deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }
    }

    @PutMapping("/edit/{categoryId}")
    public ResponseEntity<CategoryDto> editCategory(@PathVariable("categoryId") Long categoryId,
            @RequestBody CategoryDto updatedCategory) {
        CategoryDto existingCategory = categoryService.findCategoryById(categoryId);

        if (existingCategory == null) {
            return ResponseEntity.notFound().build();
        }

        // Update the properties of the existing category with the data from the
        // updatedCategory
        existingCategory.setCategoryName(updatedCategory.getCategoryName());

        // Save the updated category
        CategoryDto savedCategory = categoryService.updateCategory(existingCategory);

        return ResponseEntity.ok(savedCategory);
    }

    @PostMapping("/{categoryId}/addProduct/{productId}")
    public ResponseEntity<?> addProductToCategory(
            @PathVariable("categoryId") Long categoryId,
            @PathVariable("productId") Long productId) {
        CategoryDto category = categoryService.findCategoryById(categoryId);

        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }

        // Here, you can implement the logic to add a product to the category.
        // You can use your productService and categoryRepository as required.

        return ResponseEntity.ok("Product added to the category successfully");
    }

    @DeleteMapping("/{categoryId}/removeProduct/{productId}")
    public ResponseEntity<?> removeProductFromCategory(
            @PathVariable("categoryId") Long categoryId,
            @PathVariable("productId") Long productId) {
        CategoryDto category = categoryService.findCategoryById(categoryId);

        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        }

        // Here, you can implement the logic to remove a product from the category.
        // You can use your productService and categoryRepository as required.

        return ResponseEntity.ok("Product removed from the category successfully");
    }

    // @GetMapping("findByCategoryName/{categoryName}")
    // public ResponseEntity<List<ProductViewDto>> findByCategoryName(@PathVariable
    // String categoryName) {
    // // Implement this method to find products by category name using your
    // service.
    // // You can use productService.getProductsByCategoryName(categoryName) as
    // needed.

    // List<ProductViewDto> productDtos = new ArrayList<>(); // Implement this list
    // based on your logic.

    // return ResponseEntity.ok(productDtos);
    // }
}
