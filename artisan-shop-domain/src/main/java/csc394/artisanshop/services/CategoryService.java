package csc394.artisanshop.services;

import csc394.artisanshop.dto.CategoryDto;

public interface CategoryService {
    CategoryDto findCategoryById(Long categoryId);
}
