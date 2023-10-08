package csc394.artisanshop.service.category;

import csc394.artisanshop.model.Category;

import java.util.List;

public interface CategoryService {
    Category add(Category category);

    List<Category> getAll();

    List<Category> getByCategoryName(String categoryName);

    List<String> getCategories();

    List<Object[]> getCategoriesIds();

    boolean deleteCategoryById(int categoryId);

    Category getCategoryById(int categoryId);

    Category updateCategory(Category category);

}
