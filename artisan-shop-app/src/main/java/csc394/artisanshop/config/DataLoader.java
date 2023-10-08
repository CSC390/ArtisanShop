package csc394.artisanshop.config;

import csc394.artisanshop.model.Category;
import csc394.artisanshop.model.Product;
import csc394.artisanshop.repository.ProductRepository;
import csc394.artisanshop.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

/**
 * !!!! IMPORTANT INFORMATION !!!!
 * THIS CLASS CAN BE ELIGIBLE JUST FOR H2 in-memory DB
 * If you already change your configuration to switch DB system with
 * persistence DB (such as PostgreSQL or MySQL) you must either
 * comment out this `run` method or remove the whole class.
 */

@Component
@ConditionalOnProperty(name = "command.line.runner.enable", havingValue = "true")
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Override
    public void run(String... args) throws Exception {

        // Product product = new Product();
        // product.setProductName("Gaming Monitor");
        // product.setProductBrand("Hp");
        // product.setProductDetails("144 HZ Gaming Monitor");
        // product.setProductPrice(2999.90);
        // product.setStock(1);
        // product.setProductImageUrl("https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcSq90gpNuKH6RpNE_i"
        // +
        // "G5wyzwagNFpH-3SAOIiu4W-DPY4KUvlaVbk_z_ReQlMZ5_aq4F0u2vRKqZtj6QJMGvTbdbVR07yM0oSutCVEIDKWp&usqp=CAE");

        // // Create a List<String> for images
        // List<String> images = Arrays.asList(
        // "https://i.dummyjson.com/data/products/1/1.jpg",
        // "https://i.dummyjson.com/data/products/1/2.jpg",
        // "https://i.dummyjson.com/data/products/1/3.jpg",
        // "https://i.dummyjson.com/data/products/1/4.jpg",
        // "https://i.dummyjson.com/data/products/1/thumbnail.jpg");

        // product.setImages(images);
        // productRepository.save(product);
        Category category1 = new Category("Jewelry");
        categoryService.add(category1);
        Category category2 = new Category("Home Decor");
        categoryService.add(category2);
        Category category3 = new Category("Fashion");
        categoryService.add(category3);
        Category category4 = new Category("Art & Prints");
        categoryService.add(category4);
        Category category5 = new Category("Woodworking");
        categoryService.add(category5);
        Category category6 = new Category("Ceramics");
        categoryService.add(category6);
        Category category7 = new Category("Glass");
        categoryService.add(category7);
        Category category8 = new Category("Leather Goods");
        categoryService.add(category8);
        Category category9 = new Category("Bath & Body");
        categoryService.add(category9);
        Category category10 = new Category("Textiles");
        categoryService.add(category10);
        Category category11 = new Category("Pottery");
        categoryService.add(category11);

    }
}
