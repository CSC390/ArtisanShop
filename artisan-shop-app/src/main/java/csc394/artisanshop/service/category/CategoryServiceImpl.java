package csc394.artisanshop.service.category;

import csc394.artisanshop.repository.CategoryRepository;
import csc394.artisanshop.exception.NotFoundException;
import csc394.artisanshop.model.Category;
import csc394.artisanshop.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public List<String> getCategories() {
        return this.categoryRepository.getCategories();
    }

    @Override
    public List<Object[]> getCategoriesIds() {
        return this.categoryRepository.getCategoriesIds();
    }

    @Override
    public Category add(Category category) {
        if (categoryRepository.findByCategoryName(category.getCategoryName()) != null) {
            return null;
        }

        this.categoryRepository.save(category);
        return category;
    }

    @Override
    public List<Category> getByCategoryName(String categoryName) {
        return this.categoryRepository.getByCategoryName(categoryName);
    }

    @Override
    public boolean deleteCategoryById(int categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);

        if (categoryOptional.isPresent()) {
            categoryRepository.deleteById(categoryId);
            return true;
        }

        return false;
    }

    @Override
    public Category getCategoryById(int categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public Category updateCategory(Category category) {
        // Check if the category with the given ID exists in the database
        Optional<Category> existingCategoryOptional = categoryRepository.findById(category.getId());

        if (existingCategoryOptional.isPresent()) {
            Category existingCategory = existingCategoryOptional.get();

            // Update the properties of the existing category
            existingCategory.setCategoryName(category.getCategoryName());

            // Save the updated category
            return categoryRepository.save(existingCategory);
        } else {
            // Handle the case where the category with the given ID doesn't exist
            throw new NotFoundException("Category not found");
        }
    }

}
