package csc394.artisanshop.repository;

import csc394.artisanshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> getByproductName(String productName);

    @Query("SELECT p FROM Product p JOIN p.categories c WHERE c.categoryName = :categoryName")
    List<Product> findProductsByCategoryName(@Param("categoryName") String categoryName);

    List<Product> getByproductBrand(String productBrand);

    void deleteById(int id);
}
