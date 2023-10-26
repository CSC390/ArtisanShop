package csc394.artisanshop.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import csc394.artisanshop.dto.CategoryDto;
import csc394.artisanshop.entities.Category;
import csc394.artisanshop.repositories.CategoryDtoRepository;
import csc394.artisanshop.services.CategoryService;

import java.util.Arrays;
import java.util.List;

@Component
@ConditionalOnProperty(name = "command.line.runner.enable", havingValue = "true")
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final CategoryDtoRepository categoryDtoRepository;

    @Override
    public void run(String... args) throws Exception {

        // q: go in an save all the rest of the categories

        CategoryDto categoryDto1 = new CategoryDto();
        categoryDto1.setCategoryName("Jewelry");
        categoryDtoRepository.save(categoryDto1);

        CategoryDto categoryDto2 = new CategoryDto();
        categoryDto2.setCategoryName("Fashion");
        categoryDtoRepository.save(categoryDto2);

        CategoryDto categoryDto3 = new CategoryDto();
        categoryDto3.setCategoryName("Home Decor");
        categoryDtoRepository.save(categoryDto3);

        CategoryDto categoryDto4 = new CategoryDto();
        categoryDto4.setCategoryName("Art & Prints");
        categoryDtoRepository.save(categoryDto4);

        CategoryDto categoryDto5 = new CategoryDto();
        categoryDto5.setCategoryName("Woodworking");
        categoryDtoRepository.save(categoryDto5);

        CategoryDto categoryDto6 = new CategoryDto();
        categoryDto6.setCategoryName("Ceramics");
        categoryDtoRepository.save(categoryDto6);

        CategoryDto categoryDto7 = new CategoryDto();
        categoryDto7.setCategoryName("Glass");
        categoryDtoRepository.save(categoryDto7);

        CategoryDto categoryDto8 = new CategoryDto();
        categoryDto8.setCategoryName("Leather Goods");
        categoryDtoRepository.save(categoryDto8);

        CategoryDto categoryDto9 = new CategoryDto();
        categoryDto9.setCategoryName("Bath & Body");
        categoryDtoRepository.save(categoryDto9);

        CategoryDto categoryDto10 = new CategoryDto();
        categoryDto10.setCategoryName("Textiles");
        categoryDtoRepository.save(categoryDto10);

        CategoryDto categoryDto11 = new CategoryDto();
        categoryDto11.setCategoryName("Pottery");
        categoryDtoRepository.save(categoryDto11);

    }

}
