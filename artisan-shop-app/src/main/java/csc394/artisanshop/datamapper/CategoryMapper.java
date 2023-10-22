package csc394.artisanshop.datamapper;

import csc394.artisanshop.dto.CategoryDto;
import csc394.artisanshop.entities.ItemCategory;

public class CategoryMapper {
    public static CategoryDto toCategoryDto(ItemCategory itemCategory) {
        if (itemCategory == null) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName(itemCategory.getCategoryName());
        categoryDto.setId(itemCategory.getId());

        return categoryDto;
    }

    public static ItemCategory toCategory(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        }

        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setCategoryName(categoryDto.getCategoryName());
        itemCategory.setId(categoryDto.getId());

        return itemCategory;
    }
}
