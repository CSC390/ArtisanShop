package csc394.artisanshop.repository;

import csc394.artisanshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> getByCategoryName(String categoryName);

    @Query("from Category where categoryName=:categoryName")
    String findByCategoryName(String categoryName);

    @Query("select c.categoryName from Category c")
    List<String> getCategories();

    @Query("SELECT c.id AS id, c.categoryName AS categoryName FROM Category c")
    List<Object[]> getCategoriesIds();
}
