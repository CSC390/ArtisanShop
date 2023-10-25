package csc394.artisanshop.datamapper;

import csc394.artisanshop.dto.CategoryDto;
import csc394.artisanshop.entities.Category;

public class CategoryMapper {
    public static CategoryDto toCategoryDto(Category category) {
        if (category == null) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName(category.getCategoryName());
        categoryDto.setId(category.getId());

        return categoryDto;
    }

    public static Category toCategory(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }

        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        category.setId(categoryDto.getId());

        return category;
    }
}
