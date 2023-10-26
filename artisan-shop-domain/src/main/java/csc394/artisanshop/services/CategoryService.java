package csc394.artisanshop.services;

import java.util.List;

import csc394.artisanshop.dto.CategoryDto;

public interface CategoryService {
    CategoryDto findCategoryById(Long categoryId);

    CategoryDto getAll();

    List<CategoryDto> getByCategoryName(String categoryName);

    List<String> getCategorieslst();

    List<Object[]> getCategoriesIds();

    boolean deleteCategoryById(Long categoryId);

    CategoryDto updateCategory(CategoryDto category);

    CategoryDto add(CategoryDto category);
}
