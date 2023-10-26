package csc394.artisanshop.impl;

import csc394.artisanshop.dto.CategoryDto;
import csc394.artisanshop.repositories.CategoryDtoRepository;
import csc394.artisanshop.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDtoRepository categoryDtoRepository;

    @Autowired
    public CategoryServiceImpl(CategoryDtoRepository categoryDtoRepository) {
        this.categoryDtoRepository = categoryDtoRepository;
    }

    @Override
    public List<Object[]> getCategoriesIds() {
        return this.categoryDtoRepository.getCategoriesIds();
    }

    @Override
    public List<CategoryDto> getByCategoryName(String categoryName) {
        return this.categoryDtoRepository.findByCategoryName(categoryName);
    }

    @Override
    public CategoryDto getAll() {
        return this.categoryDtoRepository.findAll();
    }

    @Override
    public CategoryDto findCategoryById(Long categoryId) {
        Optional<CategoryDto> categoryDtoOptional = categoryDtoRepository.findById(categoryId);
        return categoryDtoOptional.orElse(null);
    }

    @Override
    public CategoryDto add(CategoryDto categoryDto) {
        // Check if a category with the same name already exists
        List<CategoryDto> existingCategories = categoryDtoRepository.findByCategoryName(categoryDto.getCategoryName());

        if (!existingCategories.isEmpty()) {
            return null; // A category with the same name already exists
        }

        // Save the new category and return it
        return categoryDtoRepository.save(categoryDto);
    }

    @Override
    public boolean deleteCategoryById(Long categoryId) {
        Optional<CategoryDto> categoryDtoOptional = categoryDtoRepository.findById((long) categoryId);

        if (categoryDtoOptional.isPresent()) {
            categoryDtoRepository.deleteById((long) categoryId);
            return true;
        }

        return false;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        // Check if the category with the given ID exists in the database
        Optional<CategoryDto> existingCategoryOptional = categoryDtoRepository.findById(categoryDto.getId());

        if (existingCategoryOptional.isPresent()) {
            CategoryDto existingCategory = existingCategoryOptional.get();

            // Update the properties of the existing category
            existingCategory.setCategoryName(categoryDto.getCategoryName());

            // Save the updated category
            return categoryDtoRepository.save(existingCategory);
        } else {
            // Handle the case where the category with the given ID doesn't exist
            return null;
        }

    }

    @Override
    public List<String> getCategorieslst() {
        return this.categoryDtoRepository.getByCategoryNamelst();
    }
}