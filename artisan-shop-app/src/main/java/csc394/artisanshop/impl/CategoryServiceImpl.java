package csc394.artisanshop.impl;

import csc394.artisanshop.dto.CategoryDto;
import csc394.artisanshop.repositories.CategoryDtoRepository;
import csc394.artisanshop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDtoRepository categoryDtoRepository;

    @Autowired
    public CategoryServiceImpl(CategoryDtoRepository categoryDtoRepository) {
        this.categoryDtoRepository = categoryDtoRepository;
    }

    @Override
    public CategoryDto findCategoryById(Long categoryId) {
        Optional<CategoryDto> categoryDtoOptional = categoryDtoRepository.findById(categoryId);
        return categoryDtoOptional.orElse(null);
    }
}
