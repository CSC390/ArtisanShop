package csc394.artisanshop.api;

import csc394.artisanshop.service.category.CategoryService;
import csc394.artisanshop.model.Category;
import csc394.artisanshop.shared.ECommerceMessage;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/categorys/")
@RequiredArgsConstructor
@CrossOrigin
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("getAll")
    public List<Category> getAll() {
        return this.categoryService.getAll();
    }

    @GetMapping("getCategories")
    public List<String> getCategories() {
        return this.categoryService.getCategories();
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
        this.categoryService.add(category);
        return ResponseEntity.ok(ECommerceMessage.CATEGORY_NAME_ALREADY_IN_USE);
    }

    @GetMapping("getByCategoryName")
    public List<Category> getByCategoryName(String categoryName) {
        return this.categoryService.getByCategoryName(categoryName);
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

}
